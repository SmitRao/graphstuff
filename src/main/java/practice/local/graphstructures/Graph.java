package practice.local.graphstructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Set<Vertex> V;
    private ArrayList<ArrayList<Integer>> shortestPathDistances;
    private Map<Integer, Vertex> idLookup;

    public Graph() {
        super();
        this.V = new HashSet<Vertex>();
        this.idLookup = new HashMap<Integer, Vertex>();
    }

    public Set<Vertex> getV() {
        return this.V;
    }

    public int getNumVertices() {
        return this.V.size();
    }

    public void addVertex(Vertex vertex) {
        this.V.add(vertex);
        this.idLookup.put(vertex.getId(), vertex);
    }

    public Map<Integer, Vertex> getIdLookups() {
        return this.idLookup;
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
     * Uses Floyd-Warshall to find shortest path from vFrom to vTo. Return cost of
     * shortest path from vFrom to vTo.
     */
    public int ShortestPath(Vertex vFrom, Vertex vTo) {
        FloydWarshall(vFrom, vTo, this.getNumVertices());
        int distance = this.shortestPathDistances.get(vFrom.getId()).get(vTo.getId());
        return distance;
    }

    public void FloydWarshall(Vertex vFrom, Vertex vTo, int numVertices) {
        this.shortestPathDistances = new ArrayList<ArrayList<Integer>>(this.getNumVertices());
        for (int i = 0; i < this.getNumVertices(); i++) {
            this.shortestPathDistances.set(i, new ArrayList<Integer>(this.getNumVertices()));
            for (int j = 0; j < this.getNumVertices(); j++)
                this.shortestPathDistances.get(i).set(j, Integer.MAX_VALUE);
        } // initialize distances to infinity (i.e. Integer.MAX_VALUE)

        for (int i = 0; i < this.getNumVertices(); i++) {
            for (int edgeNodeId : this.idLookup.get(i).getConnections()) {
                this.shortestPathDistances.get(i).set(edgeNodeId, this.idLookup.get(i).weightTo(edgeNodeId));
                // initialize shortest paths from u to v with weight from u to v if edge exists
            }
            this.shortestPathDistances.get(i).set(i, 0); // shortest path to same node is 0
        }
    }
}