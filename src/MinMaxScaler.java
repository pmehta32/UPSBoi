import java.util.ArrayList;

/**
 * MinMaxScaler class scales the coordinates between some boundaries so that the graph can be plotted
 * in the correct place in the GraphPlot class
 * makes attributes for min, max for both x and y coordinates which will be used later on in the GraphPlot
 * has a constructor which makes the boundary of 0 and 1 and scales the points into these boundaries
 */
public class MinMaxScaler {
    private float xMin;
    private float xMax;
    private float yMin;
    private float yMax;

    public MinMaxScaler(ArrayList<ArrayList> coordinates) {
        xMin = (float)coordinates.get(0).get(0);
        xMax = (float)coordinates.get(0).get(0);
        yMin = (float)coordinates.get(0).get(1);
        yMax = (float)coordinates.get(0).get(1);
        setScaler(coordinates);
    }

    /**
     * setScaler method has already triggered in the constructor
     * checks the points with the provided boundaries
     * @param coordinates inserts the ArrayList type of coordinates given in the uploaded file
     */
    private void setScaler(ArrayList coordinates) {
        for(int i=0; i<coordinates.size(); i++) {
            ArrayList point = (ArrayList) coordinates.get(i);
            if( (float)point.get(0) < xMin) {
                xMin = (float)point.get(0);
            }
            if( (float)point.get(0) > xMax) {
                xMax = (float)point.get(0);
            }
            if( (float)point.get(1) < yMin) {
                yMin = (float)point.get(1);
            }
            if( (float)point.get(1) > yMax) {
                yMax = (float)point.get(1);
            }
        }
    }

    /**
     * scaleValue takes the point from the setScaler method and transfers it to the ret in the PathDisplay
     * @param point takes the value from scaled coordinates
     * @return gives true if it scales in the boundary and transfers it to another class
     */
    public ArrayList scaleValue(ArrayList point) {
        ArrayList ret = new ArrayList();
        ret.add(((float)point.get(0)-xMin)/(xMax-xMin));
        ret.add(((float)point.get(1)-yMin)/(yMax-yMin));
        return ret;
    }

}
