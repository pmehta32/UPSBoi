import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;

/**
 * public class GUI inherits from a class called JFrame and uses the ActionListener interface
 * GUI provides the interface for users to uplaod files and check various details
 * constructor GUI uses to call the method in the class
 */
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
    /**
     * loadGUI method handles the entire GUI
     * handles the default display and the display after input file
     * when there is no file selected, loadGUI displays the default message
     * handles the display of the file name with an extension of tsp and atsp
     * handles the display of the route as a textbox
     * handles the calculated distance as a textbox
     * handles the display the graph for the symmetric points
     * manages all the above functions by creating their attributes
     */
    public void loadGUI() {
        setTitle("Travelling Salesman Problem");
        setBounds(300, 90, 900, 800   );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);

        Container container = getContentPane();
        container.setLayout(null);

        GUIComponents.setStaticComponents(container);

        chooseFileButton = GUIComponents.buttonFactory("chooseFileButton");
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

    /**
     * actionPerformed method overrides the original method from the ActionListener interface
     * works as a triggering method.
     * as the user performs the action, actionPerformed method triggers the in-built functions of the ActionListener
     *
     * @param e takes the action from the user as an ActionEvent type object
     *          and performs the actions defined in the method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == chooseFileButton) {
            handleNewFile();
        }
    }

    public void handleNewFile() {
        JFileChooser fileUploader = new JFileChooser();
        fileUploader.setFileFilter(new TspFilter());
        fileUploader.setVisible(true);
        int selectedFile = fileUploader.showOpenDialog(null);
        if(selectedFile == JFileChooser.APPROVE_OPTION) {
            fileName.setText(fileUploader.getSelectedFile().getName());
            solveTSP(fileUploader.getSelectedFile().getAbsolutePath());
        }
    }

    public void solveTSP(String filePath) {
        this.graph = null;
        this.graph = FileReader.loadFile(filePath);
        this.draw2DMap();
        PathFinder pf = new PathFinder(this.graph);
        ArrayList citiesPathDist =  pf.findPath();
        calculatedDistance.setText(citiesPathDist.get(1).toString());
        travellingPath.showPath((ArrayList)citiesPathDist.get(0));
    }

    public void draw2DMap() {
            mapPanel.setCoordinates((ArrayList) graph.getCoordinates());
            mapPanel.repaint();
    }

}
