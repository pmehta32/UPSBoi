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
import java.util.Random;


public class GUI extends JFrame implements ActionListener{

    JButton button;
    private Graph graph;

    public GUI() {

        JPanel panel = new JPanel();

        button = new JButton("Choose File");
        button.addActionListener(this);
        JLabel label1 = new JLabel("Total Distance = 69 Miles");
        JLabel label2 = new JLabel("Route: Kabul -> Lahore -> Karachi");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setTitle("Travelling Salesman");
        this.setSize(10000,10000);

        // panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
        // this.setLayout(new GridLayout(0, 2));
        this.setLayout(new BorderLayout());
        this.add(button, BorderLayout.NORTH);
        this.add(label1, BorderLayout.EAST);
        this.add(label2, BorderLayout.WEST);

        JPanel panel2 = new JPanel(){
            @Override
            public void paint(Graphics g) {
            
                Graphics2D g2 = (Graphics2D) g;
        
                g2.setPaint(Color.red);
                int w = getWidth();
                int h = getHeight();
        
                Random r = new Random();
        
                for (int i = 0; i < 2000; i++) {
        
                    int x = Math.abs(r.nextInt()) % w;
                    int y = Math.abs(r.nextInt()) % h;
                    g2.drawLine(x, y, x, y);
                }
            }
        };
        this.add(panel2, BorderLayout.CENTER);

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
                // File file = new File(fileUploader.getSelectedFile().getAbsolutePath());                
                this.graph = FileReader.loadFile(fileUploader.getSelectedFile().getAbsolutePath());
                // System.out.println(file);
                this.graph.printEdges();
            }
        }
    }

    public static void main(String[] args) {

        new GUI();
        
    }
}