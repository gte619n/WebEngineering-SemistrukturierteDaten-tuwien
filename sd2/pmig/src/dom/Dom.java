package dom;

import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;

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
        // TODO Implementation
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    /**
     * Use this method to read in a List of Player objects from the tournament document.
     * 
     * @param tournamentDocument Xml tournament document.
     * @return List of Player objects read in from tournamentDocument.
     */
    private static List<Player> calculateStatistics(Document tournamentDocument) throws Exception {
        // TODO Implementation
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    /**
     * Use this method to create the output xml file from a List of Player objects.
     * 
     * @param players List of Player objects previously read in via Dom.calculateStatistics(Document)
     * @return Xml document which encapsulates the statistics as required by the task description.
     */
    private static Document createOutput(List<Player> players) {
        // TODO Implementation
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
