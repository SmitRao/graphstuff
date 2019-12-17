package practice.local.graphstructures;

import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<Vertex> V = new HashSet<Vertex>();

    public Graph() {
        super();
    }

    public Set<Vertex> getV() {
        return this.V;
    }

    public void addVertex(Vertex vertex) {
        this.V.add(vertex);
    }

    public boolean addEdge(Vertex vFrom, Vertex vTo, int weight) {
        if (this.V.contains(vFrom) && this.V.contains(vTo)) {
            vFrom.connect(vTo, weight);
            return true;
        } else {
            return false;
        }
    }
}