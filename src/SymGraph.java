import java.util.ArrayList;

/**
 * SymGraph class inherits from the Graph class made in the Graph.java
 * makes the attributes to take the co-ordinate values as a arraylist and save them in a memory
 * has a constructor which takes vertices and coordinates and stores them in a memory in an optimized way
 */
public class SymGraph extends Graph {
    private ArrayList<ArrayList> coordinates;
    private boolean memorySaver = false;

    public SymGraph(int numVertices, ArrayList<ArrayList> coordinates) {
        super(numVertices);
        this.coordinates = coordinates;
        if(numVertices<1000) {
            this.convertCoordsToEdges();
        } else {
            this.memorySaver = true;
        }
    }

    /**
     * constructor activates convertCoordsToEdges method
     * transfers the data of the coordinates to the edges value so that the distance can be calculated
     * for the symmetric file
     */
    private void convertCoordsToEdges() {
        ArrayList edges = new ArrayList();
        for(int i=0; i<numVertices; i++) {
            ArrayList row =  new ArrayList<Float>();
            for(int j=0; j<numVertices; j++) {
                float dist = Math.abs((float)coordinates.get(j).get(0) - (float)coordinates.get(i).get(0)) +
                        Math.abs((float)coordinates.get(j).get(1) - (float)coordinates.get(i).get(1));
                row.add(dist);
            }
            edges.add(row);
        }
        this.setEdges(edges);
    }

    /**
     * getCoordinates is an Arraylist type method which gets the coordinates from the file
     * and uses them to calculate distance
     * overrides the original getCoorsinates method
     * @return gets the coordinates
     */
    @Override
    public ArrayList getCoordinates() {
        return this.coordinates;
    }
    
    /**
     * isSymmetric method is a Boolean type methos which checks if the graph is displayed for only symmetric file
     * overrides the original isSymmetric method
     * @return gives True if symmetric, otherwise False
     */
    @Override
    public boolean isSymmetric() {
        return true;
    }

    /**
     * getDistance method has a float dataype because it may have float distance value
     * calculates the distance between source that is array[0] and destination that is array[1]
     *
     * @param source coordinate of the source point of the array
     * @param destination coordinate of the destination point of the array
     * @return gives the calculated distance if there is any data in the memory, else just returns the points
     */
    @Override
    public float getDistance(int source, int destination) {
        if(memorySaver) {
            return Math.abs((float)coordinates.get(destination).get(0) - (float)coordinates.get(source).get(0)) +
                    Math.abs((float)coordinates.get(destination).get(1) - (float)coordinates.get(source).get(1));
        }
        else {
            return super.getDistance(source, destination);
        }
    }


}
