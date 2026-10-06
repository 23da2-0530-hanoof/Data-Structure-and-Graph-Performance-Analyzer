import datastructures.ArrayOperations;
import datastructures.CustomGraph;
import datastructures.CustomLinkedList;
import datastructures.CustomQueue;
import datastructures.CustomStack;
import performance.PerformanceTracker;
import search.SearchAlgorithms;
import search.SearchAlgorithms.SearchResult;
import util.InputValidator;

import java.util.Scanner;

/**
 * Entry point for the Data Structure and Graph Performance Analyzer.
 * Provides a menu-driven console interface for array, stack, queue, linked list,
 * searching, graph operations, and performance comparison.
 */
public class Main {

    private static final int DEFAULT_CAPACITY = 20;

    private final Scanner scanner;
    private final InputValidator validator;
    private final ArrayOperations arrayOps;
    private final CustomStack stack;
    private final CustomQueue queue;
    private final CustomLinkedList linkedList;
    private final CustomGraph graph;
    private final PerformanceTracker tracker;
    private int[] searchArray;

    /**
     * Constructs the application with shared structures and input helpers.
     */
    public Main() {
        this.scanner = new Scanner(System.in);
        this.validator = new InputValidator(scanner);
        this.arrayOps = new ArrayOperations(DEFAULT_CAPACITY);
        this.stack = new CustomStack(DEFAULT_CAPACITY);
        this.queue = new CustomQueue(DEFAULT_CAPACITY);
        this.linkedList = new CustomLinkedList();
        this.graph = new CustomGraph();
        this.tracker = new PerformanceTracker();
        this.searchArray = new int[0];
    }

    /**
     * Application entry point.
     *
     * @param args command-line arguments (unused)
     */
    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    /**
     * Runs the main menu loop until the user exits.
     */
    public void run() {
        System.out.println("=================================================");
        System.out.println(" Data Structure and Graph Performance Analyzer");
        System.out.println("=================================================");

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = validator.readMenuChoice("Enter choice: ", 1, 9);
            System.out.println();
            switch (choice) {
                case 1:
                    arrayMenu();
                    break;
                case 2:
                    stackMenu();
                    break;
                case 3:
                    queueMenu();
                    break;
                case 4:
                    linkedListMenu();
                    break;
                case 5:
                    searchingMenu();
                    break;
                case 6:
                    graphMenu();
                    break;
                case 7:
                    tracker.printComparisonTable();
                    break;
                case 8:
                    displayAllResults();
                    break;
                case 9:
                    running = false;
                    System.out.println("Exiting. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }

    private void printMainMenu() {
        System.out.println();
        System.out.println("MAIN MENU:");
        System.out.println("1. Array Operations");
        System.out.println("2. Stack Operations");
        System.out.println("3. Queue Operations");
        System.out.println("4. Linked List Operations");
        System.out.println("5. Searching Operations");
        System.out.println("6. Graph Operations");
        System.out.println("7. Performance Comparison");
        System.out.println("8. Display All Results");
        System.out.println("9. Exit");
    }

    // -------------------------------------------------------------------------
    // Array submenu
    // -------------------------------------------------------------------------

    private void arrayMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("--- Array Operations ---");
            System.out.println("1. Insert");
            System.out.println("2. Delete by value");
            System.out.println("3. Delete by index");
            System.out.println("4. Search");
            System.out.println("5. Display");
            System.out.println("6. Return to Main Menu");
            int choice = validator.readMenuChoice("Enter choice: ", 1, 6);
            switch (choice) {
                case 1:
                    arrayOps.insert(validator.readInt("Enter integer to insert: "));
                    break;
                case 2:
                    arrayOps.delete(validator.readInt("Enter value to delete: "));
                    break;
                case 3:
                    arrayOps.deleteAtIndex(validator.readInt("Enter index to delete: "));
                    break;
                case 4:
                    arrayOps.search(validator.readInt("Enter value to search: "));
                    break;
                case 5:
                    arrayOps.display();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    break;
            }
            System.out.println();
        }
    }

    // -------------------------------------------------------------------------
    // Stack submenu
    // -------------------------------------------------------------------------

    private void stackMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("--- Stack Operations ---");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Display");
            System.out.println("5. Check Empty");
            System.out.println("6. Return to Main Menu");
            int choice = validator.readMenuChoice("Enter choice: ", 1, 6);
            switch (choice) {
                case 1:
                    stack.push(validator.readNonEmpty("Enter value to push: "));
                    break;
                case 2:
                    stack.pop();
                    break;
                case 3:
                    stack.peek();
                    break;
                case 4:
                    stack.display();
                    break;
                case 5:
                    System.out.println(stack.isEmpty() ? "Stack is empty." : "Stack is not empty.");
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    break;
            }
            System.out.println();
        }
    }

    // -------------------------------------------------------------------------
    // Queue submenu
    // -------------------------------------------------------------------------

    private void queueMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("--- Queue Operations ---");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek / Front");
            System.out.println("4. Display");
            System.out.println("5. Check Empty");
            System.out.println("6. Return to Main Menu");
            int choice = validator.readMenuChoice("Enter choice: ", 1, 6);
            switch (choice) {
                case 1:
                    queue.enqueue(validator.readNonEmpty("Enter value to enqueue: "));
                    break;
                case 2:
                    queue.dequeue();
                    break;
                case 3:
                    queue.peek();
                    break;
                case 4:
                    queue.display();
                    break;
                case 5:
                    System.out.println(queue.isEmpty() ? "Queue is empty." : "Queue is not empty.");
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    break;
            }
            System.out.println();
        }
    }

    // -------------------------------------------------------------------------
    // Linked list submenu
    // -------------------------------------------------------------------------

    private void linkedListMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("--- Linked List Operations ---");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Display");
            System.out.println("5. Return to Main Menu");
            int choice = validator.readMenuChoice("Enter choice: ", 1, 5);
            switch (choice) {
                case 1:
                    linkedList.insert(validator.readNonEmpty("Enter value to insert: "));
                    break;
                case 2:
                    linkedList.delete(validator.readNonEmpty("Enter value to delete: "));
                    break;
                case 3:
                    linkedList.search(validator.readNonEmpty("Enter value to search: "));
                    break;
                case 4:
                    linkedList.display();
                    break;
                case 5:
                    back = true;
                    break;
                default:
                    break;
            }
            System.out.println();
        }
    }

    // -------------------------------------------------------------------------
    // Searching submenu
    // -------------------------------------------------------------------------

    private void searchingMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("--- Searching Operations ---");
            System.out.println("1. Build / Replace Search Array");
            System.out.println("2. Use Array Module Data as Search Array");
            System.out.println("3. Linear Search");
            System.out.println("4. Binary Search (requires sorted array)");
            System.out.println("5. Display Current Search Array");
            System.out.println("6. Return to Main Menu");
            int choice = validator.readMenuChoice("Enter choice: ", 1, 6);
            switch (choice) {
                case 1:
                    buildSearchArray();
                    break;
                case 2:
                    copyFromArrayModule();
                    break;
                case 3:
                    runLinearSearch();
                    break;
                case 4:
                    runBinarySearch();
                    break;
                case 5:
                    System.out.println("Current search array: " + SearchAlgorithms.arrayToString(searchArray));
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    break;
            }
            System.out.println();
        }
    }

    private void buildSearchArray() {
        int n = validator.readInt("How many integers? ");
        if (n <= 0) {
            System.out.println("Size must be positive.");
            return;
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = validator.readInt("Enter element [" + i + "]: ");
        }
        searchArray = arr;
        System.out.println("Search array set to: " + SearchAlgorithms.arrayToString(searchArray));
    }

    private void copyFromArrayModule() {
        if (arrayOps.isEmpty()) {
            System.out.println("Array module is empty. Insert values under Array Operations first.");
            return;
        }
        searchArray = arrayOps.toArray();
        System.out.println("Copied from Array module: " + SearchAlgorithms.arrayToString(searchArray));
    }

    private void runLinearSearch() {
        if (searchArray.length == 0) {
            System.out.println("Search array is empty. Build one first (option 1 or 2).");
            return;
        }
        int target = validator.readInt("Enter target value: ");
        SearchResult result = SearchAlgorithms.linearSearch(searchArray, target);
        if (result.isFound()) {
            System.out.println("Linear search found " + target + " at index " + result.getIndex()
                    + " in " + result.getSteps() + " comparison step(s).");
        } else {
            System.out.println("Linear search did not find " + target + " after "
                    + result.getSteps() + " comparison step(s).");
        }
        tracker.recordLinearSearch(result.getSteps(),
                "target=" + target + (result.isFound() ? ", index=" + result.getIndex() : ", not found"));
    }

    private void runBinarySearch() {
        if (searchArray.length == 0) {
            System.out.println("Search array is empty. Build one first (option 1 or 2).");
            return;
        }
        System.out.println("Binary search requires a sorted array.");
        System.out.println("Original: " + SearchAlgorithms.arrayToString(searchArray));
        int[] sorted = SearchAlgorithms.sortedCopy(searchArray);
        System.out.println("Sorted copy used for binary search: " + SearchAlgorithms.arrayToString(sorted));
        int target = validator.readInt("Enter target value: ");
        SearchResult result = SearchAlgorithms.binarySearch(sorted, target);
        if (result.isFound()) {
            System.out.println("Binary search found " + target + " at index " + result.getIndex()
                    + " in " + result.getSteps() + " comparison step(s).");
        } else {
            System.out.println("Binary search did not find " + target + " after "
                    + result.getSteps() + " comparison step(s).");
        }
        tracker.recordBinarySearch(result.getSteps(),
                "target=" + target + (result.isFound() ? ", index=" + result.getIndex() : ", not found"));
    }

    // -------------------------------------------------------------------------
    // Graph submenu
    // -------------------------------------------------------------------------

    private void graphMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("--- Graph Operations ---");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Return to Main Menu");
            int choice = validator.readMenuChoice("Enter choice: ", 1, 6);
            switch (choice) {
                case 1:
                    graph.addVertex(validator.readNonEmpty("Enter vertex label: "));
                    break;
                case 2: {
                    String v1 = validator.readNonEmpty("Enter first vertex: ");
                    String v2 = validator.readNonEmpty("Enter second vertex: ");
                    graph.addEdge(v1, v2);
                    break;
                }
                case 3:
                    graph.displayGraph();
                    break;
                case 4:
                    runBfs();
                    break;
                case 5:
                    runDfs();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    break;
            }
            System.out.println();
        }
    }

    private void runBfs() {
        if (graph.isEmpty()) {
            System.out.println("Graph is empty. Add vertices and edges first.");
            return;
        }
        String start = validator.readNonEmpty("Enter start vertex for BFS: ");
        CustomGraph.TraversalResult result = graph.bfs(start);
        if (result != null) {
            tracker.recordBfs(result.getSteps(), "start=" + start + ", order=" + result.getOrder());
        }
    }

    private void runDfs() {
        if (graph.isEmpty()) {
            System.out.println("Graph is empty. Add vertices and edges first.");
            return;
        }
        String start = validator.readNonEmpty("Enter start vertex for DFS: ");
        CustomGraph.TraversalResult result = graph.dfs(start);
        if (result != null) {
            tracker.recordDfs(result.getSteps(), "start=" + start + ", order=" + result.getOrder());
        }
    }

    // -------------------------------------------------------------------------
    // Display all
    // -------------------------------------------------------------------------

    private void displayAllResults() {
        System.out.println("================ DISPLAY ALL RESULTS ================");
        System.out.println();
        System.out.println("[Array]");
        arrayOps.display();
        System.out.println();
        System.out.println("[Stack]");
        stack.display();
        System.out.println();
        System.out.println("[Queue]");
        queue.display();
        System.out.println();
        System.out.println("[Linked List]");
        linkedList.display();
        System.out.println();
        System.out.println("[Search Array]");
        System.out.println(SearchAlgorithms.arrayToString(searchArray));
        System.out.println();
        System.out.println("[Graph]");
        graph.displayGraph();
        System.out.println();
        tracker.printAllResults();
        System.out.println("======================================================");
    }
}
