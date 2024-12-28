package Algorithm.Concept.NotLink;

import java.util.List;

public class Array {

    // no direct conversion from List<Integer> to int[]
    public int[] arrayListToStaticPrimitiveArray(List<Integer> result) {
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // convert any dynamic array to static array
    // alternatively al.toArray(new Integer[0])
    public Integer[] arrayListToStaticArray(List<Integer> result) {
        return result.toArray(Integer[]::new);
    }

    // binary search, return index of found, or -1 if not found
    public static int basicBinarySearch(int[] sortedArray, int target) {
        // assume array is sorted
        int high = sortedArray.length - 1;
        int low = 0;
        while (low <= high) {
            // (high - low) / 2 + low; Avoid overflow
            int mid = (low + high) >>> 1;
            if (sortedArray[mid] == target) {
                return mid;
            } else {
                if (sortedArray[mid] < target) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    // selection sort, move smallest to the LHS every pass
    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = array[min];
                array[min] = array[i];
                array[i] = temp;
            }
        }
    }

    // bubble sort, float the largest element to RHS
    public static void bubbleSort(int[] array) {

    }

    // binary search

    // insertion sort

    // First element occurring k times in an array (hashmap)

    // Nearly sorted array (heap)

    // dutch flag problem (3 pointer)

    // Min number of swaps
    // 2 pointer 2 pass count 1s and 0s

    // Assign difficulty questions (candy)
    // B[i + 1] > B[i] if A[i + 1] > A[i]
    // 2 pass

    // rotate array
    // rotate array in k c hunks
    // rotate k in place

    // longest palindromic substring
}

