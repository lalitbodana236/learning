package com.example.learning.dsa.topicwise.sorting;

public class QuickSort {
    public static void main(String[] args) {
        int arr[] = {54, 26, 93, 17, 77, 31, 44, 55};
        quicksort(arr, 0, arr.length - 1);
        
        for (int i : arr)
            System.out.print(i + " ");
        System.out.println();
    }
    
    public static void quicksort(int[] arr, int l, int r) {
        if (l >= r) return;
        
        int p = partition(arr, l, r);
        
        quicksort(arr, l, p - 1);
        quicksort(arr, p + 1, r);
    }
    
    public static int partition(int[] arr, int l, int r) {
        int pivot = arr[r];
        int i = l;
        
        for (int j = l; j < r; j++) {
            if (arr[j] < pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        
        swap(arr, i, r);
        return i;
    }
    
    
    private static void swap(int[] arr, int i1, int i2) {
        int temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;
    }
}
