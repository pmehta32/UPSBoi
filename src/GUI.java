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

    JButton chooseFileButton;
    JLabel fileName;
    JLabel calculatedDistance;
    PathDisplay travellingPath;
    MapPlot mapPanel;
    private Graph graph;
    private String distance;
    private String route;

    public GUI(){
        loadGUI();
    }

    public void loadGUI() {
        setTitle("Travelling Salesman Problem");
        setBounds(300, 90, 900, 800   );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        Container container = getContentPane();
        container.setLayout(null);

        JLabel title = new JLabel("TSP");
        title.setFont(new Font("Arial", Font.PLAIN, 40));
        title.setSize(300, 30);
        title.setLocation(300, 30);
        container.add(title);

        chooseFileButton = new JButton("Choose File");
        chooseFileButton.setSize(150, 20);
        chooseFileButton.setLocation(100, 70);
        chooseFileButton.addActionListener(this);
        container.add(chooseFileButton);

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

        travellingPath = new PathDisplay ("Path yet not calculated");
        travellingPath.setFont(new Font("Arial", Font.PLAIN, 20));
        travellingPath.setSize(200, 20);
        travellingPath.setLocation(400, 150);
        JScrollPane travellingPathScroll = new JScrollPane (travellingPath,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        travellingPathScroll.setLocation(400, 150);
        travellingPathScroll.setSize(400, 200);
        container.add(travellingPathScroll);


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

        mapPanel = new MapPlot();
        mapPanel.setSize(350, 350);
        mapPanel.setLocation(400, 370);
        mapPanel.setBackground(Color.black);
        mapPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        container.add(mapPanel);



//        this.add(panel2, BorderLayout.CENTER);

////        chooseFileButton = new JButton("Choose File");
////        chooseFileButton.addActionListener(this);
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
////        this.add(chooseFileButton, BorderLayout.NORTH);
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
        if(e.getSource() == chooseFileButton) {
            JFileChooser fileUploader = new JFileChooser();
            fileUploader.setVisible(true);
            int selectedFile = fileUploader.showOpenDialog(null);

            if(selectedFile == JFileChooser.APPROVE_OPTION) {
                this.graph = FileReader.loadFile(fileUploader.getSelectedFile().getAbsolutePath());
                //System.out.println(this.graph.getEdges());
                String filepath = fileUploader.getSelectedFile().getName();
                fileName.setText(filepath);
                PathFinder pf = new PathFinder(this.graph);
                ArrayList citiesPathDist =  pf.findPath();
                calculatedDistance.setText(citiesPathDist.get(1).toString());
                //travellingPath.setText(citiesPathDist.get(0).toString());
                travellingPath.showPath((ArrayList)citiesPathDist.get(0));
                if(graph.isSymmetric()) {
                    // System.out.println(graph.getCoordinates());
                    mapPanel.setCoordinates((ArrayList) graph.getCoordinates());
                    mapPanel.repaint();
                }
            }

//            this.repaint();
//            loadGUI();
        }
    }

    public static void main(String[] args) {

        new GUI();

    }
}