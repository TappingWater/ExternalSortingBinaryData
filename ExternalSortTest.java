import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import student.TestCase;

/**
 * This class checks the standard output created
 * from running bin files of varying lengths since the
 * project spec states that files will be of size 8N
 * 
 * @author Chanaka Perera(chanaka1)
 * @version 4/16/2019
 *
 */
public class ExternalSortTest extends TestCase {

    private Externalsort sort;

    // Local variables for generating a bin file
    private final int numRecs = 512;
    private Random value = new Random();


    /**
     * 
     * @return
     *         Returns a random long value
     */
    private long randLong() {
        return value.nextLong();
    }


    /**
     * @return
     *         Returns a random double value
     */
    private double randDouble() {
        return value.nextDouble();
    }


    /**
     * Function for generating a bin file for testing
     * 
     * @param args
     *            String arguements where args[0] = name and args[1] =
     *            size in blocks
     * @throws IOException
     */
    public void genFile(String[] args) throws IOException {
        long val;
        double val2;
        assert (args.length == 2) : "\nUsage: Genfile <filename> <size>"
            + "\nOptions \nSize is measured in blocks of 8192 bytes";

        int filesize = Integer.parseInt(args[1]); // Size of file in blocks
        DataOutputStream file = new DataOutputStream(new BufferedOutputStream(
            new FileOutputStream(args[0])));

        for (int i = 0; i < filesize; i++) {
            for (int j = 0; j < numRecs; j++) {
                val = (long)(randLong());
                file.writeLong(val);
                val2 = (double)(randDouble());
                file.writeDouble(val2);
            }
        }

        file.flush();
        file.close();
    }


    /**
     * Compare the standard output generated
     * 
     * @throws IOException
     *             check for 8 blocks of output in standard out
     */
    @SuppressWarnings("static-access")
    public void test1() throws IOException {
        String[] args = { "test1.bin", "8" };
        genFile(args);
        Path path = Paths.get(args[0]);
        byte[] byteStream = Files.readAllBytes(path);
        int length1 = byteStream.length;
        String[] sortArgs = { args[0] };
        sort.main(sortArgs);
        Path path2 = Paths.get("mergeFile.bin");
        byte[] mergeStream = Files.readAllBytes(path2);
        int length2 = mergeStream.length;
        assertEquals(length1, length2);
    }


    /**
     * Compare the standard output generated
     * 
     * @throws IOException
     *             check for 8 blocks of output in standard out
     */
    @SuppressWarnings("static-access")
    public void test2() throws IOException {
        String[] args = { "test1.bin", "64" };
        genFile(args);
        Path path = Paths.get(args[0]);
        byte[] byteStream = Files.readAllBytes(path);
        int length1 = byteStream.length;
        String[] sortArgs = { args[0] };
        sort.main(sortArgs);
        Path path2 = Paths.get("mergeFile.bin");
        byte[] mergeStream = Files.readAllBytes(path2);
        int length2 = mergeStream.length;
        assertEquals(length1, length2);
    }


    /**
     * Compare the standard output generated
     * 
     * @throws IOException
     *             check for 8 blocks of output in standard out
     */
    @SuppressWarnings("static-access")
    public void test3() throws IOException {
        String[] args = { "test1.bin", "72" };
        genFile(args);
        Path path = Paths.get(args[0]);
        byte[] byteStream = Files.readAllBytes(path);
        int length1 = byteStream.length;
        String[] sortArgs = { args[0] };
        sort.main(sortArgs);
        Path path2 = Paths.get("mergeFile.bin");
        byte[] mergeStream = Files.readAllBytes(path2);
        int length2 = mergeStream.length;
        assertEquals(length1, length2);
    }


    /**
     * Compare the standard output generated
     * 
     * @throws IOException
     *             check for 8 blocks of output in standard out
     */
    @SuppressWarnings("static-access")
    public void test4() throws IOException {
        String[] args = { "test1.bin", "128" };
        genFile(args);
        Path path = Paths.get(args[0]);
        byte[] byteStream = Files.readAllBytes(path);
        int length1 = byteStream.length;
        String[] sortArgs = { args[0] };
        sort.main(sortArgs);
        Path path2 = Paths.get("mergeFile.bin");
        byte[] mergeStream = Files.readAllBytes(path2);
        int length2 = mergeStream.length;
        assertEquals(length1, length2);
    }
}
