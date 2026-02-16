package com.example.learning.java8;

public class StringTest {
    
    public static void main(String[] args) {
        String s1 = "Lalit";
        String s2 = new String("Lalit");
        String s3 = "Lalit";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s2 == s3);
        
    }
}
