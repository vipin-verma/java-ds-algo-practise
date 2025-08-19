import java.util.*;
import java.util.stream.*;
import java.util.function.*;

/**
 * Advanced Collections Operations - Comprehensive Guide
 * 
 * This class demonstrates various advanced collections manipulation techniques
 * commonly asked in Java interviews for experienced developers.
 * 
 * Topics covered:
 * - Custom comparators and sorting
 * - Stream operations and collectors
 * - Collections utility methods
 * - Performance optimization
 * - Advanced data structures
 * - Memory management and optimization
 */
public class Question017_AdvancedCollectionsOperations {
    
    public static void main(String[] args) {
        System.out.println("=== Advanced Collections Operations - Complete Guide ===\n");
        
        demonstrateCustomComparators();
        demonstrateStreamOperations();
        demonstrateCollectionsUtilities();
        demonstratePerformanceOptimization();
        demonstrateAdvancedDataStructures();
        demonstrateMemoryOptimization();
        
        System.out.println("\n=== Advanced Collections Operations Completed! ===");
    }
    
    // ===== CUSTOM COMPARATORS =====
    
    /**
     * Student class for comparator demonstration
     */
    static class Student {
        private String name;
        private int age;
        private double gpa;
        private String department;
        
        public Student(String name, int age, double gpa, String department) {
            this.name = name;
            this.age = age;
            this.gpa = gpa;
            this.department = department;
        }
        
        // Getters
        public String getName() { return name; }
        public int getAge() { return age; }
        public double getGpa() { return gpa; }
        public String getDepartment() { return department; }
        
        @Override
        public String toString() {
            return String.format("Student{name='%s', age=%d, gpa=%.2f, dept='%s'}", 
                               name, age, gpa, department);
        }
    }
    
    /**
     * Multi-field comparator for Student
     */
    static class StudentComparator implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            // Primary: Department (ascending)
            int deptCompare = s1.getDepartment().compareTo(s2.getDepartment());
            if (deptCompare != 0) return deptCompare;
            
            // Secondary: GPA (descending)
            int gpaCompare = Double.compare(s2.getGpa(), s1.getGpa());
            if (gpaCompare != 0) return gpaCompare;
            
            // Tertiary: Age (ascending)
            int ageCompare = Integer.compare(s1.getAge(), s2.getAge());
            if (ageCompare != 0) return ageCompare;
            
            // Quaternary: Name (ascending)
            return s1.getName().compareTo(s2.getName());
        }
    }
    
    /**
     * Lambda-based comparator factory
     */
    static class ComparatorFactory {
        public static <T> Comparator<T> createComparator(Function<T, Comparable>... keyExtractors) {
            return (o1, o2) -> {
                for (Function<T, Comparable> keyExtractor : keyExtractors) {
                    Comparable key1 = keyExtractor.apply(o1);
                    Comparable key2 = keyExtractor.apply(o2);
                    int compare = key1.compareTo(key2);
                    if (compare != 0) return compare;
                }
                return 0;
            };
        }
    }
    
    // ===== STREAM OPERATIONS =====
    
    /**
     * Advanced stream operations demonstration
     */
    static class StreamOperations {
        
        /**
         * Group students by department with average GPA
         */
        public static Map<String, DoubleSummaryStatistics> groupByDepartmentWithStats(List<Student> students) {
            return students.stream()
                .collect(Collectors.groupingBy(
                    Student::getDepartment,
                    Collectors.summarizingDouble(Student::getGpa)
                ));
        }
        
        /**
         * Find top students by department
         */
        public static Map<String, List<Student>> findTopStudentsByDepartment(List<Student> students, int topN) {
            return students.stream()
                .collect(Collectors.groupingBy(
                    Student::getDepartment,
                    Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> list.stream()
                            .sorted(Comparator.comparing(Student::getGpa).reversed())
                            .limit(topN)
                            .collect(Collectors.toList())
                    )
                ));
        }
        
        /**
         * Partition students by GPA threshold
         */
        public static Map<Boolean, List<Student>> partitionByGPA(List<Student> students, double threshold) {
            return students.stream()
                .collect(Collectors.partitioningBy(student -> student.getGpa() >= threshold));
        }
        
        /**
         * Custom collector for student statistics
         */
        public static Collector<Student, ?, StudentStats> toStudentStats() {
            return Collector.of(
                StudentStats::new,
                StudentStats::accept,
                StudentStats::combine,
                StudentStats::finish
            );
        }
    }
    
    /**
     * Custom collector for student statistics
     */
    static class StudentStats {
        private int count = 0;
        private double totalGpa = 0.0;
        private double minGpa = Double.MAX_VALUE;
        private double maxGpa = Double.MIN_VALUE;
        private Set<String> departments = new HashSet<>();
        
        public void accept(Student student) {
            count++;
            totalGpa += student.getGpa();
            minGpa = Math.min(minGpa, student.getGpa());
            maxGpa = Math.max(maxGpa, student.getGpa());
            departments.add(student.getDepartment());
        }
        
        public StudentStats combine(StudentStats other) {
            count += other.count;
            totalGpa += other.totalGpa;
            minGpa = Math.min(minGpa, other.minGpa);
            maxGpa = Math.max(maxGpa, other.maxGpa);
            departments.addAll(other.departments);
            return this;
        }
        
        public Map<String, Object> finish() {
            Map<String, Object> result = new HashMap<>();
            result.put("count", count);
            result.put("averageGpa", count > 0 ? totalGpa / count : 0.0);
            result.put("minGpa", minGpa == Double.MAX_VALUE ? 0.0 : minGpa);
            result.put("maxGpa", maxGpa == Double.MIN_VALUE ? 0.0 : maxGpa);
            result.put("departments", departments);
            return result;
        }
    }
    
    // ===== COLLECTIONS UTILITIES =====
    
    /**
     * Collections utility operations
     */
    static class CollectionsUtils {
        
        /**
         * Find all permutations of a list
         */
        public static <T> List<List<T>> generatePermutations(List<T> list) {
            if (list.size() <= 1) {
                return Collections.singletonList(list);
            }
            
            List<List<T>> result = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                T current = list.get(i);
                List<T> remaining = new ArrayList<>(list);
                remaining.remove(i);
                
                for (List<T> permutation : generatePermutations(remaining)) {
                    List<T> newPermutation = new ArrayList<>();
                    newPermutation.add(current);
                    newPermutation.addAll(permutation);
                    result.add(newPermutation);
                }
            }
            return result;
        }
        
        /**
         * Find all subsets of a list
         */
        public static <T> List<List<T>> generateSubsets(List<T> list) {
            List<List<T>> result = new ArrayList<>();
            int n = list.size();
            
            for (int i = 0; i < (1 << n); i++) {
                List<T> subset = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    if ((i & (1 << j)) > 0) {
                        subset.add(list.get(j));
                    }
                }
                result.add(subset);
            }
            return result;
        }
        
        /**
         * Rotate list by k positions
         */
        public static <T> void rotateList(List<T> list, int k) {
            if (list == null || list.size() <= 1) return;
            
            int n = list.size();
            k = k % n;
            if (k < 0) k += n;
            
            Collections.reverse(list);
            Collections.reverse(list.subList(0, k));
            Collections.reverse(list.subList(k, n));
        }
        
        /**
         * Find longest common subsequence
         */
        public static <T> List<T> findLongestCommonSubsequence(List<T> list1, List<T> list2) {
            int m = list1.size();
            int n = list2.size();
            
            int[][] dp = new int[m + 1][n + 1];
            
            // Build DP table
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (Objects.equals(list1.get(i - 1), list2.get(j - 1))) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
            
            // Reconstruct subsequence
            List<T> result = new ArrayList<>();
            int i = m, j = n;
            while (i > 0 && j > 0) {
                if (Objects.equals(list1.get(i - 1), list2.get(j - 1))) {
                    result.add(0, list1.get(i - 1));
                    i--;
                    j--;
                } else if (dp[i - 1][j] > dp[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
            
            return result;
        }
    }
    
    // ===== PERFORMANCE OPTIMIZATION =====
    
    /**
     * Performance optimized collections
     */
    static class PerformanceOptimizedCollections {
        
        /**
         * Optimized list operations
         */
        public static <T> List<T> optimizedListOperations(List<T> list, int operations) {
            // Pre-allocate capacity for better performance
            List<T> result = new ArrayList<>(operations);
            
            for (int i = 0; i < operations; i++) {
                if (i % 2 == 0) {
                    result.add(list.get(i % list.size()));
                } else {
                    result.add(0, list.get(i % list.size()));
                }
            }
            
            return result;
        }
        
        /**
         * Optimized map operations
         */
        public static <K, V> Map<K, V> optimizedMapOperations(Map<K, V> map, int operations) {
            // Use initial capacity for better performance
            Map<K, V> result = new HashMap<>(operations);
            
            for (int i = 0; i < operations; i++) {
                K key = (K) ("key" + i);
                V value = (V) ("value" + i);
                result.put(key, value);
            }
            
            return result;
        }
        
        /**
         * Batch operations for better performance
         */
        public static <T> void batchAdd(List<T> list, Collection<T> elements, int batchSize) {
            List<T> batch = new ArrayList<>(batchSize);
            
            for (T element : elements) {
                batch.add(element);
                
                if (batch.size() >= batchSize) {
                    list.addAll(batch);
                    batch.clear();
                }
            }
            
            if (!batch.isEmpty()) {
                list.addAll(batch);
            }
        }
    }
    
    // ===== ADVANCED DATA STRUCTURES =====
    
    /**
     * Custom data structures
     */
    static class CustomDataStructures {
        
        /**
         * LRU Cache implementation
         */
        static class LRUCache<K, V> {
            private final int capacity;
            private final Map<K, Node<K, V>> cache;
            private Node<K, V> head;
            private Node<K, V> tail;
            
            public LRUCache(int capacity) {
                this.capacity = capacity;
                this.cache = new HashMap<>();
                this.head = new Node<>();
                this.tail = new Node<>();
                head.next = tail;
                tail.prev = head;
            }
            
            public V get(K key) {
                Node<K, V> node = cache.get(key);
                if (node == null) return null;
                
                moveToFront(node);
                return node.value;
            }
            
            public void put(K key, V value) {
                Node<K, V> node = cache.get(key);
                
                if (node == null) {
                    node = new Node<>(key, value);
                    cache.put(key, node);
                    addToFront(node);
                    
                    if (cache.size() > capacity) {
                        removeLRU();
                    }
                } else {
                    node.value = value;
                    moveToFront(node);
                }
            }
            
            private void moveToFront(Node<K, V> node) {
                removeNode(node);
                addToFront(node);
            }
            
            private void addToFront(Node<K, V> node) {
                node.next = head.next;
                node.prev = head;
                head.next.prev = node;
                head.next = node;
            }
            
            private void removeNode(Node<K, V> node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            
            private void removeLRU() {
                Node<K, V> lru = tail.prev;
                removeNode(lru);
                cache.remove(lru.key);
            }
            
            static class Node<K, V> {
                K key;
                V value;
                Node<K, V> prev;
                Node<K, V> next;
                
                Node() {}
                Node(K key, V value) {
                    this.key = key;
                    this.value = value;
                }
            }
        }
        
        /**
         * Trie data structure
         */
        static class Trie {
            private final TrieNode root;
            
            public Trie() {
                this.root = new TrieNode();
            }
            
            public void insert(String word) {
                TrieNode current = root;
                for (char c : word.toCharArray()) {
                    current.children.putIfAbsent(c, new TrieNode());
                    current = current.children.get(c);
                }
                current.isEndOfWord = true;
            }
            
            public boolean search(String word) {
                TrieNode current = root;
                for (char c : word.toCharArray()) {
                    if (!current.children.containsKey(c)) {
                        return false;
                    }
                    current = current.children.get(c);
                }
                return current.isEndOfWord;
            }
            
            public boolean startsWith(String prefix) {
                TrieNode current = root;
                for (char c : prefix.toCharArray()) {
                    if (!current.children.containsKey(c)) {
                        return false;
                    }
                    current = current.children.get(c);
                }
                return true;
            }
            
            static class TrieNode {
                Map<Character, TrieNode> children = new HashMap<>();
                boolean isEndOfWord = false;
            }
        }
    }
    
    // ===== MEMORY OPTIMIZATION =====
    
    /**
     * Memory optimization techniques
     */
    static class MemoryOptimization {
        
        /**
         * Object pooling for frequently created objects
         */
        static class ObjectPool<T> {
            private final Queue<T> pool;
            private final Supplier<T> factory;
            private final Consumer<T> resetter;
            
            public ObjectPool(int initialSize, Supplier<T> factory, Consumer<T> resetter) {
                this.pool = new LinkedList<>();
                this.factory = factory;
                this.resetter = resetter;
                
                for (int i = 0; i < initialSize; i++) {
                    pool.offer(factory.get());
                }
            }
            
            public T borrow() {
                T obj = pool.poll();
                return obj != null ? obj : factory.get();
            }
            
            public void returnObject(T obj) {
                if (obj != null) {
                    resetter.accept(obj);
                    pool.offer(obj);
                }
            }
        }
        
        /**
         * Weak reference cache
         */
        static class WeakReferenceCache<K, V> {
            private final Map<K, WeakReference<V>> cache = new HashMap<>();
            
            public V get(K key) {
                WeakReference<V> ref = cache.get(key);
                if (ref != null) {
                    V value = ref.get();
                    if (value != null) {
                        return value;
                    } else {
                        cache.remove(key);
                    }
                }
                return null;
            }
            
            public void put(K key, V value) {
                cache.put(key, new WeakReference<>(value));
            }
        }
    }
    
    // ===== DEMONSTRATION METHODS =====
    
    private static void demonstrateCustomComparators() {
        System.out.println("1. CUSTOM COMPARATORS:\n");
        
        List<Student> students = Arrays.asList(
            new Student("Alice", 20, 3.8, "Computer Science"),
            new Student("Bob", 22, 3.9, "Computer Science"),
            new Student("Charlie", 21, 3.7, "Mathematics"),
            new Student("Diana", 19, 4.0, "Computer Science"),
            new Student("Eve", 23, 3.6, "Physics")
        );
        
        System.out.println("Original list:");
        students.forEach(s -> System.out.println("  " + s));
        
        System.out.println("\nSorted by custom comparator:");
        List<Student> sorted = new ArrayList<>(students);
        sorted.sort(new StudentComparator());
        sorted.forEach(s -> System.out.println("  " + s));
        
        System.out.println("\nSorted by lambda comparator (GPA descending):");
        List<Student> gpaSorted = new ArrayList<>(students);
        gpaSorted.sort(Comparator.comparing(Student::getGpa).reversed());
        gpaSorted.forEach(s -> System.out.println("  " + s));
        
        System.out.println();
    }
    
    private static void demonstrateStreamOperations() {
        System.out.println("2. STREAM OPERATIONS:\n");
        
        List<Student> students = Arrays.asList(
            new Student("Alice", 20, 3.8, "Computer Science"),
            new Student("Bob", 22, 3.9, "Computer Science"),
            new Student("Charlie", 21, 3.7, "Mathematics"),
            new Student("Diana", 19, 4.0, "Computer Science"),
            new Student("Eve", 23, 3.6, "Physics")
        );
        
        System.out.println("Group by department with GPA stats:");
        Map<String, DoubleSummaryStatistics> deptStats = StreamOperations.groupByDepartmentWithStats(students);
        deptStats.forEach((dept, stats) -> 
            System.out.println("  " + dept + ": " + stats));
        
        System.out.println("\nTop 2 students by department:");
        Map<String, List<Student>> topStudents = StreamOperations.findTopStudentsByDepartment(students, 2);
        topStudents.forEach((dept, studentList) -> {
            System.out.println("  " + dept + ":");
            studentList.forEach(s -> System.out.println("    " + s));
        });
        
        System.out.println("\nPartition by GPA >= 3.8:");
        Map<Boolean, List<Student>> partitioned = StreamOperations.partitionByGPA(students, 3.8);
        partitioned.forEach((highGPA, studentList) -> {
            System.out.println("  " + (highGPA ? "High GPA" : "Lower GPA") + ":");
            studentList.forEach(s -> System.out.println("    " + s));
        });
        
        System.out.println("\nCustom collector statistics:");
        Map<String, Object> stats = students.stream().collect(StreamOperations.toStudentStats());
        stats.forEach((key, value) -> System.out.println("  " + key + ": " + value));
        
        System.out.println();
    }
    
    private static void demonstrateCollectionsUtilities() {
        System.out.println("3. COLLECTIONS UTILITIES:\n");
        
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        
        System.out.println("Permutations of " + numbers + ":");
        List<List<Integer>> permutations = CollectionsUtils.generatePermutations(numbers);
        permutations.forEach(p -> System.out.println("  " + p));
        
        System.out.println("\nSubsets of " + numbers + ":");
        List<List<Integer>> subsets = CollectionsUtils.generateSubsets(numbers);
        subsets.forEach(s -> System.out.println("  " + s));
        
        System.out.println("\nRotate list by 2 positions:");
        List<Integer> rotated = new ArrayList<>(numbers);
        CollectionsUtils.rotateList(rotated, 2);
        System.out.println("  Original: " + numbers);
        System.out.println("  Rotated: " + rotated);
        
        System.out.println("\nLongest common subsequence:");
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(2, 3, 5, 6);
        List<Integer> lcs = CollectionsUtils.findLongestCommonSubsequence(list1, list2);
        System.out.println("  List1: " + list1);
        System.out.println("  List2: " + list2);
        System.out.println("  LCS: " + lcs);
        
        System.out.println();
    }
    
    private static void demonstratePerformanceOptimization() {
        System.out.println("4. PERFORMANCE OPTIMIZATION:\n");
        
        List<Integer> list = IntStream.range(0, 1000).boxed().collect(Collectors.toList());
        
        System.out.println("Optimized list operations:");
        long startTime = System.nanoTime();
        List<Integer> optimized = PerformanceOptimizedCollections.optimizedListOperations(list, 10000);
        long optimizedTime = System.nanoTime() - startTime;
        System.out.println("  Optimized operations: " + optimizedTime + " ns");
        
        System.out.println("\nBatch operations:");
        List<Integer> batchList = new ArrayList<>();
        List<Integer> elements = IntStream.range(0, 10000).boxed().collect(Collectors.toList());
        
        startTime = System.nanoTime();
        PerformanceOptimizedCollections.batchAdd(batchList, elements, 1000);
        long batchTime = System.nanoTime() - startTime;
        System.out.println("  Batch add operations: " + batchTime + " ns");
        
        System.out.println();
    }
    
    private static void demonstrateAdvancedDataStructures() {
        System.out.println("5. ADVANCED DATA STRUCTURES:\n");
        
        System.out.println("LRU Cache demonstration:");
        CustomDataStructures.LRUCache<String, Integer> lruCache = new CustomDataStructures.LRUCache<>(3);
        lruCache.put("A", 1);
        lruCache.put("B", 2);
        lruCache.put("C", 3);
        System.out.println("  After adding A, B, C: " + lruCache.get("A") + ", " + lruCache.get("B") + ", " + lruCache.get("C"));
        
        lruCache.put("D", 4);
        System.out.println("  After adding D, A should be evicted: " + lruCache.get("A"));
        
        System.out.println("\nTrie demonstration:");
        CustomDataStructures.Trie trie = new CustomDataStructures.Trie();
        trie.insert("hello");
        trie.insert("world");
        trie.insert("help");
        
        System.out.println("  Search 'hello': " + trie.search("hello"));
        System.out.println("  Search 'help': " + trie.search("help"));
        System.out.println("  Starts with 'he': " + trie.startsWith("he"));
        System.out.println("  Search 'hell': " + trie.search("hell"));
        
        System.out.println();
    }
    
    private static void demonstrateMemoryOptimization() {
        System.out.println("6. MEMORY OPTIMIZATION:\n");
        
        System.out.println("Object pooling demonstration:");
        MemoryOptimization.ObjectPool<StringBuilder> pool = new MemoryOptimization.ObjectPool<>(
            3, 
            StringBuilder::new, 
            StringBuilder::setLength
        );
        
        StringBuilder sb1 = pool.borrow();
        StringBuilder sb2 = pool.borrow();
        StringBuilder sb3 = pool.borrow();
        
        sb1.append("Hello");
        sb2.append("World");
        sb3.append("Java");
        
        System.out.println("  Borrowed objects: " + sb1 + ", " + sb2 + ", " + sb3);
        
        pool.returnObject(sb1);
        pool.returnObject(sb2);
        pool.returnObject(sb3);
        
        System.out.println("  Objects returned to pool");
        
        System.out.println("\nWeak reference cache demonstration:");
        MemoryOptimization.WeakReferenceCache<String, String> weakCache = new MemoryOptimization.WeakReferenceCache<>();
        weakCache.put("key1", "value1");
        weakCache.put("key2", "value2");
        
        System.out.println("  Cache size: 2");
        System.out.println("  Get 'key1': " + weakCache.get("key1"));
        
        // Suggest garbage collection
        System.gc();
        
        System.out.println("  After GC, cache may be cleared");
        
        System.out.println();
    }
}

/**
 * INTERVIEW QUESTIONS COVERED:
 * 
 * THEORETICAL:
 * 1. Explain the difference between Comparable and Comparator.
 * 2. How do you implement a custom collector in Java streams?
 * 3. What are the performance implications of different collection operations?
 * 4. Explain the concept of object pooling and when to use it.
 * 5. How do weak references help with memory management?
 * 6. What are the trade-offs between different collection implementations?
 * 
 * PRACTICAL:
 * 1. Implement custom comparators for complex objects.
 * 2. Use advanced stream operations and collectors.
 * 3. Create custom data structures (LRU Cache, Trie).
 * 4. Optimize collection operations for performance.
 * 5. Implement memory-efficient caching strategies.
 * 
 * TRICKY SCENARIOS:
 * 1. Handle null values in comparators.
 * 2. Optimize memory usage for large collections.
 * 3. Implement thread-safe custom collections.
 * 4. Deal with concurrent modification in collections.
 * 
 * PERFORMANCE:
 * 1. Compare different collection implementations.
 * 2. Analyze stream operation performance.
 * 3. Optimize memory usage for specific use cases.
 * 4. Benchmark custom vs built-in collections.
 * 
 * DESIGN PATTERNS:
 * 1. Strategy pattern for different sorting strategies.
 * 2. Factory pattern for collection creation.
 * 3. Builder pattern for complex collection construction.
 * 4. Flyweight pattern for object pooling.
 */
