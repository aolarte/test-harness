package com.andresolarte.harness.lang.sorting;

public class SelectionSort implements SortAlgorithm {


    @Override
    public int[] sort(int[] list) {

        int n = list.length;

        int i;
        int j;
        int indexMinimumElement = 0;

        for (j = 0; j < n - 1; j++) {
            indexMinimumElement = j;
            for (i = j; i < n; i++) {
                if (list[i] < list[indexMinimumElement]) {
                    indexMinimumElement = i;
                }
            }
            SortingUtils.swap(list, j, indexMinimumElement);
        }

        if (indexMinimumElement != j) {
            SortingUtils.swap(list, j, indexMinimumElement);
        }

        return list;
    }


}
