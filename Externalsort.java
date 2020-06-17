import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This is the main class. That utilizes the methods and objects
 * in the project to sort a bin file of records.
 * 
 * @author Chanaka Perera (chanaka1)
 * @version 4/15/2019
 */
public class Externalsort {

    private static ReplacementSelection firstPhase;
    private static MergeSort secondPhase;


    /**
     * The main method that is invoked to sort a bin file in ascending
     * order of their key values
     * 
     * @param args
     *            The string argument that has the name of the bin file that
     *            needs
     *            to be sorted
     */
    public static void main(String[] args) {
        // Prints an error message if the invocation is wrong
        if (args.length < 1) {
            System.out.println("Please provide the correct input bin file");
            System.exit(0);
        }

        String inputFile = args[0];

        // Surround with try/catch to to check for input file that is not
        // Available
        try (InputStream inputStream = new FileInputStream(inputFile);) {
            // Get the input file and read the bytes
            Path path = Paths.get(inputFile);
            byte[] byteStream = Files.readAllBytes(path);
            // Initialize a replacement selection object
            firstPhase = new ReplacementSelection();
            firstPhase.replacementSelection(byteStream);
            Path path2 = Paths.get("runFile.bin");
            // Read the bytes from the file created from replacement
            // selection containing the runs
            byte[] runByteStream = Files.readAllBytes(path2);
            // Initialize a mergesort object with the maximum
            // run length of 8 initially
            secondPhase = new MergeSort();
            int runLength = 8;
            secondPhase.mergeSort(runByteStream, runLength);

            /**
             * Check whether the mergesort needs to run again
             * and runs recursively by transfering data and
             * starting a new merge file
             */
            while (secondPhase.runAgain()) {
                runLength = runLength * 8;
                Path path3 = Paths.get("mergeFile.bin");
                byte[] mergeByteStream = Files.readAllBytes(path3);
                File runFile = new File("runFile.bin");
                OutputStream runOutput = new FileOutputStream(runFile);
                runOutput.write(mergeByteStream);
                runOutput.close();
                path2 = Paths.get("runFile.bin");

                runByteStream = Files.readAllBytes(path2);
                secondPhase.mergeSort(runByteStream, runLength);
            }
            // After file has been sorted gets the sorted bytes
            // and prints them to standard output
            Path path3 = Paths.get("mergeFile.bin");
            byte[] mergeByteStream = Files.readAllBytes(path3);
            @SuppressWarnings("unused")
            Printer printer = new Printer(mergeByteStream);
            inputStream.close();

        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
