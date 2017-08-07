package com.andresolarte.harness.lang.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SortingTest {

    public static void main(String... args) {
        int[] toSort = createArray();
        System.out.println("Original: " + SortingUtils.formatArray(toSort));
        runSorting("SelectionSort", new SelectionSort());
        runSorting("InsertionSort", new InsertionSort());
        runSorting("QuickSort", new QuickSort());
        runSorting("QuickSort2", new QuickSort2());

    }

    private static void runSorting(String name, SortAlgorithm sortAlgorithm) {

        int[] toSort = createArray();
        String result = SortingUtils.formatArray(sortAlgorithm.sort(toSort));
        System.out.println(name + "Result: " + result);
    }

    private static int[] createArray() {
        return new int[]{1, 12, 5, 26, 7, 14, 3, 7, 2};
    }


}
