# Data Structure and Graph Performance Analyzer

A Java console application for a Data Structures and Algorithms course. It demonstrates manually implemented array, stack, queue, and linked list operations, linear and binary search with step counting, adjacency-list graph traversals (BFS/DFS), and a performance comparison module.

## Technologies Used

- Java (JDK) — console application
- Standard I/O (`Scanner`) for interactive menus
- Manual implementations of core structures (no `java.util.Stack`, `Queue`, `LinkedList`, or `ArrayList` for those modules)
- `java.util.HashMap` used only for the graph adjacency list

## Main System Features

1. **Array Operations** — insert, delete (by value or index), linear search, display
2. **Stack Operations** — push, pop, peek, display, isEmpty (array-based LIFO)
3. **Queue Operations** — enqueue, dequeue, peek/front, display, isEmpty (circular FIFO)
4. **Linked List Operations** — insert, delete, search, display (singly linked)
5. **Searching Operations** — linear search and binary search with comparison step counts
6. **Graph Operations** — add vertex/edge, display, BFS and DFS with step/node counts
7. **Performance Comparison** — tabular comparison of search and traversal step counts

## Project Structure

```
src/main/
  Main.java
  datastructures/
    ArrayOperations.java
    CustomStack.java
    CustomQueue.java
    CustomLinkedList.java
    CustomGraph.java
  search/
    SearchAlgorithms.java
  performance/
    PerformanceTracker.java
  util/
    InputValidator.java
```

## Compile and Run

From the project root (`CIT_300`):

```bash
javac -d bin -sourcepath src/main src/main/util/*.java src/main/datastructures/*.java src/main/search/*.java src/main/performance/*.java src/main/Main.java
java -cp bin Main
```

On Windows PowerShell:

```powershell
javac -d bin -sourcepath src/main src/main/util/*.java src/main/datastructures/*.java src/main/search/*.java src/main/performance/*.java src/main/Main.java
java -cp bin Main
```

## Team Member Names

- Member 1: AM. Fathima Rusna
- Member 2: AM. Fathima Jesira
- Member 3: 
- Member 4: AM. Fathima Hanoof

## Student IDs

- Member 1: 23DA2-0492
- Member 2: 23da2-0653
- Member 3: 
- Member 4: 23DA2-0530

## Assigned Responsibilities

- Member 1: Array and Searching implementation and integration and testing.
- Member 2: Stack and Queue implementation, testing and integration.
- Member 3: 
- Member 4: Graph implementation and traversal, Performance comparison, Main integration.

## Individual Contributions

- Member 1:  Implemented ArrayOperations: insert, delete, search, display, with full/empty/invalid-index handling and Implemented linear search (O(n)) and binary search (O(log n)) with step counting, Implemented a bubble-sort helper so binary search runs on sorted data and Tested and integrated array.
- Member 2: implemented Stack and Queue operations such as push, pop, peek, enqueue, dequeue, isFull and isEmpty. Handled overflow/underflow conditions, tested all operations and integrated Stack and Queue components into the main application. 
- Member 3: 
- Member 4: Implemented CustomGraph (adjacency list) with add vertex, add edge and display, Implemented BFS    Using CustomQueue and DFS using CustomStack, Implemented PerformanceTracker, Integrated all components into the menu-driven Main application, Led the project.
