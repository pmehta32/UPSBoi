import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapPlot extends JPanel {

    private ArrayList coordinates = new ArrayList();
    MinMaxScaler scaler;

    public MapPlot() {
        super();
    }

    public void setCoordinates(ArrayList coordinates) {
        this.coordinates = coordinates;
        scaler = new MinMaxScaler(coordinates);
    }

    private ArrayList transformPoint(ArrayList point) {
        ArrayList scaledPoint = scaler.scaleValue(point);
        ArrayList transformedPoint = new ArrayList();
        transformedPoint.add( (getWidth()-10) * (float)scaledPoint.get(0) );
        transformedPoint.add( (getHeight()-10) * (1 - (float)scaledPoint.get(1)) );
        return transformedPoint;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.red);
        int w = getWidth();
        int h = getHeight();

        if(coordinates.size()==0) {
            g2.drawOval(0, 0, 5, 5);
            g2.drawOval(340, 0, 5, 5);
            g2.drawOval(340, 340, 5, 5);
        } else {
            for(int i=0; i<coordinates.size(); i++) {
                ArrayList newPoint = transformPoint((ArrayList)coordinates.get(i)); //(ArrayList) coordinates.get(i);
                //System.out.println(newPoint);
                g2.drawOval(Math.round((float)newPoint.get(0)), Math.round((float)newPoint.get(1)), 5, 5);
            }
        }
    }

}
