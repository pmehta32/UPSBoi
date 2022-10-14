import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PathDisplay extends JTextArea {

    int maxSize = 100;

    public PathDisplay(String s) {
        super(s);
        this.setEnabled(false);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setSize(200, 20);
        this.setLocation(400, 150);
    }


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
