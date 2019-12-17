package practice.local.graphstructures;

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
        Edge e1 = new Edge(v1, v2);
        Edge e2 = new Edge(v2, v3);
        g.addE(e1);
        g.addE(e2);
        g.addV(v1);
        g.addV(v2);
        g.addV(v3);

        System.out.println(g);
        System.out.println("Edges: " + g.getE());
        System.out.println("Vertices: " + g.getV());
    }
}
