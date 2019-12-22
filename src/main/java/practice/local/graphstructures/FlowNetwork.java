package practice.local.graphstructures;

public class FlowNetwork extends Graph {
    private Vertex source;
    private Vertex target;
    private int flow;

    public FlowNetwork() {
        super();
        this.flow = 0;
    }

    public void setSource(Vertex s) {
        this.source = s;
    }

    public void setSource(int sourceId) {
        this.source = this.getIdLookups().get(sourceId);
    }

    public void setTarget(Vertex t) {
        this.target = t;
    }

    public void setTarget(int targetId) {
        this.target = this.getIdLookups().get(targetId);
    }

    public Vertex getSource() {
        return this.source;
    }

    public Vertex getTarget() {
        return this.target;
    }

    public int getCurrentFlow() {
        return this.flow;
    }

    public void edmondsKarp() {
        
    }

    public int getMaxFlow() {
        this.edmondsKarp();
        return this.flow;
    }

}