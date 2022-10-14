import java.util.ArrayList;

/**
 * handles the graph and its components triggered in the GUI to display in future
 * creates attribute for edges for using them in the methods later on in the code
 * for setting the distance between points.
 * creates attribute for vertices for using them in the methods later on in the code for co-ordinates.
 * has a constructor which returns the number of vertices
 */
public class Graph {
    private ArrayList<ArrayList<Float>> edges;
    protected int numVertices;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
    }

    /**
     * sets the edges of the given co-ordinates of the loaction points
     *
     * @param edges takes the values given in the file with the ArrayList type
     */
    public void setEdges(ArrayList edges) {
        this.edges = edges;
    }
    /**
     * gets the distance between the source point and destination point of the uploaded file
     *
     * @param source takes the integer type value for the co-ordinate of the source point
     * @param destination takes the integer type value for the co-ordinate of the destination point
     */
    public float getDistance(int source, int destination) {
        return edges.get(source).get(destination);
    }
    /**
     * gets the edges of the given co-ordinates of the loaction points as an ArrayList type
     *
     * @return gets the values of the edges given in the file
     */
    public ArrayList getEdges() {
        return this.edges;
    }
    /**
     * gets the number of vertices of the given co-ordinates of the loaction points as int type
     *
     * @return gets the numbers of vertices given in the file
     */
    public int getNumVertices() {
        return this.numVertices;
    }
    /**
     * checks if the uploaded file is symmetric to display the graph in future
     *
     * @return true if symmetric, otherwise false
     */
    public boolean isSymmetric() {
        return false;
    }
    /**
     * creates an object to get the co-ordinates of the file 
     *
     * @return null if no co-ordinates are there, otherwise returns the co-ordinates
     */
    public Object getCoordinates() {
        return null;
    }
}
