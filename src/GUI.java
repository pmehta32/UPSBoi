import javax.swing.*;

public class GUI {

    public GUI() {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        // panel.setLayout(new GridLayout(0, 1));

    }
    public static void main(String[] args) {

        new GUI();
    }
    
}
