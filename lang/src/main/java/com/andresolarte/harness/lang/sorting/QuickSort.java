package com.andresolarte.harness.lang.sorting;

public class QuickSort  implements SortAlgorithm{

    @Override
    public int[] sort(int[] list) {
        partition(list,0, list.length-1);
        return list;
    }

    /* This function takes last element as pivot,
       places the pivot element at its correct
       position in sorted array, and places all
       smaller (smaller than pivot) to left of
       pivot and all greater elements to right
       of pivot */
    int partition(int arr[], int low, int high) {
        int pivot = findPivot(low, high);
        int i = (low - 1); // index of smaller element
        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than or
            // equal to pivot
            if (arr[j] <= pivot) {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    private int findPivot(int lowerIndex, int higherIndex) {
        return lowerIndex+(higherIndex-lowerIndex)/2;
    }


}