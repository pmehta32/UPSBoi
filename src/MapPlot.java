import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * MapPlot class plots the graph on the UI by inheriting from the JPanel class
 * takes the coordinates given in the file and the object from MinMaxScaler to properly plot inside the UI
 * has a constructor which sets the coordinates on the UI according to user's screen
 */
public class MapPlot extends JPanel {

    private ArrayList coordinates = new ArrayList();
    private MinMaxScaler scaler;

    public MapPlot() {
        super();
        this.setSize(350, 350);
        this.setLocation(400, 370);
        this.setBackground(Color.black);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
    }

    /**
     * setCoordinates method tries to set the coordinates scale according to the code of the MinMaxScaler methods
     * @param coordinates takes the ArrayList type coordinates from the uploaded file
     */
    public void setCoordinates(ArrayList coordinates) {
        this.coordinates = coordinates;
        if(coordinates!=null)
            scaler = new MinMaxScaler(coordinates);
    }

    /**
     * transformPoint method takes the point as a ArrayList type
     * transforms the coordinates to the corresponding coordinates on the UI
     * used in the paint method for displaying it on the UI
     * @param point takes the scaled points
     * @return gives the coordinates which are UI-based settled
     */
    private ArrayList transformPoint(ArrayList point) {
        ArrayList scaledPoint = scaler.scaleValue(point);
        ArrayList transformedPoint = new ArrayList();
        transformedPoint.add( (getWidth()-10) * (float)scaledPoint.get(0) );
        transformedPoint.add( (getHeight()-10) * (1 - (float)scaledPoint.get(1)) );
        return transformedPoint;
    }

    /**
     * overrides the original paint method from the parent class JPanel
     * paints the graph and coordinates on the screen which are properlt scaled
     * @param g takes the in-built Graphics class type value
     */
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.red);
        if(coordinates!=null)
            for(int i=0; i<coordinates.size(); i++) {
                ArrayList newPoint = transformPoint((ArrayList)coordinates.get(i));
                g2.drawOval(Math.round((float)newPoint.get(0)), Math.round((float)newPoint.get(1)), 5, 5);
            }
    }
}
