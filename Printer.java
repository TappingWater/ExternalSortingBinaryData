/**
 * Class that prints the sorted file in the desired format
 * @author Chanaka Perera(chanaka1)
 * @version 4/16/2019
 */
public class Printer {
    
    /**
     * Constructor method for printer object
     * @param stream
     * Byte array that is used to print the needed
     * records
     */
    public Printer(byte[] stream) {
        int seperator = 0;        
        for (int i = 0; i < stream.length; i = i + 8192) {
            seperator++;
            byte[] record = new byte[16];
            for (int j = 0; j < 16; j++) {
                record[j] = stream[i + j];
            }
            Record element = new Record(record);
            if (seperator == 6) {
                System.out.println();
                seperator = 1;
            }
            System.out.print(element.recordID());
            System.out.print(" ");
            System.out.print(element.key());
            System.out.print(" ");
        }
        System.out.println();
    }
}
