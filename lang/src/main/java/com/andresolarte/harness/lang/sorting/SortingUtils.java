package com.andresolarte.harness.lang.sorting;

public class SortingUtils {
    public static void swap(int[] list, int j, int i) {
        int x = list[i];
        list[i] = list[j];
        list[j] = x;
    }
}
