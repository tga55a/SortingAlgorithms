
/**
 * Exercise 23.4 - Improve Quick Sort
 *
 * The quick sort below uses the first element as the pivot.
 * This causes O(n²) performance on already-sorted arrays.
 *
 * Your job: modify the partition method to use median-of-three
 * pivot selection instead of always using list[first].
 *
 * WHAT TO CHANGE:
 *   Only modify the partition method.
 *   quickSort does not need to change.
 *
 * MEDIAN-OF-THREE:
 *   Look at list[first], list[mid], and list[last]
 *   where mid = (first + last) / 2
 *   Use the median of those three values as the pivot.
 *
 * HINT - Finding the median of three values a, b, c:
 *   if      (a < b && b < c) || (c < b && b < a)  → median is b
 *   else if (b < a && a < c) || (c < a && a < b)  → median is a
 *   else                                           → median is c
 *
 * HINT - Once you find the median value, swap that element to
 *   list[first] so the rest of the partition logic works unchanged.
 *
 * EDGE CASE:
 *   If last - first < 2, fall back to list[first] as pivot
 *   (not enough elements for median-of-three).
 */

import java.util.Arrays;

public class Exercise23_04 {

    public static void quickSort(int[] list) {
        quickSort(list, 0, list.length - 1);
    }

    private static void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    public static void medianOfThree(int[] list, int low, int high) {
        if (high - low < 2) return;
        int middle = (low + high) / 2;

        // Find the median of first, middle, last
        if ((list[low] < list[middle] && list[middle] < list[high]) || (list[high] < list[middle] && list[middle] < list[low])) {
            int temp = list[middle];
            list[middle] = list[low];
            list[low] = temp;
        } else if ((list[middle] < list[low] && list[low] < list[high]) || (list[high]) < list[low] && list[low] < list[middle]) {
            return;
        } else {
            int temp = list[high];
            list[high] = list[low];
            list[low] = temp;
        }
    }

    // ---------------------------------------------------------------
    // TODO: Modify this method to use median-of-three pivot selection.
    //
    // Step 1: If last - first < 2, skip median-of-three (not enough elements)
    //
    // Step 2: Find the median of list[first], list[mid], list[last]
    // where mid = (first + last) / 2
    //
    // Step 3: Swap the median element to list[first]
    // so the rest of the partition logic below works unchanged.
    //
    // The code below this comment does NOT need to change.
    // ---------------------------------------------------------------
    private static int partition(int[] list, int first, int last) {

        // TODO: Add median-of-three pivot selection here
        medianOfThree(list, first, last);
        // (swap the median element to list[first], then the code below runs normally)

        int pivot = list[first];
        int low = first + 1;
        int high = last;

        while (high > low) {
            while (low <= high && list[low] <= pivot)
                low++;
            while (low <= high && list[high] > pivot)
                high--;
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        if (list[high] < pivot) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }
    }

    public static void main(String[] args) {

        // --- Test 1: Random order ---
        int[] list1 = { 5, 2, 9, 3, 8, 4, 0, 1, 6, 7 };
        System.out.println("Before (random):  " + Arrays.toString(list1));
        quickSort(list1);
        System.out.println("After  (random):  " + Arrays.toString(list1));
        System.out.println();

        // --- Test 2: Already sorted ---
        // With first-element pivot, this causes O(n²)
        // With median-of-three, this should sort correctly and efficiently
        int[] list2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        System.out.println("Before (sorted):  " + Arrays.toString(list2));
        quickSort(list2);
        System.out.println("After  (sorted):  " + Arrays.toString(list2));
        System.out.println();

        // --- Test 3: Reverse sorted ---
        int[] list3 = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
        System.out.println("Before (reverse): " + Arrays.toString(list3));
        quickSort(list3);
        System.out.println("After  (reverse): " + Arrays.toString(list3));
        System.out.println();

        // --- Test 4: All duplicates ---
        int[] list4 = { 5, 5, 5, 5, 5 };
        System.out.println("Before (dupes):   " + Arrays.toString(list4));
        quickSort(list4);
        System.out.println("After  (dupes):   " + Arrays.toString(list4));
        System.out.println();

        // --- Test 5: Two elements ---
        int[] list5 = { 9, 1 };
        System.out.println("Before (two):     " + Arrays.toString(list5));
        quickSort(list5);
        System.out.println("After  (two):     " + Arrays.toString(list5));
    }
}
