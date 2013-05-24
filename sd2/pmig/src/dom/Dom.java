package dom;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class Dom {
  private static XPath xPath;
  private static DocumentBuilderFactory documentBuilderFactory;
  private static DocumentBuilder documentBuilder;

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.err.println("Usage: java Dom <input.xml> <output.xml>");
      System.exit(1);
    }

    String inputPath = args[0];
    String outputPath = args[1];

    initialize();
    transform(inputPath, outputPath);
  }

  private static void initialize() throws Exception {
    xPath = XPathFactory.newInstance().newXPath();
    xPath.setNamespaceContext(new TournamentNamespaceContext());

    documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setNamespaceAware(true);
    documentBuilder = documentBuilderFactory.newDocumentBuilder();
  }

  /**
   * Use this method to encapsulate the main logic for this example. First read in
   * the tournament document by using Dom.calculateStatistics(Document). Second create
   * the output document by using Dom.createOutput(List<Player>). Third use a Transformer
   * to print the document to the output path.
   *
   * @param inputPath Path to the xml file to get read in.
   * @param outputPath Path to the xml file to print statistics.
   */
  private static void transform(String inputPath, String outputPath) throws Exception {
    Document tournamentDocument = documentBuilder.parse(inputPath);
    List<Player> players = calculateStatistics(tournamentDocument);

    Document statisticsDocument = createOutput(players);

    System.out.println("\n\n>>>>>>>>>>>>>    (pmig log)    " + players);

    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    DOMSource source = new DOMSource(statisticsDocument);
    StreamResult result = new StreamResult(new File(outputPath));

    // Output to console for testing
    // StreamResult result = new StreamResult(System.out);

    transformer.transform(source, result);



  }

  /**
   * Use this method to read in a List of Player objects from the tournament document.
   *
   * @param tournamentDocument Xml tournament document.
   * @return List of Player objects read in from tournamentDocument.
   */
  private static List<Player> calculateStatistics(Document tournamentDocument) throws Exception {
    List<Player> players = new ArrayList<Player>();
    Player playerToAdd = null;

    XPathExpression xpathExpr = null;
    NodeList playerNodes = null;
    Node currentNode = null;
    Node currentChildNode = null;
    NamedNodeMap attributeMap = null;
    NodeList playerChildNodes = null;
    HashMap<Integer, Integer> diceStatisticsToClone = null;


    // TODO Namespace awerness



    // iterate through tournament players and add to players list
    xpathExpr = xPath.compile("//t:tournament/t:players/t:player");
    playerNodes = (NodeList)xpathExpr.evaluate(tournamentDocument, XPathConstants.NODESET);

    diceStatisticsToClone = new HashMap<Integer, Integer>();
    for (int i = 1; i < 7 ; i++) {
      diceStatisticsToClone.put(i, 0);
    }

    for (int i = 0; i < playerNodes.getLength(); i++) {
      currentNode = playerNodes.item(i);
      if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
        playerToAdd = new Player();
        attributeMap = currentNode.getAttributes();
        playerToAdd.setUsername(attributeMap.getNamedItem("username").getTextContent());
        playerChildNodes = currentNode.getChildNodes();
        for (int j = 0; j < playerChildNodes.getLength(); j++) {
          currentChildNode = playerChildNodes.item(j);
          if (currentChildNode.getNodeType() == Node.ELEMENT_NODE && currentChildNode.getLocalName().equals("gender")) {
            playerToAdd.setGender(currentChildNode.getTextContent());
          }
        }
        playerToAdd.setHasPlayed(false);
        playerToAdd.setWinnerCount(0);
        playerToAdd.setParticipatedCount(0);
        playerToAdd.setDiceStatistics(new HashMap<Integer,Integer>(diceStatisticsToClone));
        players.add(playerToAdd);
      }
    }

    // add dice results


    // add participant


    // add winner


    return players;
  }

  /**
   * Use this method to create the output xml file from a List of Player objects.
   *
   * @param players List of Player objects previously read in via Dom.calculateStatistics(Document)
   * @return Xml document which encapsulates the statistics as required by the task description.
   */
  private static Document createOutput(List<Player> players) {
    Document outputDocument = documentBuilder.newDocument();
    Element rootElement = outputDocument.createElement("player-statistics");
    outputDocument.appendChild(rootElement);

    Element playerElement = null;
    Element diceElement = null;
    Element oneCount = null;
    Element twoCount = null;
    Element threeCount = null;
    Element fourCount = null;
    Element fiveCount = null;
    Element sixCount = null;

    for (Player player : players) {
      playerElement = outputDocument.createElement("player");
      playerElement.setAttribute("username", player.getUsername());
      playerElement.setAttribute("gender", player.getGender());
      playerElement.setAttribute("has-played", player.getHasPlayed().toString());
      playerElement.setAttribute("winner-count", player.getWinnerCount().toString());
      playerElement.setAttribute("participated-count", player.getParticipatedCount().toString());

      diceElement = outputDocument.createElement("dice-statistics");
      oneCount = outputDocument.createElement("one-count");
      oneCount.setTextContent(player.getDiceStatistics().get(1).toString());
      diceElement.appendChild(oneCount);

      twoCount = outputDocument.createElement("two-count");
      twoCount.setTextContent(player.getDiceStatistics().get(2).toString());
      diceElement.appendChild(twoCount);

      threeCount = outputDocument.createElement("three-count");
      threeCount.setTextContent(player.getDiceStatistics().get(3).toString());
      diceElement.appendChild(threeCount);

      fourCount = outputDocument.createElement("four-count");
      fourCount.setTextContent(player.getDiceStatistics().get(4).toString());
      diceElement.appendChild(fourCount);

      fiveCount = outputDocument.createElement("five-count");
      fiveCount.setTextContent(player.getDiceStatistics().get(5).toString());
      diceElement.appendChild(fiveCount);

      sixCount = outputDocument.createElement("six-count");
      sixCount.setTextContent(player.getDiceStatistics().get(6).toString());
      diceElement.appendChild(sixCount);

      playerElement.appendChild(diceElement);
      rootElement.appendChild(playerElement);
    }

    return outputDocument;
  }
}
