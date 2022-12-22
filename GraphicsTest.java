
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
 
class window extends JFrame {
     
    window(){
        //Gets the screen resolution.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //Set horixontal window position.
        //1300 is the width of the window.
        //Divide by 2 so that the same space on the right side is also on the left side.
        int x = (screenSize.width-1300)/2;
        //Set vertical window position
        //850 is the hieght of the window.
        //Divide by 2 so that the same space above is also below.
        int y = (screenSize.height-850)/2;
        //Now use the cordinates to set the position and size of the window.
        setBounds(x,y,1300,850);
        //Tell the window what to do when the "X" is clicked.
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //Set the layout to be used when laying out buttons and other JFrame objects.
        setLayout(null);//Next thing to work on is figuring out how to use an appropriate layout.
         
        //Create a variable to store the img being read.
        //I believe it loads the img into memory similar to how an array stores lots of data
        //Have to look into that better...
        BufferedImage img = null;
        //Try to display image or give an error.
        try{
            //Tell it where to get the image from.
            img = ImagePicker();
            //Create a JLabel to store the new image.
            JLabel plane = new JLabel(new ImageIcon(img));
            //Set the coordinates of the label.
            plane.setBounds(0,0,1032,720);
            //Add the label.
            add(plane);
            //Catch any errors and print them out.
        } catch (IOException e) {
            System.out.println("There appears to be an error: \n"+e);
        }
    }
 
    //Method to randomly pick the images from file.
    private BufferedImage ImagePicker() throws IOException {
        //Declare and initialize.
        BufferedImage img = null;
        int number;
         
        //Random number generator.
        Random generator = new Random();
        number = generator.nextInt(92);
         
        //Variable to hold the unchanging part of the address.
        //Less typing and being lazy. :P
        //Need to remove the need for this set path.
        String path = "Macintosh HD/Users/james/Desktop/javapokerimgs/";
         
        //The final part of the images address.
        String adresses[] = new String[]{
            "King.png"
        };
         
        img = ImageIO.read(new File(path+adresses[number]));
         
        return img;
    }
}