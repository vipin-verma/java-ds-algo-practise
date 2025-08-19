import java.util.*;

/**
 * Advanced Linked List Operations - Comprehensive Guide
 * 
 * This class demonstrates various advanced linked list manipulation techniques
 * commonly asked in Java interviews for experienced developers.
 * 
 * Topics covered:
 * - Linked list traversal and manipulation
 * - Cycle detection and removal
 * - Reversal and rotation techniques
 * - Merge and intersection operations
 * - Performance optimization and memory management
 */
public class Question013_AdvancedLinkedListOperations {
    
    public static void main(String[] args) {
        System.out.println("=== Advanced Linked List Operations - Complete Guide ===\n");
        
        demonstrateLinkedListBasics();
        demonstrateCycleDetection();
        demonstrateReversalTechniques();
        demonstrateMergeOperations();
        demonstrateAdvancedOperations();
        demonstratePerformanceAnalysis();
        
        System.out.println("\n=== Advanced Linked List Operations Completed! ===");
    }
    
    // ===== LINKED LIST NODE CLASS =====
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
        
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    
    // ===== UTILITY METHODS =====
    
    /**
     * Create linked list from array
     */
    public static ListNode createLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        
        ListNode head = new ListNode(arr[0]);
        ListNode current = head;
        
        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }
        
        return head;
    }
    
    /**
     * Print linked list
     */
    public static void printLinkedList(ListNode head) {
        if (head == null) {
            System.out.println("Empty list");
            return;
        }
        
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }
    
    /**
     * Get length of linked list
     */
    public static int getLength(ListNode head) {
        int length = 0;
        ListNode current = head;
        
        while (current != null) {
            length++;
            current = current.next;
        }
        
        return length;
    }
    
    // ===== CYCLE DETECTION =====
    
    /**
     * Detect cycle in linked list using Floyd's Cycle-Finding Algorithm
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                return true; // Cycle detected
            }
        }
        
        return false; // No cycle
    }
    
    /**
     * Find starting node of cycle
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static ListNode findCycleStart(ListNode head) {
        if (head == null || head.next == null) return null;
        
        ListNode slow = head;
        ListNode fast = head;
        boolean hasCycle = false;
        
        // Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }
        
        if (!hasCycle) return null;
        
        // Find cycle start
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        
        return slow;
    }
    
    /**
     * Remove cycle from linked list
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void removeCycle(ListNode head) {
        if (head == null || head.next == null) return;
        
        ListNode cycleStart = findCycleStart(head);
        if (cycleStart == null) return; // No cycle
        
        ListNode current = cycleStart;
        while (current.next != cycleStart) {
            current = current.next;
        }
        current.next = null; // Break the cycle
    }
    
    // ===== REVERSAL TECHNIQUES =====
    
    /**
     * Reverse linked list iteratively
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static ListNode reverseIterative(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode prev = null;
        ListNode current = head;
        ListNode next = null;
        
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        return prev;
    }
    
    /**
     * Reverse linked list recursively
     * Time Complexity: O(n), Space Complexity: O(n) due to recursion stack
     */
    public static ListNode reverseRecursive(ListNode head) {
        if (head == null || head.next == null) return head;
        
        ListNode rest = reverseRecursive(head.next);
        head.next.next = head;
        head.next = null;
        
        return rest;
    }
    
    /**
     * Reverse linked list in groups of k
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static ListNode reverseInGroups(ListNode head, int k) {
        if (head == null || k <= 1) return head;
        
        ListNode current = head;
        ListNode next = null;
        ListNode prev = null;
        int count = 0;
        
        // Reverse first k nodes
        while (current != null && count < k) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
            count++;
        }
        
        // Recursively reverse remaining nodes
        if (next != null) {
            head.next = reverseInGroups(next, k);
        }
        
        return prev;
    }
    
    /**
     * Reverse nodes between positions left and right
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        // Move to left position
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        
        ListNode current = prev.next;
        ListNode next = current.next;
        
        // Reverse nodes between left and right
        for (int i = 0; i < right - left; i++) {
            current.next = next.next;
            next.next = prev.next;
            prev.next = next;
            next = current.next;
        }
        
        return dummy.next;
    }
    
    // ===== MERGE OPERATIONS =====
    
    /**
     * Merge two sorted linked lists
     * Time Complexity: O(m + n), Space Complexity: O(1)
     */
    public static ListNode mergeSortedLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        
        // Attach remaining nodes
        if (l1 != null) current.next = l1;
        if (l2 != null) current.next = l2;
        
        return dummy.next;
    }
    
    /**
     * Merge k sorted linked lists using priority queue
     * Time Complexity: O(n log k), Space Complexity: O(k)
     * where n is total number of nodes, k is number of lists
     */
    public static ListNode mergeKSortedLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        // Add first node of each list to min heap
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            current.next = node;
            current = current.next;
            
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }
        
        return dummy.next;
    }
    
    /**
     * Find intersection point of two linked lists
     * Time Complexity: O(m + n), Space Complexity: O(1)
     */
    public static ListNode findIntersection(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        
        int lenA = getLength(headA);
        int lenB = getLength(headB);
        
        // Move longer list pointer forward
        ListNode currentA = headA;
        ListNode currentB = headB;
        
        if (lenA > lenB) {
            for (int i = 0; i < lenA - lenB; i++) {
                currentA = currentA.next;
            }
        } else {
            for (int i = 0; i < lenB - lenA; i++) {
                currentB = currentB.next;
            }
        }
        
        // Find intersection
        while (currentA != null && currentB != null) {
            if (currentA == currentB) {
                return currentA;
            }
            currentA = currentA.next;
            currentB = currentB.next;
        }
        
        return null; // No intersection
    }
    
    // ===== ADVANCED OPERATIONS =====
    
    /**
     * Remove nth node from end of list
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || n <= 0) return head;
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        
        // Move first pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            if (first == null) return head; // n is greater than list length
            first = first.next;
        }
        
        // Move both pointers until first reaches end
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        
        // Remove nth node from end
        second.next = second.next.next;
        
        return dummy.next;
    }
    
    /**
     * Reorder linked list (first half + reverse second half)
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        
        // Find middle of list
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half
        ListNode secondHalf = reverseIterative(slow.next);
        slow.next = null;
        
        // Merge first and reversed second half
        ListNode first = head;
        ListNode second = secondHalf;
        
        while (second != null) {
            ListNode temp1 = first.next;
            ListNode temp2 = second.next;
            
            first.next = second;
            second.next = temp1;
            
            first = temp1;
            second = temp2;
        }
    }
    
    /**
     * Sort linked list using merge sort
     * Time Complexity: O(n log n), Space Complexity: O(log n)
     */
    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        
        // Find middle
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        prev.next = null; // Split list
        
        // Recursively sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(slow);
        
        // Merge sorted halves
        return mergeSortedLists(left, right);
    }
    
    /**
     * Add two numbers represented by linked lists
     * Time Complexity: O(max(m, n)), Space Complexity: O(max(m, n))
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        int carry = 0;
        
        while (l1 != null || l2 != null || carry > 0) {
            int sum = carry;
            
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            
            carry = sum / 10;
            current.next = new ListNode(sum % 10);
            current = current.next;
        }
        
        return dummy.next;
    }
    
    // ===== DEMONSTRATION METHODS =====
    
    private static void demonstrateLinkedListBasics() {
        System.out.println("1. LINKED LIST BASICS:\n");
        
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = createLinkedList(arr);
        
        System.out.println("Original linked list:");
        printLinkedList(head);
        System.out.println("Length: " + getLength(head));
        
        System.out.println();
    }
    
    private static void demonstrateCycleDetection() {
        System.out.println("2. CYCLE DETECTION:\n");
        
        // Create list with cycle
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = head.next; // Create cycle
        
        System.out.println("List with cycle:");
        System.out.println("Has cycle: " + hasCycle(head));
        
        ListNode cycleStart = findCycleStart(head);
        System.out.println("Cycle starts at node with value: " + cycleStart.val);
        
        // Remove cycle
        removeCycle(head);
        System.out.println("After removing cycle:");
        System.out.println("Has cycle: " + hasCycle(head));
        
        System.out.println();
    }
    
    private static void demonstrateReversalTechniques() {
        System.out.println("3. REVERSAL TECHNIQUES:\n");
        
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = createLinkedList(arr);
        
        System.out.println("Original list:");
        printLinkedList(head);
        
        // Reverse iteratively
        ListNode reversed = reverseIterative(head);
        System.out.println("Reversed iteratively:");
        printLinkedList(reversed);
        
        // Reverse in groups
        ListNode head2 = createLinkedList(arr);
        ListNode groupReversed = reverseInGroups(head2, 2);
        System.out.println("Reversed in groups of 2:");
        printLinkedList(groupReversed);
        
        // Reverse between positions
        ListNode head3 = createLinkedList(arr);
        ListNode betweenReversed = reverseBetween(head3, 2, 4);
        System.out.println("Reversed between positions 2 and 4:");
        printLinkedList(betweenReversed);
        
        System.out.println();
    }
    
    private static void demonstrateMergeOperations() {
        System.out.println("4. MERGE OPERATIONS:\n");
        
        // Merge two sorted lists
        int[] arr1 = {1, 3, 5};
        int[] arr2 = {2, 4, 6};
        
        ListNode l1 = createLinkedList(arr1);
        ListNode l2 = createLinkedList(arr2);
        
        System.out.println("List 1:");
        printLinkedList(l1);
        System.out.println("List 2:");
        printLinkedList(l2);
        
        ListNode merged = mergeSortedLists(l1, l2);
        System.out.println("Merged sorted list:");
        printLinkedList(merged);
        
        // Find intersection
        ListNode common = new ListNode(7);
        common.next = new ListNode(8);
        
        l1.next.next.next = common;
        l2.next.next.next = common;
        
        System.out.println("\nLists with intersection:");
        printLinkedList(l1);
        printLinkedList(l2);
        
        ListNode intersection = findIntersection(l1, l2);
        System.out.println("Intersection point value: " + intersection.val);
        
        System.out.println();
    }
    
    private static void demonstrateAdvancedOperations() {
        System.out.println("5. ADVANCED OPERATIONS:\n");
        
        int[] arr = {1, 2, 3, 4, 5};
        ListNode head = createLinkedList(arr);
        
        System.out.println("Original list:");
        printLinkedList(head);
        
        // Remove nth from end
        ListNode removed = removeNthFromEnd(head, 2);
        System.out.println("After removing 2nd from end:");
        printLinkedList(removed);
        
        // Reorder list
        int[] arr2 = {1, 2, 3, 4, 5};
        ListNode head2 = createLinkedList(arr2);
        System.out.println("\nList for reordering:");
        printLinkedList(head2);
        
        reorderList(head2);
        System.out.println("After reordering:");
        printLinkedList(head2);
        
        // Sort list
        int[] arr3 = {3, 1, 5, 2, 4};
        ListNode head3 = createLinkedList(arr3);
        System.out.println("\nUnsorted list:");
        printLinkedList(head3);
        
        ListNode sorted = sortList(head3);
        System.out.println("Sorted list:");
        printLinkedList(sorted);
        
        System.out.println();
    }
    
    private static void demonstratePerformanceAnalysis() {
        System.out.println("6. PERFORMANCE ANALYSIS:\n");
        
        // Performance comparison for different operations
        int[] sizes = {1000, 10000, 100000};
        
        for (int size : sizes) {
            int[] arr = generateRandomArray(size);
            ListNode head = createLinkedList(arr);
            
            // Reverse performance
            long startTime = System.nanoTime();
            ListNode reversed = reverseIterative(head);
            long reverseTime = System.nanoTime() - startTime;
            
            // Sort performance
            startTime = System.nanoTime();
            ListNode sorted = sortList(reversed);
            long sortTime = System.nanoTime() - startTime;
            
            System.out.printf("List size %d:\n", size);
            System.out.printf("  Reverse: %d ns\n", reverseTime);
            System.out.printf("  Sort: %d ns\n", sortTime);
            System.out.println();
        }
    }
    
    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10000);
        }
        return arr;
    }
}

/**
 * INTERVIEW QUESTIONS COVERED:
 * 
 * THEORETICAL:
 * 1. Explain Floyd's Cycle-Finding Algorithm and its time complexity.
 * 2. What is the difference between iterative and recursive reversal?
 * 3. How do you find the middle of a linked list efficiently?
 * 4. Explain the merge sort algorithm for linked lists.
 * 5. What are the advantages of linked lists over arrays?
 * 6. How do you handle edge cases in linked list operations?
 * 
 * PRACTICAL:
 * 1. Implement cycle detection and removal.
 * 2. Reverse linked list in groups of k.
 * 3. Merge k sorted linked lists efficiently.
 * 4. Find intersection point of two linked lists.
 * 5. Add two numbers represented by linked lists.
 * 
 * TRICKY SCENARIOS:
 * 1. Handle lists with cycles.
 * 2. Deal with lists of different lengths.
 * 3. Reverse specific portions of the list.
 * 4. Optimize memory usage for large lists.
 * 
 * PERFORMANCE:
 * 1. Compare different reversal approaches.
 * 2. Analyze merge algorithm performance.
 * 3. Optimize cycle detection algorithms.
 * 4. Memory usage analysis for different operations.
 * 
 * DESIGN PATTERNS:
 * 1. Two-pointer technique for cycle detection.
 * 2. Dummy node pattern for edge case handling.
 * 3. Divide and conquer for merge sort.
 * 4. Sliding window for group reversal.
 */
