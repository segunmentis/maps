
package maps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DrawCoordinates{
    
   
    //private BufferedImage image = null;
   BufferedImage img = null;
   // private File f = null;
    int iw;
        int ih;
        File f;
   BufferedImage image = null;
   
    String pic = "C:\\Users\\Olawale Afuwape\\Documents\\NetBeansProjects\\maps\\img\\libya1.png";
    
    public DrawCoordinates()
    {
        
    }
    
    public BufferedImage readImages()
    {
        try{
        f = new File(pic);
        this.image = ImageIO.read(f);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return this.image;
    }
    
    public BufferedImage ShowCoordinates()
    {
        int iw;
        int ih;
        
        try
        {
            iw = image.getWidth();
            ih = image.getHeight();
            img = new BufferedImage(iw,ih,BufferedImage.TYPE_INT_ARGB);
            //Graphics g = img.getGraphics();
            Graphics2D g = img.createGraphics();
            g.drawImage(image, 0,0, null);
            g.setColor(Color.red);
             g.drawLine(105, 41, 193, 36);
             g.drawLine(193, 36,303,44);
             g.dispose();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return img;
    }
    
    public void writeImage()
    {
        try{
        ImageIO.write(ShowCoordinates(), "png", new File("C:\\Users\\Olawale Afuwape\\Documents\\NetBeansProjects\\maps\\img\\Test01.jpg"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    
//    public void paint(Graphics g) {
//      g.drawImage(image, 0, 0, this);
//      g.setColor(Color.red);
//      g.drawRect(10, 10, 10, 10);
//   }
    
    
    
    
}
