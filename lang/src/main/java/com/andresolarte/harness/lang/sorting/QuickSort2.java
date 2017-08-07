package com.andresolarte.harness.lang.sorting;

public class QuickSort2 implements SortAlgorithm {
    @Override
    public int[] sort(int[] list) {
        // check for empty or null array
        if (list ==null || list.length==0){
            return null;
        }

        int length  = list.length;
        quicksort(list, 0, length - 1);
        return list;
    }

    private void quicksort(int[] list, int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        //int pivot = list[low + (high-low)/2];

        int pivot = list[high];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller than the pivot
            // element then get the next element from the left list
            while (list[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger than the pivot
            // element then get the next element from the right list
            while (list[j] > pivot) {
                j--;
            }

            // If we have found a value in the left list which is larger than
            // the pivot element and if we have found a value in the right list
            // which is smaller than the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                if (i<j) {
                    SortingUtils.swap(list, i, j);
                }
                i++;
                j--;
            }
        }

        // Recursion
        if (low < j) {
            quicksort(list, low, j);
        }
        if (i < high) {
            quicksort(list,i, high);
        }

    }
}
