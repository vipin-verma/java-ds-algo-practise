import java.util.*;
import java.util.concurrent.*;

/**
 * Question 25: Java Collections Framework
 * 
 * This file contains 50+ Collections Framework interview questions covering:
 * - List, Set, Map, Queue, Deque Interfaces
 * - ArrayList, LinkedList, HashSet, TreeSet, HashMap, TreeMap
 * - Concurrent Collections and Thread Safety
 * - Collections Utility Methods
 * - Performance Characteristics and Best Practices
 */
public class Question025_JavaCollectionsFramework {
    
    public static void main(String[] args) {
        System.out.println("=== Java Collections Framework - Interview Questions ===\n");
        
        demonstrateListInterface();
        demonstrateSetInterface();
        demonstrateMapInterface();
        demonstrateQueueInterface();
        demonstrateDequeInterface();
        demonstrateCollectionsUtility();
        demonstrateConcurrentCollections();
        demonstratePerformanceComparison();
        
        System.out.println("\n=== Java Collections Framework Completed! ===");
    }
    
    // ===== LIST INTERFACE =====
    
    private static void demonstrateListInterface() {
        System.out.println("1. LIST INTERFACE:\n");
        
        // Q1: What is a List?
        System.out.println("Q1: What is a List?");
        System.out.println("    List is an ordered collection that allows duplicate elements.");
        System.out.println("    Elements can be accessed by their position (index).");
        System.out.println("    Main implementations: ArrayList, LinkedList, Vector\n");
        
        // Q2: What is the difference between ArrayList and LinkedList?
        System.out.println("Q2: What is the difference between ArrayList and LinkedList?");
        System.out.println("    ArrayList: Dynamic array, fast random access, slow insertions/deletions");
        System.out.println("    LinkedList: Doubly linked list, slow random access, fast insertions/deletions");
        System.out.println("    ArrayList: Better for frequent access, LinkedList: Better for frequent modifications\n");
        
        // Q3: When to use ArrayList vs LinkedList?
        System.out.println("Q3: When to use ArrayList vs LinkedList?");
        System.out.println("    ArrayList: When you need frequent random access to elements");
        System.out.println("    LinkedList: When you need frequent insertions/deletions in the middle");
        System.out.println("    ArrayList: Better memory usage, LinkedList: Better for frequent modifications\n");
        
        // Demonstrate ArrayList
        System.out.println("Example: ArrayList Operations");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Apple");
        arrayList.add("Banana");
        arrayList.add("Cherry");
        arrayList.add(1, "Blueberry"); // Insert at specific index
        
        System.out.println("    ArrayList: " + arrayList);
        System.out.println("    Size: " + arrayList.size());
        System.out.println("    Element at index 2: " + arrayList.get(2));
        System.out.println("    Contains 'Apple': " + arrayList.contains("Apple"));
        
        arrayList.remove("Banana");
        System.out.println("    After removing 'Banana': " + arrayList + "\n");
        
        // Demonstrate LinkedList
        System.out.println("Example: LinkedList Operations");
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.addFirst("First");
        linkedList.addLast("Last");
        linkedList.add("Middle");
        
        System.out.println("    LinkedList: " + linkedList);
        System.out.println("    First element: " + linkedList.getFirst());
        System.out.println("    Last element: " + linkedList.getLast());
        
        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println("    After removing first and last: " + linkedList + "\n");
    }
    
    // ===== SET INTERFACE =====
    
    private static void demonstrateSetInterface() {
        System.out.println("2. SET INTERFACE:\n");
        
        // Q4: What is a Set?
        System.out.println("Q4: What is a Set?");
        System.out.println("    Set is a collection that contains no duplicate elements.");
        System.out.println("    It models the mathematical set abstraction.");
        System.out.println("    Main implementations: HashSet, TreeSet, LinkedHashSet\n");
        
        // Q5: What is the difference between HashSet and TreeSet?
        System.out.println("Q5: What is the difference between HashSet and TreeSet?");
        System.out.println("    HashSet: Unordered, O(1) average time for add/remove/contains");
        System.out.println("    TreeSet: Sorted order, O(log n) time for add/remove/contains");
        System.out.println("    HashSet: Better performance, TreeSet: Guaranteed sorted order\n");
        
        // Q6: When to use HashSet vs TreeSet?
        System.out.println("Q6: When to use HashSet vs TreeSet?");
        System.out.println("    HashSet: When you need fast operations and don't care about order");
        System.out.println("    TreeSet: When you need elements in sorted order");
        System.out.println("    HashSet: Better performance, TreeSet: Ordered iteration\n");
        
        // Demonstrate HashSet
        System.out.println("Example: HashSet Operations");
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Apple"); // Duplicate will be ignored
        hashSet.add("Cherry");
        
        System.out.println("    HashSet: " + hashSet);
        System.out.println("    Size: " + hashSet.size());
        System.out.println("    Contains 'Apple': " + hashSet.contains("Apple"));
        
        hashSet.remove("Banana");
        System.out.println("    After removing 'Banana': " + hashSet + "\n");
        
        // Demonstrate TreeSet
        System.out.println("Example: TreeSet Operations");
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(5);
        treeSet.add(2);
        treeSet.add(8);
        treeSet.add(1);
        
        System.out.println("    TreeSet (sorted): " + treeSet);
        System.out.println("    First element: " + treeSet.first());
        System.out.println("    Last element: " + treeSet.last());
        System.out.println("    Elements less than 5: " + treeSet.headSet(5));
        System.out.println("    Elements greater than 2: " + treeSet.tailSet(2) + "\n");
    }
    
    // ===== MAP INTERFACE =====
    
    private static void demonstrateMapInterface() {
        System.out.println("3. MAP INTERFACE:\n");
        
        // Q7: What is a Map?
        System.out.println("Q7: What is a Map?");
        System.out.println("    Map is an object that maps keys to values.");
        System.out.println("    It cannot contain duplicate keys, but can contain duplicate values.");
        System.out.println("    Main implementations: HashMap, TreeMap, LinkedHashMap\n");
        
        // Q8: What is the difference between HashMap and TreeMap?
        System.out.println("Q8: What is the difference between HashMap and TreeMap?");
        System.out.println("    HashMap: Unordered, O(1) average time for get/put/remove");
        System.out.println("    TreeMap: Sorted by keys, O(log n) time for get/put/remove");
        System.out.println("    HashMap: Better performance, TreeMap: Guaranteed sorted order\n");
        
        // Q9: How does HashMap work internally?
        System.out.println("Q9: How does HashMap work internally?");
        System.out.println("    HashMap uses an array of linked lists (buckets)");
        System.out.println("    Keys are hashed to determine bucket index");
        System.out.println("    Collisions are handled by chaining (linked list in bucket)");
        System.out.println("    Load factor determines when to resize\n");
        
        // Demonstrate HashMap
        System.out.println("Example: HashMap Operations");
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Apple", 1);
        hashMap.put("Banana", 2);
        hashMap.put("Cherry", 3);
        hashMap.put("Apple", 4); // Overwrites previous value
        
        System.out.println("    HashMap: " + hashMap);
        System.out.println("    Size: " + hashMap.size());
        System.out.println("    Value for 'Apple': " + hashMap.get("Apple"));
        System.out.println("    Contains key 'Banana': " + hashMap.containsKey("Banana"));
        System.out.println("    Contains value 2: " + hashMap.containsValue(2));
        
        hashMap.remove("Banana");
        System.out.println("    After removing 'Banana': " + hashMap + "\n");
        
        // Demonstrate TreeMap
        System.out.println("Example: TreeMap Operations");
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Zebra", 26);
        treeMap.put("Apple", 1);
        treeMap.put("Banana", 2);
        
        System.out.println("    TreeMap (sorted by keys): " + treeMap);
        System.out.println("    First key: " + treeMap.firstKey());
        System.out.println("    Last key: " + treeMap.lastKey());
        System.out.println("    Keys less than 'C': " + treeMap.headMap("C").keySet() + "\n");
    }
    
    // ===== QUEUE INTERFACE =====
    
    private static void demonstrateQueueInterface() {
        System.out.println("4. QUEUE INTERFACE:\n");
        
        // Q10: What is a Queue?
        System.out.println("Q10: What is a Queue?");
        System.out.println("    Queue is a collection designed for holding elements prior to processing.");
        System.out.println("    It follows FIFO (First-In-First-Out) principle.");
        System.out.println("    Main implementations: LinkedList, PriorityQueue, ArrayDeque\n");
        
        // Q11: What is the difference between Queue and Stack?
        System.out.println("Q11: What is the difference between Queue and Stack?");
        System.out.println("    Queue: FIFO (First-In-First-Out) - like a line of people");
        System.out.println("    Stack: LIFO (Last-In-First-Out) - like a stack of plates");
        System.out.println("    Queue: poll() removes first element, Stack: pop() removes last element\n");
        
        // Q12: What is PriorityQueue?
        System.out.println("Q12: What is PriorityQueue?");
        System.out.println("    PriorityQueue is an unbounded priority queue based on priority heap.");
        System.out.println("    Elements are ordered according to their natural ordering or comparator.");
        System.out.println("    Head element is the least element with respect to the ordering.\n");
        
        // Demonstrate Queue
        System.out.println("Example: Queue Operations");
        Queue<String> queue = new LinkedList<>();
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");
        
        System.out.println("    Queue: " + queue);
        System.out.println("    Peek (first element): " + queue.peek());
        System.out.println("    Poll (remove first): " + queue.poll());
        System.out.println("    Queue after poll: " + queue + "\n");
        
        // Demonstrate PriorityQueue
        System.out.println("Example: PriorityQueue Operations");
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(5);
        priorityQueue.offer(2);
        priorityQueue.offer(8);
        priorityQueue.offer(1);
        
        System.out.println("    PriorityQueue: " + priorityQueue);
        System.out.println("    Peek (smallest): " + priorityQueue.peek());
        
        System.out.println("    Polling all elements (in order):");
        while (!priorityQueue.isEmpty()) {
            System.out.println("      " + priorityQueue.poll());
        }
        System.out.println();
    }
    
    // ===== DEQUE INTERFACE =====
    
    private static void demonstrateDequeInterface() {
        System.out.println("5. DEQUE INTERFACE:\n");
        
        // Q13: What is a Deque?
        System.out.println("Q13: What is a Deque?");
        System.out.println("    Deque (Double-ended queue) allows insertion and removal at both ends.");
        System.out.println("    It can be used as both Queue (FIFO) and Stack (LIFO).");
        System.out.println("    Main implementation: ArrayDeque\n");
        
        // Q14: When to use Deque?
        System.out.println("Q14: When to use Deque?");
        System.out.println("    When you need to add/remove elements from both ends");
        System.out.println("    When you need to implement both Queue and Stack behavior");
        System.out.println("    When you need a resizable array-based implementation\n");
        
        // Demonstrate Deque
        System.out.println("Example: Deque Operations");
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("First");
        deque.addLast("Last");
        deque.addFirst("NewFirst");
        deque.addLast("NewLast");
        
        System.out.println("    Deque: " + deque);
        System.out.println("    First element: " + deque.getFirst());
        System.out.println("    Last element: " + deque.getLast());
        
        System.out.println("    Remove first: " + deque.removeFirst());
        System.out.println("    Remove last: " + deque.removeLast());
        System.out.println("    Deque after removals: " + deque + "\n");
    }
    
    // ===== COLLECTIONS UTILITY =====
    
    private static void demonstrateCollectionsUtility() {
        System.out.println("6. COLLECTIONS UTILITY:\n");
        
        // Q15: What are Collections utility methods?
        System.out.println("Q15: What are Collections utility methods?");
        System.out.println("    Collections class provides static methods for common operations");
        System.out.println("    on collections: sorting, searching, synchronization, etc.");
        System.out.println("    Examples: sort(), binarySearch(), reverse(), shuffle()\n");
        
        // Q16: How to sort a List?
        System.out.println("Q16: How to sort a List?");
        System.out.println("    Use Collections.sort() for natural ordering");
        System.out.println("    Use Collections.sort(list, comparator) for custom ordering");
        System.out.println("    Use list.sort(comparator) for in-place sorting\n");
        
        // Demonstrate Collections utility methods
        System.out.println("Example: Collections Utility Methods");
        List<String> list = Arrays.asList("Zebra", "Apple", "Banana", "Cherry");
        System.out.println("    Original list: " + list);
        
        // Sort
        List<String> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList);
        System.out.println("    Sorted (natural order): " + sortedList);
        
        // Reverse
        Collections.reverse(sortedList);
        System.out.println("    Reversed: " + sortedList);
        
        // Shuffle
        Collections.shuffle(sortedList);
        System.out.println("    Shuffled: " + sortedList);
        
        // Binary search (list must be sorted)
        Collections.sort(sortedList);
        int index = Collections.binarySearch(sortedList, "Banana");
        System.out.println("    'Banana' found at index: " + index);
        
        // Min and Max
        System.out.println("    Min: " + Collections.min(sortedList));
        System.out.println("    Max: " + Collections.max(sortedList) + "\n");
    }
    
    // ===== CONCURRENT COLLECTIONS =====
    
    private static void demonstrateConcurrentCollections() {
        System.out.println("7. CONCURRENT COLLECTIONS:\n");
        
        // Q17: What are concurrent collections?
        System.out.println("Q17: What are concurrent collections?");
        System.out.println("    Concurrent collections are thread-safe collections designed");
        System.out.println("    for use in multi-threaded environments.");
        System.out.println("    Examples: ConcurrentHashMap, CopyOnWriteArrayList, BlockingQueue\n");
        
        // Q18: What is the difference between HashMap and ConcurrentHashMap?
        System.out.println("Q18: What is the difference between HashMap and ConcurrentHashMap?");
        System.out.println("    HashMap: Not thread-safe, can cause ConcurrentModificationException");
        System.out.println("    ConcurrentHashMap: Thread-safe, allows concurrent read/write operations");
        System.out.println("    ConcurrentHashMap: Better performance than synchronized HashMap\n");
        
        // Q19: What is CopyOnWriteArrayList?
        System.out.println("Q19: What is CopyOnWriteArrayList?");
        System.out.println("    CopyOnWriteArrayList creates a new copy of the array on each modification.");
        System.out.println("    It's thread-safe and good for scenarios with more reads than writes.");
        System.out.println("    Modifications are expensive but reads are fast and thread-safe.\n");
        
        // Demonstrate ConcurrentHashMap
        System.out.println("Example: ConcurrentHashMap");
        ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("Apple", 1);
        concurrentMap.put("Banana", 2);
        concurrentMap.put("Cherry", 3);
        
        System.out.println("    ConcurrentHashMap: " + concurrentMap);
        System.out.println("    Size: " + concurrentMap.size());
        
        // Atomic operations
        concurrentMap.putIfAbsent("Apple", 5); // Won't overwrite
        concurrentMap.replace("Banana", 2, 20); // Replace if value matches
        System.out.println("    After atomic operations: " + concurrentMap + "\n");
        
        // Demonstrate CopyOnWriteArrayList
        System.out.println("Example: CopyOnWriteArrayList");
        CopyOnWriteArrayList<String> copyOnWriteList = new CopyOnWriteArrayList<>();
        copyOnWriteList.add("Apple");
        copyOnWriteList.add("Banana");
        copyOnWriteList.add("Cherry");
        
        System.out.println("    CopyOnWriteArrayList: " + copyOnWriteList);
        System.out.println("    Size: " + copyOnWriteList.size());
        
        // Safe iteration (no ConcurrentModificationException)
        for (String item : copyOnWriteList) {
            if (item.equals("Banana")) {
                copyOnWriteList.add("NewFruit"); // Safe to modify during iteration
            }
        }
        System.out.println("    After safe modification: " + copyOnWriteList + "\n");
    }
    
    // ===== PERFORMANCE COMPARISON =====
    
    private static void demonstratePerformanceComparison() {
        System.out.println("8. PERFORMANCE COMPARISON:\n");
        
        // Q20: What are the performance characteristics of different collections?
        System.out.println("Q20: What are the performance characteristics of different collections?");
        System.out.println("    ArrayList: O(1) get/set, O(n) add/remove in middle");
        System.out.println("    LinkedList: O(n) get/set, O(1) add/remove at ends");
        System.out.println("    HashMap: O(1) average get/put/remove");
        System.out.println("    TreeMap: O(log n) get/put/remove");
        System.out.println("    HashSet: O(1) average add/remove/contains");
        System.out.println("    TreeSet: O(log n) add/remove/contains\n");
        
        // Demonstrate performance comparison
        System.out.println("Example: Performance Comparison");
        
        // ArrayList vs LinkedList performance
        int size = 100000;
        
        // ArrayList performance
        long startTime = System.currentTimeMillis();
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            arrayList.add(i);
        }
        long arrayListTime = System.currentTimeMillis() - startTime;
        
        // LinkedList performance
        startTime = System.currentTimeMillis();
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            linkedList.add(i);
        }
        long linkedListTime = System.currentTimeMillis() - startTime;
        
        System.out.println("    Adding " + size + " elements:");
        System.out.println("      ArrayList: " + arrayListTime + "ms");
        System.out.println("      LinkedList: " + linkedListTime + "ms");
        
        // HashMap vs TreeMap performance
        startTime = System.currentTimeMillis();
        HashMap<Integer, String> hashMap = new HashMap<>();
        for (int i = 0; i < size; i++) {
            hashMap.put(i, "Value" + i);
        }
        long hashMapTime = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        for (int i = 0; i < size; i++) {
            treeMap.put(i, "Value" + i);
        }
        long treeMapTime = System.currentTimeMillis() - startTime;
        
        System.out.println("    Adding " + size + " key-value pairs:");
        System.out.println("      HashMap: " + hashMapTime + "ms");
        System.out.println("      TreeMap: " + treeMapTime + "ms");
        System.out.println("    HashMap is faster but unordered, TreeMap is slower but ordered\n");
    }
}

/**
 * INTERVIEW QUESTIONS FOR JAVA COLLECTIONS FRAMEWORK:
 * 
 * BEGINNER LEVEL (1-20):
 * 1. What is the Collections Framework in Java?
 * 2. What are the main interfaces in Collections Framework?
 * 3. What is the difference between Collection and Collections?
 * 4. What is a List and what are its characteristics?
 * 5. What is the difference between ArrayList and LinkedList?
 * 6. When would you use ArrayList vs LinkedList?
 * 7. What is a Set and what are its characteristics?
 * 8. What is the difference between HashSet and TreeSet?
 * 9. When would you use HashSet vs TreeSet?
 * 10. What is a Map and what are its characteristics?
 * 11. What is the difference between HashMap and TreeMap?
 * 12. When would you use HashMap vs TreeMap?
 * 13. What is a Queue and what are its characteristics?
 * 14. What is the difference between Queue and Stack?
 * 15. What is PriorityQueue and when to use it?
 * 16. What is a Deque and when to use it?
 * 17. What are the main implementations of each interface?
 * 18. How do you iterate over collections?
 * 19. What is the difference between Iterator and ListIterator?
 * 20. How do you convert between different collection types?
 * 
 * INTERMEDIATE LEVEL (21-40):
 * 21. How does HashMap work internally?
 * 22. What is hashing and how does it work?
 * 23. What are hash collisions and how are they handled?
 * 24. What is the load factor in HashMap?
 * 25. How does TreeMap maintain sorted order?
 * 26. What is the difference between Comparable and Comparator?
 * 27. How do you implement custom sorting for collections?
 * 28. What are fail-fast and fail-safe iterators?
 * 29. What is ConcurrentModificationException?
 * 30. How do you avoid ConcurrentModificationException?
 * 31. What are the performance characteristics of different collections?
 * 32. How do you choose the right collection for your use case?
 * 33. What is the difference between Vector and ArrayList?
 * 34. What is the difference between Hashtable and HashMap?
 * 35. What are the thread-safe alternatives to standard collections?
 * 36. How do you synchronize collections?
 * 37. What is the difference between synchronized and concurrent collections?
 * 38. How do you implement custom collections?
 * 39. What are the benefits of using generics with collections?
 * 40. How do you handle null values in different collections?
 * 
 * ADVANCED LEVEL (41-60):
 * 41. How do you implement a custom HashMap?
 * 42. How do you implement a custom TreeMap?
 * 43. How do you implement a custom ArrayList?
 * 44. How do you implement a custom LinkedList?
 * 45. What is the difference between shallow and deep copying of collections?
 * 46. How do you implement custom iterators?
 * 47. How do you implement custom comparators?
 * 48. What is the difference between equals() and hashCode() in collections?
 * 49. How do you implement a custom Set?
 * 50. How do you implement a custom Queue?
 * 51. What are the memory implications of different collections?
 * 52. How do you optimize collection performance?
 * 53. What are the trade-offs between different collection implementations?
 * 54. How do you implement a thread-safe collection?
 * 55. What is the difference between CopyOnWriteArrayList and synchronized List?
 * 56. How do you implement a custom concurrent collection?
 * 57. What are the performance implications of concurrent collections?
 * 58. How do you implement a custom Map with custom key objects?
 * 59. What is the difference between WeakHashMap and HashMap?
 * 60. How do you implement a custom collection that maintains order?
 * 
 * EXPERT LEVEL (61-80):
 * 61. How do you implement a distributed cache using collections?
 * 62. How do you implement a custom garbage collection for collections?
 * 63. How do you implement a custom serialization for collections?
 * 64. How do you implement a custom persistence layer for collections?
 * 65. How do you implement a custom indexing system using collections?
 * 66. How do you implement a custom search engine using collections?
 * 67. How do you implement a custom database using collections?
 * 68. How do you implement a custom message queue using collections?
 * 69. How do you implement a custom workflow engine using collections?
 * 70. How do you implement a custom rule engine using collections?
 * 71. How do you implement a custom event system using collections?
 * 72. How do you implement a custom state machine using collections?
 * 73. How do you implement a custom parser using collections?
 * 74. How do you implement a custom compiler using collections?
 * 75. How do you implement a custom interpreter using collections?
 * 76. How do you implement a custom virtual machine using collections?
 * 77. How do you implement a custom operating system using collections?
 * 78. How do you implement a custom network protocol using collections?
 * 79. How do you implement a custom security system using collections?
 * 80. How do you implement a custom encryption system using collections?
 * 
 * SYSTEM DESIGN LEVEL (81-100):
 * 81. How would you design a high-performance caching system using collections?
 * 82. Design a distributed collection system that works across multiple JVMs.
 * 83. How would you implement a real-time data processing system using collections?
 * 84. Design a collection system that supports ACID transactions.
 * 85. How would you implement a collection system that supports versioning?
 * 86. Design a collection system that supports time-based expiration.
 * 87. How would you implement a collection system that supports compression?
 * 88. Design a collection system that supports encryption.
 * 89. How would you implement a collection system that supports replication?
 * 90. Design a collection system that supports sharding.
 * 91. How would you implement a collection system that supports load balancing?
 * 92. Design a collection system that supports fault tolerance.
 * 93. How would you implement a collection system that supports monitoring?
 * 94. Design a collection system that supports alerting.
 * 95. How would you implement a collection system that supports logging?
 * 96. Design a collection system that supports auditing.
 * 97. How would you implement a collection system that supports backup and recovery?
 * 98. Design a collection system that supports disaster recovery.
 * 99. How would you implement a collection system that supports scaling?
 * 100. Design a collection system that supports high availability.
 */
