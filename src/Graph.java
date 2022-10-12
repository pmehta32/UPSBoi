import java.util.ArrayList;

public class Graph {
    private ArrayList<ArrayList<Float>> edges;
    private int numVertices;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
    }

    public void setEdges(ArrayList edges) {
        this.edges = edges;
    }

    public float getDistance(int source, int destination) {
        return edges.get(source).get(destination);
    }

    public ArrayList getEdges() {
        return this.edges;
    }

    public int getNumVertices() {
        return this.numVertices;
    }

}
