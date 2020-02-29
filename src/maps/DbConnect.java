
package maps;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;
import org.sqlite.SQLiteJDBCLoader;

public class DbConnect {
    private Connection conn = null;
    private Statement sql = null;
    private ResultSet result = null;
    private String url = "jdbc:sqlite:maps.db";
    
    public DbConnect() 
    {
       
        Connect();
        
    }
    
    public void Connect()
    {
     
     
            try{
           Class.forName("org.sqlite.JDBC").newInstance();
           conn = DriverManager.getConnection(url);
           sql = conn.createStatement();
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }
        
    }
    
    public void closeConnection()
    {
        try {
	      sql.close();
	      result.close();
	      conn.close();
	} 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   public ArrayList<String> getCities()
   {
       try{
           Connect();
            result = sql.executeQuery("SELECT * FROM cities ");
            ArrayList<String> data = new ArrayList<String>();
            while(result.next())
            {
                data.add(String.valueOf(result.getString("names")));
            }
            return data;
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return null;
       }
       
       finally{
            closeConnection();
        }
   }
   
   public ArrayList<Cities> getNearestCity(int id)
   {
       try{
           Connect();
           result = sql.executeQuery("SELECT distance,names FROM distances JOIN cities ON cities.id=distances.neighbour_id WHERE city_id = '"+id+"'");
           ArrayList<Cities> data = new ArrayList<Cities>();
           Cities c;
           while(result.next())
            {
                c = new Cities(
                result.getInt("distance"),
                result.getString("names")
                );
                data.add(c);
            }
            return data;
       }
       catch(Exception e)
       {
           e.printStackTrace();
           return null; 
       }
       
       finally{
            closeConnection();
       }
   }
   
  
   
   public int GetK(String k) {
      
       int kd=0;
       try{
           Connect();
           result = sql.executeQuery("SELECT distance from distances WHERE indexK = '"+k+"'");
           
           while(result.next())
            {
                
                kd=result.getInt("distance");
            }
            
       }
       catch(Exception e)
       {
        //   e.printStackTrace(); 
       }
       
       finally{
            closeConnection();
       }
       return kd;
   }
   
   public ArrayList<String> getCoordinates(String name)
    {
        try{
            Connect();
            result = sql.executeQuery("SELECT * FROM city_pixel WHERE city_name='"+name+"'");
            ArrayList<String> data = new ArrayList<String>();
            while(result.next())
            {
                data.add(String.valueOf(result.getInt("xpixel")));
                data.add(String.valueOf(result.getInt("ypixel")));
                
            }
            return data;
        }
         catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
        finally{
            closeConnection();
        }
    }
           
   
   
   
}
