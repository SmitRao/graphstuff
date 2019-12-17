package practice.local.graphstructures;

public class Edge {
    Vertex from;
    Vertex to;

    public Edge(Vertex from, Vertex to) {
        this.from = from;
        this.to = to;
    }

    public Vertex getSource() {
        return this.from;
    }

    public Vertex getTarget() {
        return this.to;
    }
}