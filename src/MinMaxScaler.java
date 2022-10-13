import java.util.ArrayList;

public class MinMaxScaler {
    float xMin;
    float xMax;
    float yMin;
    float yMax;

    public MinMaxScaler(ArrayList<ArrayList> coordinates) {
        xMin = (float)coordinates.get(0).get(0);
        xMax = (float)coordinates.get(0).get(0);
        yMin = (float)coordinates.get(0).get(1);
        yMax = (float)coordinates.get(0).get(1);
        //System.out.println("xMax: " + xMax);
        //System.out.println("xMin: " + xMin);
        //System.out.println("yMax: " + yMax);
        //System.out.println("yMin: " + yMin);
        //System.out.println("********************");
        setScaler(coordinates);
    }

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
        //System.out.println("xMax: " + xMax);
        //System.out.println("xMin: " + xMin);
        //System.out.println("yMax: " + yMax);
        //System.out.println("yMin: " + yMin);
    }

    public ArrayList scaleValue(ArrayList point) {
        ArrayList ret = new ArrayList();
        ret.add(((float)point.get(0)-xMin)/(xMax-xMin));
        ret.add(((float)point.get(1)-yMin)/(yMax-yMin));
        return ret;
    }

}
