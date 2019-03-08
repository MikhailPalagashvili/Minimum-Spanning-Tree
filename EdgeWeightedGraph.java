import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EdgeWeightedGraph {
    private int vertices;
    private List<List<Edge>> adj;

    public EdgeWeightedGraph(int vertices) {
        this.vertices = vertices;
        this.adj = new ArrayList <>();
        for (int vertex = 0; vertex < vertices; vertex++) {
            this.adj.add(new LinkedList <>());
        }
    }

    public void addEdge(Edge edge) {
        int v = edge.either();
        int w = edge.other(v);
        this.adj.get(v).add(edge);
        this.adj.get(w).add(edge);
    }

    public int getVertices() {
        return this.vertices;
    }

    public Iterable<Edge> edges() {
        List<Edge> edgeList = new LinkedList <>();
        for (int v = 0; v < vertices; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v)
                    edgeList.add(e);
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) edgeList.add(e);
                    selfLoops++;
                }
            }
        }
        return edgeList;
    }

    public Iterable<Edge> adj(int v) {
        return this.adj.get(v);
    }


}
