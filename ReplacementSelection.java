import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * This method is used to take a byte array and perform
 * replacement selection using a 8 block min heap as well
 * as an input and output buffer
 * 
 * @author Chanaka Perera(chanaka1)
 * @version 4/15/2019
 */
public class ReplacementSelection {

    // Local variables that hold the needed values
    private MinHeap replacementHeap;


    /**
     * Default constructor method for replacement selection
     */
    public ReplacementSelection() {
        replacementHeap = new MinHeap(4096);
    }


    /**
     * Method that takes in a byte array and uses replacement
     * selection to create the needed run file with sorted runs
     * 
     * @param byteArray
     *            An array of bytes that is processed using replacement
     *            selection to create runs
     * @throws IOException
     */
    public void replacementSelection(byte[] byteArray) throws IOException {
        int currPtr = 0;

        File runFile = new File("runFile.bin");
        OutputStream runOutput = new FileOutputStream(runFile);

        // Allocate the needed space for an input and an output buffer
        // 8192 bytes = 1 block
        ByteBuffer outputBuffer = ByteBuffer.allocate(8192);
        
        // The code runs for all bytes within the file
        while (currPtr < byteArray.length) {
            // Loop for filling the minHeap with 8 blocks
            // of values
            for (int i = 0; i < 65536; i = i + 16) {
                if ((currPtr + i + 15) < byteArray.length) {
                    byte[] record = new byte[16];
                    for (int j = 0; j < 16; j++) {
                        record[j] = byteArray[currPtr + i + j];
                    }
                    Record element = new Record(record);
                    replacementHeap.insert(element);
                }
                
            }
            // Gets the minimum record
            Record min = replacementHeap.elementAt(0);
            // Code runs while there are values within the heap
            while (min != null) {
                min = replacementHeap.removeMin();
                if (outputBuffer.position() > 8190) {
                    runOutput.write(outputBuffer.array());
                    outputBuffer.clear();
                }
                outputBuffer.put(min.record());
                min = replacementHeap.elementAt(0);
            }

            // For handling edge cases
            if (outputBuffer.position() > 8190) {
                runOutput.write(outputBuffer.array());
                outputBuffer.clear();
            }
            // Move to the next 8 blocks of the file
            currPtr = currPtr + 65536;
        }
        runOutput.close();

    }

}
