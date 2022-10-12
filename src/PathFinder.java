import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class PathFinder {
    private Graph graph;
    private Set visited;

    public PathFinder(Graph graph) {
        this.graph = graph;
    }

    private int selectStartPoint() {
        int numCities = this.graph.getNumVertices();
        Random rng = new Random();
        return rng.nextInt(numCities);
    }

    private int findShortestEdgeFrom (int source) {
        float minDist = -1;
        int index = -1;
        for(int i=0; i<this.graph.getNumVertices(); i++) {
            if(i != source && !visited.contains(i)) {
                if (minDist == -1) {
                    minDist = this.graph.getDistance(source, i);
                    index = i;
                } else if (minDist > this.graph.getDistance(source, i)) {
                    minDist = this.graph.getDistance(source, i);
                    index = i;
                }
            }
        }
        return index;
    }

    public ArrayList findPath() {
        this.visited = new HashSet();
        ArrayList path = new ArrayList();
        float pathLength = 0;

        int curr = this.selectStartPoint();
        int start = curr;
        path.add(curr);
        visited.add(curr);

        while (visited.size() < this.graph.getNumVertices()) {
            int next = this.findShortestEdgeFrom(curr);
            pathLength = pathLength + this.graph.getDistance(curr, next);
            path.add(next);
            visited.add(next);
            curr = next;
        }

        path.add(start);
        pathLength = pathLength + this.graph.getDistance(curr, start);

        System.out.println("***************PATHFINDER FULL RUN*******************");
        System.out.println(path);
        System.out.println(pathLength);
        System.out.println("**********************************");
        ArrayList ret = new ArrayList();
        ret.add(path);
        ret.add(pathLength);
        return ret;
    }

}
