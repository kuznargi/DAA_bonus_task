package com.mst;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int vertices = 5;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 3, 6));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 8));
        edges.add(new Edge(1, 4, 5));
        edges.add(new Edge(2, 4, 7));
        edges.add(new Edge(3, 4, 9));

        MSTHandler handler = new MSTHandler(vertices, edges);

        System.out.println("Original MST:");
        handler.displayMST();

        Edge edgeToRemove = null;
        for (Edge e : handler.getMST()) {
            if ((e.src == 1 && e.dest == 2) || (e.src == 2 && e.dest == 1)) {
                edgeToRemove = e;
                break;
            }
        }

        if (edgeToRemove != null) {
            handler.removeEdge(edgeToRemove);

            List<Set<Integer>> components = handler.getComponents();
            handler.displayComponents(components);

            if (components.size() == 2) {
                Set<Integer> comp1 = components.get(0);
                Set<Integer> comp2 = components.get(1);

                Edge replacement = handler.findReplacementEdge(comp1, comp2);
                if (replacement != null) {
                    handler.addEdge(replacement);
                    System.out.println("\nUpdated MST:");
                    handler.displayMST();
                } else {
                    System.out.println("No replacement edge found.");
                }
            }
        }
    }
}