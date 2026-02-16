package com.example.learning.java8;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class TestFunctionalInterface {
    public static void main(String[] args) {
        SumOperation<Integer> intSum = Integer::sum;
        
        Integer result = intSum.sum(10, 20);
        
        
        ResponseEntity
                .status(HttpStatus.OK)
                .body(new Object());
        
        ResponseEntity.ok(new Object());
        ResponseEntity.ok().body(new Object());
        
        Set<Integer> seen = new HashSet<>();
        
        List<Integer> list = Arrays.asList(1, 2, 1, 4, 3, 5);
        
        
        List<Integer> l1 = list.stream().filter(i -> !seen.add(i)).sorted(Comparator.comparingInt((Integer::intValue))).collect(Collectors.toList());
        System.out.println(l1);
        
        List<Integer> l2 = list.stream()
                                   .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                                   .entrySet()
                                   .stream()
                                   .filter(e -> e.getValue() > 1)
                                   .map(Map.Entry::getKey)
                                   .collect(Collectors.toList());
        
        System.out.println(l2);
        
        
        List<Integer> l3 = list
                                   .stream()
                                   .map(value -> value * value)
                                   .toList();
        
        String word = "Hello Lalit";
        
        System.out.println(Arrays.stream(word.split(" ")).map(String::toUpperCase).collect(Collectors.joining(" ")));
        
        word.chars().mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(ch -> ch, LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst();
    }
}
