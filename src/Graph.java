import java.util.ArrayList;

public class Graph {
    private ArrayList<ArrayList<Integer>> edges;
    private int numVertices;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
    }

    public void setEdges(ArrayList edges) {
        this.edges = edges;
    }

    public int getDistance(int source, int destination) {
        return edges.get(source).get(destination);
    }

    public void printEdges() {
        System.out.println(this.edges);
    }

}
