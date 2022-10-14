import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * PathDisplay class displays the calculated path on the UI inheriting the JtextArea class and styles it
 * has a constructor which takes the string of the text route
 */
public class PathDisplay extends JTextArea {

    private int maxSize = 100;

    public PathDisplay(String s) {
        super(s);
        this.setEnabled(false);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setSize(200, 20);
        this.setLocation(400, 150);
    }

    /**
     * showPath method just displays the route according the screen size of the GUI
     * @param path takes the ArrayList type values of the route to display them
     */
    public void showPath(ArrayList path) {
        this.setText("");
        int cnt = 0;
        String row = "";
        for(int i=0; i<path.size(); i++) {
            row += path.get(i).toString();
            if( i != path.size()-1) {
                row+=",";
            }
            if (row.length() > maxSize) {
                row += "\n";
                this.append(row);
                cnt+= row.length();
                row = "";
            }
        }
        this.append(row);
    }
}
