package com.andresolarte.harness.lang.sorting;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SortingUtils {
    public static void swap(int[] list, int j, int i) {
        int x = list[i];
        list[i] = list[j];
        list[j] = x;
    }

    public static String formatArray(int[] arr) {
        return Arrays.stream(arr).boxed()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
    }
}
