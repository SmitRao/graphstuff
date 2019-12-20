package practice.local.graphstructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Graph {
    protected final static int maxEdgeWeight = 999999999;
    protected final static int INF = 1073741823; // floor(Integer.MAX_VALUE / 2)...
    // anything higher than this value will cause incorrect output

    protected static Graph currentGraph; // singleton instance for current graph, making old graphs
    // uneditable due to vertex ordering issues

    private Set<Vertex> V;
    private ArrayList<ArrayList<Integer>> shortestPathDistances;
    private Map<Integer, Vertex> idLookup;
    private boolean recomputeDistances; // we can short-circuit Floyd-Warshall if this is false! (Amortization)

    /**
     * Whenever a new graph is instantiated, old graphs cannot add more vertices!
     * This is a significant side-effect!
     */
    public Graph() {
        super();
        Graph.currentGraph = this;
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
        if (Graph.currentGraph == this) {
            this.V.add(vertex);
            this.idLookup.put(vertex.getId(), vertex);
            this.recomputeDistances = true;
        }
    }

    public Map<Integer, Vertex> getIdLookups() {
        return this.idLookup;
    }

    public boolean addEdge(Vertex vFrom, Vertex vTo, int weight) {
        if (this.V.contains(vFrom) && this.V.contains(vTo) && weight <= Graph.maxEdgeWeight
                && Graph.currentGraph == this) {
            vFrom.connect(vTo, weight);
            this.recomputeDistances = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean addEdge(int vFromId, int vToId, int weight) {
        if (this.idLookup.keySet().contains(vFromId) && this.idLookup.keySet().contains(vToId)
                && weight <= Graph.maxEdgeWeight && Graph.currentGraph == this) {
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
                this.shortestPathDistances.get(i).add(Graph.INF);
        } // initialize distances to infinity (i.e. Graph.INF)

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

        int i, j, k;
        for (k = 0; k < this.getNumVertices(); k++) {
            for (i = 0; i < this.getNumVertices(); i++) {
                for (j = 0; j < this.getNumVertices(); j++) {
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
        System.out.println("\n-----------------------");
        for (ArrayList<Integer> row : this.shortestPathDistances) {
            System.out.println(row);
        }
        System.out.println("-----------------------\n");
        return this.shortestPathDistances;
    }

    public ArrayList<Integer> bfs(Vertex source) {
        ArrayList<Integer> traversalIds = new ArrayList<Integer>();
        // Queue<Integer> processQueue = new Queue<Integer>();
        return traversalIds;
    }

    public ArrayList<Integer> bfs(int sourceId) {
        // short version... might be naive
        return this.bfs(this.idLookup.get(sourceId));
    }
}