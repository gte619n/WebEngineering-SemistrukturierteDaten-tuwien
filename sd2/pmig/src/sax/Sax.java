package sax;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import org.xml.sax.helpers.DefaultHandler;

public class Sax {

    /**
     * The PrintWriter's append() function can be used to write the
     * SQL-statements into the output file.
     */
    private static PrintWriter printWriter;

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: java Sax <input file> <output file>");
            System.exit(1);
        }

        String inputPath = args[0];
        String outputPath = args[1];

        createInsertSQL(inputPath, outputPath);
    }

    /**
     * TODO: Set up I/O and start parsing.
     *
     * @param inputPath Path to an input .xml file.
     * @param outputPath Path of an output .sql file.
     * @throws Exception
     */
    private static void createInsertSQL(String inputPath, String outputPath) throws Exception {
        // you can use the printWriter to write your generated SQL into the output file
        printWriter = new PrintWriter(new FileOutputStream(outputPath));

        // TODO: Setup and start parsing.

        // close stream
        printWriter.close();
    }

    /**
     * TODO: Implement this content handler.
     */
    public static class SQLContentHandler extends DefaultHandler {
    }
}