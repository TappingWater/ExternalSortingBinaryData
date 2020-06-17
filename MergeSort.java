import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * This class is used to mergesort the
 * run file that is created through replacement
 * selection
 * 
 * @author Chanaka Perera(chanaka1)
 * @version 4/16/2019
 */
public class MergeSort {

    private MinHeap mergeHeap;
    private boolean needsAnotherRun = false;


    /**
     * Default constructor method for mergesort
     */
    public MergeSort() {
        mergeHeap = new MinHeap(4096);
    }


    /**
     * @return
     *         Returns the boolean value whether the file has been
     *         sorted or needs another run
     */
    public boolean runAgain() {
        return needsAnotherRun;
    }


    /**
     * Takes the byte[] array of the run file created from
     * the replacement selection and then merges the needed
     * records to create the final sorted files
     * 
     * @param runFileStream
     *            The stream of bytes from the run file
     * @param runFileLength
     *            The length in blocks of one run in the file
     * @throws IOException
     */
    public void mergeSort(byte[] runFileStream, int runFileLength)
        throws IOException {

        // Creates a merge file to sort the data as required
        File mergeFile = new File("mergeFile.bin");
        OutputStream runOutput = new FileOutputStream(mergeFile);

        // Allocate the needed space for an input and an output buffer
        // 8192 bytes = 1 block
        ByteBuffer outputBuffer = ByteBuffer.allocate(8192);

        // The offset of each run from one another
        int offset = runFileLength * 8192;
        double runDouble = (double)runFileStream.length / (double)offset;
        int runs = (int)Math.ceil(runDouble);

        if (runs > 8) {
            needsAnotherRun = true;
        }
        else {
            needsAnotherRun = false;
        }

        // Initialize 8 blocks for insertion to the minHeap
        int[] blockCount = { 0, 0, 0, 0, 0, 0, 0, 0 };
        int[] blockRecordCount = { 0, 0, 0, 0, 0, 0, 0, 0 };
        int remRuns = runs;
        int counter = 0;

        while (remRuns > 0) {

            // Initialize upto 8 blocks of memory into the heap
            // from the run files
            for (int i = 0; (i < remRuns) && (i < 8); i++) {
                int blockVal = i % 8;
                for (int j = 0; j < 8192; j = j + 16) {
                    byte[] record = new byte[16];
                    for (int r = 0; r < 16; r++) {
                        record[r] = runFileStream[(counter * offset) + (offset
                            * i) + j + r];
                    }
                    Record element = new Record(record);
                    element.setMergeKey(blockVal);
                    mergeHeap.insert(element);
                }
            }

            // Gets the minimum record
            Record min = mergeHeap.elementAt(0);
            // Code runs while there are values within the heap
            while (min != null) {
                min = mergeHeap.removeMin();
                blockRecordCount[min.getMergeKey()]++;
                // If the output buffer gets filled it writes the
                // data to the file
                if (outputBuffer.position() > 8190) {
                    runOutput.write(outputBuffer.array());
                    outputBuffer.clear();
                }
                outputBuffer.put(min.record());
                // Checks whether any of the blocks has been filled with 512
                // records so that the next block from that run can be
                // inserted into the heap
                for (int k = 0; k < blockRecordCount.length; k++) {
                    if (blockRecordCount[k] == 512) {
                        blockRecordCount[k] = 0;
                        blockCount[k]++;
                        // If the block count is less than run file length and
                        // there
                        // are more blocks to process
                        if (blockCount[k] < runFileLength && ((counter * offset)
                            + (offset * k) + (8192 * blockCount[k])
                            + 1) < runFileStream.length) {
                            for (int j = 0; j < 8192; j = j + 16) {
                                byte[] record = new byte[16];
                                for (int r = 0; r < 16; r++) {
                                    record[r] = runFileStream[(counter * offset)
                                        + (offset * k) + j + r + (8192
                                            * blockCount[k])];
                                }
                                Record element = new Record(record);
                                element.setMergeKey(k);
                                mergeHeap.insert(element);
                            }
                        }
                    }
                }
                min = mergeHeap.elementAt(0);
            }
            // For handling edge cases
            if (outputBuffer.position() > 8190) {
                runOutput.write(outputBuffer.array());
                outputBuffer.clear();
            }
            // Reset the block counts
            for (int t = 0; t < blockCount.length; t++) {
                blockCount[t] = 0;
            }

            remRuns = remRuns - 8;
            counter = counter + 8;
        }
        runOutput.close();

    }

}
