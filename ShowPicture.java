import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
class ShowPicture {
    public static void main(String args[]) {
        var frame = new JFrame();
        /*
        var icon = new ImageIcon("Cards/queen_of_clubs2.png");
        var label = new JLabel(icon);
        */
        ImageIcon imageIcon = new ImageIcon("Cards/queen_of_clubs2.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it 
        Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // transform it back
        frame.setSize(10000, 1000);
        frame.add(image);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}