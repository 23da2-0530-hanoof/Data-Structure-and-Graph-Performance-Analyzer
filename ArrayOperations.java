package datastructures;

/**
 * Fixed-capacity integer array wrapper with manual insert, delete, search, and display.
 * Demonstrates array operations without using java.util.ArrayList.
 */
public class ArrayOperations {

    private final int[] data;
    private int size;
    private final int capacity;

    /**
     * Creates an array wrapper with the given fixed capacity.
     *
     * @param capacity maximum number of elements the array can hold
     */
    public ArrayOperations(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capacity = capacity;
        this.data = new int[capacity];
        this.size = 0;
    }

    /**
     * Inserts a value at the end of the array if space remains.
     *
     * @param value integer to insert
     * @return true if inserted successfully, false if the array is full
     */
    public boolean insert(int value) {
        if (size >= capacity) {
            System.out.println("Cannot insert: array is full (capacity " + capacity + ").");
            return false;
        }
        data[size] = value;
        size++;
        System.out.println("Inserted " + value + " at index " + (size - 1) + ".");
        return true;
    }

    /**
     * Deletes the first occurrence of the given value.
     *
     * @param value value to remove
     * @return true if a matching element was deleted, false otherwise
     */
    public boolean delete(int value) {
        if (size == 0) {
            System.out.println("Cannot delete: array is empty.");
            return false;
        }
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Value " + value + " not found in the array.");
            return false;
        }
        return deleteAtIndex(index);
    }

    /**
     * Deletes the element at the specified index and shifts remaining elements left.
     *
     * @param index zero-based index to delete
     * @return true if deleted successfully, false if index is invalid or array empty
     */
    public boolean deleteAtIndex(int index) {
        if (size == 0) {
            System.out.println("Cannot delete: array is empty.");
            return false;
        }
        if (index < 0 || index >= size) {
            System.out.println("Invalid index " + index + ". Valid range is 0 to " + (size - 1) + ".");
            return false;
        }
        int removed = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        System.out.println("Deleted value " + removed + " at index " + index + ".");
        return true;
    }

    /**
     * Performs a linear search for the given value.
     *
     * @param value value to find
     * @return index of the first match, or -1 if not found or array empty
     */
    public int search(int value) {
        if (size == 0) {
            System.out.println("Cannot search: array is empty.");
            return -1;
        }
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                System.out.println("Found " + value + " at index " + i + ".");
                return i;
            }
        }
        System.out.println("Value " + value + " not found in the array.");
        return -1;
    }

    /**
     * Displays all current elements in the array.
     */
    public void display() {
        if (size == 0) {
            System.out.println("Array is empty.");
            return;
        }
        System.out.print("Array contents [" + size + "/" + capacity + "]: ");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    /**
     * Returns a copy of the current elements for use by search algorithms.
     *
     * @return new int array containing the live elements only
     */
    public int[] toArray() {
        int[] copy = new int[size];
        for (int i = 0; i < size; i++) {
            copy[i] = data[i];
        }
        return copy;
    }

    /**
     * @return current number of stored elements
     */
    public int getSize() {
        return size;
    }

    /**
     * @return maximum capacity of the array
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @return true if no elements are stored
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
