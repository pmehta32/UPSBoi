import java.util.ArrayList;

public class SymGraph extends Graph {

    private ArrayList<ArrayList> coordinates;
    private boolean memorySaver = false;

    public SymGraph(int numVertices, ArrayList<ArrayList> coordinates) {
        super(numVertices);
        this.coordinates = coordinates;
        if(numVertices<11000) {
            this.convertCoordsToEdges();
        } else {
            this.memorySaver = true;
        }
    }

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

    @Override
    public ArrayList getCoordinates() {
        return this.coordinates;
    }

    @Override
    public boolean isSymmetric() {
        return true;
    }

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
