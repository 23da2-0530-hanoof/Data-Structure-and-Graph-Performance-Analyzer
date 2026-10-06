package datastructures;

import java.util.HashMap;
import java.util.Map;

/**
 * Undirected graph using an adjacency-list representation.
 * Vertices map to neighbor lists stored in {@link CustomLinkedList}.
 * BFS uses {@link CustomQueue}; DFS uses {@link CustomStack}.
 * Does not use java.util.ArrayList, Stack, Queue, or LinkedList.
 */
public class CustomGraph {

    private final Map<String, CustomLinkedList> adjacency;
    private final CustomLinkedList vertices;

    /**
     * Result of a graph traversal: ordered visit sequence and step/node count.
     */
    public static class TraversalResult {
        private final String order;
        private final int steps;

        /**
         * @param order space-separated visitation order
         * @param steps number of nodes visited / steps taken
         */
        public TraversalResult(String order, int steps) {
            this.order = order;
            this.steps = steps;
        }

        /**
         * @return traversal order as a string
         */
        public String getOrder() {
            return order;
        }

        /**
         * @return number of steps (nodes processed)
         */
        public int getSteps() {
            return steps;
        }
    }

    /**
     * Creates an empty graph.
     */
    public CustomGraph() {
        this.adjacency = new HashMap<>();
        this.vertices = new CustomLinkedList();
    }

    /**
     * Adds a vertex if it does not already exist.
     *
     * @param v vertex label
     * @return true if added, false if duplicate or invalid
     */
    public boolean addVertex(String v) {
        if (v == null || v.trim().isEmpty()) {
            System.out.println("Cannot add vertex: label is empty.");
            return false;
        }
        String label = v.trim();
        if (adjacency.containsKey(label)) {
            System.out.println("Cannot add vertex: \"" + label + "\" already exists.");
            return false;
        }
        adjacency.put(label, new CustomLinkedList());
        vertices.insertUnique(label);
        System.out.println("Added vertex \"" + label + "\".");
        return true;
    }

    /**
     * Adds an undirected edge between two existing vertices.
     * Rejects edges that reference a missing vertex.
     *
     * @param v1 first vertex
     * @param v2 second vertex
     * @return true if the edge was added
     */
    public boolean addEdge(String v1, String v2) {
        if (v1 == null || v2 == null) {
            System.out.println("Cannot add edge: vertex labels cannot be null.");
            return false;
        }
        String a = v1.trim();
        String b = v2.trim();
        if (!adjacency.containsKey(a)) {
            System.out.println("Cannot add edge: vertex \"" + a + "\" does not exist.");
            return false;
        }
        if (!adjacency.containsKey(b)) {
            System.out.println("Cannot add edge: vertex \"" + b + "\" does not exist.");
            return false;
        }
        if (a.equals(b)) {
            System.out.println("Cannot add edge: self-loops are not allowed.");
            return false;
        }
        CustomLinkedList neighborsA = adjacency.get(a);
        CustomLinkedList neighborsB = adjacency.get(b);
        if (neighborsA.contains(b)) {
            System.out.println("Edge already exists between \"" + a + "\" and \"" + b + "\".");
            return false;
        }
        neighborsA.insertUnique(b);
        neighborsB.insertUnique(a);
        System.out.println("Added edge \"" + a + "\" -- \"" + b + "\".");
        return true;
    }

    /**
     * Displays all vertices and their adjacency lists.
     */
    public void displayGraph() {
        if (adjacency.isEmpty()) {
            System.out.println("Graph is empty.");
            return;
        }
        System.out.println("Graph adjacency list:");
        String[] verts = vertices.toArray();
        for (String v : verts) {
            System.out.print("  " + v + " -> ");
            CustomLinkedList neighbors = adjacency.get(v);
            if (neighbors == null || neighbors.isEmpty()) {
                System.out.println("(none)");
            } else {
                String[] n = neighbors.toArray();
                for (int i = 0; i < n.length; i++) {
                    System.out.print(n[i]);
                    if (i < n.length - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        }
    }

    /**
     * Breadth-first traversal starting from the given vertex.
     * Uses {@link CustomQueue} for the frontier.
     *
     * @param startVertex starting vertex label
     * @return TraversalResult with order and step count, or null on error
     */
    public TraversalResult bfs(String startVertex) {
        if (startVertex == null || !adjacency.containsKey(startVertex.trim())) {
            System.out.println("BFS failed: start vertex does not exist.");
            return null;
        }
        String start = startVertex.trim();
        Map<String, Boolean> visited = new HashMap<>();
        CustomQueue queue = new CustomQueue(Math.max(adjacency.size(), 1));
        StringBuilder order = new StringBuilder();
        int steps = 0;

        queue.enqueueSilent(start);
        visited.put(start, true);

        while (!queue.isEmpty()) {
            String current = queue.dequeueSilent();
            if (current == null) {
                break;
            }
            steps++;
            if (order.length() > 0) {
                order.append(" -> ");
            }
            order.append(current);

            CustomLinkedList neighbors = adjacency.get(current);
            if (neighbors != null) {
                for (String neighbor : neighbors.toArray()) {
                    if (!Boolean.TRUE.equals(visited.get(neighbor))) {
                        visited.put(neighbor, true);
                        queue.enqueueSilent(neighbor);
                    }
                }
            }
        }

        System.out.println("BFS order: " + order);
        System.out.println("BFS steps (nodes visited): " + steps);
        return new TraversalResult(order.toString(), steps);
    }

    /**
     * Depth-first traversal starting from the given vertex.
     * Uses {@link CustomStack} for the frontier.
     *
     * @param startVertex starting vertex label
     * @return TraversalResult with order and step count, or null on error
     */
    public TraversalResult dfs(String startVertex) {
        if (startVertex == null || !adjacency.containsKey(startVertex.trim())) {
            System.out.println("DFS failed: start vertex does not exist.");
            return null;
        }
        String start = startVertex.trim();
        Map<String, Boolean> visited = new HashMap<>();
        CustomStack stack = new CustomStack(Math.max(adjacency.size(), 1));
        StringBuilder order = new StringBuilder();
        int steps = 0;

        stack.pushSilent(start);

        while (!stack.isEmpty()) {
            String current = stack.popSilent();
            if (current == null || Boolean.TRUE.equals(visited.get(current))) {
                continue;
            }
            visited.put(current, true);
            steps++;
            if (order.length() > 0) {
                order.append(" -> ");
            }
            order.append(current);

            CustomLinkedList neighbors = adjacency.get(current);
            if (neighbors != null) {
                String[] n = neighbors.toArray();
                // Push in reverse so the first neighbor is processed first (stack LIFO)
                for (int i = n.length - 1; i >= 0; i--) {
                    if (!Boolean.TRUE.equals(visited.get(n[i]))) {
                        stack.pushSilent(n[i]);
                    }
                }
            }
        }

        System.out.println("DFS order: " + order);
        System.out.println("DFS steps (nodes visited): " + steps);
        return new TraversalResult(order.toString(), steps);
    }

    /**
     * @return true if the graph has no vertices
     */
    public boolean isEmpty() {
        return adjacency.isEmpty();
    }

    /**
     * @return number of vertices
     */
    public int vertexCount() {
        return adjacency.size();
    }
}
