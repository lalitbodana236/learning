package com.example.learning.dsa.topicwise.stack;

public class TrappingRainWater {
    public static void main(String[] args) {
        TrappingRainWater rainWater = new TrappingRainWater();
        
        int[] arr = {4, 2, 0, 3, 2, 5};
        System.out.println("Water trapped: " + rainWater.bruteForce(arr)); // Expected: 9
    }
    
    public int bruteForce(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        
        int total = 0;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        
        // Build prefix max
        prefixMax[0] = arr[0];
        for (int i = 1; i < n; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], arr[i]);
        }
        
        // Build suffix max
        suffixMax[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMax[i] = Math.max(suffixMax[i + 1], arr[i]);
        }
        
        // Calculate trapped water
        for (int i = 0; i < n; i++) {
            int waterLevel = Math.min(prefixMax[i], suffixMax[i]);
            if (waterLevel > arr[i]) {
                total += waterLevel - arr[i];
            }
        }
        
        return total;
    }
    
    
    public int optimized(int[] arr) {
        int n = arr.length;
        if (n == 0) return 0;
        
        int left = 0, right = n - 1;
        int leftMax = 0, rightMax = 0;
        int total = 0;
        
        while (left < right) {
            if (arr[left] <= arr[right]) {
                if (arr[left] >= leftMax) {
                    leftMax = arr[left];
                } else {
                    total += leftMax - arr[left];
                }
                left++;
            } else {
                if (arr[right] >= rightMax) {
                    rightMax = arr[right];
                } else {
                    total += rightMax - arr[right];
                }
                right--;
            }
        }
        
        return total;
    }
}

