package com.example.learning.dsa.topicwise.stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {
    Queue<Integer> q = new LinkedList<>();
    
    public void push(int x) {
        q.offer(x);
        int size = q.size();
        // rotate: move previous elements behind x
        for (int i = 1; i < size; i++) {
            q.offer(q.poll());
        }
    }
    
    public int pop() {
        return q.poll();
    }
    
    public int top() {
        return q.peek();
    }
    
    public boolean empty() {
        return q.isEmpty();
    }
}
