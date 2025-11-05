package com.example.learning.dsa.topicwise.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SumOfSubArrayMinimum {
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 4};
        //
        
        new SumOfSubArrayMinimum().bruteFroce(arr);
        new SumOfSubArrayMinimum().optimized(arr);
    }
    
    public void bruteFroce(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = i; j < arr.length; j++) {
                min = Math.min(min, arr[j]);
                System.out.println("min" + min);
                count += min;
            }
        }
        
        System.out.println(count);
    }
    
    public void optimized(int[] arr) {
        int[] nse = findNextSmallerElement(arr);
        int[] pse = findPreviousSmallerElement(arr);
        long total = 0;
        int mod = 1_000_000_007;
        int n = arr.length;
        
        for (int i = 0; i < n; i++) {
            long left = i - pse[i];
            long right = nse[i] - i;
            long contrib = (left * right % mod) * arr[i] % mod;
            total = (total + contrib) % mod;
        }
        
        System.out.println("Optimized Sum: " + total);
    }
    
    private int[] findPreviousSmallerElement(int[] arr) {
        int n = arr.length;
        int[] pse = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        
        Arrays.fill(pse, -1);
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            pse[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        
        return pse;
    }
    
    private int[] findNextSmallerElement(int[] arr) {
        int n = arr.length;
        int[] nse = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        
        Arrays.fill(nse, n); // default when no smaller element exists to the right
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            nse[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        
        return nse;
    }
}

