import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener{

    JButton chooseFileButton;
    JLabel fileName;
    JLabel calculatedDistance;
    PathDisplay travellingPath;
    MapPlot mapPanel;
    private Graph graph;


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
        travellingPath.setEnabled(false);
        travellingPath.setFont(new Font("Arial", Font.PLAIN, 20));
        travellingPath.setSize(200, 20);
        travellingPath.setLocation(400, 150);
        JScrollPane travellingPathScroll = new JScrollPane (travellingPath,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        travellingPathScroll.setLocation(400, 150);
        travellingPathScroll.setSize(400, 200);
        container.add(travellingPathScroll);

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

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == chooseFileButton) {
            JFileChooser fileUploader = new JFileChooser();
            fileUploader.setVisible(true);
            int selectedFile = fileUploader.showOpenDialog(null);
            if(selectedFile == JFileChooser.APPROVE_OPTION) {
                this.graph = null;
                this.graph = FileReader.loadFile(fileUploader.getSelectedFile().getAbsolutePath());
                this.draw2DMap();
                fileName.setText(fileUploader.getSelectedFile().getName());
                PathFinder pf = new PathFinder(this.graph);
                ArrayList citiesPathDist =  pf.findPath();
                calculatedDistance.setText(citiesPathDist.get(1).toString());
                travellingPath.showPath((ArrayList)citiesPathDist.get(0));
            }
        }
    }

    public void draw2DMap() {
        if(graph.isSymmetric()) {
            mapPanel.setCoordinates((ArrayList) graph.getCoordinates());
            mapPanel.repaint();
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}