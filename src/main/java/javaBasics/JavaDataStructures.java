import java.util.*;
import java.util.concurrent.*;

public class JavaDataStructures {
    
    public static void main(String[] args) {
        System.out.println("=== Java Data Structures - Complete Guide ===\n");
        
        // 1. Arrays
        demonstrateArrays();
        
        // 2. ArrayList vs LinkedList
        demonstrateListComparison();
        
        // 3. HashSet vs TreeSet vs LinkedHashSet
        demonstrateSetComparison();
        
        // 4. HashMap vs TreeMap vs LinkedHashMap
        demonstrateMapComparison();
        
        // 5. Queue and Stack
        demonstrateQueueAndStack();
        
        // 6. Priority Queue
        demonstratePriorityQueue();
        
        // 7. When to Use What
        demonstrateWhenToUseWhat();
        
        // 8. Interview Questions
        demonstrateInterviewQuestions();
        
        System.out.println("\n=== Data Structures Tutorial Completed! ===");
    }
    
    private static void demonstrateArrays() {
        System.out.println("1. ARRAYS:");
        
        // Fixed-size array
        int[] numbers = new int[5];
        numbers[0] = 10;
        numbers[1] = 20;
        numbers[2] = 30;
        numbers[3] = 40;
        numbers[4] = 50;
        
        System.out.println("Fixed array: " + Arrays.toString(numbers));
        
        // Array initialization
        String[] fruits = {"Apple", "Banana", "Orange"};
        System.out.println("Fruits array: " + Arrays.toString(fruits));
        
        // 2D Array
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("2D Matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        
        // Array operations
        System.out.println("\nArray Operations:");
        System.out.println("Length: " + numbers.length);
        System.out.println("First element: " + numbers[0]);
        System.out.println("Last element: " + numbers[numbers.length - 1]);
        
        // Array sorting
        Arrays.sort(numbers);
        System.out.println("Sorted array: " + Arrays.toString(numbers));
        
        // Binary search (array must be sorted)
        int index = Arrays.binarySearch(numbers, 30);
        System.out.println("Index of 30: " + index);
        
        System.out.println();
    }
    
    private static void demonstrateListComparison() {
        System.out.println("2. ARRAYLIST vs LINKEDLIST:");
        
        // ArrayList - Dynamic array
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("First");
        arrayList.add("Second");
        arrayList.add("Third");
        
        System.out.println("ArrayList: " + arrayList);
        System.out.println("ArrayList get(1): " + arrayList.get(1));
        
        // LinkedList - Doubly linked list
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("First");
        linkedList.add("Second");
        linkedList.add("Third");
        
        System.out.println("LinkedList: " + linkedList);
        System.out.println("LinkedList get(1): " + linkedList.get(1));
        
        // Performance comparison
        System.out.println("\nPerformance Comparison:");
        
        // ArrayList - Fast random access, slow insertions/deletions
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            arrayList.add(0, "Element" + i); // Add at beginning
        }
        long arrayListTime = System.nanoTime() - startTime;
        
        // LinkedList - Slow random access, fast insertions/deletions
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            linkedList.add(0, "Element" + i); // Add at beginning
        }
        long linkedListTime = System.nanoTime() - startTime;
        
        System.out.println("ArrayList add at beginning: " + arrayListTime + " ns");
        System.out.println("LinkedList add at beginning: " + linkedListTime + " ns");
        System.out.println("LinkedList is " + String.format("%.2f", (double)arrayListTime/linkedListTime) + "x faster for insertions at beginning");
        
        // Random access comparison
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            arrayList.get(i % arrayList.size());
        }
        long arrayListAccessTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            linkedList.get(i % linkedList.size());
        }
        long linkedListAccessTime = System.nanoTime() - startTime;
        
        System.out.println("ArrayList random access: " + arrayListAccessTime + " ns");
        System.out.println("LinkedList random access: " + linkedListAccessTime + " ns");
        System.out.println("ArrayList is " + String.format("%.2f", (double)linkedListAccessTime/arrayListAccessTime) + "x faster for random access");
        
        System.out.println();
    }
    
    private static void demonstrateSetComparison() {
        System.out.println("3. HASHSET vs TREESET vs LINKEDHASHSET:");
        
        // HashSet - Unordered, fastest
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("Zebra");
        hashSet.add("Apple");
        hashSet.add("Banana");
        hashSet.add("Cat");
        
        System.out.println("HashSet (unordered): " + hashSet);
        
        // TreeSet - Sorted, slower
        TreeSet<String> treeSet = new TreeSet<>();
        treeSet.add("Zebra");
        treeSet.add("Apple");
        treeSet.add("Banana");
        treeSet.add("Cat");
        
        System.out.println("TreeSet (sorted): " + treeSet);
        
        // LinkedHashSet - Insertion order, medium speed
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("Zebra");
        linkedHashSet.add("Apple");
        linkedHashSet.add("Banana");
        linkedHashSet.add("Cat");
        
        System.out.println("LinkedHashSet (insertion order): " + linkedHashSet);
        
        // Performance comparison
        System.out.println("\nPerformance Comparison:");
        
        // HashSet performance
        HashSet<Integer> numbersHash = new HashSet<>();
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            numbersHash.add(i);
        }
        long hashSetTime = System.nanoTime() - startTime;
        
        // TreeSet performance
        TreeSet<Integer> numbersTree = new TreeSet<>();
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            numbersTree.add(i);
        }
        long treeSetTime = System.nanoTime() - startTime;
        
        System.out.println("HashSet add time: " + hashSetTime + " ns");
        System.out.println("TreeSet add time: " + treeSetTime + " ns");
        System.out.println("HashSet is " + String.format("%.2f", (double)treeSetTime/hashSetTime) + "x faster for additions");
        
        // TreeSet specific operations
        System.out.println("\nTreeSet specific operations:");
        System.out.println("First element: " + treeSet.first());
        System.out.println("Last element: " + treeSet.last());
        System.out.println("Elements less than 'D': " + treeSet.headSet("D"));
        System.out.println("Elements greater than 'B': " + treeSet.tailSet("B"));
        
        System.out.println();
    }
    
    private static void demonstrateMapComparison() {
        System.out.println("4. HASHMAP vs TREEMAP vs LINKEDHASHMAP:");
        
        // HashMap - Unordered, fastest
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Zebra", 100);
        hashMap.put("Apple", 50);
        hashMap.put("Banana", 75);
        hashMap.put("Cat", 25);
        
        System.out.println("HashMap (unordered): " + hashMap);
        
        // TreeMap - Sorted by keys, slower
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Zebra", 100);
        treeMap.put("Apple", 50);
        treeMap.put("Banana", 75);
        treeMap.put("Cat", 25);
        
        System.out.println("TreeMap (sorted by keys): " + treeMap);
        
        // LinkedHashMap - Insertion order, medium speed
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("Zebra", 100);
        linkedHashMap.put("Apple", 50);
        linkedHashMap.put("Banana", 75);
        linkedHashMap.put("Cat", 25);
        
        System.out.println("LinkedHashMap (insertion order): " + linkedHashMap);
        
        // Performance comparison
        System.out.println("\nPerformance Comparison:");
        
        // HashMap performance
        HashMap<Integer, String> numbersHash = new HashMap<>();
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            numbersHash.put(i, "Value" + i);
        }
        long hashMapTime = System.nanoTime() - startTime;
        
        // TreeMap performance
        TreeMap<Integer, String> numbersTree = new TreeMap<>();
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            numbersTree.put(i, "Value" + i);
        }
        long treeMapTime = System.nanoTime() - startTime;
        
        System.out.println("HashMap put time: " + hashMapTime + " ns");
        System.out.println("TreeMap put time: " + treeMapTime + " ns");
        System.out.println("HashMap is " + String.format("%.2f", (double)treeMapTime/hashMapTime) + "x faster for additions");
        
        // TreeMap specific operations
        System.out.println("\nTreeMap specific operations:");
        System.out.println("First key: " + treeMap.firstKey());
        System.out.println("Last key: " + treeMap.lastKey());
        System.out.println("Keys less than 'D': " + treeMap.headMap("D").keySet());
        System.out.println("Keys greater than 'B': " + treeMap.tailMap("B").keySet());
        
        System.out.println();
    }
    
    private static void demonstrateQueueAndStack() {
        System.out.println("5. QUEUE AND STACK:");
        
        // Queue - FIFO (First In First Out)
        Queue<String> queue = new LinkedList<>();
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");
        
        System.out.println("Queue: " + queue);
        System.out.println("Peek (first element): " + queue.peek());
        System.out.println("Poll (remove first): " + queue.poll());
        System.out.println("Queue after poll: " + queue);
        
        // Stack - LIFO (Last In First Out) - Using Deque
        Deque<String> stack = new LinkedList<>();
        stack.push("First");
        stack.push("Second");
        stack.push("Third");
        
        System.out.println("\nStack: " + stack);
        System.out.println("Peek (top element): " + stack.peek());
        System.out.println("Pop (remove top): " + stack.pop());
        System.out.println("Stack after pop: " + stack);
        
        // ArrayDeque - More efficient than LinkedList for stack/queue
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast("First");  // Add to end
        arrayDeque.addLast("Second");
        arrayDeque.addLast("Third");
        
        System.out.println("\nArrayDeque as Queue: " + arrayDeque);
        System.out.println("Remove from front: " + arrayDeque.removeFirst());
        System.out.println("ArrayDeque after removal: " + arrayDeque);
        
        System.out.println();
    }
    
    private static void demonstratePriorityQueue() {
        System.out.println("6. PRIORITY QUEUE:");
        
        // Natural ordering (ascending)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(30);
        minHeap.offer(10);
        minHeap.offer(50);
        minHeap.offer(20);
        minHeap.offer(40);
        
        System.out.println("Min Heap (natural ordering): " + minHeap);
        System.out.println("Peek (smallest): " + minHeap.peek());
        
        // Custom ordering (descending)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.offer(30);
        maxHeap.offer(10);
        maxHeap.offer(50);
        maxHeap.offer(20);
        maxHeap.offer(40);
        
        System.out.println("Max Heap (reverse ordering): " + maxHeap);
        System.out.println("Peek (largest): " + maxHeap.peek());
        
        // Custom comparator
        PriorityQueue<String> customOrder = new PriorityQueue<>((s1, s2) -> s1.length() - s2.length());
        customOrder.offer("LongerString");
        customOrder.offer("Short");
        customOrder.offer("MediumLength");
        customOrder.offer("A");
        
        System.out.println("Custom order (by length): " + customOrder);
        
        // Processing elements in priority order
        System.out.println("\nProcessing min heap in order:");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println();
        
        System.out.println();
    }
    
    private static void demonstrateWhenToUseWhat() {
        System.out.println("7. WHEN TO USE WHAT:");
        
        System.out.println("\nARRAYS:");
        System.out.println("✅ Use when:");
        System.out.println("   - Fixed size is known");
        System.out.println("   - Need primitive types");
        System.out.println("   - Want maximum performance");
        System.out.println("   - Working with 2D/3D data");
        System.out.println("❌ Avoid when:");
        System.out.println("   - Size is unknown");
        System.out.println("   - Need dynamic resizing");
        
        System.out.println("\nARRAYLIST:");
        System.out.println("✅ Use when:");
        System.out.println("   - Need dynamic resizing");
        System.out.println("   - Frequent random access");
        System.out.println("   - Adding/removing at end");
        System.out.println("❌ Avoid when:");
        System.out.println("   - Frequent insertions/deletions at beginning/middle");
        
        System.out.println("\nLINKEDLIST:");
        System.out.println("✅ Use when:");
        System.out.println("   - Frequent insertions/deletions");
        System.out.println("   - Need to implement stack/queue");
        System.out.println("   - Working with doubly linked list");
        System.out.println("❌ Avoid when:");
        System.out.println("   - Frequent random access");
        System.out.println("   - Memory is a concern");
        
        System.out.println("\nHASHSET:");
        System.out.println("✅ Use when:");
        System.out.println("   - Need unique elements");
        System.out.println("   - Order doesn't matter");
        System.out.println("   - Fast add/remove/contains");
        System.out.println("❌ Avoid when:");
        System.out.println("   - Need sorted elements");
        System.out.println("   - Need insertion order");
        
        System.out.println("\nTREESET:");
        System.out.println("✅ Use when:");
        System.out.println("   - Need sorted elements");
        System.out.println("   - Need range operations");
        System.out.println("   - Need first/last elements");
        System.out.println("❌ Avoid when:");
        System.out.println("   - Order doesn't matter");
        System.out.println("   - Need maximum performance");
        
        System.out.println("\nHASHMAP:");
        System.out.println("✅ Use when:");
        System.out.println("   - Need key-value pairs");
        System.out.println("   - Order doesn't matter");
        System.out.println("   - Fast get/put operations");
        System.out.println("❌ Avoid when:");
        System.out.println("   - Need sorted keys");
        System.out.println("   - Need insertion order");
        
        System.out.println("\nTREEMAP:");
        System.out.println("✅ Use when:");
        System.out.println("   - Need sorted keys");
        System.out.println("   - Need range operations");
        System.out.println("   - Need first/last keys");
        System.out.println("❌ Avoid when:");
        System.out.println("   - Order doesn't matter");
        System.out.println("   - Need maximum performance");
        
        System.out.println("\nPRIORITY QUEUE:");
        System.out.println("✅ Use when:");
        System.out.println("   - Need heap data structure");
        System.out.println("   - Need min/max element quickly");
        System.out.println("   - Implementing algorithms like Dijkstra");
        System.out.println("❌ Avoid when:");
        System.out.println("   - Need random access");
        System.out.println("   - Order doesn't matter");
        
        System.out.println();
    }
    
    private static void demonstrateInterviewQuestions() {
        System.out.println("8. INTERVIEW QUESTIONS AND SCENARIOS:");
        
        // 1. Find first non-repeating character
        System.out.println("\nQ1: Find first non-repeating character in string:");
        String str = "leetcode";
        char result = findFirstNonRepeating(str);
        System.out.println("String: " + str + ", First non-repeating: " + result);
        
        // 2. Check if array contains duplicates
        System.out.println("\nQ2: Check if array contains duplicates:");
        int[] nums = {1, 2, 3, 1, 4, 5};
        boolean hasDuplicates = containsDuplicates(nums);
        System.out.println("Array: " + Arrays.toString(nums) + ", Has duplicates: " + hasDuplicates);
        
        // 3. Find missing number in array
        System.out.println("\nQ3: Find missing number in array (0 to n):");
        int[] missingNums = {3, 0, 1};
        int missing = findMissingNumber(missingNums);
        System.out.println("Array: " + Arrays.toString(missingNums) + ", Missing: " + missing);
        
        // 4. Two Sum problem
        System.out.println("\nQ4: Find two numbers that sum to target:");
        int[] twoSumNums = {2, 7, 11, 15};
        int target = 9;
        int[] twoSumResult = twoSum(twoSumNums, target);
        System.out.println("Array: " + Arrays.toString(twoSumNums) + ", Target: " + target);
        System.out.println("Indices: " + Arrays.toString(twoSumResult));
        
        // 5. Valid parentheses
        System.out.println("\nQ5: Check if parentheses are valid:");
        String parentheses = "({[]})";
        boolean isValid = isValidParentheses(parentheses);
        System.out.println("String: " + parentheses + ", Valid: " + isValid);
        
        // 6. Implement LRU Cache
        System.out.println("\nQ6: LRU Cache implementation:");
        LRUCache<Integer, String> lruCache = new LRUCache<>(3);
        lruCache.put(1, "One");
        lruCache.put(2, "Two");
        lruCache.put(3, "Three");
        System.out.println("Cache after adding 3 elements: " + lruCache);
        lruCache.put(4, "Four"); // This will evict key 1
        System.out.println("Cache after adding 4th element: " + lruCache);
        System.out.println("Get key 2: " + lruCache.get(2));
        System.out.println("Cache after accessing key 2: " + lruCache);
        
        // 7. Find Kth largest element
        System.out.println("\nQ7: Find Kth largest element:");
        int[] kthNums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        int kthLargest = findKthLargest(kthNums, k);
        System.out.println("Array: " + Arrays.toString(kthNums) + ", " + k + "nd largest: " + kthLargest);
        
        // 8. Merge K sorted lists
        System.out.println("\nQ8: Merge K sorted arrays:");
        int[][] kSortedArrays = {
            {1, 4, 5},
            {1, 3, 4},
            {2, 6}
        };
        List<Integer> merged = mergeKSortedArrays(kSortedArrays);
        System.out.println("K sorted arrays: " + Arrays.deepToString(kSortedArrays));
        System.out.println("Merged result: " + merged);
        
        System.out.println();
    }
    
    // Helper methods for interview questions
    private static char findFirstNonRepeating(String s) {
        Map<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        
        for (char c : s.toCharArray()) {
            if (count.get(c) == 1) {
                return c;
            }
        }
        return ' ';
    }
    
    private static boolean containsDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }
    
    private static int findMissingNumber(int[] nums) {
        int expectedSum = nums.length * (nums.length + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }
    
    private static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
    
    private static boolean isValidParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put('}', '{');
        pairs.put(']', '[');
        
        for (char c : s.toCharArray()) {
            if (pairs.containsValue(c)) {
                stack.push(c);
            } else if (pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
    
    private static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }
    
    private static List<Integer> mergeKSortedArrays(int[][] arrays) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        List<Integer> result = new ArrayList<>();
        
        // Add first element from each array with array index and element index
        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new int[]{arrays[i][0], i, 0});
            }
        }
        
        while (!minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            result.add(current[0]);
            
            int arrayIndex = current[1];
            int elementIndex = current[2];
            
            if (elementIndex + 1 < arrays[arrayIndex].length) {
                minHeap.offer(new int[]{arrays[arrayIndex][elementIndex + 1], arrayIndex, elementIndex + 1});
            }
        }
        
        return result;
    }
    
    // LRU Cache implementation
    static class LRUCache<K, V> extends LinkedHashMap<K, V> {
        private final int capacity;
        
        public LRUCache(int capacity) {
            super(capacity, 0.75f, true);
            this.capacity = capacity;
        }
        
        @Override
        protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }
}
