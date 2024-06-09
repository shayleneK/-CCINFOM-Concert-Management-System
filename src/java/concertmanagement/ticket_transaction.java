/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concertmanagement;
import java.sql.Timestamp;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ccslearner
 */
public class ticket_transaction {
    public int transaction_ID;
    public Timestamp transaction_date;
    public int ticket_ID;
    public int amount;
    public int amount_paid;
    public int customer_ID;
    
    public int ticket_transaction() {
        try {
        // 1. Instantiate a connection variable
            Connection conn;     
            // 2. Connect to your DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            // 3. Indicate a notice of successful connection
            System.out.println("Connection Successful");
            // 4. Prepare our INSERT Statement
            transaction_date = new Timestamp(System.currentTimeMillis());

            
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ticket_transactions VALUES (?, ?, ?, ?, ?, ?);");
            pstmt.setInt(1, transaction_ID);
            pstmt.setTimestamp(2, transaction_date);
            pstmt.setInt(3, ticket_ID);
            pstmt.setInt(4, amount); 
            pstmt.setInt(5, amount_paid);
            pstmt.setInt(6, customer_ID);        
            pstmt.executeUpdate();
            
            pstmt = conn.prepareStatement("UPDATE tickets SET isSOLD = TRUE WHERE ticket_id = ?");
            pstmt.setInt(1, ticket_ID);
            pstmt.executeUpdate();


            
            pstmt.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());  
            return 0;
        }
    }
    
    public static void main (String args[]) {
        ticket_transaction t = new ticket_transaction();
        t.ticket_ID = 1110000004;
        t.customer_ID = 123000004;
        t.amount = 450;
        t.amount_paid = 450;
        t.ticket_transaction();
    }
}
