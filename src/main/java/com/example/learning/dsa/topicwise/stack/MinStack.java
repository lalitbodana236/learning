package com.example.learning.dsa.topicwise.stack;

import java.util.Stack;

public class MinStack {
    Stack<Pair> stack;
    
    public MinStack() {
        this.stack = new Stack<>();
    }
    
    public void push(int val){
        if(stack.isEmpty()){
            stack.push(new Pair(val,val));
        }else{
            int min = Math.min(val,stack.peek().min);
            stack.push(new Pair(val,min));
        }
    }
    
    public void pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot pop.");
        }
        stack.pop();
    }
    
    public int top() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty. No top element.");
        }
        return stack.peek().val;
    }
    
    public int getMin(){
        return stack.peek().min;
    }
}

class Pair{
    int val;
    int min;
    
    public Pair(int val, int min) {
        this.val = val;
        this.min = min;
    }
}
