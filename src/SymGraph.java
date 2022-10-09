import java.util.ArrayList;

public class SymGraph extends Graph {

    private ArrayList coordinates;

    public SymGraph(int numVertices, ArrayList<ArrayList<Float>> coordinates) {
        super(numVertices);
        this.coordinates = coordinates;
        ArrayList edges = new ArrayList();
        for(int i=0; i<numVertices; i++) {
            ArrayList row =  new ArrayList<Float>();
            for(int j=0; j<numVertices; j++) {
                float dist;
                //if (j==i) {
                //    dist = 9999F;
                //} else {
                    dist = Math.abs(coordinates.get(j).get(0) - coordinates.get(i).get(0)) +
                            Math.abs(coordinates.get(j).get(1) - coordinates.get(i).get(1));
                //}
                row.add(dist);
            }
            edges.add(row);
        }
        this.setEdges(edges);
    }


}
