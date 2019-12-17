package practice.local.graphstructures;

import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<Vertex> V = new HashSet<Vertex>();
    private Set<Edge> E = new HashSet<Edge>();

    public Graph() {
        super();
    }

    public Graph(Set<Vertex> V, Set<Edge> E) {
        this.V = V;
        this.E = E;
    }

    public void addV(Vertex vertex) {
        this.V.add(vertex);
    }

    public void addE(Edge edge) {
        this.E.add(edge);
    }

    public Set<Vertex> getV() {
        return this.V;
    }

    public Set<Edge> getE() {
        return this.E;
    }

}