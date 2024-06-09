/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concertmanagement;
import java.util.*;
import java.sql.*;
/**
 *
 * @author ccslearner
 */
public class customers {
    public int customer_ID;
    public String first_name;
    public String last_name;
    public String email;
    public int ticket_ID;
    
    public ArrayList<Integer> customer_IDList = new ArrayList<>();
    
    public int register_customer() {
        try {
        // 1. Instantiate a connection variable
            Connection conn;     
            // 2. Connect to your DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            // 3. Indicate a notice of successful connection
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(customer_ID) + 1 AS newID FROM customers");
            ResultSet rst = pstmt.executeQuery();
            
            while(rst.next()) {
                customer_ID = rst.getInt("newID");
            }
            
            pstmt = conn.prepareStatement("INSERT INTO customers VALUES (?, ?, ?, ?, ?);");
            pstmt.setInt(1, customer_ID);
            pstmt.setString(2, first_name);
            pstmt.setString(3, last_name);
            pstmt.setString(4, email); 
            pstmt.setInt(5, ticket_ID);
            pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());  
            return 0;
        }
    }
    
    public int load_customer_IDs() {
        try {
        // 1. Instantiate a connection variable
            Connection conn;     
            // 2. Connect to your DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            // 3. Indicate a notice of successful connection
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT customer_ID FROM customers");
            ResultSet rs = pstmt.executeQuery();
            
            customer_IDList.clear();
            while(rs.next()) {
                int C;
                C = rs.getInt("customer_ID");
                customer_IDList.add(C);
            } 
            
            pstmt.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());  
            return 0;
        }
    }
    public static void main (String args[]) {
        customers c = new customers();
        c.first_name = "julian";
        c.last_name = "quirante";
        c.email = "jr";
        c.ticket_ID = 1110000005;
        c.register_customer();
    }
}
