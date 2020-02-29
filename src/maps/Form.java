
package maps;

import javax.swing.*;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Form extends JFrame implements ActionListener {
    
    JLabel loc = new JLabel("Location");
    JLabel dest = new JLabel("Destination");
   // JLabel dist = new JLabel("Distance");
    
    JComboBox locText= new JComboBox();
    JComboBox destText= new JComboBox();
    JTextArea distText= new JTextArea();
    JLabel pimage = new JLabel();
    
    JButton cal = new JButton("Calculate");
    JButton cancel = new JButton("Clear");
    ArrayList<String> city;
    Node src;
    Node goal;
    String pic;
    DbConnect db = new DbConnect();
    public Form()
    {
        city = db.getCities();
        setTitle("Calculate Distance");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setSize(750, 900);
        setLocationRelativeTo(null);
        setVisible(true);
        
        loc.setBounds(100, 40, 89, 23);
        getContentPane().add(loc);
        
        
        for(int i=0;i<city.size();i++)
         {
            locText.addItem(city.get(i));
         }
        
        locText.setBounds(180, 40, 120, 23);
        getContentPane().add(locText);
        
        dest.setBounds(350, 40, 89, 23);
        getContentPane().add(dest);
        
        for(int i=0;i<city.size();i++)
         {
            destText.addItem(city.get(i));
         }
        
        destText.setBounds(440, 40, 120, 23);
        getContentPane().add(destText);
        
       // dist.setBounds(100, 128, 72, 23);
        //getContentPane().add(dist);
        distText.setBounds(10, 75, 690, 70);
        getContentPane().add(distText);
        
        cal.setBounds(100, 150, 100, 40);
        getContentPane().add(cal);
        
        cancel.setBounds(250, 150, 100, 40);
        getContentPane().add(cancel);
        
        //String pic = "C:\\Users\\Olawale Afuwape\\Documents\\NetBeansProjects\\maps\\img\\libya1.png";
        //pimage = new JLabel(pic);
        pimage.setBounds(10, 200, 730, 490);
        getContentPane().add(pimage);
        
        distText.setEditable(false);
        cal.addActionListener(this);
        cancel.addActionListener(this);
        
        pimage.setIcon(ResizeImage(pic));
        
    }
    
    public ImageIcon ResizeImage(String imagePath){
         ImageIcon myImage = null;
         myImage = new ImageIcon(imagePath);
         Image img= myImage.getImage();
         Image img2= img.getScaledInstance(pimage.getWidth(), pimage.getHeight(), Image.SCALE_SMOOTH);

        ImageIcon picture= new  ImageIcon(img2);
        return picture;
    
}
    
    public void actionPerformed(ActionEvent e)
    {
        String source;
        int strt;
        String dest;
        int finish;
        if(e.getSource() == cal)
        {
            long startTime = System.currentTimeMillis();
            //strt = locText.getSelectedIndex();
            source = ""+locText.getSelectedItem();
            //finish = destText.getSelectedIndex();
            dest = ""+destText.getSelectedItem();
            
            if(source.equals(dest))
            {
                JOptionPane.showMessageDialog(null,"Start location and destination cannot be the same");
            }
            else
            {
                UniformCost c = new UniformCost();
         int selection = locText.getSelectedIndex();
        switch (selection)
        {
            case 0: src = c.n1;
                break;
            case 1: src = c.n2;
                break;
            case 2: src = c.n3;
                break;
            case 3: src = c.n4;
                break;
            case 4: src = c.n5;
                break;
            case 5: src = c.n6;
                break;
            case 6: src = c.n7;
                break;
            case 7: src = c.n8;
                break;
            case 8: src = c.n9;
                break;
            case 9: src = c.n10;
                break;
            case 10: src = c.n11;
                break;
            case 11: src = c.n12;
                break;
            case 12: src = c.n13;
                break;
            case 13: src = c.n14;
                break;
            default: break;
        }
    
         int selection2 = destText.getSelectedIndex();
        switch (selection2)
        {
            case 0: goal=c.n1;
                break;
            case 1: goal=c.n2;
                break;
            case 2: goal=c.n3;
                break;
            case 3: goal=c.n4;
                break;
            case 4: goal=c.n5;
                break;
            case 5: goal=c.n6;
                break;
            case 6: goal=c.n7;
                break;
            case 7: goal=c.n8;
                break;
            case 8: goal=c.n9;
                break;
            case 9: goal=c.n10;
                break;
            case 10: goal=c.n11;
                break;
            case 11: goal=c.n12;
                break;
            case 12: goal=c.n13;
                break;
            case 13: goal=c.n14;
                break;
            default: break;
        }
              c.Search(src, goal);
              c.readImages();
              pic = c.writeImage(goal);
              pimage.setIcon(ResizeImage(pic));
              List<Node> path = c.printPath(goal);
              distText.setText("The path is\n"+path);
              long endTime   = System.currentTimeMillis();
              long totalTime = endTime - startTime;
              System.out.println("The time in milliseconds is :"+totalTime);
              
            }
        }
        
        if(e.getSource() == cancel)
        {
            distText.setText("");
            pimage.setIcon(null);
            pimage.revalidate();
        }
    }
    
}
