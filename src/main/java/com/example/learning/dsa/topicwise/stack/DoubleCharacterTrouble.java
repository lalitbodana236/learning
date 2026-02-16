package com.example.learning.dsa.topicwise.stack;

import java.util.Stack;

public class DoubleCharacterTrouble {
    public static void main(String[] args) {
        System.out.println(solve("abccbc"));
    }
    
    public static String solve(String A) {
        Stack<Character> stack = new Stack();
        char[] carray = A.toCharArray();
        for (char ch : carray) {
            if (!stack.isEmpty() && ch == stack.peek()) {
                while (!stack.isEmpty() && ch == stack.peek()) {
                    stack.pop();
                }
            } else
                stack.push(ch);
        }
        
        StringBuilder sb = new StringBuilder();
        for (Character ch : stack) {
            sb.append(ch);
        }
        
        return sb.toString();
    }
}
