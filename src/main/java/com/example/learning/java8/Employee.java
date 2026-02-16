package com.example.learning.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Employee {
    private String name;
    private int salary;
    private String department;
    
    private String city;
    
    public Employee(String name, int salary, String department, String city) {
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.city = city;
    }
    
    public Employee(String name, int salary, String department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
    
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("A", 3000, "IT", "Indore"),
                new Employee("B", 2000, "HR", "Indore"),
                new Employee("C", 5000, "IT", "Bhopal")
        );
        System.out.println(sortByName(employees));
    }
    
    private static List<Employee> sortByName(List<Employee> employees) {
        employees
                .stream()
                .sorted(Comparator.comparing(Employee::getName))
                .distinct().map(emp -> new Employee(emp.getName(), emp.getSalary(), emp.getDepartment())
                ).collect(Collectors.toList());
        
        employees.stream()
                .sorted(Comparator.comparing(Employee::getName)
                                .thenComparing(Employee::getSalary))
                .map(emp -> Map.of("name", emp.getName(), "salary", emp.getSalary(), "Degi", emp.getDepartment()))
                .collect(Collectors.toList());
        
        return employees;
    }
    
    public String getName() {
        return name;
    }
    
    public int getSalary() {
        return salary;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public String toString() {
        return name + "-" + salary + "-" + department;
    }
}
