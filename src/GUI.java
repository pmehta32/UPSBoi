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
import java.awt.*;
import javax.swing.border.Border;

public class GUI extends JFrame implements ActionListener{

    JButton button;
    private Graph graph;

    public GUI() {

        JPanel panel = new JPanel();
        button = new JButton("Choose File");
        button.addActionListener(this);

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        this.setLayout(new GridLayout(0, 1));
        this.add(button);

        this.add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Travelling Salesman");
        this.pack();
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == button) {

            JFileChooser fileUploader = new JFileChooser();
            fileUploader.setVisible(true);
            int selectedFile = fileUploader.showOpenDialog(null);

            if(selectedFile == JFileChooser.APPROVE_OPTION) {
                //File file = new File(fileUploader.getSelectedFile().getAbsolutePath());
                // System.out.println(file);
                this.graph = FileReader.loadFile(fileUploader.getSelectedFile().getAbsolutePath());
                this.graph.printEdges();
            }
        }
    }

    public static void main(String[] args) {

        new GUI();
        
    }
}