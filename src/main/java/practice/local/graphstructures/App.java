package practice.local.graphstructures;

import java.util.Map;

/**
 * Experiments with graph stuff.
 *
 */
public class App {

    public static void p(String s) {
        System.out.println(s);
    }

    public static void p(int s) {
        System.out.println(s);
    }

    public static void p(Object s) {
        System.out.println(s);
    }

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
            System.out.println(v.getId() + ":");
            for (int connection : v.getConnections())
                System.out.println(" " + g.getIdLookups().get(connection) + " (id: "
                        + g.getIdLookups().get(connection).getId() + ")");
        }

        System.out.println("\nConnecting from 0 to 3: " + v1.connectsTo(v4));
        System.out.println("Connecting from 0 to 2: " + v1.connectsTo(v3));
        System.out.println("Connecting from 0 to 1: " + v1.connectsTo(v2) + " with weight " + v1.weightTo(v2));
        System.out.println("Connecting from 3 to 1: " + v4.connectsTo(v2) + " with weight " + v4.weightTo(v2));
        System.out.println("Connecting from 1 to 0: " + v2.connectsTo(v1));
        System.out.println("Connecting from 1 to 1: " + v2.connectsTo(v2) + " with weight " + v2.weightTo(v2) + "\n");

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
        g2.getShortestPathDistanceMap();

        System.out.println("\nShortest path from 3 to 2... : " + g2.getShortestPath(3, 2));
        System.out.println("Shortest path from 3 to 3... : " + g2.getShortestPath(3, 3));

        System.out.println("\n\nTest Floyd Warshall 2:\n\n");
        Graph g3 = new Graph();
        for (int i = 0; i < 8; i++) {
            g3.addVertex(new Vertex(i));
        }
        g3.addEdge(0, 1, 7);
        g3.addEdge(0, 2, 2);
        g3.addEdge(0, 4, 2);
        g3.addEdge(1, 0, 7);
        g3.addEdge(1, 2, 1);
        g3.addEdge(1, 5, 8);
        g3.addEdge(1, 6, 3);
        g3.addEdge(2, 0, 2);
        g3.addEdge(3, 1, 2);
        g3.addEdge(3, 7, 8);
        g3.addEdge(4, 2, 9);
        g3.addEdge(4, 6, 1);
        g3.addEdge(5, 1, 1);
        g3.addEdge(5, 3, 2);
        g3.addEdge(5, 7, 9);
        g3.addEdge(6, 5, 8);
        g3.addEdge(6, 7, 6);
        g3.getShortestPathDistanceMap();

        System.out.println("\n\nBFS: " + g3.bfs(0));

        Graph g4 = new Graph();
        for (int x = 0; x < 7; x++)
            g4.addVertex(new Vertex(x));
        g4.addEdge(0, 1, 2);
        g4.addEdge(0, 2, 2);
        g4.addEdge(1, 2, 2);
        g4.addEdge(2, 0, 2);
        g4.addEdge(2, 3, 2);
        g4.addEdge(3, 3, 2);

        g4.addEdge(3, 4, 2);
        g4.addEdge(5, 0, 2);

        System.out.println("\n\nBFS from 2: " + g4.bfs(2));
        System.out.println("\n\nDFS from 2: " + g4.dfs(2));

        // Test flow network

        FlowNetwork net = new FlowNetwork();

        Vertex s = new Vertex(0);
        Vertex t = new Vertex(1);

        p("\n\nSource ID: " + s.getId());
        p("Target ID: " + t.getId());

        net.setSource(s);
        net.setTarget(t);

        for (int i = 2; i < 5; i++) {
            net.addVertex(new Vertex(i));
        }

        net.addEdge(0, 2, 1);
        net.addEdge(0, 3, 1);
        net.addEdge(0, 4, 1);

        net.addEdge(2, 1, 1);
        net.addEdge(3, 1, 1);
        net.addEdge(4, 1, 1);
        p("Max flow: " + net.getMaxFlow());
        p("BFS from source: " + net.bfs(s));

    }
}
