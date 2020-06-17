import java.nio.ByteBuffer;
import student.TestCase;

/**
 * This class tests the methods in the record object
 * class and whether they work as intended
 * 
 * @author Chanaka Perera(chanak1)
 * @version 4/15/2019
 */
public class RecordTest extends TestCase {

    /**
     * Tests the methods within the Record class and whetther they are correct
     */
    public void testRecord() {
        // Initialize 2 record objects
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.putLong(0, 43);
        buffer.putDouble(8, 16.0);
        ByteBuffer buffer2 = ByteBuffer.allocate(16);
        buffer2.putLong(0, 42);
        buffer2.putDouble(8, 17.0);
        Record record1 = new Record(buffer.array());
        Record record2 = new Record(buffer2.array());

        // Tests the getter methods in the Record object class
        assertEquals(record1.key(), 16.0, 0.0);
        assertEquals(record1.recordID(), 43);
        assertEquals(record1.record(), buffer.array());

        // Tests the boolean method
        assertTrue(record1.isLessThan(record2));
        assertFalse(record1.isLessThan(record1));
        assertFalse(record2.isLessThan(record1));
    }
}
