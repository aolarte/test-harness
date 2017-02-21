package com.andresolarte.harness.lang.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SortingTest {

    public static void main(String... args) {
        int[] toSort = new int[]{4, 2, 6, 3, 1, 5,10,11,0};
        System.out.println("Original: " + formatArray(toSort));
        runSorting("SelectionSort", new SelectionSort());
        runSorting("InsertionSort", new InsertionSort());
        runSorting("QuickSort", new QuickSort());

    }

    private static void runSorting(String name, SortAlgorithm sortAlgorithm) {

        int[] toSort = new int[]{4, 2, 6, 3, 1, 5,10,11,0};
        String result = formatArray(sortAlgorithm.sort(toSort));
        System.out.println(name + "Result: " + result);
    }

    private static String formatArray(int[] arr) {
        return Arrays.stream(arr).boxed()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }
}
