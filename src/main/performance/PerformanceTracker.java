package performance;

/**
 * Collects and displays step-count results for search and graph traversal algorithms.
 * Results feed the Performance Comparison and Display All Results menu options.
 */
public class PerformanceTracker {

    private Integer linearSearchSteps;
    private Integer binarySearchSteps;
    private Integer bfsSteps;
    private Integer dfsSteps;

    private String linearSearchDetail;
    private String binarySearchDetail;
    private String bfsDetail;
    private String dfsDetail;

    /**
     * Creates an empty tracker with no recorded results yet.
     */
    public PerformanceTracker() {
        clear();
    }

    /**
     * Clears all stored performance results.
     */
    public void clear() {
        linearSearchSteps = null;
        binarySearchSteps = null;
        bfsSteps = null;
        dfsSteps = null;
        linearSearchDetail = null;
        binarySearchDetail = null;
        bfsDetail = null;
        dfsDetail = null;
    }

    /**
     * Records the latest linear search step count.
     *
     * @param steps  comparison steps taken
     * @param detail optional description (e.g. target / found status)
     */
    public void recordLinearSearch(int steps, String detail) {
        this.linearSearchSteps = steps;
        this.linearSearchDetail = detail;
    }

    /**
     * Records the latest binary search step count.
     *
     * @param steps  comparison steps taken
     * @param detail optional description
     */
    public void recordBinarySearch(int steps, String detail) {
        this.binarySearchSteps = steps;
        this.binarySearchDetail = detail;
    }

    /**
     * Records the latest BFS step/node count.
     *
     * @param steps  nodes visited
     * @param detail optional description (e.g. start vertex / order)
     */
    public void recordBfs(int steps, String detail) {
        this.bfsSteps = steps;
        this.bfsDetail = detail;
    }

    /**
     * Records the latest DFS step/node count.
     *
     * @param steps  nodes visited
     * @param detail optional description
     */
    public void recordDfs(int steps, String detail) {
        this.dfsSteps = steps;
        this.dfsDetail = detail;
    }

    /**
     * @return true if at least one result has been recorded
     */
    public boolean hasAnyResults() {
        return linearSearchSteps != null
                || binarySearchSteps != null
                || bfsSteps != null
                || dfsSteps != null;
    }

    /**
     * Prints the performance comparison table and a brief explanation of differences.
     */
    public void printComparisonTable() {
        System.out.println();
        System.out.println("=============================================");
        System.out.println(" PERFORMANCE COMPARISON");
        System.out.println("=============================================");
        System.out.printf("%-18s %-16s %s%n", "Operation", "Algorithm", "Steps");
        System.out.println("---------------------------------------------");
        System.out.printf("%-18s %-16s %s%n", "Search", "Linear Search", formatSteps(linearSearchSteps));
        System.out.printf("%-18s %-16s %s%n", "Search", "Binary Search", formatSteps(binarySearchSteps));
        System.out.printf("%-18s %-16s %s%n", "Graph Traversal", "BFS", formatSteps(bfsSteps));
        System.out.printf("%-18s %-16s %s%n", "Graph Traversal", "DFS", formatSteps(dfsSteps));
        System.out.println("=============================================");
        System.out.println(buildExplanation());
        System.out.println();
    }

    /**
     * Prints all recorded results with optional detail lines.
     */
    public void printAllResults() {
        System.out.println();
        System.out.println("--- Recorded Performance Results ---");
        if (!hasAnyResults()) {
            System.out.println("No performance data yet. Run searching and graph traversals first.");
            System.out.println();
            return;
        }
        if (linearSearchSteps != null) {
            System.out.println("Linear Search: " + linearSearchSteps + " steps"
                    + (linearSearchDetail != null ? " (" + linearSearchDetail + ")" : ""));
        } else {
            System.out.println("Linear Search: (not run yet)");
        }
        if (binarySearchSteps != null) {
            System.out.println("Binary Search: " + binarySearchSteps + " steps"
                    + (binarySearchDetail != null ? " (" + binarySearchDetail + ")" : ""));
        } else {
            System.out.println("Binary Search: (not run yet)");
        }
        if (bfsSteps != null) {
            System.out.println("BFS: " + bfsSteps + " steps"
                    + (bfsDetail != null ? " (" + bfsDetail + ")" : ""));
        } else {
            System.out.println("BFS: (not run yet)");
        }
        if (dfsSteps != null) {
            System.out.println("DFS: " + dfsSteps + " steps"
                    + (dfsDetail != null ? " (" + dfsDetail + ")" : ""));
        } else {
            System.out.println("DFS: (not run yet)");
        }
        System.out.println();
        printComparisonTable();
    }

    private String formatSteps(Integer steps) {
        return steps == null ? "N/A" : String.valueOf(steps);
    }

    private String buildExplanation() {
        if (linearSearchSteps != null && binarySearchSteps != null) {
            if (binarySearchSteps < linearSearchSteps) {
                return "Note: Binary search took fewer steps because it halves the search space "
                        + "each comparison, while linear search checks elements sequentially.";
            } else if (binarySearchSteps > linearSearchSteps) {
                return "Note: Linear search took fewer steps on this run (common for small arrays "
                        + "or early matches); binary search still has better O(log n) growth on large sorted data.";
            } else {
                return "Note: Both searches used the same number of steps on this data set; "
                        + "binary search still scales better as the sorted array grows.";
            }
        }
        if (bfsSteps != null && dfsSteps != null) {
            return "Note: BFS explores level by level while DFS goes deep along one path; "
                    + "step counts can match on the same graph size but visit order differs.";
        }
        return "Note: Run both search algorithms (and optionally BFS/DFS) to compare step counts. "
                + "Binary search typically needs fewer comparisons on sorted data because it halves "
                + "the search space each step, while linear search checks elements one by one.";
    }
}
