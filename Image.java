import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
public class Image extends JFrame{
    JFrame frame;
    JLabel displayField;
    ImageIcon image;
    public Image(String i){
        frame = new JFrame("Image Display Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
            image = new ImageIcon(getClass().getResource(i));
            displayField = new JLabel(image);
            frame.add(displayField);
        } catch(Exception e){
            System.out.println("balls");
        }
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }
    public static void main(String[] args){
        Image i = new Image("Cards/aos.png");
        Image j = new Image("Cards/kos.png");
    }
}