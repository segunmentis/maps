
package maps;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import javax.imageio.ImageIO;

public class UniformCost {
    
    private BufferedImage img = null;
    private int iw;
    private int ih;
    private File f;
    private Node dest;
    List<String> city;
   BufferedImage image = null;
   
    String loc = System.getProperty("user.dir");
    String pic = "C:\\Users\\Olawale Afuwape\\Documents\\NetBeansProjects\\maps\\img\\libya1.png";
   // int sid,gid;
    
    Node n1 = new Node("Al-zawiya");
    Node n2 = new Node("Tripoli");
    Node n3 = new Node("Ghadames");
    Node n4 = new Node("Al-khums");
    Node n5 = new Node("Misratah");
    Node n6 = new Node("Ben-waled");
    Node n7 = new Node("Sabha");
    Node n8 = new Node("Hun");
    Node n9 = new Node("Sirt");
    Node n10 = new Node("Ajdabya");
    Node n11 = new Node("Benghazi");
    Node n12 = new Node("Derna");
    Node n13 = new Node("Jalu");
    Node n14 = new Node("Kufra");
    DbConnect kv = new DbConnect();
    
    public UniformCost()
    {
        
        
        // initialize the edges
    
    
        float k1=kv.GetK("k1");
        float k2=kv.GetK("k2");
        float k3=kv.GetK("k3");
        float k4=kv.GetK("k4");
        float k5= kv.GetK("k5");
        float k6 = kv.GetK("k6");
        float k7=kv.GetK("k7");
        float k8=kv.GetK("k8");
        float k9=kv.GetK("k9");
        float k10=kv.GetK("k10");
        float k11=kv.GetK("k11");
        float k12=kv.GetK("k12");
        float k13=kv.GetK("k13");
        float k14=kv.GetK("k14");
        float k15=kv.GetK("k15");
       
        
        n1.adjacencies = new Edge[] { new Edge(n2, k1)};

        n2.adjacencies = new Edge[] { new Edge(n1, k1), new Edge(n3, k2),new Edge(n4, k3)};

        n3.adjacencies = new Edge[] { new Edge(n2, k2)};

        n4.adjacencies = new Edge[] { new Edge(n2, k3), new Edge(n5, k4)};

        n5.adjacencies = new Edge[] { new Edge(n4, k4), new Edge(n6, k5), new Edge(n9, k8) };

        n6.adjacencies = new Edge[] { new Edge(n5, k5), new Edge(n7, k6)};

        n7.adjacencies = new Edge[] { new Edge(n6, k6), new Edge(n8, k7) };

        n8.adjacencies = new Edge[] { new Edge(n7, k7), new Edge(n9, k10), new Edge(n10, k11)};

        n9.adjacencies = new Edge[] { new Edge(n5, k8), new Edge(n8, k10),new Edge(n10, k9)};

        n10.adjacencies = new Edge[] { new Edge(n9, k9), new Edge(n8, k11),new Edge(n11, k12), new Edge(n13, k13)};

        n11.adjacencies = new Edge[] { new Edge(n10, k12), new Edge(n12, k14), };

        n12.adjacencies = new Edge[] { new Edge(n11, k14)};

        n13.adjacencies = new Edge[] { new Edge(n10, k13), new Edge(n14, k15)};

        n14.adjacencies = new Edge[] { new Edge(n13, k15) };
    }
    
    
    
    public void Search(Node source, Node goal)
    {
        List<Node> list = new ArrayList<Node>();
        source.pathCost = 0;
        
        PriorityQueue<Node> queue = new PriorityQueue<Node>(14,new Comparator<Node>() {

            // override compare method
            public int compare(Node i, Node j) {
                if ((i.pathCost > j.pathCost)) {
                    return 1;
                }

                else if (i.pathCost < j.pathCost) {
                    return -1;
                }

                else {
                    return 0;
                }
            }
        }

);

        queue.add(source);
Set<Node> explored = new HashSet<Node>();
List<Node> path = new ArrayList<Node>();

// while frontier is not empty
do {

    path.clear();
    Node current = queue.poll();
    explored.add(current);
    for (Node node = current; node != null; node = node.parent) {
        path.add(node);
    }
    if (current.value.equals(goal.value)) {
        goal.parent = current.parent;
        goal.pathCost = current.pathCost;
        break;

    }

    for (Edge e : current.adjacencies) {
        Node child = e.target;
        double cost = e.cost;
        if ((queue.contains(child) || explored.contains(child))
                && !path.contains(child)) {
            Node n = new Node(child);
            list.add(n);
            list.get(list.size() - 1).pathCost = current.pathCost
                    + cost;
            list.get(list.size() - 1).parent = current;
            queue.add(list.get(list.size() - 1));

            //System.out.println(list.get(list.size() - 1));
           // System.out.println(queue);
        } else if (!path.contains(child)) {
            child.pathCost = current.pathCost + cost;
            child.parent = current;
            queue.add(child);

          //  System.out.println(child);
            //System.out.println(queue.size());
        }

    }
    
} while (!queue.isEmpty());
System.out.println("The number of nodes viisted is: "+explored.size());
System.out.println("The number of nodes in the path is: "+path.size());
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
        ArrayList<String> name;
        ArrayList<String> names;
        try
        {
            iw = image.getWidth();
            ih = image.getHeight();
            img = new BufferedImage(iw,ih,BufferedImage.TYPE_INT_ARGB);
            //Graphics g = img.getGraphics();
            Graphics2D g = img.createGraphics();
            g.setStroke(new BasicStroke(6));
            g.drawImage(image, 0,0, null);
            g.setColor(brighten(Color.red, 0.0002));
            
            for(int i =0; i<city.size()-1; i++)
            {
             name = kv.getCoordinates(city.get(i));
             names = kv.getCoordinates(city.get(i+1));
             g.drawLine(Integer.parseInt(name.get(0)), Integer.parseInt(name.get(1)), Integer.parseInt(names.get(0)), Integer.parseInt(names.get(1)));
             
            }
            System.out.println();
            g.dispose();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return img;
    }
    
    public String writeImage(Node t)
    {
        String doc = "C:\\Users\\Olawale Afuwape\\Documents\\NetBeansProjects\\maps\\img\\Test01.jpg";
        this.dest = t;
        city = nodeName();
        try{
        ImageIO.write(ShowCoordinates(), "png", new File(doc));
        return doc;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Color brighten(Color color, double fraction) {

        int red = (int) Math.round(Math.min(255, color.getRed() + 255 * fraction));
        int green = (int) Math.round(Math.min(255, color.getGreen() + 255 * fraction));
        int blue = (int) Math.round(Math.min(255, color.getBlue() + 255 * fraction));

        int alpha = color.getAlpha();

        return new Color(red, green, blue, alpha);

    }
    
    public List<String> nodeName()
    {
        List<String> names = new ArrayList<String>();
        for (Node node = dest; node != null; node = node.parent) {
           names.add(node.value);
        }
        
        Collections.reverse(names);
        
        return names;
    }
    
    
    
    public static List<Node> printPath(Node target) {
List<Node> path = new ArrayList<Node>();

for (Node node = target; node != null; node = node.parent) {
    path.add(node);
   
}

Collections.reverse(path);


return path;

}
    
    
}
