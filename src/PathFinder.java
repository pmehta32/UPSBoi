import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * PathFinder class is the brain of the project.
 * generates the algorithm to find the optimal route for the given file.
 * uses k-nearest neighbor algorithm which has 0(n^2) complexity.
 * has a constructor which takes graph as the input
 */
public class PathFinder {
    private Graph graph;
    private Set visited;

    public PathFinder(Graph graph) {
        this.graph = graph;
    }

    /**
     * selectStartPoint method takes a random integer coordinate value as a starting point for the route from the file
     * @return gives the next coordinates
     */
    private int selectStartPoint() {
        int numCities = this.graph.getNumVertices();
        Random rng = new Random();
        return rng.nextInt(numCities);
    }

    /**
     * findShortestEdgeFrom class calculates and finds the shortest edge from the start point
     * to the next destination point
     * gets used in the findPath method for the temporary calculations
     *
     * @param source takes the integer start point
     * @return gives the index or position of the destination
     */
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
    
    /**
     * findPath method finds the most optimal solution
     * also calculates the distance from the source to destination by calling getDistance method
     *
     * @return displays the path of the optimal route on the UI
     */
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
            if(path.size() % 1000 == 0)
            System.out.println("Running: " + path.size() + " / " + this.graph.getNumVertices());
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
