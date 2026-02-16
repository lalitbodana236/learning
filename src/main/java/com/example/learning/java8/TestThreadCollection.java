package com.example.learning.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestThreadCollection {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        
        //list2.add(10);
        
        List<Integer> list = new CopyOnWriteArrayList<>(list1);
        // list1.removeIf(integer -> integer % 2 == 0);
        
        for (Integer i : list) {
            if (i % 2 == 0) {
                list.remove(i);
            }
        }
    }
}
