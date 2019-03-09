public class Edge implements Comparable<Edge> {
    private int v, w;
    private double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    //either endpoint
    public int either() {
        return v;
    }
    //other endpoint
    public int other(int vertex) {
        if (vertex == v) return w;
        else return v;
    }

    public double getWeight() {
        return this.weight;
    }
    //compare edges by weight
    @Override
    public int compareTo(Edge that) {
        if (this.weight > that.weight) return 1;
        else if(this.weight < that.weight) return -1;
        else return 0;
    }
}
