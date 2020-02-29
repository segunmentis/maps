
package maps;

public class Cities {
    private String name;
    private int dist;
    
    public Cities(int dist,String name)
    {
        this.name = name;
        this.dist = dist;
    }
    
    public String getName(){
        return name;
    }
    
    public int getDistance(){
        return dist;
    }
    
}
