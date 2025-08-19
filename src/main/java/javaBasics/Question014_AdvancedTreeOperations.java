import java.util.*;

/**
 * Advanced Tree Operations - Comprehensive Guide
 * 
 * This class demonstrates various advanced tree manipulation techniques
 * commonly asked in Java interviews for experienced developers.
 * 
 * Topics covered:
 * - Tree traversal techniques (inorder, preorder, postorder, level order)
 * - Binary Search Tree operations
 * - Tree balancing and optimization
 * - Advanced tree algorithms
 * - Performance analysis and memory management
 */
public class Question014_AdvancedTreeOperations {
    
    public static void main(String[] args) {
        System.out.println("=== Advanced Tree Operations - Complete Guide ===\n");
        
        demonstrateTreeBasics();
        demonstrateTraversalTechniques();
        demonstrateBSTOperations();
        demonstrateTreeBalancing();
        demonstrateAdvancedAlgorithms();
        demonstratePerformanceAnalysis();
        
        System.out.println("\n=== Advanced Tree Operations Completed! ===");
    }
    
    // ===== TREE NODE CLASS =====
    
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
        
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    // ===== UTILITY METHODS =====
    
    /**
     * Create a sample binary tree
     */
    public static TreeNode createSampleTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        return root;
    }
    
    /**
     * Create a sample Binary Search Tree
     */
    public static TreeNode createSampleBST() {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(14);
        root.left.right.left = new TreeNode(4);
        root.left.right.right = new TreeNode(7);
        return root;
    }
    
    /**
     * Get height of tree
     */
    public static int getHeight(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
    
    /**
     * Get number of nodes in tree
     */
    public static int getNodeCount(TreeNode root) {
        if (root == null) return 0;
        return 1 + getNodeCount(root.left) + getNodeCount(root.right);
    }
    
    // ===== TRAVERSAL TECHNIQUES =====
    
    /**
     * Inorder traversal (Left -> Root -> Right)
     * Time Complexity: O(n), Space Complexity: O(h) where h is height
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }
    
    private static void inorderHelper(TreeNode root, List<Integer> result) {
        if (root == null) return;
        inorderHelper(root.left, result);
        result.add(root.val);
        inorderHelper(root.right, result);
    }
    
    /**
     * Inorder traversal using iterative approach (stack)
     * Time Complexity: O(n), Space Complexity: O(h)
     */
    public static List<Integer> inorderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            current = stack.pop();
            result.add(current.val);
            current = current.right;
        }
        
        return result;
    }
    
    /**
     * Preorder traversal (Root -> Left -> Right)
     * Time Complexity: O(n), Space Complexity: O(h)
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderHelper(root, result);
        return result;
    }
    
    private static void preorderHelper(TreeNode root, List<Integer> result) {
        if (root == null) return;
        result.add(root.val);
        preorderHelper(root.left, result);
        preorderHelper(root.right, result);
    }
    
    /**
     * Preorder traversal using iterative approach
     * Time Complexity: O(n), Space Complexity: O(h)
     */
    public static List<Integer> preorderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            result.add(current.val);
            
            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
        
        return result;
    }
    
    /**
     * Postorder traversal (Left -> Right -> Root)
     * Time Complexity: O(n), Space Complexity: O(h)
     */
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderHelper(root, result);
        return result;
    }
    
    private static void postorderHelper(TreeNode root, List<Integer> result) {
        if (root == null) return;
        postorderHelper(root.left, result);
        postorderHelper(root.right, result);
        result.add(root.val);
    }
    
    /**
     * Postorder traversal using iterative approach
     * Time Complexity: O(n), Space Complexity: O(h)
     */
    public static List<Integer> postorderIterative(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        TreeNode lastVisited = null;
        
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            TreeNode peekNode = stack.peek();
            
            if (peekNode.right != null && peekNode.right != lastVisited) {
                current = peekNode.right;
            } else {
                result.add(peekNode.val);
                lastVisited = stack.pop();
            }
        }
        
        return result;
    }
    
    /**
     * Level order traversal (Breadth First Search)
     * Time Complexity: O(n), Space Complexity: O(w) where w is max width
     */
    public static List<List<Integer>> levelOrderTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                currentLevel.add(current.val);
                
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            
            result.add(currentLevel);
        }
        
        return result;
    }
    
    /**
     * Zigzag level order traversal
     * Time Complexity: O(n), Space Complexity: O(w)
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;
        
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                
                if (leftToRight) {
                    currentLevel.add(current.val);
                } else {
                    currentLevel.add(0, current.val);
                }
                
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            
            result.add(currentLevel);
            leftToRight = !leftToRight;
        }
        
        return result;
    }
    
    // ===== BST OPERATIONS =====
    
    /**
     * Search in Binary Search Tree
     * Time Complexity: O(h), Space Complexity: O(1)
     */
    public static TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;
        
        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }
    
    /**
     * Insert into Binary Search Tree
     * Time Complexity: O(h), Space Complexity: O(1)
     */
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        }
        
        return root;
    }
    
    /**
     * Delete from Binary Search Tree
     * Time Complexity: O(h), Space Complexity: O(1)
     */
    public static TreeNode deleteFromBST(TreeNode root, int key) {
        if (root == null) return null;
        
        if (key < root.val) {
            root.left = deleteFromBST(root.left, key);
        } else if (key > root.val) {
            root.right = deleteFromBST(root.right, key);
        } else {
            // Node to delete found
            
            // Case 1: Leaf node
            if (root.left == null && root.right == null) {
                return null;
            }
            // Case 2: One child
            else if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Case 3: Two children
            else {
                TreeNode successor = findMin(root.right);
                root.val = successor.val;
                root.right = deleteFromBST(root.right, successor.val);
            }
        }
        
        return root;
    }
    
    private static TreeNode findMin(TreeNode root) {
        while (root.left != null) {
            root = root.left;
        }
        return root;
    }
    
    /**
     * Validate Binary Search Tree
     * Time Complexity: O(n), Space Complexity: O(h)
     */
    public static boolean isValidBST(TreeNode root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private static boolean isValidBSTHelper(TreeNode root, long min, long max) {
        if (root == null) return true;
        
        if (root.val <= min || root.val >= max) return false;
        
        return isValidBSTHelper(root.left, min, root.val) &&
               isValidBSTHelper(root.right, root.val, max);
    }
    
    // ===== TREE BALANCING =====
    
    /**
     * Check if tree is balanced (height difference <= 1)
     * Time Complexity: O(n), Space Complexity: O(h)
     */
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }
    
    private static int checkHeight(TreeNode root) {
        if (root == null) return 0;
        
        int leftHeight = checkHeight(root.left);
        if (leftHeight == -1) return -1;
        
        int rightHeight = checkHeight(root.right);
        if (rightHeight == -1) return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        
        return Math.max(leftHeight, rightHeight) + 1;
    }
    
    /**
     * Convert sorted array to balanced BST
     * Time Complexity: O(n), Space Complexity: O(h)
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }
    
    private static TreeNode sortedArrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) return null;
        
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        
        root.left = sortedArrayToBSTHelper(nums, left, mid - 1);
        root.right = sortedArrayToBSTHelper(nums, mid + 1, right);
        
        return root;
    }
    
    // ===== ADVANCED ALGORITHMS =====
    
    /**
     * Find Lowest Common Ancestor (LCA) in BST
     * Time Complexity: O(h), Space Complexity: O(1)
     */
    public static TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestorBST(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestorBST(root.right, p, q);
        } else {
            return root;
        }
    }
    
    /**
     * Find Lowest Common Ancestor in Binary Tree
     * Time Complexity: O(n), Space Complexity: O(h)
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }
    
    /**
     * Serialize and deserialize binary tree
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static String serialize(TreeNode root) {
        if (root == null) return "null";
        return root.val + "," + serialize(root.left) + "," + serialize(root.right);
    }
    
    public static TreeNode deserialize(String data) {
        String[] values = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(values));
        return deserializeHelper(queue);
    }
    
    private static TreeNode deserializeHelper(Queue<String> queue) {
        String value = queue.poll();
        if ("null".equals(value)) return null;
        
        TreeNode root = new TreeNode(Integer.parseInt(value));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);
        
        return root;
    }
    
    /**
     * Find kth smallest element in BST
     * Time Complexity: O(k), Space Complexity: O(h)
     */
    public static int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        int count = 0;
        
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            
            current = stack.pop();
            count++;
            
            if (count == k) {
                return current.val;
            }
            
            current = current.right;
        }
        
        return -1; // k is greater than number of nodes
    }
    
    // ===== DEMONSTRATION METHODS =====
    
    private static void demonstrateTreeBasics() {
        System.out.println("1. TREE BASICS:\n");
        
        TreeNode root = createSampleTree();
        System.out.println("Sample tree created");
        System.out.println("Height: " + getHeight(root));
        System.out.println("Node count: " + getNodeCount(root));
        
        System.out.println();
    }
    
    private static void demonstrateTraversalTechniques() {
        System.out.println("2. TRAVERSAL TECHNIQUES:\n");
        
        TreeNode root = createSampleTree();
        
        System.out.println("Inorder traversal:");
        System.out.println("  Recursive: " + inorderTraversal(root));
        System.out.println("  Iterative: " + inorderIterative(root));
        
        System.out.println("\nPreorder traversal:");
        System.out.println("  Recursive: " + preorderTraversal(root));
        System.out.println("  Iterative: " + preorderIterative(root));
        
        System.out.println("\nPostorder traversal:");
        System.out.println("  Recursive: " + postorderTraversal(root));
        System.out.println("  Iterative: " + postorderIterative(root));
        
        System.out.println("\nLevel order traversal:");
        List<List<Integer>> levelOrder = levelOrderTraversal(root);
        for (int i = 0; i < levelOrder.size(); i++) {
            System.out.println("  Level " + i + ": " + levelOrder.get(i));
        }
        
        System.out.println("\nZigzag level order traversal:");
        List<List<Integer>> zigzag = zigzagLevelOrder(root);
        for (int i = 0; i < zigzag.size(); i++) {
            System.out.println("  Level " + i + ": " + zigzag.get(i));
        }
        
        System.out.println();
    }
    
    private static void demonstrateBSTOperations() {
        System.out.println("3. BST OPERATIONS:\n");
        
        TreeNode bst = createSampleBST();
        System.out.println("Sample BST created");
        
        // Search
        TreeNode searchResult = searchBST(bst, 6);
        System.out.println("Search for 6: " + (searchResult != null ? "Found" : "Not found"));
        
        // Insert
        bst = insertIntoBST(bst, 9);
        System.out.println("Inserted 9");
        
        // Validate
        System.out.println("Is valid BST: " + isValidBST(bst));
        
        // Delete
        bst = deleteFromBST(bst, 3);
        System.out.println("Deleted 3");
        
        System.out.println();
    }
    
    private static void demonstrateTreeBalancing() {
        System.out.println("4. TREE BALANCING:\n");
        
        TreeNode root = createSampleTree();
        System.out.println("Original tree is balanced: " + isBalanced(root));
        
        // Create unbalanced tree
        TreeNode unbalanced = new TreeNode(1);
        unbalanced.right = new TreeNode(2);
        unbalanced.right.right = new TreeNode(3);
        unbalanced.right.right.right = new TreeNode(4);
        
        System.out.println("Unbalanced tree is balanced: " + isBalanced(unbalanced));
        
        // Convert sorted array to balanced BST
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        TreeNode balancedBST = sortedArrayToBST(sortedArray);
        System.out.println("Balanced BST from sorted array created");
        System.out.println("Height: " + getHeight(balancedBST));
        
        System.out.println();
    }
    
    private static void demonstrateAdvancedAlgorithms() {
        System.out.println("5. ADVANCED ALGORITHMS:\n");
        
        TreeNode bst = createSampleBST();
        
        // LCA in BST
        TreeNode p = new TreeNode(1);
        TreeNode q = new TreeNode(6);
        TreeNode lca = lowestCommonAncestorBST(bst, p, q);
        System.out.println("LCA of 1 and 6 in BST: " + lca.val);
        
        // Serialize and deserialize
        String serialized = serialize(bst);
        System.out.println("Serialized tree: " + serialized);
        
        TreeNode deserialized = deserialize(serialized);
        System.out.println("Deserialized tree height: " + getHeight(deserialized));
        
        // Kth smallest
        int kthSmallest = kthSmallest(bst, 3);
        System.out.println("3rd smallest element: " + kthSmallest);
        
        System.out.println();
    }
    
    private static void demonstratePerformanceAnalysis() {
        System.out.println("6. PERFORMANCE ANALYSIS:\n");
        
        // Performance comparison for different tree sizes
        int[] sizes = {1000, 10000, 100000};
        
        for (int size : sizes) {
            int[] arr = generateSortedArray(size);
            TreeNode tree = sortedArrayToBST(arr);
            
            // Traversal performance
            long startTime = System.nanoTime();
            List<Integer> inorder = inorderTraversal(tree);
            long inorderTime = System.nanoTime() - startTime;
            
            startTime = System.nanoTime();
            List<Integer> inorderIter = inorderIterative(tree);
            long inorderIterTime = System.nanoTime() - startTime;
            
            System.out.printf("Tree size %d:\n", size);
            System.out.printf("  Recursive inorder: %d ns\n", inorderTime);
            System.out.printf("  Iterative inorder: %d ns\n", inorderIterTime);
            System.out.println();
        }
    }
    
    private static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }
}

/**
 * INTERVIEW QUESTIONS COVERED:
 * 
 * THEORETICAL:
 * 1. Explain different tree traversal algorithms and their time complexities.
 * 2. What is the difference between recursive and iterative traversal?
 * 3. How do you validate a Binary Search Tree?
 * 4. Explain the concept of tree balancing and its importance.
 * 5. What are the advantages of BST over regular binary trees?
 * 6. How do you handle tree serialization and deserialization?
 * 
 * PRACTICAL:
 * 1. Implement all three traversal methods (inorder, preorder, postorder).
 * 2. Create balanced BST from sorted array.
 * 3. Find Lowest Common Ancestor in both BST and binary tree.
 * 4. Implement tree serialization and deserialization.
 * 5. Find kth smallest element in BST.
 * 
 * TRICKY SCENARIOS:
 * 1. Handle null nodes in traversal.
 * 2. Deal with unbalanced trees.
 * 3. Optimize memory usage for large trees.
 * 4. Handle edge cases in BST operations.
 * 
 * PERFORMANCE:
 * 1. Compare recursive vs iterative approaches.
 * 2. Analyze space complexity of different methods.
 * 3. Optimize tree operations for specific use cases.
 * 4. Benchmark traversal algorithms.
 * 
 * DESIGN PATTERNS:
 * 1. Visitor pattern for different traversal strategies.
 * 2. Iterator pattern for iterative traversal.
 * 3. Strategy pattern for different tree operations.
 * 4. Template method for traversal algorithms.
 */
