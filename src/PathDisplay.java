import javax.swing.*;
import java.util.ArrayList;

public class PathDisplay extends JTextArea {

    int maxSize = 20;

    public PathDisplay(String s) {
        super(s);
    }


    public void showPath(ArrayList path) {
        String s = "";
        String row = "";
        for(int i=0; i<path.size(); i++) {
            row += path.get(i).toString();
            if( i < path.size()-1) {
                row+=",";
            }
            if (row.length() > maxSize) {
                row += "\n";
                s += row;
                row = "";
            }
        }
        this.setText(s);
        //System.out.println(s);
    }
}
