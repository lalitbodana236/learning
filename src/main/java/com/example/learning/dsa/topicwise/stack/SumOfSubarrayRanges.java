package com.example.learning.dsa.topicwise.stack;

public class SumOfSubarrayRanges {
    public static void main(String[] args) {
        int[] arr = {1, 4, 3, 2};
        new SumOfSubarrayRanges().bruteForce(arr);
    }
    
    public void bruteForce(int[] arr) {
        int n = arr.length;
        long sum = 0;
        
        for (int i = 0; i < n; i++) {
            int longest = arr[i];
            int smallest = arr[i];
            
            for (int j = i; j < n; j++) {
                longest = Math.max(longest, arr[j]);
                smallest = Math.min(smallest, arr[j]);
                sum += (longest - smallest);
            }
        }
        
        System.out.println("Brute Force Sum: " + sum);
    }
}
