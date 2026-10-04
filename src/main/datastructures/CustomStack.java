package datastructures;

/**
 * Array-based stack (LIFO) implemented manually without java.util.Stack.
 * Supports push, pop, peek, display, and isEmpty with safe empty-stack handling.
 */
public class CustomStack {

    private final String[] data;
    private int top;
    private final int capacity;

    /**
     * Creates a stack with the given fixed capacity.
     *
     * @param capacity maximum number of elements
     */
    public CustomStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        this.capacity = capacity;
        this.data = new String[capacity];
        this.top = -1;
    }

    /**
     * Pushes a value onto the top of the stack.
     *
     * @param value string to push
     * @return true if pushed, false if the stack is full
     */
    public boolean push(String value) {
        if (top >= capacity - 1) {
            System.out.println("Cannot push: stack is full (capacity " + capacity + ").");
            return false;
        }
        top++;
        data[top] = value;
        System.out.println("Pushed \"" + value + "\" onto the stack.");
        return true;
    }

    /**
     * Removes and returns the top value.
     *
     * @return the popped value, or null if the stack is empty
     */
    public String pop() {
        if (isEmpty()) {
            System.out.println("Cannot pop: stack is empty.");
            return null;
        }
        String value = data[top];
        data[top] = null;
        top--;
        System.out.println("Popped \"" + value + "\" from the stack.");
        return value;
    }

    /**
     * Returns the top value without removing it.
     *
     * @return the top value, or null if the stack is empty
     */
    public String peek() {
        if (isEmpty()) {
            System.out.println("Cannot peek: stack is empty.");
            return null;
        }
        System.out.println("Top of stack: \"" + data[top] + "\".");
        return data[top];
    }

    /**
     * Displays stack contents from top to bottom.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty.");
            return;
        }
        System.out.print("Stack (top -> bottom): ");
        for (int i = top; i >= 0; i--) {
            System.out.print(data[i]);
            if (i > 0) {
                System.out.print(" | ");
            }
        }
        System.out.println();
    }

    /**
     * @return true if the stack contains no elements
     */
    public boolean isEmpty() {
        return top < 0;
    }

    /**
     * @return current number of elements on the stack
     */
    public int size() {
        return top + 1;
    }

    /**
     * Pushes without printing (used by graph DFS).
     *
     * @param value value to push
     * @return true if pushed, false if full
     */
    public boolean pushSilent(String value) {
        if (top >= capacity - 1) {
            return false;
        }
        top++;
        data[top] = value;
        return true;
    }

    /**
     * Pops without printing (used by graph DFS).
     *
     * @return popped value, or null if empty
     */
    public String popSilent() {
        if (isEmpty()) {
            return null;
        }
        String value = data[top];
        data[top] = null;
        top--;
        return value;
    }
}
