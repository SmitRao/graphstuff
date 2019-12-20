package practice.local.graphstructures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Vertex {
    private static int ordering = 0; // augmentation for maintaining an integer ordering for vertices
    private int id;
    private int value;
    private HashSet<Integer> connections;
    private Map<Integer, Integer> edgeWeights;

    public Vertex(int val) {
        this.value = val;
        this.connections = new HashSet<Integer>();
        this.edgeWeights = new HashMap<Integer, Integer>();
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

    public HashSet<Integer> getConnections() {
        return connections;
    }

    public void connect(Vertex newConnection, int weight) {
        if (newConnection == this)
            return;
        this.connections.add(newConnection.getId());
        this.edgeWeights.put(newConnection.getId(), weight);
    }

    public void connect(int newConnectionId, int weight) {
        if (newConnectionId == this.getId())
            return;
        this.connections.add(newConnectionId);
        this.edgeWeights.put(newConnectionId, weight);
    }

    public boolean connectsTo(Vertex candidate) {
        if (this == candidate)
            return true;
        else
            return this.connections.contains(candidate.getId());
    }

    public boolean connectsTo(int candidateId) {
        if (this.getId() == candidateId)
            return true;
        else
            return this.connections.contains(candidateId);
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
            return this.edgeWeights.get(candidate.getId());
        } else {
            return Integer.MAX_VALUE;
        }
    }

    public int weightTo(int candidateId) {
        if (this.getId() == candidateId)
            return 0;
        if (this.connectsTo(candidateId)) {
            return this.edgeWeights.get(candidateId);
        } else {
            return Integer.MAX_VALUE;
        }
    }
}