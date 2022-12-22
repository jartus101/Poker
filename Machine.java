import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
public class Machine extends JFrame{
    JFrame frame;
    JLabel displayField;
    ImageIcon image;
    public Machine(String i){
        frame = new JFrame("Image Display Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
            image = new ImageIcon(getClass().getResource(i));
            BufferedImage wPic = ImageIO.read(this.getClass().getResource(i));
            JLabel wIcon = new JLabel(new ImageIcon(wPic));
            frame.add(wIcon);
        } catch(Exception e){
            System.out.println("balls");
        }
        frame.setSize(1000, 1000);
        frame.setVisible(true);
    }
    public static void main(String[] args)
    {
        JPanel panel = new JPanel(null)
        {
            Image image = new ImageIcon("mong.jpg").getImage();

            protected void paintComponent(Graphics g)
            {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
                super.paintComponent(g);
            }
        };
        panel.setOpaque(false);
//      panel.add( new AnimationBackground(10, 10, 2, 3, 1, 1, 10) );
        panel.add( new AnimationBackground(300, 100, 3, 2, -1, 1, 20) );
        panel.add( new AnimationBackground(200, 200, 2, 3, 1, -1, 20) );
        panel.add( new AnimationBackground(50, 50, 5, 5, -1, -1, 20) );
//      panel.add( new AnimationBackground(0, 000, 5, 0, 1, 1, 20) );
        panel.add( new AnimationBackground(0, 200, 5, 0, 1, 1, 80) );

        JFrame frame = new JFrame();
        frame.setContentPane(panel);
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.setSize(400, 400);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }
}