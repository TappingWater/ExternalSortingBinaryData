import java.nio.ByteBuffer;

/**
 * This class represents a single record object
 * 
 * @author Chanaka Perera(chanaka1)
 * @version 4/15/2019
 */
public class Record {

    private long recordID;
    private double key;
    private int mergeBuffer = 0;
    private byte[] recordByte;


    /**
     * Default constructor method for a record object that
     * uses a byte array to initialize the needed values
     * 
     * @param recordP
     *            The record in byte representation
     */
    public Record(byte[] recordP) {
        recordByte = recordP;
        ByteBuffer recordIDBuffer = ByteBuffer.allocate(8);
        ByteBuffer keyBuffer = ByteBuffer.allocate(8);
        recordIDBuffer.put(recordP, 0, 8);
        keyBuffer.put(recordP, 8, 8);
        recordIDBuffer.rewind();
        keyBuffer.rewind();
        recordID = recordIDBuffer.getLong();
        key = keyBuffer.getDouble();
    }


    /**
     * This is used during mergesort to check which
     * block of runs the record is taken from
     * 
     * @param val
     *            The key Val of the merggeBuffer that acts
     *            as a key
     */
    public void setMergeKey(int val) {
        mergeBuffer = val;
    }


    /**
     * @return
     *         The charValue or the key used during mergeSort
     */
    public int getMergeKey() {
        return mergeBuffer;
    }


    /**
     * @return
     *         Getter method for the key
     */
    public double key() {
        return key;
    }


    /**
     * @return
     *         Getter method for the recordID
     */
    public long recordID() {
        return recordID;
    }


    /**
     * @return
     *         Getter method for the byte representation
     *         of a record
     */
    public byte[] record() {
        return recordByte;
    }


    /**
     * @return
     *         True or false depending on whether the record entered
     *         in the parameter of the method has a key value that is greater
     *         than or equal to the key value of this record object
     * @param recordP
     *            The record object that this record object is being compared
     *            against
     */
    public boolean isLessThan(Record recordP) {
        double keyP = recordP.key();
        return (this.key < keyP);
    }
}
