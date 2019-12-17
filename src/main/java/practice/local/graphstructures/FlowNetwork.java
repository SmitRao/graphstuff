package practice.local.graphstructures;

public class FlowNetwork extends Graph {
    private Vertex source;
    private Vertex target;
    private int currentFlow;

    public FlowNetwork(Vertex s, Vertex t) {
        super();
        this.source = s;
        this.target = t;
        this.currentFlow = s.connectsTo(t) ? s.weightTo(t) : 0;
    }

    public Vertex getSource() {
        return this.source;
    }

    public Vertex getTarget() {
        return this.target;
    }

    public int getCurrentFlow() {
        return this.currentFlow;
    }
}