import java.util.*;

/**
 * Advanced Graph Operations - Comprehensive Guide
 * 
 * This class demonstrates various advanced graph manipulation techniques
 * commonly asked in Java interviews for experienced developers.
 * 
 * Topics covered:
 * - Graph representation (adjacency list, matrix)
 * - Graph traversal (DFS, BFS)
 * - Shortest path algorithms (Dijkstra, Bellman-Ford)
 * - Minimum Spanning Tree (Kruskal, Prim)
 * - Topological sorting and cycle detection
 * - Performance analysis and optimization
 */
public class Question015_AdvancedGraphOperations {
    
    public static void main(String[] args) {
        System.out.println("=== Advanced Graph Operations - Complete Guide ===\n");
        
        demonstrateGraphRepresentation();
        demonstrateGraphTraversal();
        demonstrateShortestPathAlgorithms();
        demonstrateMinimumSpanningTree();
        demonstrateTopologicalSorting();
        demonstratePerformanceAnalysis();
        
        System.out.println("\n=== Advanced Graph Operations Completed! ===");
    }
    
    // ===== GRAPH REPRESENTATION =====
    
    /**
     * Graph representation using adjacency list
     */
    static class Graph {
        private int vertices;
        private List<List<Edge>> adjacencyList;
        
        public Graph(int vertices) {
            this.vertices = vertices;
            this.adjacencyList = new ArrayList<>();
            
            for (int i = 0; i < vertices; i++) {
                adjacencyList.add(new ArrayList<>());
            }
        }
        
        public void addEdge(int source, int destination, int weight) {
            adjacencyList.get(source).add(new Edge(destination, weight));
            // For undirected graph, uncomment the next line
            // adjacencyList.get(destination).add(new Edge(source, weight));
        }
        
        public List<Edge> getNeighbors(int vertex) {
            return adjacencyList.get(vertex);
        }
        
        public int getVertices() {
            return vertices;
        }
        
        public void printGraph() {
            for (int i = 0; i < vertices; i++) {
                System.out.print("Vertex " + i + " -> ");
                for (Edge edge : adjacencyList.get(i)) {
                    System.out.print("(" + edge.destination + ", " + edge.weight + ") ");
                }
                System.out.println();
            }
        }
    }
    
    static class Edge {
        int destination;
        int weight;
        
        Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
    
    /**
     * Create sample graph for demonstration
     */
    public static Graph createSampleGraph() {
        Graph graph = new Graph(6);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 1);
        graph.addEdge(4, 5, 6);
        return graph;
    }
    
    // ===== GRAPH TRAVERSAL =====
    
    /**
     * Depth First Search (DFS) traversal
     * Time Complexity: O(V + E), Space Complexity: O(V)
     */
    public static List<Integer> depthFirstSearch(Graph graph, int startVertex) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVertices()];
        
        dfsHelper(graph, startVertex, visited, result);
        return result;
    }
    
    private static void dfsHelper(Graph graph, int vertex, boolean[] visited, List<Integer> result) {
        visited[vertex] = true;
        result.add(vertex);
        
        for (Edge edge : graph.getNeighbors(vertex)) {
            if (!visited[edge.destination]) {
                dfsHelper(graph, edge.destination, visited, result);
            }
        }
    }
    
    /**
     * DFS using iterative approach (stack)
     * Time Complexity: O(V + E), Space Complexity: O(V)
     */
    public static List<Integer> depthFirstSearchIterative(Graph graph, int startVertex) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVertices()];
        Stack<Integer> stack = new Stack<>();
        
        stack.push(startVertex);
        
        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            
            if (!visited[vertex]) {
                visited[vertex] = true;
                result.add(vertex);
                
                // Push neighbors in reverse order to maintain DFS order
                List<Edge> neighbors = graph.getNeighbors(vertex);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    Edge edge = neighbors.get(i);
                    if (!visited[edge.destination]) {
                        stack.push(edge.destination);
                    }
                }
            }
        }
        
        return result;
    }
    
    /**
     * Breadth First Search (BFS) traversal
     * Time Complexity: O(V + E), Space Complexity: O(V)
     */
    public static List<Integer> breadthFirstSearch(Graph graph, int startVertex) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVertices()];
        Queue<Integer> queue = new LinkedList<>();
        
        visited[startVertex] = true;
        queue.offer(startVertex);
        
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);
            
            for (Edge edge : graph.getNeighbors(vertex)) {
                if (!visited[edge.destination]) {
                    visited[edge.destination] = true;
                    queue.offer(edge.destination);
                }
            }
        }
        
        return result;
    }
    
    // ===== SHORTEST PATH ALGORITHMS =====
    
    /**
     * Dijkstra's Shortest Path Algorithm
     * Time Complexity: O((V + E) log V), Space Complexity: O(V)
     */
    public static int[] dijkstraShortestPath(Graph graph, int source) {
        int vertices = graph.getVertices();
        int[] distances = new int[vertices];
        boolean[] visited = new boolean[vertices];
        
        // Initialize distances
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        
        // Priority queue to get minimum distance vertex
        PriorityQueue<NodeDistance> pq = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        pq.offer(new NodeDistance(source, 0));
        
        while (!pq.isEmpty()) {
            NodeDistance current = pq.poll();
            int vertex = current.vertex;
            
            if (visited[vertex]) continue;
            visited[vertex] = true;
            
            for (Edge edge : graph.getNeighbors(vertex)) {
                int neighbor = edge.destination;
                int weight = edge.weight;
                
                if (!visited[neighbor] && distances[vertex] != Integer.MAX_VALUE &&
                    distances[vertex] + weight < distances[neighbor]) {
                    distances[neighbor] = distances[vertex] + weight;
                    pq.offer(new NodeDistance(neighbor, distances[neighbor]));
                }
            }
        }
        
        return distances;
    }
    
    static class NodeDistance {
        int vertex;
        int distance;
        
        NodeDistance(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
    }
    
    /**
     * Bellman-Ford Shortest Path Algorithm
     * Time Complexity: O(VE), Space Complexity: O(V)
     */
    public static int[] bellmanFordShortestPath(Graph graph, int source) {
        int vertices = graph.getVertices();
        int[] distances = new int[vertices];
        
        // Initialize distances
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;
        
        // Relax all edges V-1 times
        for (int i = 0; i < vertices - 1; i++) {
            for (int u = 0; u < vertices; u++) {
                for (Edge edge : graph.getNeighbors(u)) {
                    int v = edge.destination;
                    int weight = edge.weight;
                    
                    if (distances[u] != Integer.MAX_VALUE && 
                        distances[u] + weight < distances[v]) {
                        distances[v] = distances[u] + weight;
                    }
                }
            }
        }
        
        // Check for negative weight cycles
        for (int u = 0; u < vertices; u++) {
            for (Edge edge : graph.getNeighbors(u)) {
                int v = edge.destination;
                int weight = edge.weight;
                
                if (distances[u] != Integer.MAX_VALUE && 
                    distances[u] + weight < distances[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    return null;
                }
            }
        }
        
        return distances;
    }
    
    // ===== MINIMUM SPANNING TREE =====
    
    /**
     * Kruskal's Minimum Spanning Tree Algorithm
     * Time Complexity: O(E log E), Space Complexity: O(V)
     */
    public static List<MSTEdge> kruskalMST(Graph graph) {
        List<MSTEdge> result = new ArrayList<>();
        List<MSTEdge> edges = new ArrayList<>();
        
        // Collect all edges
        for (int i = 0; i < graph.getVertices(); i++) {
            for (Edge edge : graph.getNeighbors(i)) {
                edges.add(new MSTEdge(i, edge.destination, edge.weight));
            }
        }
        
        // Sort edges by weight
        edges.sort((a, b) -> a.weight - b.weight);
        
        // Union-Find data structure
        UnionFind uf = new UnionFind(graph.getVertices());
        
        for (MSTEdge edge : edges) {
            if (uf.find(edge.source) != uf.find(edge.destination)) {
                result.add(edge);
                uf.union(edge.source, edge.destination);
                
                if (result.size() == graph.getVertices() - 1) break;
            }
        }
        
        return result;
    }
    
    static class MSTEdge {
        int source;
        int destination;
        int weight;
        
        MSTEdge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }
    
    static class UnionFind {
        private int[] parent;
        private int[] rank;
        
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
    }
    
    /**
     * Prim's Minimum Spanning Tree Algorithm
     * Time Complexity: O(E log V), Space Complexity: O(V)
     */
    public static List<MSTEdge> primMST(Graph graph) {
        List<MSTEdge> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVertices()];
        
        // Priority queue to get minimum weight edge
        PriorityQueue<MSTEdge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        
        // Start with vertex 0
        visited[0] = true;
        for (Edge edge : graph.getNeighbors(0)) {
            pq.offer(new MSTEdge(0, edge.destination, edge.weight));
        }
        
        while (!pq.isEmpty() && result.size() < graph.getVertices() - 1) {
            MSTEdge edge = pq.poll();
            
            if (visited[edge.destination]) continue;
            
            visited[edge.destination] = true;
            result.add(edge);
            
            for (Edge neighborEdge : graph.getNeighbors(edge.destination)) {
                if (!visited[neighborEdge.destination]) {
                    pq.offer(new MSTEdge(edge.destination, neighborEdge.destination, neighborEdge.weight));
                }
            }
        }
        
        return result;
    }
    
    // ===== TOPOLOGICAL SORTING =====
    
    /**
     * Topological Sort using DFS
     * Time Complexity: O(V + E), Space Complexity: O(V)
     */
    public static List<Integer> topologicalSort(Graph graph) {
        List<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVertices()];
        boolean[] recStack = new boolean[graph.getVertices()];
        
        for (int i = 0; i < graph.getVertices(); i++) {
            if (!visited[i]) {
                if (topologicalSortDFS(graph, i, visited, recStack, result)) {
                    return null; // Cycle detected
                }
            }
        }
        
        Collections.reverse(result);
        return result;
    }
    
    private static boolean topologicalSortDFS(Graph graph, int vertex, boolean[] visited, 
                                            boolean[] recStack, List<Integer> result) {
        visited[vertex] = true;
        recStack[vertex] = true;
        
        for (Edge edge : graph.getNeighbors(vertex)) {
            if (!visited[edge.destination]) {
                if (topologicalSortDFS(graph, edge.destination, visited, recStack, result)) {
                    return true; // Cycle detected
                }
            } else if (recStack[edge.destination]) {
                return true; // Cycle detected
            }
        }
        
        recStack[vertex] = false;
        result.add(vertex);
        return false;
    }
    
    /**
     * Kahn's Algorithm for Topological Sort
     * Time Complexity: O(V + E), Space Complexity: O(V)
     */
    public static List<Integer> kahnsTopologicalSort(Graph graph) {
        List<Integer> result = new ArrayList<>();
        int[] inDegree = new int[graph.getVertices()];
        
        // Calculate in-degrees
        for (int i = 0; i < graph.getVertices(); i++) {
            for (Edge edge : graph.getNeighbors(i)) {
                inDegree[edge.destination]++;
            }
        }
        
        // Add vertices with 0 in-degree to queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < graph.getVertices(); i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int count = 0;
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);
            count++;
            
            for (Edge edge : graph.getNeighbors(vertex)) {
                inDegree[edge.destination]--;
                if (inDegree[edge.destination] == 0) {
                    queue.offer(edge.destination);
                }
            }
        }
        
        if (count != graph.getVertices()) {
            return null; // Cycle detected
        }
        
        return result;
    }
    
    // ===== DEMONSTRATION METHODS =====
    
    private static void demonstrateGraphRepresentation() {
        System.out.println("1. GRAPH REPRESENTATION:\n");
        
        Graph graph = createSampleGraph();
        System.out.println("Sample graph created:");
        graph.printGraph();
        
        System.out.println();
    }
    
    private static void demonstrateGraphTraversal() {
        System.out.println("2. GRAPH TRAVERSAL:\n");
        
        Graph graph = createSampleGraph();
        
        System.out.println("Depth First Search (DFS):");
        System.out.println("  Recursive: " + depthFirstSearch(graph, 0));
        System.out.println("  Iterative: " + depthFirstSearchIterative(graph, 0));
        
        System.out.println("\nBreadth First Search (BFS):");
        System.out.println("  " + breadthFirstSearch(graph, 0));
        
        System.out.println();
    }
    
    private static void demonstrateShortestPathAlgorithms() {
        System.out.println("3. SHORTEST PATH ALGORITHMS:\n");
        
        Graph graph = createSampleGraph();
        
        System.out.println("Dijkstra's Shortest Path from vertex 0:");
        int[] dijkstraDistances = dijkstraShortestPath(graph, 0);
        for (int i = 0; i < dijkstraDistances.length; i++) {
            System.out.println("  Distance to " + i + ": " + dijkstraDistances[i]);
        }
        
        System.out.println("\nBellman-Ford Shortest Path from vertex 0:");
        int[] bellmanDistances = bellmanFordShortestPath(graph, 0);
        if (bellmanDistances != null) {
            for (int i = 0; i < bellmanDistances.length; i++) {
                System.out.println("  Distance to " + i + ": " + bellmanDistances[i]);
            }
        }
        
        System.out.println();
    }
    
    private static void demonstrateMinimumSpanningTree() {
        System.out.println("4. MINIMUM SPANNING TREE:\n");
        
        Graph graph = createSampleGraph();
        
        System.out.println("Kruskal's MST:");
        List<MSTEdge> kruskalMST = kruskalMST(graph);
        int totalWeight = 0;
        for (MSTEdge edge : kruskalMST) {
            System.out.println("  Edge: " + edge.source + " -> " + edge.destination + " (weight: " + edge.weight + ")");
            totalWeight += edge.weight;
        }
        System.out.println("  Total MST weight: " + totalWeight);
        
        System.out.println("\nPrim's MST:");
        List<MSTEdge> primMST = primMST(graph);
        totalWeight = 0;
        for (MSTEdge edge : primMST) {
            System.out.println("  Edge: " + edge.source + " -> " + edge.destination + " (weight: " + edge.weight + ")");
            totalWeight += edge.weight;
        }
        System.out.println("  Total MST weight: " + totalWeight);
        
        System.out.println();
    }
    
    private static void demonstrateTopologicalSorting() {
        System.out.println("5. TOPOLOGICAL SORTING:\n");
        
        // Create a directed acyclic graph
        Graph dag = new Graph(6);
        dag.addEdge(5, 2, 1);
        dag.addEdge(5, 0, 1);
        dag.addEdge(4, 0, 1);
        dag.addEdge(4, 1, 1);
        dag.addEdge(2, 3, 1);
        dag.addEdge(3, 1, 1);
        
        System.out.println("Directed Acyclic Graph:");
        dag.printGraph();
        
        System.out.println("\nTopological Sort (DFS):");
        List<Integer> topoSortDFS = topologicalSort(dag);
        if (topoSortDFS != null) {
            System.out.println("  " + topoSortDFS);
        } else {
            System.out.println("  Cycle detected");
        }
        
        System.out.println("\nTopological Sort (Kahn's):");
        List<Integer> topoSortKahn = kahnsTopologicalSort(dag);
        if (topoSortKahn != null) {
            System.out.println("  " + topoSortKahn);
        } else {
            System.out.println("  Cycle detected");
        }
        
        System.out.println();
    }
    
    private static void demonstratePerformanceAnalysis() {
        System.out.println("6. PERFORMANCE ANALYSIS:\n");
        
        // Performance comparison for different graph sizes
        int[] sizes = {100, 1000, 10000};
        
        for (int size : sizes) {
            Graph graph = createRandomGraph(size, size * 2);
            
            // DFS performance
            long startTime = System.nanoTime();
            List<Integer> dfs = depthFirstSearch(graph, 0);
            long dfsTime = System.nanoTime() - startTime;
            
            // BFS performance
            startTime = System.nanoTime();
            List<Integer> bfs = breadthFirstSearch(graph, 0);
            long bfsTime = System.nanoTime() - startTime;
            
            System.out.printf("Graph size %d (V=%d, E=%d):\n", size, size, size * 2);
            System.out.printf("  DFS: %d ns\n", dfsTime);
            System.out.printf("  BFS: %d ns\n", bfsTime);
            System.out.println();
        }
    }
    
    private static Graph createRandomGraph(int vertices, int edges) {
        Graph graph = new Graph(vertices);
        Random random = new Random();
        
        for (int i = 0; i < edges; i++) {
            int source = random.nextInt(vertices);
            int destination = random.nextInt(vertices);
            int weight = random.nextInt(10) + 1;
            
            if (source != destination) {
                graph.addEdge(source, destination, weight);
            }
        }
        
        return graph;
    }
}

/**
 * INTERVIEW QUESTIONS COVERED:
 * 
 * THEORETICAL:
 * 1. Explain the difference between DFS and BFS traversal.
 * 2. What are the advantages of adjacency list over adjacency matrix?
 * 3. How does Dijkstra's algorithm work and when to use it?
 * 4. Explain the concept of Minimum Spanning Tree.
 * 5. What is topological sorting and when is it used?
 * 6. How do you detect cycles in a directed graph?
 * 
 * PRACTICAL:
 * 1. Implement DFS and BFS traversal algorithms.
 * 2. Find shortest path using Dijkstra's algorithm.
 * 3. Implement Kruskal's and Prim's MST algorithms.
 * 4. Perform topological sorting on directed acyclic graphs.
 * 5. Detect cycles in graphs.
 * 
 * TRICKY SCENARIOS:
 * 1. Handle disconnected graphs in traversal.
 * 2. Deal with negative weight edges in shortest path.
 * 3. Optimize memory usage for large graphs.
 * 4. Handle cycles in topological sorting.
 * 
 * PERFORMANCE:
 * 1. Compare DFS vs BFS performance.
 * 2. Analyze shortest path algorithm complexity.
 * 3. Optimize MST algorithms for specific use cases.
 * 4. Benchmark graph operations.
 * 
 * DESIGN PATTERNS:
 * 1. Strategy pattern for different traversal algorithms.
 * 2. Visitor pattern for graph operations.
 * 3. Iterator pattern for graph traversal.
 * 4. Factory pattern for graph creation.
 */
