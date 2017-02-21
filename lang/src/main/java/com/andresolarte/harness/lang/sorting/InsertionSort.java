package com.andresolarte.harness.lang.sorting;

public class InsertionSort implements SortAlgorithm {


    @Override
    public int[] sort(int[] list) {
        for (int i = 0; i < list.length; i++) {
            int j = i;
            while (j > 0 && list[j - 1] > list[j]) {
                SortingUtils.swap(list, j, j - 1);
                j--;
            }

        }
        return list;
    }

}
