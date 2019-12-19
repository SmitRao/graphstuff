package practice.local.graphstructures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Vertex {
    private static int ordering = 1; // augmentation for maintaining an integer ordering for vertices
    private int id;
    private int value;
    private HashSet<Vertex> connections;
    private Map<Vertex, Integer> edgeWeights;

    public Vertex(int val) {
        this.value = val;
        this.connections = new HashSet<Vertex>();
        this.edgeWeights = new HashMap<Vertex, Integer>();
        this.id = Vertex.ordering;
        this.incrementOrdering();
    }

    private void incrementOrdering() {
        Vertex.ordering++;
    }

    public int getId() {
        return this.id;
    }

    public int getValue() {
        return this.value;
    }

    public HashSet<Vertex> getConnections() {
        return connections;
    }

    public void connect(Vertex newConnection, int weight) {
        if (newConnection == this)
            return;
        this.connections.add(newConnection);
        this.edgeWeights.put(newConnection, weight);
    }

    public boolean connectsTo(Vertex candidate) {
        if (this == candidate)
            return true;
        else
            return this.connections.contains(candidate);
    }

    /**
     * Returns weight from this vertex to candidate. Just hope no weight is
     * Integer.MAX_VALUE (2147483647) since this is the representation of an edge
     * not existing (hence having "infinite" weight).
     * 
     * @param candidate
     * @return int weight from this to candidate
     */
    public int weightTo(Vertex candidate) {
        if (this == candidate)
            return 0;
        if (this.connectsTo(candidate)) {
            return this.edgeWeights.get(candidate);
        } else {
            return Integer.MAX_VALUE;
        }
    }
}