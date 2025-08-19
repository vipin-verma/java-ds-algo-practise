/**
 * Question 9: Advanced Algorithms Implementation
 * 
 * Problem: Implement and demonstrate various advanced algorithms including
 * graph algorithms, dynamic programming, and advanced data structures.
 * 
 * Requirements:
 * - Should implement multiple advanced algorithms
 * - Should include graph algorithms (Dijkstra, BFS, DFS)
 * - Should implement dynamic programming solutions
 * - Should include advanced data structures
 * - Should be efficient and well-optimized
 * 
 * Difficulty: Hard
 * Category: Algorithms, Data Structures, Graph Theory
 * Experience Level: 5-8 years
 */
public class Question009_AdvancedAlgorithms {
    
    public static void main(String[] args) {
        System.out.println("=== Advanced Algorithms Implementation ===\n");
        
        // Test Graph Algorithms
        testGraphAlgorithms();
        
        // Test Dynamic Programming
        testDynamicProgramming();
        
        // Test Advanced Data Structures
        testAdvancedDataStructures();
        
        // Test String Algorithms
        testStringAlgorithms();
        
        // Test Tree Algorithms
        testTreeAlgorithms();
        
        // Test Performance Comparison
        testPerformanceComparison();
    }
    
    private static void testGraphAlgorithms() {
        System.out.println("=== Graph Algorithms Test ===");
        
        // Create a weighted graph
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 2);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 8);
        graph.addEdge(2, 4, 10);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 6);
        graph.addEdge(4, 5, 3);
        
        System.out.println("Graph created with 6 vertices");
        
        // Test Dijkstra's algorithm
        System.out.println("\nDijkstra's Shortest Path from vertex 0:");
        int[] distances = graph.dijkstra(0);
        for (int i = 0; i < distances.length; i++) {
            System.out.println("  Distance to vertex " + i + ": " + distances[i]);
        }
        
        // Test BFS
        System.out.println("\nBFS traversal starting from vertex 0:");
        java.util.List<Integer> bfsResult = graph.bfs(0);
        System.out.println("  BFS order: " + bfsResult);
        
        // Test DFS
        System.out.println("\nDFS traversal starting from vertex 0:");
        java.util.List<Integer> dfsResult = graph.dfs(0);
        System.out.println("  DFS order: " + dfsResult);
        
        // Test topological sort
        System.out.println("\nTopological Sort:");
        try {
            java.util.List<Integer> topoResult = graph.topologicalSort();
            System.out.println("  Topological order: " + topoResult);
        } catch (Exception e) {
            System.out.println("  Topological sort failed: " + e.getMessage());
        }
        
        // Test cycle detection
        System.out.println("\nCycle Detection:");
        boolean hasCycle = graph.hasCycle();
        System.out.println("  Graph has cycle: " + hasCycle);
        
        System.out.println("---");
    }
    
    private static void testDynamicProgramming() {
        System.out.println("=== Dynamic Programming Test ===");
        
        // Test Fibonacci with different approaches
        int n = 40;
        System.out.println("Calculating Fibonacci(" + n + "):");
        
        long startTime = System.currentTimeMillis();
        long fibRecursive = fibonacciRecursive(n);
        long timeRecursive = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        long fibDP = fibonacciDP(n);
        long timeDP = System.currentTimeMillis() - startTime;
        
        startTime = System.currentTimeMillis();
        long fibOptimized = fibonacciOptimized(n);
        long timeOptimized = System.currentTimeMillis() - startTime;
        
        System.out.println("  Recursive: " + fibRecursive + " (" + timeRecursive + "ms)");
        System.out.println("  Dynamic Programming: " + fibDP + " (" + timeDP + "ms)");
        System.out.println("  Optimized: " + fibOptimized + " (" + timeOptimized + "ms)");
        
        // Test Longest Common Subsequence
        String str1 = "ABCDGH";
        String str2 = "AEDFHR";
        System.out.println("\nLongest Common Subsequence:");
        System.out.println("  String 1: " + str1);
        System.out.println("  String 2: " + str2);
        int lcsLength = longestCommonSubsequence(str1, str2);
        System.out.println("  LCS Length: " + lcsLength);
        
        // Test Knapsack Problem
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int capacity = 10;
        System.out.println("\n0/1 Knapsack Problem:");
        System.out.println("  Weights: " + java.util.Arrays.toString(weights));
        System.out.println("  Values: " + java.util.Arrays.toString(values));
        System.out.println("  Capacity: " + capacity);
        int maxValue = knapsack01(weights, values, capacity);
        System.out.println("  Maximum value: " + maxValue);
        
        // Test Edit Distance
        String word1 = "horse";
        String word2 = "ros";
        System.out.println("\nEdit Distance:");
        System.out.println("  Word 1: " + word1);
        System.out.println("  Word 2: " + word2);
        int editDistance = editDistance(word1, word2);
        System.out.println("  Edit distance: " + editDistance);
        
        System.out.println("---");
    }
    
    private static void testAdvancedDataStructures() {
        System.out.println("=== Advanced Data Structures Test ===");
        
        // Test Trie
        System.out.println("Testing Trie Data Structure:");
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");
        trie.insert("banana");
        trie.insert("ball");
        
        System.out.println("  Contains 'apple': " + trie.search("apple"));
        System.out.println("  Contains 'app': " + trie.search("app"));
        System.out.println("  Contains 'banana': " + trie.search("banana"));
        System.out.println("  Contains 'orange': " + trie.search("orange"));
        System.out.println("  Starts with 'app': " + trie.startsWith("app"));
        
        // Test Segment Tree
        System.out.println("\nTesting Segment Tree:");
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTree segmentTree = new SegmentTree(arr);
        System.out.println("  Array: " + java.util.Arrays.toString(arr));
        System.out.println("  Sum from index 1 to 4: " + segmentTree.query(1, 4));
        System.out.println("  Sum from index 0 to 5: " + segmentTree.query(0, 5));
        
        // Update value
        segmentTree.update(2, 10);
        System.out.println("  After updating index 2 to 10:");
        System.out.println("  Sum from index 1 to 4: " + segmentTree.query(1, 4));
        
        // Test Union-Find
        System.out.println("\nTesting Union-Find:");
        UnionFind uf = new UnionFind(6);
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);
        uf.union(4, 5);
        
        System.out.println("  Connected components:");
        System.out.println("    0 and 2 connected: " + uf.connected(0, 2));
        System.out.println("    0 and 3 connected: " + uf.connected(0, 3));
        System.out.println("    3 and 5 connected: " + uf.connected(3, 5));
        
        System.out.println("---");
    }
    
    private static void testStringAlgorithms() {
        System.out.println("=== String Algorithms Test ===");
        
        // Test KMP Pattern Matching
        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        System.out.println("KMP Pattern Matching:");
        System.out.println("  Text: " + text);
        System.out.println("  Pattern: " + pattern);
        int kmpResult = kmpSearch(text, pattern);
        System.out.println("  Pattern found at index: " + kmpResult);
        
        // Test Rabin-Karp
        String text2 = "GEEKSFORGEEKS";
        String pattern2 = "GEEK";
        System.out.println("\nRabin-Karp Pattern Matching:");
        System.out.println("  Text: " + text2);
        System.out.println("  Pattern: " + pattern2);
        java.util.List<Integer> rkResult = rabinKarpSearch(text2, pattern2);
        System.out.println("  Pattern found at indices: " + rkResult);
        
        // Test Longest Palindromic Substring
        String palindromeText = "babad";
        System.out.println("\nLongest Palindromic Substring:");
        System.out.println("  Text: " + palindromeText);
        String longestPalindrome = longestPalindromicSubstring(palindromeText);
        System.out.println("  Longest palindrome: " + longestPalindrome);
        
        // Test String Permutations
        String permText = "ABC";
        System.out.println("\nString Permutations:");
        System.out.println("  Text: " + permText);
        java.util.List<String> permutations = generatePermutations(permText);
        System.out.println("  Permutations: " + permutations);
        
        System.out.println("---");
    }
    
    private static void testTreeAlgorithms() {
        System.out.println("=== Tree Algorithms Test ===");
        
        // Create a binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        
        System.out.println("Binary Tree created");
        
        // Test tree traversals
        System.out.println("\nTree Traversals:");
        System.out.println("  Inorder: " + inorderTraversal(root));
        System.out.println("  Preorder: " + preorderTraversal(root));
        System.out.println("  Postorder: " + postorderTraversal(root));
        System.out.println("  Level Order: " + levelOrderTraversal(root));
        
        // Test tree properties
        System.out.println("\nTree Properties:");
        System.out.println("  Height: " + getTreeHeight(root));
        System.out.println("  Size: " + getTreeSize(root));
        System.out.println("  Is Balanced: " + isBalanced(root));
        System.out.println("  Is BST: " + isBST(root));
        
        // Test LCA (Lowest Common Ancestor)
        TreeNode node4 = root.left.left;
        TreeNode node5 = root.left.right;
        TreeNode lca = lowestCommonAncestor(root, node4, node5);
        System.out.println("  LCA of nodes 4 and 5: " + lca.val);
        
        System.out.println("---");
    }
    
    private static void testPerformanceComparison() {
        System.out.println("=== Performance Comparison Test ===");
        
        // Test sorting algorithms
        int size = 10000;
        int[] arr = new int[size];
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(10000);
        }
        
        System.out.println("Sorting " + size + " elements:");
        
        // Quick Sort
        int[] arr1 = arr.clone();
        long startTime = System.currentTimeMillis();
        quickSort(arr1, 0, arr1.length - 1);
        long quickSortTime = System.currentTimeMillis() - startTime;
        System.out.println("  Quick Sort: " + quickSortTime + "ms");
        
        // Merge Sort
        int[] arr2 = arr.clone();
        startTime = System.currentTimeMillis();
        mergeSort(arr2, 0, arr2.length - 1);
        long mergeSortTime = System.currentTimeMillis() - startTime;
        System.out.println("  Merge Sort: " + mergeSortTime + "ms");
        
        // Heap Sort
        int[] arr3 = arr.clone();
        startTime = System.currentTimeMillis();
        heapSort(arr3);
        long heapSortTime = System.currentTimeMillis() - startTime;
        System.out.println("  Heap Sort: " + heapSortTime + "ms");
        
        // Verify sorting
        boolean quickSorted = isSorted(arr1);
        boolean mergeSorted = isSorted(arr2);
        boolean heapSorted = isSorted(arr3);
        
        System.out.println("  All arrays sorted correctly: " + (quickSorted && mergeSorted && heapSorted));
        
        System.out.println("---");
    }
    
    // ===== GRAPH ALGORITHMS =====
    
    /**
     * Graph implementation with adjacency matrix
     */
    static class Graph {
        private final int vertices;
        private final int[][] adjacencyMatrix;
        private final boolean isDirected;
        
        public Graph(int vertices) {
            this(vertices, false);
        }
        
        public Graph(int vertices, boolean isDirected) {
            this.vertices = vertices;
            this.adjacencyMatrix = new int[vertices][vertices];
            this.isDirected = isDirected;
        }
        
        public void addEdge(int source, int destination, int weight) {
            adjacencyMatrix[source][destination] = weight;
            if (!isDirected) {
                adjacencyMatrix[destination][source] = weight;
            }
        }
        
        public void addEdge(int source, int destination) {
            addEdge(source, destination, 1);
        }
        
        /**
         * Dijkstra's shortest path algorithm
         * Time Complexity: O(V²)
         */
        public int[] dijkstra(int source) {
            int[] distances = new int[vertices];
            boolean[] visited = new boolean[vertices];
            
            // Initialize distances
            java.util.Arrays.fill(distances, Integer.MAX_VALUE);
            distances[source] = 0;
            
            for (int count = 0; count < vertices - 1; count++) {
                // Find vertex with minimum distance
                int minVertex = -1;
                int minDistance = Integer.MAX_VALUE;
                
                for (int v = 0; v < vertices; v++) {
                    if (!visited[v] && distances[v] < minDistance) {
                        minDistance = distances[v];
                        minVertex = v;
                    }
                }
                
                if (minVertex == -1) break;
                
                visited[minVertex] = true;
                
                // Update distances to adjacent vertices
                for (int v = 0; v < vertices; v++) {
                    if (!visited[v] && adjacencyMatrix[minVertex][v] != 0 &&
                        distances[minVertex] != Integer.MAX_VALUE &&
                        distances[minVertex] + adjacencyMatrix[minVertex][v] < distances[v]) {
                        distances[v] = distances[minVertex] + adjacencyMatrix[minVertex][v];
                    }
                }
            }
            
            return distances;
        }
        
        /**
         * Breadth-First Search
         * Time Complexity: O(V + E)
         */
        public java.util.List<Integer> bfs(int start) {
            java.util.List<Integer> result = new java.util.ArrayList<>();
            boolean[] visited = new boolean[vertices];
            java.util.Queue<Integer> queue = new java.util.LinkedList<>();
            
            visited[start] = true;
            queue.offer(start);
            
            while (!queue.isEmpty()) {
                int vertex = queue.poll();
                result.add(vertex);
                
                for (int i = 0; i < vertices; i++) {
                    if (adjacencyMatrix[vertex][i] != 0 && !visited[i]) {
                        visited[i] = true;
                        queue.offer(i);
                    }
                }
            }
            
            return result;
        }
        
        /**
         * Depth-First Search
         * Time Complexity: O(V + E)
         */
        public java.util.List<Integer> dfs(int start) {
            java.util.List<Integer> result = new java.util.ArrayList<>();
            boolean[] visited = new boolean[vertices];
            dfsUtil(start, visited, result);
            return result;
        }
        
        private void dfsUtil(int vertex, boolean[] visited, java.util.List<Integer> result) {
            visited[vertex] = true;
            result.add(vertex);
            
            for (int i = 0; i < vertices; i++) {
                if (adjacencyMatrix[vertex][i] != 0 && !visited[i]) {
                    dfsUtil(i, visited, result);
                }
            }
        }
        
        /**
         * Topological Sort (for DAGs)
         * Time Complexity: O(V + E)
         */
        public java.util.List<Integer> topologicalSort() {
            java.util.List<Integer> result = new java.util.ArrayList<>();
            boolean[] visited = new boolean[vertices];
            java.util.Stack<Integer> stack = new java.util.Stack<>();
            
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    topologicalSortUtil(i, visited, stack);
                }
            }
            
            while (!stack.isEmpty()) {
                result.add(stack.pop());
            }
            
            return result;
        }
        
        private void topologicalSortUtil(int vertex, boolean[] visited, java.util.Stack<Integer> stack) {
            visited[vertex] = true;
            
            for (int i = 0; i < vertices; i++) {
                if (adjacencyMatrix[vertex][i] != 0 && !visited[i]) {
                    topologicalSortUtil(i, visited, stack);
                }
            }
            
            stack.push(vertex);
        }
        
        /**
         * Cycle detection using DFS
         * Time Complexity: O(V + E)
         */
        public boolean hasCycle() {
            boolean[] visited = new boolean[vertices];
            boolean[] recStack = new boolean[vertices];
            
            for (int i = 0; i < vertices; i++) {
                if (hasCycleUtil(i, visited, recStack)) {
                    return true;
                }
            }
            
            return false;
        }
        
        private boolean hasCycleUtil(int vertex, boolean[] visited, boolean[] recStack) {
            if (recStack[vertex]) return true;
            if (visited[vertex]) return false;
            
            visited[vertex] = true;
            recStack[vertex] = true;
            
            for (int i = 0; i < vertices; i++) {
                if (adjacencyMatrix[vertex][i] != 0 && hasCycleUtil(i, visited, recStack)) {
                    return true;
                }
            }
            
            recStack[vertex] = false;
            return false;
        }
    }
    
    // ===== DYNAMIC PROGRAMMING =====
    
    /**
     * Recursive Fibonacci (inefficient)
     * Time Complexity: O(2^n)
     */
    public static long fibonacciRecursive(int n) {
        if (n <= 1) return n;
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }
    
    /**
     * Dynamic Programming Fibonacci
     * Time Complexity: O(n), Space Complexity: O(n)
     */
    public static long fibonacciDP(int n) {
        if (n <= 1) return n;
        
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        
        return dp[n];
    }
    
    /**
     * Optimized Fibonacci (space-efficient)
     * Time Complexity: O(n), Space Complexity: O(1)
     */
    public static long fibonacciOptimized(int n) {
        if (n <= 1) return n;
        
        long prev = 0, curr = 1;
        for (int i = 2; i <= n; i++) {
            long next = prev + curr;
            prev = curr;
            curr = next;
        }
        
        return curr;
    }
    
    /**
     * Longest Common Subsequence
     * Time Complexity: O(m*n), Space Complexity: O(m*n)
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[m][n];
    }
    
    /**
     * 0/1 Knapsack Problem
     * Time Complexity: O(n*W), Space Complexity: O(n*W)
     */
    public static int knapsack01(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], 
                                       dp[i - 1][w - weights[i - 1]] + values[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        return dp[n][capacity];
    }
    
    /**
     * Edit Distance (Levenshtein Distance)
     * Time Complexity: O(m*n), Space Complexity: O(m*n)
     */
    public static int editDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], 
                                           Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        
        return dp[m][n];
    }
    
    // ===== ADVANCED DATA STRUCTURES =====
    
    /**
     * Trie (Prefix Tree) implementation
     */
    static class Trie {
        private final TrieNode root;
        
        public Trie() {
            root = new TrieNode();
        }
        
        public void insert(String word) {
            TrieNode current = root;
            for (char c : word.toCharArray()) {
                if (!current.children.containsKey(c)) {
                    current.children.put(c, new TrieNode());
                }
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
        
        private static class TrieNode {
            java.util.Map<Character, TrieNode> children = new java.util.HashMap<>();
            boolean isEndOfWord = false;
        }
    }
    
    /**
     * Segment Tree implementation
     */
    static class SegmentTree {
        private final int[] tree;
        private final int n;
        
        public SegmentTree(int[] arr) {
            n = arr.length;
            tree = new int[4 * n];
            buildTree(arr, 1, 0, n - 1);
        }
        
        private void buildTree(int[] arr, int node, int start, int end) {
            if (start == end) {
                tree[node] = arr[start];
                return;
            }
            
            int mid = (start + end) / 2;
            buildTree(arr, 2 * node, start, mid);
            buildTree(arr, 2 * node + 1, mid + 1, end);
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
        
        public int query(int left, int right) {
            return query(1, 0, n - 1, left, right);
        }
        
        private int query(int node, int start, int end, int left, int right) {
            if (right < start || left > end) return 0;
            if (left <= start && right >= end) return tree[node];
            
            int mid = (start + end) / 2;
            return query(2 * node, start, mid, left, right) +
                   query(2 * node + 1, mid + 1, end, left, right);
        }
        
        public void update(int index, int value) {
            update(1, 0, n - 1, index, value);
        }
        
        private void update(int node, int start, int end, int index, int value) {
            if (start == end) {
                tree[node] = value;
                return;
            }
            
            int mid = (start + end) / 2;
            if (index <= mid) {
                update(2 * node, start, mid, index, value);
            } else {
                update(2 * node + 1, mid + 1, end, index, value);
            }
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }
    
    /**
     * Union-Find (Disjoint Set) implementation
     */
    static class UnionFind {
        private final int[] parent;
        private final int[] rank;
        
        public UnionFind(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX == rootY) return;
            
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
        
        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }
    }
    
    // ===== STRING ALGORITHMS =====
    
    /**
     * KMP Pattern Matching
     * Time Complexity: O(m + n)
     */
    public static int kmpSearch(String text, String pattern) {
        int[] lps = computeLPS(pattern);
        int i = 0, j = 0;
        
        while (i < text.length()) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }
            
            if (j == pattern.length()) {
                return i - j;
            } else if (i < text.length() && pattern.charAt(j) != text.charAt(i)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        return -1;
    }
    
    private static int[] computeLPS(String pattern) {
        int[] lps = new int[pattern.length()];
        int len = 0;
        int i = 1;
        
        while (i < pattern.length()) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        return lps;
    }
    
    /**
     * Rabin-Karp Pattern Matching
     * Time Complexity: O(m + n) average case
     */
    public static java.util.List<Integer> rabinKarpSearch(String text, String pattern) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        
        if (m > n) return result;
        
        int patternHash = pattern.hashCode();
        
        for (int i = 0; i <= n - m; i++) {
            String substring = text.substring(i, i + m);
            if (substring.hashCode() == patternHash && substring.equals(pattern)) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    /**
     * Longest Palindromic Substring
     * Time Complexity: O(n²), Space Complexity: O(n²)
     */
    public static String longestPalindromicSubstring(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLength = 1;
        
        // Single characters are palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        
        // Check for 2-character palindromes
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLength = 2;
            }
        }
        
        // Check for palindromes of length > 2
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    if (len > maxLength) {
                        start = i;
                        maxLength = len;
                    }
                }
            }
        }
        
        return s.substring(start, start + maxLength);
    }
    
    /**
     * Generate all permutations of a string
     * Time Complexity: O(n!)
     */
    public static java.util.List<String> generatePermutations(String str) {
        java.util.List<String> result = new java.util.ArrayList<>();
        generatePermutationsUtil("", str, result);
        return result;
    }
    
    private static void generatePermutationsUtil(String prefix, String remaining, java.util.List<String> result) {
        if (remaining.length() == 0) {
            result.add(prefix);
            return;
        }
        
        for (int i = 0; i < remaining.length(); i++) {
            String newPrefix = prefix + remaining.charAt(i);
            String newRemaining = remaining.substring(0, i) + remaining.substring(i + 1);
            generatePermutationsUtil(newPrefix, newRemaining, result);
        }
    }
    
    // ===== TREE ALGORITHMS =====
    
    /**
     * Binary Tree Node
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        
        TreeNode(int val) {
            this.val = val;
        }
    }
    
    /**
     * Inorder Traversal
     * Time Complexity: O(n)
     */
    public static java.util.List<Integer> inorderTraversal(TreeNode root) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        inorderTraversalUtil(root, result);
        return result;
    }
    
    private static void inorderTraversalUtil(TreeNode node, java.util.List<Integer> result) {
        if (node != null) {
            inorderTraversalUtil(node.left, result);
            result.add(node.val);
            inorderTraversalUtil(node.right, result);
        }
    }
    
    /**
     * Preorder Traversal
     * Time Complexity: O(n)
     */
    public static java.util.List<Integer> preorderTraversal(TreeNode root) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        preorderTraversalUtil(root, result);
        return result;
    }
    
    private static void preorderTraversalUtil(TreeNode node, java.util.List<Integer> result) {
        if (node != null) {
            result.add(node.val);
            preorderTraversalUtil(node.left, result);
            preorderTraversalUtil(node.right, result);
        }
    }
    
    /**
     * Postorder Traversal
     * Time Complexity: O(n)
     */
    public static java.util.List<Integer> postorderTraversal(TreeNode root) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        postorderTraversalUtil(root, result);
        return result;
    }
    
    private static void postorderTraversalUtil(TreeNode node, java.util.List<Integer> result) {
        if (node != null) {
            postorderTraversalUtil(node.left, result);
            postorderTraversalUtil(node.right, result);
            result.add(node.val);
        }
    }
    
    /**
     * Level Order Traversal (BFS)
     * Time Complexity: O(n)
     */
    public static java.util.List<Integer> levelOrderTraversal(TreeNode root) {
        java.util.List<Integer> result = new java.util.ArrayList<>();
        if (root == null) return result;
        
        java.util.Queue<TreeNode> queue = new java.util.LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.val);
            
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        
        return result;
    }
    
    /**
     * Get tree height
     * Time Complexity: O(n)
     */
    public static int getTreeHeight(TreeNode root) {
        if (root == null) return -1;
        return 1 + Math.max(getTreeHeight(root.left), getTreeHeight(root.right));
    }
    
    /**
     * Get tree size
     * Time Complexity: O(n)
     */
    public static int getTreeSize(TreeNode root) {
        if (root == null) return 0;
        return 1 + getTreeSize(root.left) + getTreeSize(root.right);
    }
    
    /**
     * Check if tree is balanced
     * Time Complexity: O(n)
     */
    public static boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }
    
    private static int checkBalance(TreeNode node) {
        if (node == null) return 0;
        
        int leftHeight = checkBalance(node.left);
        if (leftHeight == -1) return -1;
        
        int rightHeight = checkBalance(node.right);
        if (rightHeight == -1) return -1;
        
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        
        return 1 + Math.max(leftHeight, rightHeight);
    }
    
    /**
     * Check if tree is BST
     * Time Complexity: O(n)
     */
    public static boolean isBST(TreeNode root) {
        return isBSTUtil(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private static boolean isBSTUtil(TreeNode node, long min, long max) {
        if (node == null) return true;
        
        if (node.val <= min || node.val >= max) return false;
        
        return isBSTUtil(node.left, min, node.val) && 
               isBSTUtil(node.right, node.val, max);
    }
    
    /**
     * Lowest Common Ancestor
     * Time Complexity: O(n)
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }
    
    // ===== SORTING ALGORITHMS =====
    
    /**
     * Quick Sort
     * Time Complexity: O(n log n) average, O(n²) worst case
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    /**
     * Merge Sort
     * Time Complexity: O(n log n)
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }
        
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    
    /**
     * Heap Sort
     * Time Complexity: O(n log n)
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;
        
        // Build heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        
        // Extract elements from heap
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }
    
    private static void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }
        
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }
        
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) return false;
        }
        return true;
    }
    
    /**
     * Interview Questions to Ask:
     * 
     * 1. What is the time complexity of Dijkstra's algorithm and how can you optimize it?
     * 2. How would you implement Dijkstra's algorithm for a graph with negative weights?
     * 3. What is the difference between BFS and DFS and when would you use each?
     * 4. How would you detect cycles in a directed graph?
     * 5. What is the time complexity of topological sort and when is it possible?
     * 6. How would you optimize the Fibonacci calculation further?
     * 7. What is the space complexity of the LCS algorithm and how can you optimize it?
     * 8. How would you solve the unbounded knapsack problem?
     * 9. What is the difference between edit distance and longest common subsequence?
     * 10. How would you implement a suffix tree?
     * 11. What is the time complexity of building a segment tree?
     * 12. How would you implement lazy propagation in a segment tree?
     * 13. What is the time complexity of union-find operations?
     * 14. How would you implement path compression and union by rank?
     * 15. What is the difference between KMP and Rabin-Karp algorithms?
     * 16. How would you implement the Boyer-Moore algorithm?
     * 17. What is the time complexity of finding the longest palindromic substring?
     * 18. How would you implement Manacher's algorithm?
     * 19. What is the time complexity of generating all permutations?
     * 20. How would you implement an iterative version of tree traversals?
     * 21. What is the time complexity of checking if a tree is balanced?
     * 22. How would you implement a threaded binary tree?
     * 23. What is the difference between quick sort and merge sort?
     * 24. How would you implement a stable quick sort?
     * 25. What is the time complexity of heap sort and when would you use it?
     */
}
