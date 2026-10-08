package datastructures;

/**
 * Singly linked list implemented manually without java.util.LinkedList.
 * Supports insert, delete, search, and display with safe handling of missing values
 * and empty-list operations.
 */
public class CustomLinkedList {

    /**
     * Node of the singly linked list.
     */
    private static class Node {
        String data;
        Node next;

        Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    /**
     * Creates an empty linked list.
     */
    public CustomLinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Inserts a value at the end of the list.
     *
     * @param value string to insert
     */
    public void insert(String value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("Inserted \"" + value + "\" into the linked list.");
    }

    /**
     * Deletes the first occurrence of the given value.
     *
     * @param value value to remove
     * @return true if deleted, false if not found or list empty
     */
    public boolean delete(String value) {
        if (head == null) {
            System.out.println("Cannot delete: linked list is empty.");
            return false;
        }
        if (head.data.equals(value)) {
            head = head.next;
            size--;
            System.out.println("Deleted \"" + value + "\" from the linked list.");
            return true;
        }
        Node current = head;
        while (current.next != null && !current.next.data.equals(value)) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("Value \"" + value + "\" not found in the linked list.");
            return false;
        }
        current.next = current.next.next;
        size--;
        System.out.println("Deleted \"" + value + "\" from the linked list.");
        return true;
    }

    /**
     * Searches for the first occurrence of the given value.
     *
     * @param value value to find
     * @return zero-based index if found, or -1 if not present / list empty
     */
    public int search(String value) {
        if (head == null) {
            System.out.println("Cannot search: linked list is empty.");
            return -1;
        }
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(value)) {
                System.out.println("Found \"" + value + "\" at position " + index + ".");
                return index;
            }
            current = current.next;
            index++;
        }
        System.out.println("Value \"" + value + "\" not found in the linked list.");
        return -1;
    }

    /**
     * Displays all elements from head to tail.
     */
    public void display() {
        if (head == null) {
            System.out.println("Linked list is empty.");
            return;
        }
        System.out.print("Linked list: ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" -> null");
    }

    /**
     * @return number of nodes in the list
     */
    public int getSize() {
        return size;
    }

    /**
     * @return true if the list has no nodes
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns values as a string array (manual, no ArrayList).
     * Useful for internal graph neighbor iteration.
     *
     * @return array of current values
     */
    public String[] toArray() {
        String[] result = new String[size];
        Node current = head;
        int i = 0;
        while (current != null) {
            result[i++] = current.data;
            current = current.next;
        }
        return result;
    }

    /**
     * Silently checks whether a value already exists (no console output).
     *
     * @param value value to check
     * @return true if present
     */
    public boolean contains(String value) {
        Node current = head;
        while (current != null) {
            if (current.data.equals(value)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Inserts a value only if it is not already present (no duplicate console message).
     *
     * @param value value to insert uniquely
     * @return true if inserted, false if already present
     */
    public boolean insertUnique(String value) {
        if (contains(value)) {
            return false;
        }
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }
}
