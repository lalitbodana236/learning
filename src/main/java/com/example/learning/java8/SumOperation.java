package com.example.learning.java8;

@FunctionalInterface
public interface SumOperation<T> {
    T sum(T num1, T num2);
}
