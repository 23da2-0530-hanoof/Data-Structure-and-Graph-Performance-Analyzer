package datastructures;

/**
 * Array-based circular FIFO queue implemented manually without java.util.Queue.
 * Supports enqueue, dequeue, peek/front, display, and isEmpty with safe empty-queue handling.
 */
public class CustomQueue {

    private final String[] data;
    private int front;
    private int rear;
    private int count;
    private final int capacity;

    /**
     * Creates a queue with the given fixed capacity.
     *
     * @param capacity maximum number of elements
     */
    public CustomQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capacity = capacity;
        this.data = new String[capacity];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    /**
     * Adds a value to the rear of the queue.
     *
     * @param value string to enqueue
     * @return true if enqueued, false if the queue is full
     */
    public boolean enqueue(String value) {
        if (count >= capacity) {
            System.out.println("Cannot enqueue: queue is full (capacity " + capacity + ").");
            return false;
        }
        rear = (rear + 1) % capacity;
        data[rear] = value;
        count++;
        System.out.println("Enqueued \"" + value + "\".");
        return true;
    }

    /**
     * Removes and returns the front value.
     *
     * @return the dequeued value, or null if the queue is empty
     */
    public String dequeue() {
        if (isEmpty()) {
            System.out.println("Cannot dequeue: queue is empty.");
            return null;
        }
        String value = data[front];
        data[front] = null;
        front = (front + 1) % capacity;
        count--;
        System.out.println("Dequeued \"" + value + "\".");
        return value;
    }

    /**
     * Returns the front value without removing it.
     *
     * @return the front value, or null if the queue is empty
     */
    public String peek() {
        if (isEmpty()) {
            System.out.println("Cannot peek: queue is empty.");
            return null;
        }
        System.out.println("Front of queue: \"" + data[front] + "\".");
        return data[front];
    }

    /**
     * Alias for {@link #peek()}.
     *
     * @return the front value, or null if empty
     */
    public String front() {
        return peek();
    }

    /**
     * Displays queue contents from front to rear.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.print("Queue (front -> rear): ");
        for (int i = 0; i < count; i++) {
            int index = (front + i) % capacity;
            System.out.print(data[index]);
            if (i < count - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    /**
     * @return true if the queue contains no elements
     */
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * @return current number of elements in the queue
     */
    public int size() {
        return count;
    }

    /**
     * Enqueues without printing (used by graph BFS).
     *
     * @param value value to enqueue
     * @return true if enqueued, false if full
     */
    public boolean enqueueSilent(String value) {
        if (count >= capacity) {
            return false;
        }
        rear = (rear + 1) % capacity;
        data[rear] = value;
        count++;
        return true;
    }

    /**
     * Dequeues without printing (used by graph BFS).
     *
     * @return dequeued value, or null if empty
     */
    public String dequeueSilent() {
        if (isEmpty()) {
            return null;
        }
        String value = data[front];
        data[front] = null;
        front = (front + 1) % capacity;
        count--;
        return value;
    }
}
