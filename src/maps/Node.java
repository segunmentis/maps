package maps;


public class Node {
    
   public final String value;
   public double pathCost;
   public Edge[] adjacencies;
   public Node parent;
    
    public Node(String val)
    {
        this.value = val;
    }
    //total of distance 

    public Node(Node node) {
    int i = 0;
    adjacencies = new Edge[node.adjacencies.length];
    value = node.value;
    pathCost = node.pathCost;
    for (Edge e : node.adjacencies) {
        adjacencies[i++] = e;
    }
    parent = node.parent;
    }
    
    public String toString() {
    return value +"  "+ pathCost + " Km ";
    }

    
}

class Edge {
public final double cost;
public final Node target;

public Edge(Node targetNode, double costVal) {
cost = costVal;
target = targetNode;

}

}

