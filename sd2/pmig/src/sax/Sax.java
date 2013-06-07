package sax;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileReader;
import java.util.Arrays;
import org.xml.sax.helpers.*;
import org.xml.sax.*;

public class Sax {
  private static PrintWriter printWriter = null;

  public static void main(String[] args) throws Exception {
    if (args.length != 2) {
      System.err.println("Usage: java Sax <input file> <output file>");
      System.exit(1);
    }

    String inputPath = args[0];
    String outputPath = args[1];

    createInsertSQL(inputPath, outputPath);
  }


  private static void createInsertSQL(String inputPath, String outputPath) throws Exception {
    // you can use the printWriter to write your generated SQL into the output file
    printWriter = new PrintWriter(new FileOutputStream(outputPath));

    // read XML file and set ContentHanlder
    XMLReader xmlReader = XMLReaderFactory.createXMLReader();
    FileReader fileReader = new FileReader(inputPath);
    InputSource inputSource = new InputSource(fileReader);
    SQLContentHandler sqlContentHandler = new SQLContentHandler();
    xmlReader.setContentHandler(sqlContentHandler);
    xmlReader.parse(inputSource);

    // close stream
    printWriter.close();
  }


  public static class SQLContentHandler extends DefaultHandler {
    // helper data
    private final String sqlStatementTemplate = "INSERT INTO Highscore (username, averageWins, averageDots) VALUES ('%s',%,.2f,%,.3f)";
    private final String[] numberArray = {"one", "two", "three", "four", "five", "six"};

    // player data
    private String username = null;
    private double averageWins = -1.0;
    private double averageDots = -1.0;
    private int[] diceStatistics = null;

    // xml data
    private String currentValue = null;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

      if (localName.equals("player")) {
        double winnerCount = -1.0;
        double participatedCount = -1.0;

        // initialize new Player
        username = attributes.getValue("username");
        winnerCount = Double.valueOf(attributes.getValue("winner-count"));
        participatedCount = Double.valueOf(attributes.getValue("participated-count"));
        averageWins = winnerCount / participatedCount;
        diceStatistics = new int[6];
        averageDots = 0.0;
      }
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
      currentValue = new String(ch, start, length);
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      if (localName.endsWith("-count")) {
        String number = null;
        int position = -1;

        // calculate position
        number = localName.split("-count")[0];
        position = Arrays.asList(numberArray).indexOf(number);

        // add to diceresult array
        diceStatistics[position] = Integer.valueOf(currentValue);

      } else if (localName.equals("player")) {
        String sqlStatement = null;
        double diceses = 0;

        // calculate averageDots
        for (int i = 0; i < diceStatistics.length; i++) {
          averageDots += (i+1) * diceStatistics[i];
          diceses += diceStatistics[i];
        }
        averageDots = averageDots / diceses;

        // write sqlStatement
        sqlStatement = String.format(sqlStatementTemplate, username, averageWins, averageDots);
        printWriter.append(sqlStatement + '\n');

        // log statement
        System.out.println(">>>>>>>>>>>>>    (pmig log)    " +sqlStatement);
      }
    }
  }
}
