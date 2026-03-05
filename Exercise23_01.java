
/**
 * Exercise 23.1 - Insertion Sort and Bubble Sort
 *
 * Implement four generic sort methods:
 *   1. insertionSort using Comparable
 *   2. insertionSort using Comparator
 *   3. bubbleSort using Comparable
 *   4. bubbleSort using Comparator
 *
 * Then test all four methods in main().
 *
 * HINT - The only difference between the Comparable and Comparator versions
 * is how you compare two elements:
 *
 *   Comparable:  list[i].compareTo(list[i+1]) > 0
 *   Comparator:  comparator.compare(list[i], list[i+1]) > 0
 *
 * Everything else (the loops, the swaps, the logic) stays the same.
 */

import java.util.Arrays;
import java.util.Comparator;

public class Exercise23_01 {

    // ---------------------------------------------------------------
    // TODO 1: Implement insertionSort using Comparable
    //
    // Method signature:
    // public static <E extends Comparable<E>> void insertionSort(E[] list)
    //
    // Steps:
    // - Outer loop: i from 1 to list.length - 1
    // - Save list[i] as currentElement
    // - Inner while loop: shift elements right while they are greater
    // than currentElement (use compareTo)
    // - Place currentElement at list[k + 1]
    // ---------------------------------------------------------------

    public static <E extends Comparable<E>> void insertionSort(E[] list) {
        for (int i = 1; i < list.length; i++) {
            E currentElement = list[i]; // element to insert
            int k = i - 1;

            // Shift elements right until we find the right spot
            while (k >= 0 && list[k].compareTo(currentElement) > 0) {
                list[k + 1] = list[k]; // shift right
                k--;
            }

            // Insert the element into its correct position
            list[k + 1] = currentElement;
        }
    }

    // ---------------------------------------------------------------
    // TODO 2: Implement insertionSort using Comparator
    //
    // Method signature:
    // public static <E> void insertionSort(E[] list, Comparator<? super E>
    // comparator)
    //
    // Same logic as TODO 1, but replace compareTo() with comparator.compare()
    // ---------------------------------------------------------------
    public static <E> void insertionSort(E[] list, Comparator<? super E> comparator) {
        for (int i = 1; i < list.length; i++) {
            E currentElement = list[i];  // element to insert
            int k = i - 1;

            // Shift elements right until we find the right spot
            //int compare = comparator.compare(list[k], (currentElement));
            while (k >= 0 && comparator.compare(list[k], (currentElement)) > 0 ) {
                list[k + 1] = list[k];  // shift right
                k--;
            }

            // Insert the element into its correct position
            list[k + 1] = currentElement;
        }
    }

    // ---------------------------------------------------------------
    // TODO 3: Implement bubbleSort using Comparable
    //
    // Method signature:
    // public static <E extends Comparable<E>> void bubbleSort(E[] list)
    //
    // Steps:
    // - Use a boolean needNextPass, start true
    // - Outer loop: k from 1 to list.length - 1, stop if !needNextPass
    // - Set needNextPass = false at start of each pass
    // - Inner loop: i from 0 to list.length - k - 1
    // - If list[i].compareTo(list[i+1]) > 0, swap and set needNextPass = true
    // ---------------------------------------------------------------
    public static <E extends Comparable<E>> void bubbleSort(E[] list, Comparator<? super E> comparator) {
        boolean needNextPass = true;

        for (int k = 1; k < list.length && needNextPass; k++) {
            needNextPass = false;  // assume sorted until a swap occurs

            for (int i = 0; i < list.length - k; i++) {
                int compare = comparator.compare(list[i], (list[i + 1]));
                if (compare > 0) {
                    // Swap adjacent elements
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;

                    needNextPass = true;  // a swap occurred, keep going
                }
            }
        }
    }
    // ---------------------------------------------------------------
    // TODO 4: Implement bubbleSort using Comparator
    //
    // Method signature:
    // public static <E> void bubbleSort(E[] list, Comparator<? super E> comparator)
    //
    // Same logic as TODO 3, but replace compareTo() with comparator.compare()
    // ---------------------------------------------------------------

    public static <E extends Comparable<E>> void bubbleSort(E[] list) {
        boolean needNextPass = true;

        for (int k = 1; k < list.length && needNextPass; k++) {
            needNextPass = false;  // assume sorted until a swap occurs

            for (int i = 0; i < list.length - k; i++) {
                int compare = list[i].compareTo(list[i + 1]);
                if (compare > 0) {
                    // Swap adjacent elements
                    E temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;

                    needNextPass = true;  // a swap occurred, keep going
                }
            }
        }
    }

    public static void main(String[] args) {

        // --- Test 1: insertionSort with Comparable ---
        // Sort String array alphabetically (String implements Comparable)
        String[] names1 = { "Maria", "Alex", "Jordan", "Beth", "Chris" };
        System.out.println("Before insertion sort (Comparable): " + Arrays.toString(names1));
        insertionSort(names1);
        System.out.println("After  insertion sort (Comparable): " + Arrays.toString(names1));
        System.out.println();

        // --- Test 2: insertionSort with Comparator ---
        // Sort String array by LENGTH (shortest to longest)
        String[] names2 = { "Maria", "Al", "Jordan", "Beth", "Christopher" };
        Comparator<String> byLength = Comparator.comparingInt(String::length);
        System.out.println("Before insertion sort (Comparator - by length): " + Arrays.toString(names2));
        insertionSort(names2, byLength);
        System.out.println("After  insertion sort (Comparator - by length): " + Arrays.toString(names2));
        System.out.println();

        // --- Test 3: bubbleSort with Comparable ---
        Integer[] numbers1 = { 64, 34, 25, 12, 22, 11, 90 };
        System.out.println("Before bubble sort (Comparable): " + Arrays.toString(numbers1));
        bubbleSort(numbers1);
        System.out.println("After  bubble sort (Comparable): " + Arrays.toString(numbers1));
        System.out.println();

        // --- Test 4: bubbleSort with Comparator ---
        // Sort integers in REVERSE order (largest to smallest)
        Integer[] numbers2 = { 64, 34, 25, 12, 22, 11, 90 };
        Comparator<Integer> reverseOrder = Comparator.reverseOrder();
        System.out.println("Before bubble sort (Comparator - reverse): " + Arrays.toString(numbers2));
        bubbleSort(numbers2, reverseOrder);
        System.out.println("After  bubble sort (Comparator - reverse): " + Arrays.toString(numbers2));

        // --- Test 5: Already sorted (tests the needNextPass optimization) ---
        System.out.println();
        Integer[] sorted = { 1, 2, 3, 4, 5 };
        System.out.println("Already sorted array: " + Arrays.toString(sorted));
        bubbleSort(sorted);
        System.out.println("After bubble sort:    " + Arrays.toString(sorted));
        System.out.println("(Should exit after 1 pass with no swaps)");
    }
}
