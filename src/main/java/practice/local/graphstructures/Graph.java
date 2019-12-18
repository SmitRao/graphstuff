package practice.local.graphstructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Graph {
    private Set<Vertex> V;
    private ArrayList<ArrayList<Vertex>> shortestPathDistances;

    public Graph() {
        super();
        this.V = new HashSet<Vertex>();
        this.shortestPathDistances = new ArrayList<ArrayList<Vertex>>();
    }

    public Set<Vertex> getV() {
        return this.V;
    }

    public int getNumVertices() {
        return this.V.size();
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

    /**
     * Uses Floyd-Warshall to find shortest path from vFrom to vTo.
     */
    public void ShortestPath(Vertex vFrom, Vertex vTo) {
        FloydWarshall(vFrom, vTo, this.getNumVertices());
    }

    public void FloydWarshall(Vertex vFrom, Vertex vTo, int numVertices) {
        
    }
}