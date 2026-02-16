package com.example.learning.dsa.topicwise.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class PreviousGreaterElement {
    
    public static void main(String[] args) {
        int[] arr = {10, 4, 2, 20, 40, 12, 30};
        PreviousGreaterElement pge = new PreviousGreaterElement();
        
        System.out.println("Brute Force:");
        printArray(pge.bruteForce(arr));
        
        System.out.println("\nOptimized:");
        printArray(pge.optimized(arr));
    }
    
    private static void printArray(int[] arr) {
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }
    
    // ✅ Brute Force Approach (O(n²))
    public int[] bruteForce(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        
        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[i]) {
                    ans[i] = arr[j];
                    break;
                }
            }
        }
        return ans;
    }
    
    // ✅ Optimized Approach (O(n)) using Monotonic Stack
    public int[] optimized(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i = 0; i < n; i++) {
            // Pop smaller or equal elements, since they can’t be "previous greater"
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            
            // Top of stack is the previous greater element
            if (!stack.isEmpty()) {
                ans[i] = stack.peek();
            }
            
            // Push current element to stack
            stack.push(arr[i]);
        }
        
        return ans;
    }
}

