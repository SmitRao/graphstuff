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
    private boolean recomputeDistances; // we can short-circuit Floyd-Warshall if this is false! 
                                        // Good for amoritzation.

    /**
     * Whenever a new graph is instantiated, old graphs cannot add more vertices!
     * This is a significant side-effect!
     */
    public Graph() {
        super();
        this.recomputeDistances = true;
        Vertex.resetOrdering();
        this.V = new HashSet<Vertex>();
        this.shortestPathDistances = new ArrayList<ArrayList<Integer>>();
        this.idLookup = new HashMap<Integer, Vertex>(); // id to vertex mapping
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
        this.recomputeDistances = true;
    }

    public Map<Integer, Vertex> getIdLookups() {
        return this.idLookup;
    }

    public boolean addEdge(Vertex vFrom, Vertex vTo, int weight) {
        if (this.V.contains(vFrom) && this.V.contains(vTo)) {
            vFrom.connect(vTo, weight);
            this.recomputeDistances = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean addEdge(int vFromId, int vToId, int weight) {
        if (this.idLookup.keySet().contains(vFromId) && this.idLookup.keySet().contains(vToId)) {
            this.idLookup.get(vFromId).connect(vToId, weight);
            this.recomputeDistances = true;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Uses Floyd-Warshall to find shortest path from vFrom to vTo. Return cost of
     * shortest path from vFrom to vTo.
     */
    public int getShortestPath(Vertex vFrom, Vertex vTo) {
        if (this.shortestPathDistances == null || this.shortestPathDistances.size() != this.getNumVertices())
            return -1;
        if (this.recomputeDistances)
            this.updateFloydWarshallDistances();
        int distance = this.shortestPathDistances.get(vFrom.getId()).get(vTo.getId());
        return distance;
    }

    public int getShortestPath(int vFromId, int vToId) {
        if (this.shortestPathDistances == null || this.shortestPathDistances.size() != this.getNumVertices())
            return -1;
        if (this.recomputeDistances)
            this.updateFloydWarshallDistances();
        int distance = this.shortestPathDistances.get(vFromId).get(vToId);
        return distance;
    }

    protected void initializeFloydWarshallDistances() {
        if (!this.recomputeDistances)
            return;
        for (int i = 0; i < this.getNumVertices(); i++) {
            this.shortestPathDistances.add(new ArrayList<Integer>(this.getNumVertices()));
            for (int j = 0; j < this.getNumVertices(); j++)
                this.shortestPathDistances.get(i).add(Integer.MAX_VALUE);
        } // initialize distances to infinity (i.e. Integer.MAX_VALUE)

        for (int i = 0; i < this.getNumVertices(); i++) {
            for (int edgeNodeId : this.idLookup.get(i).getConnections()) {
                this.shortestPathDistances.get(i).set(edgeNodeId, this.idLookup.get(i).weightTo(edgeNodeId));
                // initialize shortest paths from u to v with weight from u to v if edge exists
            }
            this.shortestPathDistances.get(i).set(i, 0); // shortest path to same node is 0
        }
    }

    /**
     * Updates shortestPathDistances using the Floyd-Warshall multi-source shortest
     * path algorithm. Accetps all edge weights.
     * 
     */
    protected void updateFloydWarshallDistances() {
        if (!this.recomputeDistances)
            return;
        if (this.shortestPathDistances == null || this.shortestPathDistances.size() != this.getNumVertices())
            this.initializeFloydWarshallDistances();
        for (int k = 0; k < this.getNumVertices(); k++) {
            for (int i = 0; i < this.getNumVertices(); i++) {
                for (int j = 0; j < this.getNumVertices(); j++) {
                    if (this.shortestPathDistances.get(i).get(j) > this.shortestPathDistances.get(i).get(k)
                            + this.shortestPathDistances.get(k).get(j)) {
                        this.shortestPathDistances.get(i).set(j,
                                this.shortestPathDistances.get(i).get(k) + this.shortestPathDistances.get(k).get(j));
                    }
                }
            }
        }
        this.recomputeDistances = false;
    }

    public ArrayList<ArrayList<Integer>> getShortestPathDistanceMap() {
        if (this.recomputeDistances)
            this.updateFloydWarshallDistances();
        return this.shortestPathDistances;
    }
}