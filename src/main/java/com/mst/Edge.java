package com.mst;

public class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }

    @Override
    public String toString() {
        return "(" + src + " - " + dest + ", weight: " + weight + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return (src == edge.src && dest == edge.dest) ||
                (src == edge.dest && dest == edge.src);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(src) + Integer.hashCode(dest);
    }
}
