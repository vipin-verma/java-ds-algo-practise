package javaConcepts;

import java.util.*;

public class ArrayDequeExample {
    public static void main(String[] args) {
        // ArrayDeque बनाना
        ArrayDeque<String> deque = new ArrayDeque<>();

        System.out.println("=== ArrayDeque क्या है? ===");
        System.out.println("ArrayDeque = Array + Deque (Double Ended Queue)");
        System.out.println("दोनों ends (आगे और पीछे) से add/remove कर सकते हैं");
        System.out.println();

        // 1. DEQUE के रूप में उपयोग (दोनों ends से operations)
        System.out.println("=== 1. DEQUE Operations ===");

        // आगे से add करना (front)
        deque.addFirst("Front1");
        deque.addFirst("Front2");
        System.out.println("After addFirst: " + deque);

        // पीछे से add करना (rear)
        deque.addLast("Rear1");
        deque.addLast("Rear2");
        System.out.println("After addLast: " + deque);

        // आगे से remove करना
        String removedFirst = deque.removeFirst();
        System.out.println("Removed from first: " + removedFirst);
        System.out.println("After removeFirst: " + deque);

        // पीछे से remove करना
        String removedLast = deque.removeLast();
        System.out.println("Removed from last: " + removedLast);
        System.out.println("After removeLast: " + deque);
        System.out.println();

        // 2. STACK के रूप में उपयोग (LIFO - Last In First Out)
        System.out.println("=== 2. STACK Operations (LIFO) ===");
        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // Push करना (top पर add)
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Stack after pushing 10, 20, 30: " + stack);

        // Pop करना (top से remove)
        int popped = stack.pop();
        System.out.println("Popped element: " + popped);
        System.out.println("Stack after pop: " + stack);

        // Peek करना (top देखना without removing)
        int peeked = stack.peek();
        System.out.println("Peeked element: " + peeked);
        System.out.println("Stack after peek: " + stack);
        System.out.println();

        // 3. QUEUE के रूप में उपयोग (FIFO - First In First Out)
        System.out.println("=== 3. QUEUE Operations (FIFO) ===");
        ArrayDeque<String> queue = new ArrayDeque<>();

        // Enqueue (पीछे से add)
        queue.offer("Person1");
        queue.offer("Person2");
        queue.offer("Person3");
        System.out.println("Queue after offering: " + queue);

        // Dequeue (आगे से remove)
        String served = queue.poll();
        System.out.println("Served person: " + served);
        System.out.println("Queue after poll: " + queue);

        // Peek (आगे का element देखना)
        String next = queue.peek();
        System.out.println("Next person in queue: " + next);
        System.out.println();

        // 4. अन्य useful methods
        System.out.println("=== 4. Other Useful Methods ===");
        ArrayDeque<String> demo = new ArrayDeque<>();
        demo.addAll(Arrays.asList("A", "B", "C", "D"));

        System.out.println("Original deque: " + demo);
        System.out.println("Size: " + demo.size());
        System.out.println("Is empty: " + demo.isEmpty());
        System.out.println("Contains 'B': " + demo.contains("B"));

        // Iterator से traverse करना
        System.out.print("Forward iteration: ");
        for (String item : demo) {
            System.out.print(item + " ");
        }
        System.out.println();

        // Reverse iterator
        System.out.print("Reverse iteration: ");
        Iterator<String> reverseIter = demo.descendingIterator();
        while (reverseIter.hasNext()) {
            System.out.print(reverseIter.next() + " ");
        }
        System.out.println();

        // Clear करना
        demo.clear();
        System.out.println("After clear: " + demo);
        System.out.println();

        // 5. Real-world examples
        System.out.println("=== 5. Real World Examples ===");

        // Browser history simulation
        System.out.println("--- Browser History ---");
        ArrayDeque<String> browserHistory = new ArrayDeque<>();
        browserHistory.push("google.com");
        browserHistory.push("youtube.com");
        browserHistory.push("github.com");
        System.out.println("Current page: " + browserHistory.peek());
        System.out.println("Going back: " + browserHistory.pop());
        System.out.println("Current page: " + browserHistory.peek());

        // Task scheduling simulation
        System.out.println("--- Task Queue ---");
        ArrayDeque<String> taskQueue = new ArrayDeque<>();
        taskQueue.addLast("Download file");
        taskQueue.addLast("Process image");
        taskQueue.addLast("Send email");
        System.out.println("Processing: " + taskQueue.removeFirst());
        System.out.println("Next task: " + taskQueue.peekFirst());

        // Performance comparison
        System.out.println();
        System.out.println("=== 6. ArrayDeque vs अन्य Collections ===");
        System.out.println("ArrayDeque vs ArrayList:");
        System.out.println("✓ ArrayDeque: दोनों ends से O(1) add/remove");
        System.out.println("✗ ArrayList: middle operations slow हैं");
        System.out.println();
        System.out.println("ArrayDeque vs LinkedList:");
        System.out.println("✓ ArrayDeque: Better memory locality, faster");
        System.out.println("✗ LinkedList: Extra memory overhead (pointers)");
        System.out.println();
        System.out.println("ArrayDeque vs Stack (legacy):");
        System.out.println("✓ ArrayDeque: Resizable, no synchronization overhead");
        System.out.println("✗ Stack: Fixed size, synchronized (slower)");
    }
}
