package search;

/**
 * Implements custom logic for binary search.
 *
 * @author liashevich
 */
public class CustomBinarySearch {

    /**
     * @param array   array to search in
     * @param element element to search
     * @param <T>     class of array elements
     * @return index of element in array. If not found return -1.
     */
    public static <T extends Comparable<T>> int search(T[] array, T element) {
        int left = 0;
        int right = array.length - 1;
        while (left <= right) {
            int index = (left + right) / 2;
            T candidate = array[index];
            if (candidate.compareTo(element) == 0) {
                return index;
            } else if (candidate.compareTo(element) > 0) {
                right = index - 1;
            } else if (candidate.compareTo(element) < 0) {
                left = index + 1;
            }
        }
        return -1;

    }
}
