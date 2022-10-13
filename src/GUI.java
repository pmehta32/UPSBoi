import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Graphics;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.border.Border;
import java.util.ArrayList;
import java.util.Random;


public class GUI extends JFrame implements ActionListener{

    JButton button;
    JLabel fileName;
    JLabel calculatedDistance;
    PathDisplay textArea;
    private Graph graph;
//    private String filepath = "No file selected";
    private String distance;
    private String route;

    public GUI(){
        loadGUI();
    }

    public void loadGUI() {
//        this.removeAll();
        setTitle("TSP");
        setBounds(300, 90, 900, 800   );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        Container container = getContentPane();
        container.setLayout(null);

        JLabel title = new JLabel("TSP");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        container.add(title);

        JPanel panel = new JPanel();

        button = new JButton("Choose File");
        button.setSize(150, 20);
        button.setLocation(100, 70);
        button.addActionListener(this);
        container.add(button);

//        System.out.println(filepath);
        fileName = new JLabel("No file selected");
        fileName.setFont(new Font("Arial", Font.PLAIN, 20));
        fileName.setSize(200, 20);
        fileName.setLocation(400, 70);
        container.add(fileName);

        JLabel totalDistance = new JLabel("Total Distance:");
        totalDistance.setFont(new Font("Arial", Font.PLAIN, 20));
        totalDistance.setSize(200, 20);
        totalDistance.setLocation(100, 120);
        container.add(totalDistance);

        calculatedDistance = new JLabel("Not yet calculated");
        calculatedDistance.setFont(new Font("Arial", Font.PLAIN, 20));
        calculatedDistance.setSize(200, 20);
        calculatedDistance.setLocation(400, 120);
        container.add(calculatedDistance);

        JLabel route = new JLabel("Route:");
        route.setFont(new Font("Arial", Font.PLAIN, 20));
        route.setSize(200, 20);
        route.setLocation(100, 150);
        container.add(route);

        textArea = new PathDisplay ("Path yet not calculated");
        textArea.setFont(new Font("Arial", Font.PLAIN, 20));
        textArea.setSize(200, 20);
        textArea.setLocation(400, 150);
        JScrollPane scroll = new JScrollPane (textArea,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setLocation(400, 150);
        scroll.setSize(400, 200);
        container.add(scroll);


//        JPanel panel2 = new JPanel(){
////            @Override
////            public void paint(Graphics g) {
////
////                Graphics2D g2 = (Graphics2D) g;
////
////                g2.setPaint(Color.blue);
////                int w = getWidth();
////                int h = getHeight();
////
////                Random r = new Random();
////
////                for (int i = 0; i < 2000; i++) {
////
////                    int x = Math.abs(r.nextInt()) % w;
////                    int y = Math.abs(r.nextInt()) % h;
////                    g2.drawLine(x, y, x, y);
////                }
////            }
//        };
//        panel2.setSize(400, 400);
        JLabel map = new JLabel("Map:");
        map.setFont(new Font("Arial", Font.PLAIN, 20));
        map.setSize(200, 20);
        map.setLocation(100, 370);
        container.add(map);

        JPanel panel2 = new JPanel();
        panel2.setSize(350, 350);
        panel2.setLocation(400, 370);
//        panel2.setBackground(Color.black);
        panel2.setBorder(BorderFactory.createLineBorder(Color.black));
        container.add(panel2);



//        this.add(panel2, BorderLayout.CENTER);

////        button = new JButton("Choose File");
////        button.addActionListener(this);
////        JLabel label1 = new JLabel("Total Distance = 69 Miles");
////        JLabel label2 = new JLabel("Route: Kabul -> Lahore -> Karachi");
////
////        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////        this.setLocationRelativeTo(null);
////        this.setTitle("Travelling Salesman");
////        this.setSize(10000,10000);
////
////        // panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
////        // this.setLayout(new GridLayout(0, 2));
////        this.setLayout(new BorderLayout());
////        this.add(button, BorderLayout.NORTH);
////        this.add(label1, BorderLayout.EAST);
////        this.add(label2, BorderLayout.WEST);
////
////        JPanel panel2 = new JPanel(){
////            @Override
////            public void paint(Graphics g) {
////
////                Graphics2D g2 = (Graphics2D) g;
////
////                g2.setPaint(Color.red);
////                int w = getWidth();
////                int h = getHeight();
////
////                Random r = new Random();
////
////                for (int i = 0; i < 2000; i++) {
////
////                    int x = Math.abs(r.nextInt()) % w;
////                    int y = Math.abs(r.nextInt()) % h;
////                    g2.drawLine(x, y, x, y);
////                }
////            }
////        };
////        this.add(panel2, BorderLayout.CENTER);
//
//        this.pack();
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == button) {
            JFileChooser fileUploader = new JFileChooser();
            fileUploader.setVisible(true);
            int selectedFile = fileUploader.showOpenDialog(null);

            if(selectedFile == JFileChooser.APPROVE_OPTION) {
                // File file = new File(fileUploader.getSelectedFile().getAbsolutePath());
                this.graph = FileReader.loadFile(fileUploader.getSelectedFile().getAbsolutePath());
                //System.out.println(this.graph.getEdges());
                String filepath = fileUploader.getSelectedFile().getName();
                fileName.setText(filepath);
                PathFinder pf = new PathFinder(this.graph);
                ArrayList citiesPathDist =  pf.findPath();
                calculatedDistance.setText(citiesPathDist.get(1).toString());
                //textArea.setText(citiesPathDist.get(0).toString());
                textArea.showPath((ArrayList)citiesPathDist.get(0));
            }

//            this.repaint();
//            loadGUI();
        }
    }

    public static void main(String[] args) {

        new GUI();

    }
}