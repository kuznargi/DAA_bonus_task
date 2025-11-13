package com.mst;

import java.util.*;

public class MSTHandler {
    private int vertices;
    private List<Edge> allEdges;
    private List<Edge> mstEdges;

    public MSTHandler(int vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.allEdges = edges;
        this.mstEdges = buildMST();
    }

    private List<Edge> buildMST() {
        List<Edge> sortedEdges = new ArrayList<>(allEdges);
        Collections.sort(sortedEdges);
        UnionFind uf = new UnionFind(vertices);
        List<Edge>  mst = new ArrayList<>();

        for (Edge edge : sortedEdges) {
            if(uf.union(edge.src, edge.dest)) {
                mst.add(edge);
            }
        }
        return mst;
    }

    public List<Edge> getMST(){
        return new ArrayList<>(mstEdges);
    }

    public void displayMST(){
        System.out.println("MST Edges:");
        for(Edge edge : mstEdges){
            System.out.println(edge);
        }
    }

    public void removeEdge(Edge edgeToRemove){
        mstEdges.remove(edgeToRemove);
        allEdges.remove("Removed edge: " + edgeToRemove);
    }

    public List<Set<Integer>> getComponents() {
        UnionFind uf = new UnionFind(vertices);
        for (Edge edge : mstEdges) {
            uf.union(edge.src, edge.dest);
        }

        Map<Integer, Set<Integer>> componentMap = new HashMap<>();
        for (int i = 0; i < vertices; i++) {
            int root = uf.find(i);
            componentMap.computeIfAbsent(root, k -> new HashSet<>()).add(i);
        }

        return new ArrayList<>(componentMap.values());
    }

    public void displayComponents(List<Set<Integer>> components) {
        System.out.println("Disconnected Components:");
        for (int i = 0; i < components.size(); i++) {
            System.out.println("Component " + (i + 1) + ": " + components.get(i));
        }
    }

    public Edge findReplacementEdge(Set<Integer> comp1, Set<Integer> comp2) {
        Edge minEdge = null;
        int minWeight = Integer.MAX_VALUE;

        for (Edge edge : allEdges) {
            boolean inComp1Src = comp1.contains(edge.src);
            boolean inComp1Dest = comp1.contains(edge.dest);
            boolean inComp2Src = comp2.contains(edge.src);
            boolean inComp2Dest = comp2.contains(edge.dest);

            if ((inComp1Src && inComp2Dest) || (inComp1Dest && inComp2Src)) {
                if (edge.weight < minWeight) {
                    minWeight = edge.weight;
                    minEdge = edge;
                }
            }
        }

        return minEdge;
    }

    public void addEdge(Edge edge) {
        mstEdges.add(edge);
        System.out.println("Added replacement edge: " + edge);
    }
}
