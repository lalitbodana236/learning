package com.example.learning.dsa.topicwise.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class AsteriodCollision {
    public static void main(String[] args) {
        int[] arr = {4, 7, 1, 1, 2, -3, -7, 17, -15, 16};
        new AsteriodCollision().collision(arr);
    }
    
    public int[] collision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (Integer asteroid : asteroids) {
            boolean isAlive = true;
            
            while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                if (Math.abs(asteroid) > stack.peek()) {
                    stack.pop();
                } else if (Math.abs(asteroid) == stack.peek()) {
                    stack.pop();
                    isAlive = false;
                    break;
                } else {
                    isAlive = false;
                    break;
                }
            }
            
            if (isAlive) stack.push(asteroid);
        }
        
        int[] res = new int[stack.size()];
        
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }
}
