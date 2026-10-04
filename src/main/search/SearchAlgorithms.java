package search;

/**
 * Linear and binary search algorithms that report both the found index
 * and the number of comparison steps taken for performance comparison.
 */
public class SearchAlgorithms {

    /**
     * Immutable result of a search: index found (-1 if absent) and comparison step count.
     */
    public static class SearchResult {
        private final int index;
        private final int steps;

        /**
         * @param index index of the target, or -1 if not found
         * @param steps number of comparisons performed
         */
        public SearchResult(int index, int steps) {
            this.index = index;
            this.steps = steps;
        }

        /**
         * @return index of the target, or -1 if not found
         */
        public int getIndex() {
            return index;
        }

        /**
         * @return number of comparison steps taken
         */
        public int getSteps() {
            return steps;
        }

        /**
         * @return true if the target was found
         */
        public boolean isFound() {
            return index >= 0;
        }
    }

    /**
     * Linear search: checks each element sequentially until the target is found or the array ends.
     *
     * @param array  array to search (may be unsorted)
     * @param target value to find
     * @return SearchResult with index and comparison step count
     */
    public static SearchResult linearSearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            System.out.println("Cannot perform linear search: array is empty or null.");
            return new SearchResult(-1, 0);
        }
        int steps = 0;
        for (int i = 0; i < array.length; i++) {
            steps++;
            if (array[i] == target) {
                return new SearchResult(i, steps);
            }
        }
        return new SearchResult(-1, steps);
    }

    /**
     * Binary search on a sorted array. Halves the search space each comparison.
     * The caller must provide a sorted array; this method does not sort the input.
     *
     * @param sortedArray array that must already be sorted in ascending order
     * @param target      value to find
     * @return SearchResult with index and comparison step count
     */
    public static SearchResult binarySearch(int[] sortedArray, int target) {
        if (sortedArray == null || sortedArray.length == 0) {
            System.out.println("Cannot perform binary search: array is empty or null.");
            return new SearchResult(-1, 0);
        }
        int steps = 0;
        int low = 0;
        int high = sortedArray.length - 1;
        while (low <= high) {
            steps++;
            int mid = low + (high - low) / 2;
            if (sortedArray[mid] == target) {
                return new SearchResult(mid, steps);
            } else if (sortedArray[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new SearchResult(-1, steps);
    }

    /**
     * Returns a new ascending-sorted copy of the given array (manual bubble sort).
     * Used so binary search can run without mutating the original data.
     *
     * @param array source array
     * @return sorted copy, or empty array if input is null/empty
     */
    public static int[] sortedCopy(int[] array) {
        if (array == null || array.length == 0) {
            return new int[0];
        }
        int[] copy = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            copy[i] = array[i];
        }
        for (int i = 0; i < copy.length - 1; i++) {
            for (int j = 0; j < copy.length - 1 - i; j++) {
                if (copy[j] > copy[j + 1]) {
                    int temp = copy[j];
                    copy[j] = copy[j + 1];
                    copy[j + 1] = temp;
                }
            }
        }
        return copy;
    }

    /**
     * Formats an array for console display.
     *
     * @param array array to format
     * @return human-readable string representation
     */
    public static String arrayToString(int[] array) {
        if (array == null || array.length == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
