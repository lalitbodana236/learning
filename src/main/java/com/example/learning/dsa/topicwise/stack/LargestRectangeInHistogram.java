package com.example.learning.dsa.topicwise.stack;

import java.util.ArrayList;
import java.util.Stack;

public class LargestRectangeInHistogram {
    public static void main(String[] args) {
        int[] arr = {2, 1, 5, 6, 2, 3};
        //{1,-1,2,2,-1,-1}
    }
    
    public void findArea(int arr) {
    
    }
    
    public int largestRectangleArea(ArrayList<Integer> A) {
        int n = A.size();
        
        int[] next = findNextSmaller(A, n);
        int[] prev = findPrevSmaller(A, n);
        
        int maxArea = 0;
        
        for (int i = 0; i < n; i++) {
            int width = next[i] - prev[i] - 1;
            int area = A.get(i) * width;
            maxArea = Math.max(maxArea, area);
        }
        
        return maxArea;
    }
    
    private int[] findNextSmaller(ArrayList<Integer> A, int n) {
        int[] next = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && A.get(stack.peek()) >= A.get(i)) {
                stack.pop();
            }
            
            next[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        
        return next;
    }
    
    private int[] findPrevSmaller(ArrayList<Integer> A, int n) {
        int[] prev = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && A.get(stack.peek()) >= A.get(i)) {
                stack.pop();
            }
            
            prev[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        
        return prev;
    }
}
