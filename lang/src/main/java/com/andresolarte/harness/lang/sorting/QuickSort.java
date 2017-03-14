package com.andresolarte.harness.lang.sorting;

public class QuickSort  implements SortAlgorithm{

    @Override
    public int[] sort(int[] list) {
        //quickSort(list,0, list.length-1);
        return list;
    }

//    int partition(int arr[], int left, int right)
//    {
//        int i = left;
//        int j = right;
//        int pivot = arr[findPivot(left,right)];
//
//        while (i <= j) {
//            while (arr[i] < pivot)
//                i++;
//            while (arr[j] > pivot)
//                j--;
//            if (i <= j) {
//                SortingUtils.swap(arr,i,j);
//                i++;
//                j--;
//            }
//        };
//
//        return i;
//    }
//
//    void quickSort(int arr[], int left, int right) {
//        //System.out.println("Sorting from " + left + " to " + right + " out of " + (arr.length-1));
//        System.out.println("Current: " + SortingUtils.formatArray(arr));
//        int index = partition(arr, left, right);
//        if (left < index - 1)
//            quickSort(arr, left, index - 1);
//        if (index < right)
//            quickSort(arr, index, right);
//    }
//
//    private int findPivot(int lowerIndex, int higherIndex) {
//        int pivot = lowerIndex+(higherIndex-lowerIndex)/2;
//        //System.out.println("Pivot from " + lowerIndex + " to " + higherIndex + " => "+  pivot);
//        return pivot;
//    }


}