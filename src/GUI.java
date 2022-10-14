import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener{

    private JButton chooseFileButton;
    private JLabel fileName;
    private JLabel calculatedDistance;
    private PathDisplay travellingPath;
    private MapPlot mapPanel;
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

        GUIComponents.setStaticComponents(container);

        chooseFileButton = new JButton("Choose File");
        chooseFileButton.setSize(150, 20);
        chooseFileButton.setLocation(100, 70);
        chooseFileButton.addActionListener(this);
        container.add(chooseFileButton);

        fileName = GUIComponents.labelFactory("fileName");
        container.add(fileName);

        calculatedDistance = GUIComponents.labelFactory("calculatedDistance");
        container.add(calculatedDistance);

        travellingPath = new PathDisplay ("Path yet not calculated");
        GUIComponents.setRouteScrollPane(container, travellingPath);

        mapPanel = new MapPlot();
        container.add(mapPanel);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == chooseFileButton) {
            JFileChooser fileUploader = new JFileChooser();
            fileUploader.setFileFilter(new TspFilter());
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
            mapPanel.setCoordinates((ArrayList) graph.getCoordinates());
            mapPanel.repaint();
    }

}