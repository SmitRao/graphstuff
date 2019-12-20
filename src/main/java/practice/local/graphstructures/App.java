package practice.local.graphstructures;

import java.util.Map;
import java.util.ArrayList;

/**
 * Experiments with graph stuff.
 *
 */
public class App {
    public static void main(String[] args) {

        Graph g = new Graph();

        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addEdge(v1, v2, 33);
        g.addEdge(v1, v2, 1);
        g.addEdge(v4, v2, 43);

        System.out.println("\n\n\nConnections: ");
        for (Vertex v : g.getV()) {
            System.out.println(v.getValue() + ":");
            for (int connection : v.getConnections())
                System.out.println(" " + g.getIdLookups().get(connection));
        }

        System.out.println("\nConnecting from 1 to 4: " + v1.connectsTo(v4));
        System.out.println("Connecting from 1 to 3: " + v1.connectsTo(v3));
        System.out.println("Connecting from 1 to 2: " + v1.connectsTo(v2) + " with weight " + v1.weightTo(v2));
        System.out.println("Connecting from 4 to 2: " + v4.connectsTo(v2) + " with weight " + v4.weightTo(v2));
        System.out.println("Connecting from 2 to 1: " + v2.connectsTo(v1));
        System.out.println("Connecting from 2 to 2: " + v2.connectsTo(v2) + " with weight " + v2.weightTo(v2) + "\n");

        Map<Integer, Vertex> idLookups = g.getIdLookups();
        for (int i = 0; i < g.getNumVertices(); i++) {
            System.out.println("Vertex at " + i + ": " + idLookups.get(i));
        }

        // Vertex s = new Vertex(111);
        // Vertex t = new Vertex(999);
        // FlowNetwork flowNet = new FlowNetwork(s, t);

        System.out.println("\n\nTest Floyd Warshall:\n\n");
        Graph g2 = new Graph();
        for (int i = 0; i < 4; i++) {
            g2.addVertex(new Vertex(i));
        }
        g2.addEdge(0, 2, -2);
        g2.addEdge(1, 2, 3);
        g2.addEdge(1, 0, 4);
        g2.addEdge(2, 3, 2);
        g2.addEdge(3, 1, -1);

        g2.updateFloydWarshallDistances();
        System.out.println(g2.getShortestPathDistanceMap());

    }
}
