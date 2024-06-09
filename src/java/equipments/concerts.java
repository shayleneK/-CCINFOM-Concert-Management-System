/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipments;
import java.util.*;
import java.sql.*;
/**
 *
 * @author ccslearner
 */
public class concerts {
    public int concert_ID;
    public String concert_name;
    public ArrayList<concerts> Conlist  = new ArrayList<> ();
    public ArrayList<concerts> futureConcerts = new ArrayList<>();
    
    public concerts() {}
    
    public int loadConcerts () {
        try {
            Connection conn;
            //TO DO:
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement pstmt = conn.prepareStatement(("SELECT concert_ID, concert_name FROM concerts"));
            ResultSet rs = pstmt.executeQuery();
            
            Conlist.clear();
            while(rs.next()) {
                concerts C = new concerts();
                C.concert_ID = rs.getInt("concert_ID");
                C.concert_name = rs.getString("concert_name");
                Conlist.add(C);
            } 
            rs.close();
            pstmt.close();
            conn.close();
            return 1;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
        
        
    }
    
    public void loadFutureConcerts(){
        try {
            Connection conn;
            //TO DO:
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement pstmt = conn.prepareStatement(("SELECT * FROM concerts WHERE concert_datetime > NOW();"));
            ResultSet rs = pstmt.executeQuery();
            
            Conlist.clear();
            while(rs.next()) {
                concerts C = new concerts();
                C.concert_ID = rs.getInt("concert_ID");
                C.concert_name = rs.getString("concert_name");
                futureConcerts.add(C);
            } 
            rs.close();
            pstmt.close();
            conn.close();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
   
        }
        
    
    
    
    
    }

      public static void main(String[] args) {
        concerts C  = new concerts();
       
        C.loadConcerts();
        
        System.out.println(C.Conlist);
    }
}

