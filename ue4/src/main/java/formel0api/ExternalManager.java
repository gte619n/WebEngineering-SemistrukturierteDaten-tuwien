package formel0api;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import tuwien.big.formel0.twitter.*;


public enum ExternalManager implements ITwitterClient {
  INSTANCE;
  private String tournamentNamespaceURL = "http://www.dbai.tuwien.ac.at/education/ssd/SS13/uebung/Tournament";
  private String tournamentNamespace = "ssd";
  private String dataNamespaceURL = "http://big.tuwien.ac.at/we/highscore/data";
  private String dataNamespace = "data";

  public void performHighscorePush(Game game) {
    System.out.println("\n\n>>>>>>>>>>>>>    (pmig log)    " + "hi");
    try {
      SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
      SOAPConnection soapConnection = soapConnectionFactory.createConnection();

      // Send SOAP Message to SOAP Server
      String url = "http://ws.cdyne.com/emailverify/Emailvernotestemail.asmx";
      SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(game), url);

      System.out.println("\n\n>>>>>>>>>>>>>    (pmig log)    " + soapResponse);


      // Process the SOAP Response
      soapResponse.writeTo(System.err);

      soapConnection.close();
    } catch (Exception e) {
      e.printStackTrace();
      System.err.println(e.getMessage());
    }


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
    SOAPElement highScoreRequest = soapBody.addChildElement("HighScoreRequest", "data");

    // set namespace decleartion
    highScoreRequest.addNamespaceDeclaration(dataNamespace, dataNamespaceURL);
    highScoreRequest.addNamespaceDeclaration(tournamentNamespace, tournamentNamespaceURL);

    // add Userkey
    SOAPElement userKeyElement = highScoreRequest.addChildElement("Userkey", dataNamespace);
    userKeyElement.setTextContent("34EphAp2C4ebaswu");

    // add tournament
    SOAPElement tournamentElement = highScoreRequest.addChildElement("tournament", tournamentNamespace);

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

  public void publishUuid(TwitterStatusMessage message) throws Exception {

  }

}
