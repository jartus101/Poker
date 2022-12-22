import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
public class Image2 extends JPanel
{
    public Image2(String i)
    {
         ImageIcon imageIcon = new ImageIcon(i);
         JLabel label = new JLabel(imageIcon);
         add(label);
    }

     public static void main(String[] args)
     {
         JFrame frame = new JFrame();
         frame.add(new Image2("Cards/qos.png"));
         frame.setVisible(true);
         frame.setSize(new Dimension(1080, 720));
     }
}