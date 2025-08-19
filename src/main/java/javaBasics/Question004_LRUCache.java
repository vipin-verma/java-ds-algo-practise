/**
 * Question 4: LRU Cache Implementation
 * 
 * Problem: Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 * 
 * Requirements:
 * - get(key) - Get the value of the key if the key exists in the cache
 * - put(key, value) - Set or insert the value if the key is not already present
 * - When the cache reached its capacity, it should invalidate the least recently used item
 * - All operations must be O(1) time complexity
 * 
 * Difficulty: Medium
 * Category: Design, Data Structures
 * Experience Level: 3-5 years
 */
public class Question004_LRUCache {
    
    public static void main(String[] args) {
        System.out.println("=== LRU Cache Implementation ===\n");
        
        // Test basic operations
        testBasicOperations();
        
        // Test capacity management
        testCapacityManagement();
        
        // Test performance
        testPerformance();
        
        // Test edge cases
        testEdgeCases();
    }
    
    private static void testBasicOperations() {
        System.out.println("=== Basic Operations Test ===");
        
        LRUCache<Integer, String> cache = new LRUCache<>(3);
        
        // Put operations
        cache.put(1, "One");
        cache.put(2, "Two");
        cache.put(3, "Three");
        
        System.out.println("Cache after adding 3 elements: " + cache);
        System.out.println("Size: " + cache.size());
        
        // Get operations
        System.out.println("Get key 1: " + cache.get(1));
        System.out.println("Get key 2: " + cache.get(2));
        System.out.println("Get non-existent key 5: " + cache.get(5));
        
        // Access pattern affects LRU order
        cache.get(1); // Move key 1 to front
        System.out.println("Cache after accessing key 1: " + cache);
        
        cache.put(4, "Four"); // This should evict key 2 (least recently used)
        System.out.println("Cache after adding key 4: " + cache);
        System.out.println("Size: " + cache.size());
        
        System.out.println("---");
    }
    
    private static void testCapacityManagement() {
        System.out.println("=== Capacity Management Test ===");
        
        LRUCache<String, Integer> cache = new LRUCache<>(2);
        
        cache.put("A", 1);
        cache.put("B", 2);
        System.out.println("Cache: " + cache);
        
        cache.put("C", 3); // Should evict "A"
        System.out.println("Cache after adding C: " + cache);
        
        cache.get("B"); // Access B to make it most recently used
        cache.put("D", 4); // Should evict "C"
        System.out.println("Cache after adding D: " + cache);
        
        System.out.println("---");
    }
    
    private static void testPerformance() {
        System.out.println("=== Performance Test ===");
        
        int capacity = 10000;
        LRUCache<Integer, String> cache = new LRUCache<>(capacity);
        
        // Fill cache
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < capacity; i++) {
            cache.put(i, "Value" + i);
        }
        long putTime = System.currentTimeMillis() - startTime;
        
        // Access operations
        startTime = System.currentTimeMillis();
        for (int i = 0; i < capacity; i++) {
            cache.get(i);
        }
        long getTime = System.currentTimeMillis() - startTime;
        
        // Eviction operations
        startTime = System.currentTimeMillis();
        for (int i = capacity; i < capacity + 1000; i++) {
            cache.put(i, "Value" + i);
        }
        long evictionTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Put " + capacity + " elements: " + putTime + "ms");
        System.out.println("Get " + capacity + " elements: " + getTime + "ms");
        System.out.println("Eviction of 1000 elements: " + evictionTime + "ms");
        System.out.println("Final size: " + cache.size());
        System.out.println("All operations O(1): " + (putTime < 100 && getTime < 100 && evictionTime < 100));
        
        System.out.println("---");
    }
    
    private static void testEdgeCases() {
        System.out.println("=== Edge Cases Test ===");
        
        // Test with capacity 0
        try {
            LRUCache<Integer, String> cache = new LRUCache<>(0);
            cache.put(1, "One");
            System.out.println("Capacity 0 handled: " + cache.size());
        } catch (Exception e) {
            System.out.println("Capacity 0 error: " + e.getMessage());
        }
        
        // Test with capacity 1
        LRUCache<Integer, String> cache1 = new LRUCache<>(1);
        cache1.put(1, "One");
        cache1.put(2, "Two");
        System.out.println("Capacity 1 test: " + cache1);
        
        // Test null values
        LRUCache<String, String> cache2 = new LRUCache<>(3);
        cache2.put("key1", null);
        cache2.put("key2", "value2");
        System.out.println("Null value test: " + cache2.get("key1"));
        
        // Test concurrent access (basic)
        testConcurrentAccess();
        
        System.out.println("---");
    }
    
    private static void testConcurrentAccess() {
        System.out.println("=== Concurrent Access Test ===");
        
        LRUCache<Integer, String> cache = new LRUCache<>(100);
        
        // Simulate concurrent access
        Thread[] threads = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int threadId = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    int key = threadId * 100 + j;
                    cache.put(key, "Thread" + threadId + "_Value" + j);
                    cache.get(key);
                }
            });
        }
        
        // Start all threads
        long startTime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        
        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        long totalTime = System.currentTimeMillis() - startTime;
        System.out.println("Concurrent access completed in: " + totalTime + "ms");
        System.out.println("Final cache size: " + cache.size());
    }
    
    /**
     * LRU Cache implementation using HashMap + Doubly Linked List
     * Time Complexity: O(1) for both get and put operations
     * Space Complexity: O(capacity)
     */
    static class LRUCache<K, V> {
        private final int capacity;
        private final java.util.Map<K, Node<K, V>> cache;
        private Node<K, V> head; // Most recently used
        private Node<K, V> tail; // Least recently used
        private int size;
        
        public LRUCache(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Capacity must be positive");
            }
            this.capacity = capacity;
            this.cache = new java.util.HashMap<>();
            this.size = 0;
            
            // Initialize dummy head and tail nodes
            this.head = new Node<>();
            this.tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }
        
        /**
         * Get value for the given key
         * Time Complexity: O(1)
         */
        public V get(K key) {
            Node<K, V> node = cache.get(key);
            if (node == null) {
                return null; // Key doesn't exist
            }
            
            // Move to front (most recently used)
            moveToFront(node);
            return node.value;
        }
        
        /**
         * Put key-value pair in cache
         * Time Complexity: O(1)
         */
        public void put(K key, V value) {
            Node<K, V> node = cache.get(key);
            
            if (node != null) {
                // Key exists, update value and move to front
                node.value = value;
                moveToFront(node);
            } else {
                // Key doesn't exist, create new node
                node = new Node<>(key, value);
                cache.put(key, node);
                addToFront(node);
                size++;
                
                // Check capacity
                if (size > capacity) {
                    removeLRU();
                }
            }
        }
        
        /**
         * Remove the least recently used item
         */
        private void removeLRU() {
            Node<K, V> lru = tail.prev;
            if (lru != head) {
                removeNode(lru);
                cache.remove(lru.key);
                size--;
            }
        }
        
        /**
         * Move node to front (most recently used)
         */
        private void moveToFront(Node<K, V> node) {
            removeNode(node);
            addToFront(node);
        }
        
        /**
         * Add node to front
         */
        private void addToFront(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }
        
        /**
         * Remove node from list
         */
        private void removeNode(Node<K, V> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        
        /**
         * Get current size of cache
         */
        public int size() {
            return size;
        }
        
        /**
         * Check if cache is empty
         */
        public boolean isEmpty() {
            return size == 0;
        }
        
        /**
         * Clear all entries from cache
         */
        public void clear() {
            cache.clear();
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        
        /**
         * Check if key exists in cache
         */
        public boolean containsKey(K key) {
            return cache.containsKey(key);
        }
        
        /**
         * Remove specific key from cache
         */
        public V remove(K key) {
            Node<K, V> node = cache.remove(key);
            if (node != null) {
                removeNode(node);
                size--;
                return node.value;
            }
            return null;
        }
        
        @Override
        public String toString() {
            java.util.List<String> entries = new java.util.ArrayList<>();
            Node<K, V> current = head.next;
            
            while (current != tail) {
                entries.add(current.key + "=" + current.value);
                current = current.next;
            }
            
            return "LRUCache{" + String.join(", ", entries) + "}";
        }
        
        /**
         * Node class for doubly linked list
         */
        private static class Node<K, V> {
            K key;
            V value;
            Node<K, V> prev;
            Node<K, V> next;
            
            Node() {
                this(null, null);
            }
            
            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
    }
    
    /**
     * Alternative implementation using LinkedHashMap
     * This is simpler but less customizable
     */
    static class LRUCacheLinkedHashMap<K, V> extends java.util.LinkedHashMap<K, V> {
        private final int capacity;
        
        public LRUCacheLinkedHashMap(int capacity) {
            super(capacity, 0.75f, true); // accessOrder = true for LRU behavior
            this.capacity = capacity;
        }
        
        @Override
        protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
            return size() > capacity;
        }
    }
    
    /**
     * Interview Questions to Ask:
     * 
     * 1. What is the time complexity of get and put operations?
     * 2. How would you handle concurrent access to the cache?
     * 3. What if the cache needs to be distributed across multiple servers?
     * 4. How would you implement cache eviction policies other than LRU?
     * 5. What if the cache needs to persist data to disk?
     * 6. How would you implement cache statistics (hit rate, miss rate)?
     * 7. What if the cache needs to support TTL (Time To Live)?
     * 8. How would you handle cache warming (pre-loading frequently accessed data)?
     * 9. What if the cache needs to support batch operations?
     * 10. How would you implement cache compression for memory optimization?
     * 11. What if the cache needs to support hierarchical storage (L1, L2, L3)?
     * 12. How would you implement cache partitioning for better performance?
     * 13. What if the cache needs to support transactions?
     * 14. How would you implement cache monitoring and alerting?
     * 15. What if the cache needs to support data consistency across replicas?
     */
}
