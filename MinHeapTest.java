import java.nio.ByteBuffer;
import student.TestCase;

/**
 * This class represents the test methods used to test
 * the minHeap object class and check whether they work
 * as intended
 * 
 * @author Chanaka Perera(chanaka1)
 * @version 4/15/2019
 */
public class MinHeapTest extends TestCase {

    /**
     * Tests the methods in the MinHeapclass and whether they
     * work as intended
     */
    public void testMinHeap() {
        // Create a minHeap of capacity 10 that will hold
        // 10 Record objects
        MinHeap heap1 = new MinHeap(10);
        MinHeap heap2 = new MinHeap(10);

        // Initialize 10 Record objects
        ByteBuffer buffer1 = ByteBuffer.allocate(16);
        buffer1.putLong(0, 43);
        buffer1.putDouble(8, 28.0);
        ByteBuffer buffer2 = ByteBuffer.allocate(16);
        buffer2.putLong(0, 42);
        buffer2.putDouble(8, 36.0);
        ByteBuffer buffer3 = ByteBuffer.allocate(16);
        buffer3.putLong(0, 43);
        buffer3.putDouble(8, 47.0);
        ByteBuffer buffer4 = ByteBuffer.allocate(16);
        buffer4.putLong(0, 42);
        buffer4.putDouble(8, 45.0);
        ByteBuffer buffer5 = ByteBuffer.allocate(16);
        buffer5.putLong(0, 43);
        buffer5.putDouble(8, 28.0);
        ByteBuffer buffer6 = ByteBuffer.allocate(16);
        buffer6.putLong(0, 42);
        buffer6.putDouble(8, 77.0);
        ByteBuffer buffer7 = ByteBuffer.allocate(16);
        buffer7.putLong(0, 43);
        buffer7.putDouble(8, 65.0);
        ByteBuffer buffer8 = ByteBuffer.allocate(16);
        buffer8.putLong(0, 42);
        buffer8.putDouble(8, 99.0);
        ByteBuffer buffer9 = ByteBuffer.allocate(16);
        buffer9.putLong(0, 43);
        buffer9.putDouble(8, 46.0);
        ByteBuffer buffer10 = ByteBuffer.allocate(16);
        buffer10.putLong(0, 42);
        buffer10.putDouble(8, 90.0);
        Record record1 = new Record(buffer1.array());
        Record record2 = new Record(buffer2.array());
        Record record3 = new Record(buffer3.array());
        Record record4 = new Record(buffer4.array());
        Record record5 = new Record(buffer5.array());
        Record record6 = new Record(buffer6.array());
        Record record7 = new Record(buffer7.array());
        Record record8 = new Record(buffer8.array());
        Record record9 = new Record(buffer9.array());
        Record record10 = new Record(buffer10.array());

        // Insert the record objects into the heap
        heap1.insert(record6);
        heap1.insert(record8);
        heap1.insert(record4);
        heap1.insert(record9);
        heap1.insert(record10);
        heap1.insert(record3);
        heap1.insert(record7);
        heap1.insert(record2);
        heap1.insert(record5);
        heap1.insert(record1);

        // Checks whether the heap would return the correct indexes
        // of the children and parent nodes for the given index parameter
        assertEquals(heap1.leftChild(2), 5);
        assertEquals(heap1.leftChild(5), -1);
        assertEquals(heap1.leftChild(10), -1);
        assertEquals(heap1.leftChild(4), 9);
        assertEquals(heap1.rightChild(2), 6);
        assertEquals(heap1.rightChild(4), -1);
        assertEquals(heap1.rightChild(10), -1);
        assertEquals(heap1.parent(0), -1);
        assertEquals(heap1.parent(-1), -1);
        assertEquals(heap1.parent(5), 2);
        assertEquals(heap1.parent(8), 3);

        // Checks whether the insert method would place the record
        // elements at the correct position based on their key value
        assertEquals(heap1.elementAt(0).key(), 28.0, 0.0);
        assertEquals(heap1.elementAt(1).key(), 28.0, 0.0);
        assertEquals(heap1.elementAt(2).key(), 47.0, 0.0);
        assertEquals(heap1.elementAt(3).key(), 45.0, 0.0);
        assertEquals(heap1.elementAt(4).key(), 36.0, 0.0);
        assertEquals(heap1.elementAt(5).key(), 77.0, 0.0);
        assertEquals(heap1.elementAt(6).key(), 65.0, 0.0);
        assertEquals(heap1.elementAt(7).key(), 99.0, 0.0);
        assertEquals(heap1.elementAt(8).key(), 46.0, 0.0);
        assertEquals(heap1.elementAt(9).key(), 90.0, 0.0);
        assertNull(heap1.elementAt(10));
        assertNull(heap1.elementAt(-1));

        assertEquals(heap1.removeMin().key(), 28.0, 0.0);
        assertEquals(heap1.elementAt(0).key(), 28.0, 0.0);
        assertEquals(heap1.elementAt(1).key(), 36.0, 0.0);
        assertEquals(heap1.removeMin().key(), 28.0, 0.0);
        assertEquals(heap1.elementAt(0).key(), 36.0, 0.0);

        // Checks whether the build heap function of the minHeap would work
        // correctly for a random array of data
        // Initialize 10 Record objects
        ByteBuffer buffer11 = ByteBuffer.allocate(16);
        buffer11.putLong(0, 43);
        buffer11.putDouble(8, 96.0);
        ByteBuffer buffer12 = ByteBuffer.allocate(16);
        buffer12.putLong(0, 42);
        buffer12.putDouble(8, 61.0);
        ByteBuffer buffer13 = ByteBuffer.allocate(16);
        buffer13.putLong(0, 43);
        buffer13.putDouble(8, 42.0);
        ByteBuffer buffer14 = ByteBuffer.allocate(16);
        buffer14.putLong(0, 42);
        buffer14.putDouble(8, 17.0);
        ByteBuffer buffer15 = ByteBuffer.allocate(16);
        buffer15.putLong(0, 43);
        buffer15.putDouble(8, 30.0);
        ByteBuffer buffer16 = ByteBuffer.allocate(16);
        buffer16.putLong(0, 42);
        buffer16.putDouble(8, 60.0);
        ByteBuffer buffer17 = ByteBuffer.allocate(16);
        buffer17.putLong(0, 43);
        buffer17.putDouble(8, 35.0);
        ByteBuffer buffer18 = ByteBuffer.allocate(16);
        buffer18.putLong(0, 42);
        buffer18.putDouble(8, 26.0);
        ByteBuffer buffer19 = ByteBuffer.allocate(16);
        buffer19.putLong(0, 43);
        buffer19.putDouble(8, 46.0);
        ByteBuffer buffer20 = ByteBuffer.allocate(16);
        buffer20.putLong(0, 42);
        buffer20.putDouble(8, 92.0);
        Record record11 = new Record(buffer11.array());
        Record record12 = new Record(buffer12.array());
        Record record13 = new Record(buffer13.array());
        Record record14 = new Record(buffer14.array());
        Record record15 = new Record(buffer15.array());
        Record record16 = new Record(buffer16.array());
        Record record17 = new Record(buffer17.array());
        Record record18 = new Record(buffer18.array());
        Record record19 = new Record(buffer19.array());
        Record record20 = new Record(buffer20.array());

        heap2.insertAt(record11, 0);
        heap2.insertAt(record12, 1);
        heap2.insertAt(record13, 2);
        heap2.insertAt(record14, 3);
        heap2.insertAt(record15, 4);
        heap2.insertAt(record16, 5);
        heap2.insertAt(record17, 6);
        heap2.insertAt(record18, 7);
        heap2.insertAt(record19, 8);
        heap2.insertAt(record20, 9);

        // Checks whether the elements are inserted are there in the correct
        // position
        assertEquals(heap2.elementAt(0).key(), 96.0, 0.0);
        assertEquals(heap2.elementAt(1).key(), 61.0, 0.0);
        assertEquals(heap2.elementAt(2).key(), 42.0, 0.0);
        assertEquals(heap2.elementAt(3).key(), 17.0, 0.0);
        assertEquals(heap2.elementAt(4).key(), 30.0, 0.0);
        assertEquals(heap2.elementAt(5).key(), 60.0, 0.0);
        assertEquals(heap2.elementAt(6).key(), 35.0, 0.0);
        assertEquals(heap2.elementAt(7).key(), 26.0, 0.0);
        assertEquals(heap2.elementAt(8).key(), 46.0, 0.0);
        assertEquals(heap2.elementAt(9).key(), 92.0, 0.0);

        // Call the buildheap function
        heap2.buildHeap();

        // Checks whether the elements are placed at the correct position
        // Afterwards
        assertEquals(heap2.elementAt(0).key(), 17.0, 0.0);
        assertEquals(heap2.elementAt(1).key(), 26.0, 0.0);
        assertEquals(heap2.elementAt(2).key(), 35.0, 0.0);
        assertEquals(heap2.elementAt(3).key(), 46.0, 0.0);
        assertEquals(heap2.elementAt(4).key(), 30.0, 0.0);
        assertEquals(heap2.elementAt(5).key(), 60.0, 0.0);
        assertEquals(heap2.elementAt(6).key(), 42.0, 0.0);
        assertEquals(heap2.elementAt(7).key(), 61.0, 0.0);
        assertEquals(heap2.elementAt(8).key(), 96.0, 0.0);
        assertEquals(heap2.elementAt(9).key(), 92.0, 0.0);

    }
}
