import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
/*
Using Prim's Algorithm
 */
public class MST {

    private PriorityQueue<Edge> pq;
    private Queue<Edge> mst;
    private boolean[] marked;
    private double weight;

    public MST(EdgeWeightedGraph graph) {
        this.weight = 0.0;
        this.marked = new boolean[graph.getVertices()];
        this.mst = new LinkedList <>();
        this.pq = new PriorityQueue <>();
        visit(graph,0); //assume graph is connected

        while (!pq.isEmpty()) {
            Edge e = pq.poll(); //repeatedly delete the min-weight edge of the pq
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) continue; //ignore if both endpoints are in the tree (edge is obsolete)
            this.mst.add(e); //add e to the tree
            if (!marked[v]) visit(graph,v); //add unmarked vertex on the tree as well as its incident edges on the pq
            if (!marked[w]) visit(graph, w);
        }
        for (Edge e : mst) this.weight += e.getWeight();
    }
    //put unmarked vertex on the tree and add any non-tree edges to the pq
    private void visit(EdgeWeightedGraph graph, int v) {
        this.marked[v] = true;
        for (Edge e : graph.adj(v))
            if (!marked[e.other(v)])
                this.pq.add(e);
    }

    public Iterable<Edge> edges() {
        return this.mst;
    }

    public double weight() { return this.weight; }

    public static void main(String[] args) {
        final int v = 8;
        EdgeWeightedGraph graph = new EdgeWeightedGraph(v);
        System.out.println(graph.getVertices() + " vertices");
        System.out.println("********");
        graph.addEdge(new Edge(0,7,.16)); graph.addEdge(new Edge(0,2,.26));
        graph.addEdge(new Edge(1,7,.19)); graph.addEdge(new Edge(2,3, .17));
        graph.addEdge(new Edge(5,7,.28)); graph.addEdge(new Edge(4,5,.35));
        graph.addEdge(new Edge(6,2,.40));
        MST mst = new MST(graph);
        for (Edge e : mst.edges())
            System.out.println(e.either() + " - " + e.other(e.either()) + " " + e.getWeight());
        System.out.println(mst.weight());

    }
}
