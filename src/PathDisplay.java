import javax.swing.*;
import java.util.ArrayList;

public class PathDisplay extends JTextArea {

    int maxSize = 100;

    public PathDisplay(String s) {
        super(s);
    }


    public void showPath(ArrayList path) {
        this.setText("");
        int cnt = 0;
        //String s = "";
        //ArrayList s = new ArrayList();
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
                //s += row;
                //s.add(row);
                row = "";
            }
        }
        this.append(row);
        //this.setText(s);
        //System.out.println(path.subList(0,10));
        //System.out.println(path.subList(path.size()-10, path.size()));
        //System.out.println(cnt);

        //System.out.println(s.substring(0, 10));
        //System.out.println(s.substring(s.length()-10, s.length()));
    }
}
