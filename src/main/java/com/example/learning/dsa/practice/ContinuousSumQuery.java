package com.example.learning.dsa.practice;

public class ContinuousSumQuery {
    public static void main(String[] args) {
        int A = 5;
        int[][] B = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};
        System.out.println(solve(A, B));
    }
    
    public static int[] solve(int A, int[][] B) {
        int[] pf = new int[A];
        
        for (int[] query : B) {
            int start = query[0];
            int end = query[1];
            int val = query[2];
            
            pf[start - 1] += val;
            if (end < A) {
                pf[end - 1] -= val;
            }
        }
        
        return pf;
    }
}
