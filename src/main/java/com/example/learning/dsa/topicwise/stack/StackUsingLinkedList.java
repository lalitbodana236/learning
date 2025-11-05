package com.example.learning.dsa.topicwise.stack;

public class StackUsingLinkedList {
    
    int size;
    Node top;
    
    void push(int val) {
        Node temp = new Node(val);
        temp.next = top;
        top = temp;
        size++;
    }
    
    int pop() {
        if (top == null)
            throw new RuntimeException("Stack is empty");
        
        int val = top.val;
        top = top.next;
        size--;
        return val;
    }
    
    int top() {
        if (top == null)
            throw new RuntimeException("Stack is empty");
        
        return top.val;
    }
    
    int size() {
        return size;
    }
    
    public static void main(String[] args) {
        StackUsingLinkedList sl = new StackUsingLinkedList();
        
        System.out.println(sl.size);
        sl.push(1);
        sl.push(2);
        sl.push(3);
        
        System.out.println("size " + sl.size);
        System.out.println("top " + sl.top());
        System.out.println("remove " + sl.pop());
        System.out.println("top " + sl.top());
        System.out.println("size " + sl.size);
    }
}

class Node {
    int val;
    Node next;
    
    Node(int val) {
        this.val = val;
        this.next = null;
    }
}

