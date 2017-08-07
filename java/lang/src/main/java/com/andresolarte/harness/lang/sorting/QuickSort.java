package com.andresolarte.harness.lang.sorting;

public class QuickSort implements SortAlgorithm {

    @Override
    public int[] sort(int[] list) {
        int pivot = sortSubArray(list, 0, list.length);
        sortSubLists(list, 0, pivot);
        sortSubLists(list, pivot + 1, list.length);
        return list;
    }

    public void sortSubLists(int[] list, int start, int end) {
        int pivot = sortSubArray(list, start, end);
        if (pivot - start > 1) {
            sortSubLists(list, start, pivot);
        }

        if (end - (pivot + 1) > 1) {
            sortSubLists(list, pivot + 1, end);
        }
    }


    public int sortSubArray(int[] list, int startIndex, int endIndex) {
        int currentPivot = endIndex - 1;
        int currentIndex = startIndex;
        int highIndex = endIndex - 1;

        while (currentIndex < highIndex) {
            if (list[currentIndex] > list[currentPivot]) {

                //item is before and higher
                //Swap to the far end

                if (highIndex == currentPivot && (currentPivot - currentIndex) > 1) {
                    //Bump pivot one spot to the left
                    SortingUtils.swap(list, (highIndex - 1), highIndex);
                    currentPivot = (highIndex - 1);
                }
                SortingUtils.swap(list, currentIndex, highIndex);
                if (currentPivot - currentIndex == 1) {
                    currentPivot = (highIndex - 1);
                }

                highIndex--;

            } else {
                //item is equal or lower than pivot
                //We assume pivot is moving FROM the right
                // nothing to do
                currentIndex++;
            }
        }

        return currentPivot;
    }

}