
/**
 * This class represents a minHeap that is used to hold the
 * record objects and organize them as required
 * 
 * @author Chanaka Perera(chanaka1)
 * @version 4/15/2019
 */
public class MinHeap {
    private Record[] heap;
    private int size;
    private int capacity;


    /**
     * This is the default constructor method for
     * the minHeap class that is used to initialize a heap
     * 
     * @param capacityP
     *            The parameter that defines the maximum number of objects
     *            that the heap can hold
     */
    public MinHeap(int capacityP) {
        this.capacity = capacityP;
        this.size = 0;
        heap = new Record[capacity];
    }


    /**
     * @param posP
     *            The position of the parent for whom you need to find the left
     *            child
     *            for
     * @return
     *         The position of the the left child for the given position of the
     *         parent
     *         within the heap
     */
    public int leftChild(int posP) {
        if (posP >= size / 2) {
            return -1;
        }
        return 2 * posP + 1;
    }


    /**
     * @param posP
     *            The position of the parent for whom you need to find the right
     *            child
     *            for
     * @return
     *         The position of the right child for the given position of the
     *         parent
     *         within the heap
     */
    public int rightChild(int posP) {
        if (posP >= (size - 1) / 2) {
            return -1;
        }
        return 2 * posP + 2;
    }


    /**
     * @param pos
     *            The position of the child for whom you need to find the parent
     *            for
     * @return
     *         The position of the parent for the given position of the child
     */
    public int parent(int pos) {
        if (pos <= 0) {
            return -1;
        }
        return (pos - 1) / 2;
    }


    /**
     * @param posP
     *            The position of a node within the heap to check for
     * @return
     *         Whether the given position of a node indicates whether it is a
     *         leaf
     */
    private boolean isLeaf(int posP) {
        return ((posP >= (size / 2)) && (posP <= size));
    }


    /**
     * Helper function that takes two position parameters and
     * swaps them within the heap
     * 
     * @param fPos
     *            The position of the first node that needs swapping
     * @param sPos
     *            The position of the second node that needs swapping
     */
    private void swap(int fPos, int sPos) {
        Record tmp;
        tmp = heap[fPos];
        heap[fPos] = heap[sPos];
        heap[sPos] = tmp;
    }


    /**
     * Inserts a record into the heap and places it in the correct
     * position in the heap considering that it is already heapified.
     * 
     * @return
     *         True or false depending on whether the record was inserted into
     *         the heap
     * @param element
     *            The record object that needs to be inserted into the heap
     */
    public boolean insert(Record element) {
        if (size >= capacity) {
            return false;
        }
        int curr = size;
        heap[curr] = element; // Start at end of heap
        // Now sift up until curr's parent's key < curr's key
        while ((curr != 0) && (heap[curr].isLessThan(heap[parent(curr)]))) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
        size = size + 1;
        return true;
    }


    /**
     * Helper function that is used to sift down an element within
     * the heap to the correct position within the heap
     * 
     * @param pos
     *            The position of the element within the heap that needs to be
     *            placed in the correct position
     */
    private void siftDown(int pos) {
        if ((pos < 0) || (pos >= size)) {
            return; // Illegal position
        }
        while (!isLeaf(pos)) {
            int j = leftChild(pos);
            if ((j < (size - 1)) && (heap[j + 1].isLessThan(heap[j]))) {
                j++;
            }
            if (heap[j].isLessThan(heap[pos])) {
                swap(pos, j);
                pos = j; // Move down
            }
            else {
                return;
            }
        }
    }


    /**
     * Function that is used to heapify the elements within the heap
     */
    public void buildHeap() {
        for (int i = size / 2 - 1; i >= 0; i--) {
            siftDown(i);
        }
    }


    /**
     * Function that is used to remove the minValue from the
     * heap which will be the root
     * 
     * @return
     *         The minimum element within the heap
     */
    public Record removeMin() {
        if (size == 0) {
            return null; // Removing from empty heap
        }
        swap(0, (size - 1)); // Swap maximum with last value
        Record min = heap[size - 1];
        heap[size - 1] = null;
        size = size - 1;
        if (size != 0) { // Not on last element
            siftDown(0);
        }
        return min;
    }


    /**
     * @return
     *         Record element that is at the given position parameter
     * @param posP
     *            The position within the heap with the given element
     */
    public Record elementAt(int posP) {
        if (posP > (size - 1) || posP < 0) {
            return null;
        }
        return heap[posP];
    }


    /**
     * Inserts a record element at the given index parameter of the function
     * 
     * @return
     *         boolean value indicating true or false whether the record has
     *         been inserted at the position
     * @param posP
     *            THe position at which the record element should be inserted
     *            into the heap
     * @param recordP
     *            The record object that needs to be inserted into the heap
     */
    public boolean insertAt(Record recordP, int posP) {
        heap[posP] = recordP;
        size = posP + 1;
        return true;
    }
}
