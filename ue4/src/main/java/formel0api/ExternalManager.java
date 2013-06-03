package formel0api;

import java.io.*;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import java.net.URL;

import javax.xml.namespace.*;
import javax.xml.parsers.*;
import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.xpath.*;

import org.w3c.dom.*;

import tuwien.big.formel0.twitter.*;
import tuwien.big.formel0.picasa.*;

import twitter4j.*;
import twitter4j.conf.*;

import com.google.gdata.client.*;
import com.google.gdata.client.photos.*;
import com.google.gdata.data.*;
import com.google.gdata.data.media.*;
import com.google.gdata.data.photos.*;
import com.google.gdata.util.ServiceException;


// best practice singleton --> threadsave + singleton by default
public enum ExternalManager implements ITwitterClient, IRaceDriverService {
  INSTANCE;

  // soap server parameter
  String soapUrl = "http://playground.big.tuwien.ac.at:8080/highscore/PublishHighScoreService";

  // SOAP request parameters
  private String tournamentNamespaceURL = "http://www.dbai.tuwien.ac.at/education/ssd/SS13/uebung/Tournament";
  private String tournamentNamespace = "ssd";
  private String dataNamespaceURL = "http://big.tuwien.ac.at/we/highscore/data";
  private String dataNamespace = "data";

  // persistent Driver List
  private ArrayList<RaceDriver> raceDriverList = new ArrayList<RaceDriver>();

  public void performHighscorePush(Game game) {
    SOAPConnectionFactory soapConnectionFactory = null;
    SOAPConnection soapConnection = null;
    SOAPMessage soapResponse = null;

    String uuid = "";
    TwitterStatusMessage twitterMessage = null;

    try {
      // start connection
      soapConnectionFactory = SOAPConnectionFactory.newInstance();
      soapConnection = soapConnectionFactory.createConnection();

      // Send SOAP Message to SOAP Server
      soapResponse = soapConnection.call(createSOAPRequest(game), soapUrl);

      // end connection
      soapConnection.close();

      soapResponse.writeTo(System.out); // debug

      // Process the SOAP Response
      uuid = extractUuidFromSoapResponse(soapResponse);

      // create Twitter Message
      twitterMessage = new TwitterStatusMessage(game.getWinner().getUsername(), uuid, new Date());

      // perform tweet
      publishUuid(twitterMessage);

    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
    }
  }


  public List<RaceDriver> getRaceDrivers() throws IOException, ServiceException {
    if (raceDriverList.isEmpty())
      performPicasaRequest();

    return raceDriverList;
  }



  private SOAPMessage createSOAPRequest(Game game) throws Exception {
    // Game data
    Player player = game.getPlayer();

    MessageFactory messageFactory = MessageFactory.newInstance();
    SOAPMessage soapMessage = messageFactory.createMessage();
    SOAPPart soapPart = soapMessage.getSOAPPart();

    SOAPEnvelope envelope = soapPart.getEnvelope();

    SOAPBody soapBody = envelope.getBody();

    // add request to SOAP Body
    SOAPElement highScoreRequest = soapBody.addChildElement("HighScoreRequest");

    // set namespace decleartion
    highScoreRequest.addNamespaceDeclaration(dataNamespace, dataNamespaceURL);
    highScoreRequest.addNamespaceDeclaration(tournamentNamespace, tournamentNamespaceURL);

    //set prefix
    highScoreRequest.setPrefix(dataNamespace);

    // add Userkey
    SOAPElement userKeyElement = highScoreRequest.addChildElement("UserKey", dataNamespace);
    userKeyElement.setTextContent("34EphAp2C4ebaswu");

    // add tournament
    SOAPElement tournamentElement = highScoreRequest.addChildElement("tournament", tournamentNamespace);
    tournamentElement.setAttribute("start-date", "2013-01-01");
    tournamentElement.setAttribute("end-date", "2014-01-01");
    tournamentElement.setAttribute("registration-deadline", "2013-01-01T00:00:00Z");

    // add player
    SOAPElement playerElement =  tournamentElement.addChildElement("players", tournamentNamespace).addChildElement("player", tournamentNamespace);
    playerElement.setAttribute("username", player.getUsername());
    playerElement.addChildElement("date-of-birth",tournamentNamespace).setTextContent(player.getBirthdate());
    playerElement.addChildElement("gender", tournamentNamespace).setTextContent(player.getSex());

    // add round
    SOAPElement roundElement = tournamentElement.addChildElement("rounds", tournamentNamespace).addChildElement("round", tournamentNamespace);
    roundElement.setAttribute("number", "0");

    // add Game
    SOAPElement gameElement =  roundElement.addChildElement("game", tournamentNamespace);
    gameElement.setAttribute("date", game.getStartDate());
    gameElement.setAttribute("status", "finished");
    gameElement.setAttribute("duration", Integer.toString(game.getRound()));
    gameElement.setAttribute("winner", game.getWinner().getUsername());
    gameElement.addChildElement("players", tournamentNamespace);

    soapMessage.saveChanges();

    return soapMessage;
  }

  public String extractUuidFromSoapResponse(SOAPMessage soapResponse) throws Exception {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setNamespaceAware(true);
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    soapResponse.writeTo(out);
    InputStream is = new ByteArrayInputStream(out.toByteArray());
    Document responseDocument = documentBuilder.parse(is);


    XPath xPath = XPathFactory.newInstance().newXPath();

    xPath.setNamespaceContext(new SoapNamespaceContext());

    XPathExpression xpathExpr = xPath.compile("//S:Body");

    NodeList selectedNodes = (NodeList) xpathExpr.evaluate(responseDocument, XPathConstants.NODESET);

    return selectedNodes.item(0).getTextContent();
  }

  private void performPicasaRequest() {
    // picasa service helper
    URL picasaURL = null;
    PicasawebService myService = null;
    AlbumFeed picasaAlbumFeed = null;

    // photo attributes
    String driverName = null;
    String driverWikiLink = null;
    String driverPhotoUrl = null;
    RaceDriver raceDriver = null;
    boolean isDriver = false;


    try {
      picasaURL = new URL("https://picasaweb.google.com/data/feed/api/user/107302466601293793664/albumid/5868849825181458161");
      myService = new PicasawebService("we-driver-app");

      picasaAlbumFeed = myService.getFeed(picasaURL, AlbumFeed.class);

      // iterate through photos
      for(PhotoEntry photo : picasaAlbumFeed.getPhotoEntries()) {
        isDriver = false;

        // iterate through tags
        for (String tag : photo.getMediaKeywords().getKeywords()) {
          if (tag.equals("Driver"))
            isDriver = true;

          // set wiki link
          if (tag.contains("wiki"))
            driverWikiLink = tag.split(":",2)[1];

        }

        // if driver then add
        if (isDriver) {
          driverName = photo.getDescription().getPlainText();
          driverPhotoUrl = photo.getMediaContents().get(0).getUrl();

          raceDriver = new RaceDriver();
          raceDriver.setName(driverName);
          raceDriver.setUrl(driverPhotoUrl);
          raceDriver.setWikiUrl(driverWikiLink);

          raceDriverList.add(raceDriver);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
    }
  }


  public void publishUuid(TwitterStatusMessage twitterMessage) throws Exception {
    ConfigurationBuilder cb = new ConfigurationBuilder();
    cb.setDebugEnabled(true)
      .setOAuthConsumerKey("GZ6tiy1XyB9W0P4xEJudQ")
      .setOAuthConsumerSecret("gaJDlW0vf7en46JwHAOkZsTHvtAiZ3QUd2mD1x26J9w")
      .setOAuthAccessToken("1366513208-MutXEbBMAVOwrbFmZtj1r4Ih2vcoHGHE2207002")
      .setOAuthAccessTokenSecret("RMPWOePlus3xtURWRVnv1TgrjTyK7Zk33evp4KKyA");
    TwitterFactory tf = new TwitterFactory(cb.build());
    Twitter twitter = tf.getInstance();

    Status status = twitter.updateStatus(new StatusUpdate(twitterMessage.getTwitterPublicationString()));
  }
}
