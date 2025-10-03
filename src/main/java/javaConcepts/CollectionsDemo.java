package javaConcepts;

import java.util.*;

public class CollectionsDemo {

    public static void main(String[] args) {
        System.out.println("=== Java Collections Framework ===\n");

        // 1. List Interface
        demonstrateList();

        // 2. Set Interface
        demonstrateSet();

        // 3. Map Interface
        demonstrateMap();

        // 4. Queue Interface
        demonstrateQueue();

        // 5. Collections Utility Methods
        demonstrateCollectionsUtility();

        // 6. Iterator and Enhanced For Loop
        demonstrateIteration();

        // 7. Custom Objects in Collections
        demonstrateCustomObjects();
    }

    private static void demonstrateList() {
        System.out.println("1. LIST INTERFACE:");

        // ArrayList - dynamic array
        System.out.println("\nArrayList (Dynamic Array):");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");
        arrayList.add(1, "Blueberry");  // Insert at specific index
        System.out.println("ArrayList: " + arrayList);
        System.out.println("Size: " + arrayList.size());
        System.out.println("Element at index 2: " + arrayList.get(2));

        // LinkedList - doubly linked list
        System.out.println("\nLinkedList (Doubly Linked List):");
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(10);      // Add at beginning
        linkedList.addLast(30);       // Add at end
        linkedList.add(1, 20);        // Add at specific position
        linkedList.addFirst(5);       // Add at beginning
        System.out.println("LinkedList: " + linkedList);
        System.out.println("First element: " + linkedList.getFirst());
        System.out.println("Last element: " + linkedList.getLast());

        // Vector - synchronized ArrayList
        System.out.println("\nVector (Thread-safe ArrayList):");
        Vector<Double> vector = new Vector<>();
        vector.add(1.1);
        vector.add(2.2);
        vector.add(3.3);
        System.out.println("Vector: " + vector);
        System.out.println("Capacity: " + vector.capacity());

        // List operations
        System.out.println("\nList Operations:");
        List<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry", "Date"));
        System.out.println("Original list: " + fruits);

        fruits.remove("Banana");           // Remove by object
        fruits.remove(1);                  // Remove by index
        System.out.println("After removal: " + fruits);

        fruits.set(0, "Apricot");         // Replace element
        System.out.println("After replacement: " + fruits);

        Collections.sort(fruits);          // Sort the list
        System.out.println("After sorting: " + fruits);

        List<String> test = Arrays.asList("1","1","2");
                System.out.println("vipin --> " + test);
    }

    private static void demonstrateSet() {
        System.out.println("\n2. SET INTERFACE:");

        // HashSet - unordered, no duplicates
        System.out.println("\nHashSet (Unordered, No Duplicates):");
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Red");
        hashSet.add("Green");
        hashSet.add("Blue");
        hashSet.add("Red");  // Duplicate - will be ignored
        hashSet.add("Yellow");
        System.out.println("HashSet: " + hashSet);
        System.out.println("Contains 'Green': " + hashSet.contains("Green"));

        // LinkedHashSet - ordered, no duplicates
        System.out.println("\nLinkedHashSet (Ordered, No Duplicates):");
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("First");
        linkedHashSet.add("Second");
        linkedHashSet.add("Third");
        linkedHashSet.add("First");  // Duplicate - will be ignored
        System.out.println("LinkedHashSet: " + linkedHashSet);

        // TreeSet - sorted, no duplicates
        System.out.println("\nTreeSet (Sorted, No Duplicates):");
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(30);
        treeSet.add(10);
        treeSet.add(50);
        treeSet.add(20);
        treeSet.add(10);  // Duplicate - will be ignored
        System.out.println("TreeSet: " + treeSet);
        System.out.println("First element: " + treeSet.first());
        System.out.println("Last element: " + treeSet.last());
        System.out.println("Elements less than 25: " + treeSet.headSet(25));
        System.out.println("Elements greater than 20: " + treeSet.tailSet(20));

        // Set operations
        System.out.println("\nSet Operations:");
        Set<String> set1 = new HashSet<>(Arrays.asList("A", "B", "C", "D"));
        Set<String> set2 = new HashSet<>(Arrays.asList("C", "D", "E", "F"));

        System.out.println("Set 1: " + set1);
        System.out.println("Set 2: " + set2);

        // Union
        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("Union: " + union);

        // Intersection
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);

        // Difference
        Set<String> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (Set1 - Set2): " + difference);
    }

    private static void demonstrateMap() {
        System.out.println("\n3. MAP INTERFACE:");

        // HashMap - unordered key-value pairs
        System.out.println("\nHashMap (Unordered Key-Value):");
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Apple", 5);
        hashMap.put("Banana", 3);
        hashMap.put("Cherry", 8);
        hashMap.put("Apple", 6);  // Overwrites existing value
        System.out.println("HashMap: " + hashMap);
        System.out.println("Value for 'Banana': " + hashMap.get("Banana"));
        System.out.println("Contains key 'Cherry': " + hashMap.containsKey("Cherry"));
        System.out.println("Contains value 8: " + hashMap.containsValue(8));

        // LinkedHashMap - ordered key-value pairs
        System.out.println("\nLinkedHashMap (Ordered Key-Value):");
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("First", "Value1");
        linkedHashMap.put("Second", "Value2");
        linkedHashMap.put("Third", "Value3");
        System.out.println("LinkedHashMap: " + linkedHashMap);

        // TreeMap - sorted key-value pairs
        System.out.println("\nTreeMap (Sorted Key-Value):");
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(30, "Thirty");
        treeMap.put(10, "Ten");
        treeMap.put(50, "Fifty");
        treeMap.put(20, "Twenty");
        System.out.println("TreeMap: " + treeMap);
        System.out.println("First key: " + treeMap.firstKey());
        System.out.println("Last key: " + treeMap.lastKey());

        // Map operations
        System.out.println("\nMap Operations:");
        Map<String, Integer> scores = new HashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        scores.put("David", 78);

        System.out.println("Scores: " + scores);

        // Iterating through map
        System.out.println("\nIterating through map:");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Using forEach (Java 8+)
        System.out.println("\nUsing forEach:");
        scores.forEach((name, score) ->
                System.out.println(name + " scored " + score));

        // Remove entry
        scores.remove("David");
        System.out.println("\nAfter removing David: " + scores);
    }

    private static void demonstrateQueue() {
        System.out.println("\n4. QUEUE INTERFACE:");

        // PriorityQueue - priority-based queue
        System.out.println("\nPriorityQueue (Priority-based):");
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(30);
        priorityQueue.offer(10);
        priorityQueue.offer(50);
        priorityQueue.offer(20);
        priorityQueue.offer(5);
        System.out.println("PriorityQueue: " + priorityQueue);
        System.out.println("Peek (highest priority): " + priorityQueue.peek());
        System.out.println("Poll (remove highest priority): " + priorityQueue.poll());
        System.out.println("After poll: " + priorityQueue);

        // LinkedList as Queue
        System.out.println("\nLinkedList as Queue:");
        Queue<String> queue = new LinkedList<>();
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");
        System.out.println("Queue: " + queue);
        System.out.println("Peek: " + queue.peek());
        System.out.println("Poll: " + queue.poll());
        System.out.println("After poll: " + queue);

        // Deque (Double-ended queue)
        System.out.println("\nDeque (Double-ended Queue):");
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("Front");
        deque.addLast("Back");
        deque.addFirst("NewFront");
        deque.addLast("NewBack");
        System.out.println("Deque: " + deque);
        System.out.println("First: " + deque.getFirst());
        System.out.println("Last: " + deque.getLast());
        System.out.println("Remove first: " + deque.removeFirst());
        System.out.println("Remove last: " + deque.removeLast());
        System.out.println("After removal: " + deque);
    }

    private static void demonstrateCollectionsUtility() {
        System.out.println("\n5. COLLECTIONS UTILITY METHODS:");

        List<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9, 3, 7, 4, 6));
        System.out.println("Original list: " + numbers);

        // Sorting
        Collections.sort(numbers);
        System.out.println("After sorting: " + numbers);

        // Reverse sorting
        Collections.sort(numbers, Collections.reverseOrder());
        System.out.println("After reverse sorting: " + numbers);

        // Shuffling
        Collections.shuffle(numbers);
        System.out.println("After shuffling: " + numbers);

        // Finding min and max
        System.out.println("Minimum: " + Collections.min(numbers));
        System.out.println("Maximum: " + Collections.max(numbers));

        // Binary search (list must be sorted)
        Collections.sort(numbers);
        int index = Collections.binarySearch(numbers, 7);
        System.out.println("Index of 7: " + index);

        // Frequency
        numbers.add(5);
        numbers.add(5);
        System.out.println("Frequency of 5: " + Collections.frequency(numbers, 5));

        // Replace all
        Collections.replaceAll(numbers, 5, 50);
        System.out.println("After replacing 5 with 50: " + numbers);

        // Fill
        List<String> stringList = new ArrayList<>(Arrays.asList("A", "B", "C", "D"));
        Collections.fill(stringList, "X");
        System.out.println("After filling with X: " + stringList);
    }

    private static void demonstrateIteration() {
        System.out.println("\n6. ITERATION METHODS:");

        List<String> colors = new ArrayList<>(Arrays.asList("Red", "Green", "Blue", "Yellow", "Purple"));

        // Traditional for loop
        System.out.println("Traditional for loop:");
        for (int i = 0; i < colors.size(); i++) {
            System.out.print(colors.get(i) + " ");
        }

        // Enhanced for loop
        System.out.println("\n\nEnhanced for loop:");
        for (String color : colors) {
            System.out.print(color + " ");
        }

        // Iterator
        System.out.println("\n\nIterator:");
        Iterator<String> iterator = colors.iterator();
        while (iterator.hasNext()) {
            String color = iterator.next();
            System.out.print(color + " ");
        }

        // ListIterator (bidirectional)
        System.out.println("\n\nListIterator (bidirectional):");
        ListIterator<String> listIterator = colors.listIterator();
        System.out.println("Forward:");
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next() + " ");
        }
        System.out.println("\nBackward:");
        while (listIterator.hasPrevious()) {
            System.out.print(listIterator.previous() + " ");
        }

        // forEach with lambda (Java 8+)
        System.out.println("\n\nforEach with lambda:");
        colors.forEach(color -> System.out.print(color + " "));

        // forEach with method reference
        System.out.println("\n\nforEach with method reference:");
        colors.forEach(System.out::print);
    }

    private static void demonstrateCustomObjects() {
        System.out.println("\n7. CUSTOM OBJECTS IN COLLECTIONS:");

        // List of custom objects
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 20, 95.5));
        students.add(new Student("Bob", 19, 87.2));
        students.add(new Student("Charlie", 21, 92.8));
        students.add(new Student("David", 20, 78.9));

        System.out.println("Students list:");
        students.forEach(System.out::println);

        // Sorting by different criteria
        System.out.println("\nSorted by name:");
        students.sort(Comparator.comparing(Student::getName));
        students.forEach(System.out::println);

        System.out.println("\nSorted by age:");
        students.sort(Comparator.comparing(Student::getAge));
        students.forEach(System.out::println);

        System.out.println("\nSorted by score (descending):");
        students.sort(Comparator.comparing(Student::getScore).reversed());
        students.forEach(System.out::println);

        // Using TreeSet with custom objects
        TreeSet<Student> studentSet = new TreeSet<>(Comparator.comparing(Student::getScore));
        studentSet.addAll(students);
        System.out.println("\nStudents in TreeSet (sorted by score):");
        studentSet.forEach(System.out::println);

        // Map with custom objects
        Map<String, Student> studentMap = new HashMap<>();
        for (Student student : students) {
            studentMap.put(student.getName(), student);
        }

        System.out.println("\nStudent map:");
        studentMap.forEach((name, student) ->
                System.out.println(name + " -> " + student));
    }
}

// Custom class for demonstration
class Student {
    private String name;
    private int age;
    private double score;

    public Student(String name, int age, double score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    // Getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getScore() { return score; }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", score=" + score + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return age == student.age &&
                Double.compare(student.score, score) == 0 &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, score);
    }
}
