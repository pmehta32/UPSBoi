import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

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
        // 0,0 -> 0,340
        // x,0 -> x,340
        // 0,y -> 0, 340-y
        ArrayList pt = new ArrayList();
        pt.add(getWidth()*scaler.scaleValue((float)point.get(0)));
        pt.add(getHeight()*(1-scaler.scaleValue((float)point.get(1))));
        return pt;
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.red);
        int w = getWidth();
        int h = getHeight();

        //System.out.println(w);
        //System.out.println(h);

        //Random r = new Random();

        if(coordinates.size()==0) {
            g2.drawOval(0, 0, 5, 5);
            g2.drawOval(340, 0, 5, 5);
            g2.drawOval(340, 340, 5, 5);
        } else {
            for(int i=0; i<coordinates.size(); i++) {
                ArrayList newPoint = transformPoint((ArrayList)coordinates.get(i)); //(ArrayList) coordinates.get(i);
                g2.drawOval(Math.round((float)newPoint.get(0)), Math.round((float)newPoint.get(1)), 5, 5);
            }
        }
        //g2.drawOval(0, 540, 2, 2);




        //for (int i = 0; i < 10; i++) {

                    //int x = Math.abs(r.nextInt()) % w;
                    //int y = Math.abs(r.nextInt()) % h;
                    //g2.draw

                //}
            }

}
