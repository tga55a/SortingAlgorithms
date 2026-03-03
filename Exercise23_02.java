/**
 * Exercise 23.2 - Merge Sort
 *
 * Implement two generic merge sort methods:
 *   1. mergeSort using Comparable
 *   2. mergeSort using Comparator
 *
 * Each mergeSort method needs a corresponding merge helper method.
 *
 * HINT 1 - Generic array creation:
 *   You cannot write: E[] firstHalf = new E[size];
 *   Instead write:    E[] firstHalf = (E[]) new Object[size];
 *
 * HINT 2 - The only comparison change (in your merge method):
 *   int version:        list1[c1] < list2[c2]
 *   Comparable version: list1[c1].compareTo(list2[c2]) < 0
 *   Comparator version: comparator.compare(list1[c1], list2[c2]) < 0
 *
 * HINT 3 - Comparator version:
 *   The comparator must be passed through to the recursive mergeSort calls
 *   AND to the merge helper method.
 */

import java.util.*;
import java.util.Arrays;
import java.util.Comparator;
import java.lang.reflect.Array;

public class Exercise23_02 {

    // ---------------------------------------------------------------
    // TODO 1: Implement mergeSort using Comparable
    //
    // Method signature:
    //   public static <E extends Comparable<E>> void mergeSort(E[] list)
    //
    // Steps:
    //   - Base case: if list.length <= 1, return (already sorted)
    //   - Create firstHalf: (E[]) new Object[list.length / 2]
    //   - Copy first half from list using System.arraycopy
    //   - Create secondHalf: (E[]) new Object[list.length - list.length / 2]
    //   - Copy second half from list using System.arraycopy
    //   - Recursively call mergeSort on firstHalf and secondHalf
    //   - Call merge(firstHalf, secondHalf, list)
    // ---------------------------------------------------------------
    public static <E extends Comparable<E>> void mergeSort(E[] list) {

        if (list.length > 1) {                                    // line A
            @SuppressWarnings("unchecked")
            E[] firstHalf = (E[]) Array.newInstance(list.getClass().getComponentType(), list.length / 2);          // line B
            System.arraycopy(list, 0, firstHalf, 0,               // line C
                            list.length / 2);

            int secondHalfLength = list.length - list.length / 2; // line D
            @SuppressWarnings("unchecked")
            E[] secondHalf =(E[]) Array.newInstance(list.getClass().getComponentType(), secondHalfLength);          // line E
            System.arraycopy(list, list.length / 2,               // line F
                            secondHalf, 0, secondHalfLength);

            mergeSort(firstHalf);                                  // line G
            mergeSort(secondHalf);                                 // line H

            merge(firstHalf, secondHalf, list);                   // line I
        }
    }

    // ---------------------------------------------------------------
    // TODO 2: Implement merge helper for Comparable version
    //
    // Method signature:
    //   private static <E extends Comparable<E>>
    //   void merge(E[] list1, E[] list2, E[] temp)
    //
    // Steps:
    //   - Three pointers: current1=0, current2=0, current3=0
    //   - While both lists have elements:
    //       if list1[current1].compareTo(list2[current2]) < 0
    //           take from list1
    //       else
    //           take from list2
    //   - Copy any remaining elements from list1
    //   - Copy any remaining elements from list2
    // ---------------------------------------------------------------
    private static <E extends Comparable<E>> void merge(E[] list1, E[] list2, E[] temp) {
        int current1 = 0;   // pointer into list1             // line J
        int current2 = 0;   // pointer into list2             // line K
        int current3 = 0;   // pointer into temp (result)     // line L

        while (current1 < list1.length && current2 < list2.length) { // line M

            if (list1[current1].compareTo(list2[current2]) > 0)             // line N
                temp[current3++] = list1[current1++];          // line O
            else
                temp[current3++] = list2[current2++];          // line P
        }

        while (current1 < list1.length)                        // line Q
            temp[current3++] = list1[current1++];

        while (current2 < list2.length)                        // line R
            temp[current3++] = list2[current2++];
    }

    // ---------------------------------------------------------------
    // TODO 3: Implement mergeSort using Comparator
    //
    // Method signature:
    //   public static <E> void mergeSort(E[] list, Comparator<? super E> comparator)
    //
    // Same structure as TODO 1, but:
    //   - Pass comparator to both recursive mergeSort calls
    //   - Pass comparator to the merge call
    // ---------------------------------------------------------------

    public static <E> void mergeSort(E[] list, Comparator<? super E> comparator) {

        if (list.length > 1) {                                    // line A
            @SuppressWarnings("unchecked")
            E[] firstHalf = (E[]) Array.newInstance(list.getClass().getComponentType(), list.length / 2);          // line B
            System.arraycopy(list, 0, firstHalf, 0,               // line C
                            list.length / 2);

            int secondHalfLength = list.length - list.length / 2; // line D
            @SuppressWarnings("unchecked")
            E[] secondHalf = (E[]) Array.newInstance(list.getClass().getComponentType(), secondHalfLength);          // line E
            System.arraycopy(list, list.length / 2,               // line F
                            secondHalf, 0, secondHalfLength);

            mergeSort(firstHalf, comparator);                                  // line G
            mergeSort(secondHalf, comparator);                                 // line H
            merge(firstHalf, secondHalf, list, comparator);        // line I
        }
    }
    // ---------------------------------------------------------------
    // TODO 4: Implement merge helper for Comparator version
    //
    // Method signature:
    //   private static <E>
    //   void merge(E[] list1, E[] list2, E[] temp, Comparator<? super E> comparator)
    //
    // Same structure as TODO 2, but use:
    //   comparator.compare(list1[current1], list2[current2]) < 0
    // ---------------------------------------------------------------
    private static <E> void merge(E[] firstHalf, E[] secondHalf, E[] list, Comparator<? super E> comparator) {
        int current1 = 0;   // pointer into list1             // line J
        int current2 = 0;   // pointer into list2             // line K
        int current3 = 0;   // pointer into temp (result)     // line L

        while (current1 < firstHalf.length && current2 < secondHalf.length) { // line M

            if (comparator.compare(firstHalf[current1], secondHalf[current2]) < 0)             // line N
                list[current3++] = firstHalf[current1++];          // line O
            else
                list[current3++] = secondHalf[current2++];          // line P
        }

        while (current1 < firstHalf.length)                        // line Q
            list[current3++] = firstHalf[current1++];

        while (current2 < secondHalf.length)                        // line R
            list[current3++] = secondHalf[current2++];
    }

    public static void main(String[] args) {

        // --- Test 1: mergeSort with Comparable ---
        String[] names1 = {"Maria", "Alex", "Jordan", "Beth", "Chris"};
        System.out.println("Before merge sort (Comparable): " + Arrays.toString(names1));
        mergeSort(names1);
        System.out.println("After  merge sort (Comparable): " + Arrays.toString(names1));
        System.out.println();

        // --- Test 2: mergeSort with Comparator (by length) ---
        String[] names2 = {"Maria", "Al", "Jordan", "Beth", "Christopher"};
        Comparator<String> byLength = Comparator.comparingInt(String::length);
        System.out.println("Before merge sort (Comparator - by length): " + Arrays.toString(names2));
        mergeSort(names2, byLength);
        System.out.println("After  merge sort (Comparator - by length): " + Arrays.toString(names2));
        System.out.println();

        // --- Test 3: larger Integer array ---
        Integer[] numbers = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("Before merge sort (Comparable): " + Arrays.toString(numbers));
        mergeSort(numbers);
        System.out.println("After  merge sort (Comparable): " + Arrays.toString(numbers));
        System.out.println();

        // --- Test 4: reverse order with Comparator ---
        Integer[] numbers2 = {38, 27, 43, 3, 9, 82, 10};
        Comparator<Integer> reverseOrder = Comparator.reverseOrder();
        System.out.println("Before merge sort (Comparator - reverse): " + Arrays.toString(numbers2));
        mergeSort(numbers2, reverseOrder);
        System.out.println("After  merge sort (Comparator - reverse): " + Arrays.toString(numbers2));
        System.out.println();

        // --- Test 5: single element (base case) ---
        Integer[] single = {42};
        System.out.println("Single element: " + Arrays.toString(single));
        mergeSort(single);
        System.out.println("After merge sort: " + Arrays.toString(single));
    }
}
