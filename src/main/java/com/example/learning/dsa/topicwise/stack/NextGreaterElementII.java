package com.example.learning.dsa.topicwise.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NextGreaterElementII {
    public static void main(String[] args) {
        int[] arr = {2, 10, 12, 1, 11};
        //        {10,12,-1,11,12}
        
        System.out.println("Brute Force:1");
        printArray(new NextGreaterElementII().bruteForce1(arr));
        System.out.println("Brute Force:2");
        printArray(new NextGreaterElementII().bruteForce2(arr));
        
        System.out.println("\nOptimized:");
        printArray(new NextGreaterElementII().optimized(arr));
        
    }
    
    private static void printArray(int[] arr) {
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
    }
    
    public int[] bruteForce1(int[] arr) {
        int[] ans = new int[arr.length];
        Arrays.fill(ans, -1);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] > arr[i]) {
                    ans[i] = arr[j];
                    break;
                }
            }
        }
        return ans;
    }
    
    public int[] bruteForce2(int[] arr) {
        int[] ans = new int[arr.length];
        int n = arr.length;
        Arrays.fill(ans, -1);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < i + arr.length; j++) {
                int idx = j % n;
                if (arr[idx] > arr[i]) {
                    ans[i] = arr[idx];
                    break;
                }
            }
        }
        return ans;
    }
    
    public int[] optimized(int[] arr) {
        int[] ans = new int[arr.length];
        int n = arr.length;
        Arrays.fill(ans, -1);
        Deque<Integer> dq = new ArrayDeque<>();
        
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!dq.isEmpty() && dq.peek() <= arr[i % n]) {
                dq.pop();
            }
            
            if (!dq.isEmpty())
                ans[i % n] = dq.peek();
            
            dq.push(arr[i % n]);
        }
        
        return ans;
    }
}
