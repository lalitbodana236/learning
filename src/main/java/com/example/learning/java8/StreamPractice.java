package com.example.learning.java8;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class StreamPractice {
    
    
    // 20. Cache
    private final ConcurrentHashMap<String, Integer> cache = new ConcurrentHashMap<>();
    
    public static void main(String[] args) throws Exception {
        Map<Integer, List<Integer>> map = new HashMap();
        StreamPractice sp = new StreamPractice();
        
        // 1. Filter even numbers
        System.out.println(sp.filterEven(Arrays.asList(1, 2, 3, 4, 5, 6)));
        
        // 2. Word frequency
        System.out.println(sp.countWordFrequency("hello world hello java"));
        
        // 3. First non-repeating
        System.out.println(sp.firstNonRepeating("swiss"));
        
        // 4. Remove duplicates
        System.out.println(sp.removeDuplicates(Arrays.asList(1, 2, 2, 3, 3, 4)));
        
        // 5. Sort employees by salary
        List<Employee> employees = Arrays.asList(
                new Employee("A", 3000, "IT"),
                new Employee("B", 2000, "HR"),
                new Employee("C", 5000, "IT")
        );
        System.out.println(sp.sortBySalary(employees));
        
        // 6. Uppercase conversion
        System.out.println(sp.toUpper(Arrays.asList("a", "b", "c")));
        
        // 7. Sum using reduce
        System.out.println(sp.sumUsingReduce(Arrays.asList(1, 2, 3, 4)));
        
        // 8. Join comma
        System.out.println(sp.joinComma(Arrays.asList("a", "b", "c")));
        
        // 9. Private constructor explanation
        System.out.println(sp.canConstructorBePrivate());
        
        // 10. Partition even/odd
        System.out.println(sp.partitionEvenOdd(Arrays.asList(1, 2, 3, 4, 5)));
        
        // 11. Second highest
        System.out.println(sp.secondHighest(Arrays.asList(10, 20, 30, 40)));
        
        // 12. Flatten lists
        System.out.println(sp.flatten(Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4),
                Arrays.asList(5)
        )));
        
        // 13. Count by department
        System.out.println(sp.countByDepartment(employees));
        
        // 14. Sort map by value
        Map<String, Integer> mapTest = new HashMap<>();
        mapTest.put("a", 3);
        mapTest.put("b", 1);
        mapTest.put("c", 2);
        System.out.println(sp.sortMapByValue(mapTest));
        
        // 15. Filter by multiple conditions
        System.out.println(sp.filterWithConditions(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)));
        
        // 16. Find duplicates
        System.out.println(sp.findDuplicates(Arrays.asList(1, 2, 2, 3, 3, 4)));
        
        // 17. Remove null & empty
        System.out.println(sp.removeNullAndEmpty(Arrays.asList("a", "", null, "b")));
        
        // 18. Debug with peek
        System.out.println(sp.debugWithPeek(Arrays.asList(1, 2, 3)));
        
        // 19. Group employees by dept
        System.out.println(sp.groupByDept(employees));
        
        // 20. Cache computeIfAbsent
        System.out.println(sp.getCachedValue("x"));
        System.out.println(sp.getCachedValue("x")); // second call should be cached
        
        // 21. Custom collector to join uppercase
        System.out.println(sp.joinUpperCustom(Arrays.asList("a", "b", "c")));
        
        // 22. Optional nested
        User user = new User(new Address("Mumbai"), true, "test@gmail.com");
        System.out.println(sp.getCity(user));
        
        // 23. Large file processing (example only)
        // System.out.println(sp.readLargeFile("test.txt"));
        
        // 24. Generic Function<T,R>
        // System.out.println(sp.applyFunction(5, x -> x * 2));
        
        // 25. Optional + Stream chaining
        List<User> users = Arrays.asList(
                new User(new Address("Delhi"), false, "x@mail.com"),
                new User(new Address("Pune"), true, "active@mail.com")
        );
        System.out.println(sp.getFirstActiveEmail(users));
        
        // 26. toMap merge duplicate keys
        List<Person> persons = Arrays.asList(
                new Person("A", "Delhi"),
                new Person("B", "Delhi"),
                new Person("C", "Mumbai")
        );
        System.out.println(sp.mergeDuplicateCities(persons));
        
        // 27. Stream vs ParallelStream comparison
        System.out.println(sp.compareStreamPerformance(Arrays.asList(1, 2, 3, 4, 5)));
        
        // 28. CSV processing
        List<String> csvLines = Arrays.asList("1,John,IT", "2,Mark,HR");
        System.out.println(sp.processCsv(csvLines));
        
        // 29. Longest string
        System.out.println(sp.longestString(Arrays.asList("apple", "banana", "kiwi")));
    }
    
    // ------------------------------------------------------------
    // 1. Filter even numbers
    // ------------------------------------------------------------
    public List<Integer> filterEven(List<Integer> input) {
        return input.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
    }
    
    // 2. Word frequency
    public Map<String, Long> countWordFrequency(String input) {
        return Arrays.stream(input.split(" ")).collect(Collectors.groupingBy(s -> s, Collectors.counting()));
        
    }
    
    // 3. First non-repeating char
    public Optional<Character> firstNonRepeating(String input) {
        return input.chars()
                       .mapToObj(c -> (char) c)
                       .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                       .entrySet()
                       .stream()
                       .filter(e -> e.getValue() == 1)
                       .map(Map.Entry::getKey)
                       .findFirst();
    }
    
    // 4. Remove duplicates
    public List<Integer> removeDuplicates(List<Integer> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }
    
    // 5. Sort employees
    public List<Employee> sortBySalary(List<Employee> employees) {
        return employees.stream().sorted(Comparator.comparing(Employee::getSalary)).collect(Collectors.toList());
    }
    
    // 6. Uppercase
    public List<String> toUpper(List<String> input) {
        return input.stream().map(String::toUpperCase).collect(Collectors.toList());
    }
    
    // 7. Sum reduce
    public int sumUsingReduce(List<Integer> list) {
        return list.stream().reduce(0, Integer::sum);
    }
    
    // 8. Join comma
    public String joinComma(List<String> list) {
        return String.join(",", list);
    }
    
    // 9. Constructor private?
    public String canConstructorBePrivate() {
        return "Yes, used in Singleton and factory patterns";
    }
    
    // 10. Partition even/odd
    public Map<Boolean, List<Integer>> partitionEvenOdd(List<Integer> list) {
        return list.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));
    }
    
    // 11. Second highest
    public Optional<Integer> secondHighest(List<Integer> list) {
        return list
                       .stream()
                       .sorted(Comparator.reverseOrder())
                       .skip(1)
                       .findFirst();
    }
    
    // 12. Flatten list of lists
    public List<Integer> flatten(List<List<Integer>> list) {
        return list.stream().flatMap(Collection::stream).collect(Collectors.toList());
    }
    
    // 13. Count by department
    public Map<String, Long> countByDepartment(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }
    
    // 14. Sort map by value
    public Map<String, Integer> sortMapByValue(Map<String, Integer> map) {
        return map.entrySet()
                       .stream().sorted(Map.Entry.comparingByValue())
                       .collect(Collectors.toMap(
                               Map.Entry::getKey,
                               Map.Entry::getValue,
                               (a, b) -> a,
                               LinkedHashMap::new
                       ));
    }
    
    // 15. Filter multiple conditions
    public List<Integer> filterWithConditions(List<Integer> list) {
        return list.stream()
                       .filter(i -> i > 5)
                       .filter(i -> i % 2 == 0)
                       .collect(Collectors.toList());
    }
    
    // 16. Find duplicates
    public Set<Integer> findDuplicates(List<Integer> list) {
        return list.stream().filter(i -> Collections.frequency(list, i) > 1).collect(Collectors.toSet());
    }
    
    // 17. Remove nulls and empty
    public List<String> removeNullAndEmpty(List<String> list) {
        return list.stream().filter(Objects::isNull)
                       .filter(s -> !s.isBlank())
                       .collect(Collectors.toList());
    }
    
    // 18. Debug with peek
    public List<Integer> debugWithPeek(List<Integer> list) {
        return list.stream()
                       .peek(i -> System.out.println("Processing: " + i))
                       .map(i -> i * 2)
                       .peek(i -> System.out.println("After multiply: " + i))
                       .collect(Collectors.toList());
    }
    
    // 19. Group employees
    public Map<String, List<Employee>> groupByDept(List<Employee> employees) {
        return employees.stream().collect(Collectors.groupingBy(Employee::getDepartment));
    }
    
    public Integer getCachedValue(String key) {
        return cache.computeIfAbsent(key, k -> k.length() * 10);
    }
    
    // 21. Custom collector uppercase join
    public String joinUpperCustom(List<String> list) {
        return list.stream()
                       .map(String::toUpperCase)
                       .collect(Collectors.joining("-"));
    }
    
    // 22. Optional nested
    public String getCity(User user) {
        return Optional.ofNullable(user).map(User::getAddress).map(Address::getCity).orElse("unkwon");
    }
    
    // 23. File streaming
    public List<String> readLargeFile(String filePath) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.collect(Collectors.toList());
        }
    }
    
    // 24. Generic method
    public <T, R> R applyFunction(T input, Function<T, R> function) {
        return function.apply(input);
    }
    
    // 25. Optional + stream chaining
    public String getFirstActiveEmail(List<User> users) {
        return users.stream()
                       .filter(User::isActive)
                       .map(User::getEmail)
                       .findFirst()
                       .orElse("No active user found");
    }
    
    // 26. Duplicate keys merge
    public Map<String, String> mergeDuplicateCities(List<Person> persons) {
        return persons.stream()
                       .collect(Collectors.toMap(
                               Person::getCity,
                               Person::getName,
                               (n1, n2) -> n1 + "," + n2
                       ));
    }
    
    // 27. Stream vs parallel performance
    public Map<String, Long> compareStreamPerformance(List<Integer> list) {
        long t1 = System.currentTimeMillis();
        list.stream().map(i -> i * 2).count();
        long normal = System.currentTimeMillis() - t1;
        
        long t2 = System.currentTimeMillis();
        list.parallelStream().map(i -> i * 2).count();
        long parallel = System.currentTimeMillis() - t2;
        
        Map<String, Long> result = new HashMap<>();
        result.put("stream", normal);
        result.put("parallelStream", parallel);
        return result;
    }
    
    // 28. CSV processing pipeline
    public List<String> processCsv(List<String> lines) {
        return lines.stream()
                       .map(line -> line.split(","))
                       .filter(arr -> arr.length == 3)
                       .map(arr -> arr[1]) // name
                       .collect(Collectors.toList());
    }
    
    // 29. Longest string
    public Optional<String> longestString(List<String> list) {
        return list.stream()
                       .max(Comparator.comparingInt(String::length));
    }
}


class User {
    private Address address;
    private boolean active;
    private String email;
    
    public User(Address address, boolean active, String email) {
        this.address = address;
        this.active = active;
        this.email = email;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public String getEmail() {
        return email;
    }
}

class Address {
    private String city;
    
    public Address(String city) {
        this.city = city;
    }
    
    public String getCity() {
        return city;
    }
}

class Person {
    private String name;
    private String city;
    
    public Person(String name, String city) {
        this.name = name;
        this.city = city;
    }
    
    public String getCity() {
        return city;
    }
    
    public String getName() {
        return name;
    }
}

