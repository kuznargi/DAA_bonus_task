# Minimum Spanning Tree (MST) – Edge Removal & Replacement Demo

This is a complete, educational Java project (bonus task) that demonstrates the full process of working with a Minimum Spanning Tree (MST). It includes building the MST using Kruskal’s algorithm, removing an edge from it, detecting the resulting disconnected components, and then efficiently finding the cheapest replacement edge to reconnect the components and restore a valid MST. The program displays the original MST, the removed edge, the disconnected components, the replacement edge, and the new MST. The implementation is clean, well-structured, and runs seamlessly with either plain javac/java commands or using Maven.

## Problem Statement

Given an undirected, weighted graph, the task is to:

Construct its Minimum Spanning Tree (MST) using Kruskal’s algorithm.

Remove a specified edge from the MST, which disconnects the tree into two components.

Identify the disconnected components after edge removal.

Efficiently find the lightest (minimum-weight) edge that reconnects the two components and restores the MST property.
## Project Structure
```
├── src/
│   ├── Edge.java
│   ├── UnionFind.java
│   ├── MSTHandler.java
│   └── Main.java
├── pom.xml
└── README.md
```


## How to Run

### Option 1: Using Maven (recommended)

```bash

git clone https://github.com/kuznargi/DAA_bonus_task.git

```
### Run directly
```bash 
mvn compile
mvn exec:java
```

### Option 2: Executable JAR
```bash 
mvn package
java -jar target/bonus_task-1.0-SNAPSHOT-jar-with-dependencies.jar
```
### One-liner (always works):
```bash
java -jar target/*-jar-with-dependencies.jar
``` 


## Algorithm Explanation

### Overview: Time Complexity Analysis

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| **MST Construction** (Kruskal's) | O(E log E) | O(V + E) |
| **Edge Removal** | O(E) | O(1) |
| **Finding Replacement** | O(E) | O(V) |
| **Total Operation** | **O(E log E)** | **O(V + E)** |

*Where V = number of vertices, E = number of edges, E_mst = edges in MST (always V-1)*

---

### 1. MST Construction (Kruskal's Algorithm)

**Time Complexity:** O(E log E)  
**Space Complexity:** O(V + E)

#### Pseudocode

```
Algorithm: Kruskal_MST(Graph G)
Input: Graph G with V vertices and E edges
Output: MST as a list of edges

1. Initialize empty MST = []
2. Initialize UnionFind(V)           // O(V)
3. Sort all edges by weight          // O(E log E)
4. 
5. for each edge (u, v, weight) in sorted_edges:  // O(E) iterations
6.     if find(u) ≠ find(v):         // O(α(V)) ≈ O(1)
7.         MST.add(edge(u, v, weight))
8.         union(u, v)               // O(α(V)) ≈ O(1)
9.         if MST.size == V - 1:
10.            break
11. 
12. return MST
```

#### Complexity Breakdown

- **Sorting edges:** O(E log E) - dominates the complexity
- **Union-Find operations:** O(E · α(V)) ≈ O(E) where α is the inverse Ackermann function
- **Total:** O(E log E + E · α(V)) = **O(E log E)**

**Key optimization:** Union-Find with path compression and union-by-rank ensures α(V) ≈ O(1) amortized time per operation.

---

### 2. Edge Removal

**Time Complexity:** O(E)  
**Space Complexity:** O(1)

#### Pseudocode

```
Algorithm: Remove_Edge(MST, edge_to_remove)
Input: MST edge list, edge to remove
Output: Updated MST without the edge

1. for i = 0 to MST.size - 1:       // O(E_mst) = O(V)
2.     if MST[i].equals(edge_to_remove):
3.         MST.remove(i)             // O(1) for ArrayList removal at known index
4.         return MST
5. 
6. throw EdgeNotFoundException
```

#### Complexity Note

While finding the edge is O(V), in practice this is negligible. The removal creates exactly two disconnected components.

---

### Union-Find Data Structure

**Time Complexity per operation:** O(α(V)) ≈ O(1) amortized  
**Space Complexity:** O(V)

#### Pseudocode

```
Algorithm: Union_Find
Variables: parent[V], rank[V]

Initialize(V):
1. for i = 0 to V - 1:
2.     parent[i] = i           // Each vertex is its own parent
3.     rank[i] = 0             // Initial rank is 0

Find(x):                        // Path compression
1. if parent[x] ≠ x:
2.     parent[x] = Find(parent[x])    // Recursively compress path
3. return parent[x]

Union(x, y):                    // Union by rank
1. root_x = Find(x)
2. root_y = Find(y)
3. 
4. if root_x == root_y:
5.     return                   // Already in same set
6. 
7. if rank[root_x] < rank[root_y]:
8.     parent[root_x] = root_y
9. else if rank[root_x] > rank[root_y]:
10.     parent[root_y] = root_x
11. else:
12.     parent[root_y] = root_x
13.     rank[root_x]++
```

# Conclusion
This project provides a complete, hands-on demonstration of how to efficiently maintain and restore a Minimum Spanning Tree (MST) after an edge removal, a common challenge in network optimization and dynamic graph problems.

By combining Kruskal’s algorithm with an optimized Union-Find data structure (featuring path compression and union by rank), the program achieves excellent performance, maintaining nearly linear time complexity even for large graphs.