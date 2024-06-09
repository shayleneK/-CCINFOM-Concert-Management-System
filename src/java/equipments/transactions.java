/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipments;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ccslearner
 */
public class transactions {
    
    public int transaction_ID;
    public int booking_ID;
    public float amount;
    public float amount_paid;
    
    
    public ArrayList<Integer> transaction_IDList = new ArrayList<>();
    public ArrayList<Integer> booking_IDList = new ArrayList<>();
    public ArrayList<Float> amountList = new ArrayList<>();
    public ArrayList<Float> amount_paidList = new ArrayList<>();
    
    
public int createTransaction(int booking_param, int expense_param) {
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
        System.out.println("Connection Successful!");
        
        // Get the new transaction_ID
        PreparedStatement pstmt1 = conn.prepareStatement("SELECT MAX(transaction_ID) + 1 AS newID FROM expense_transaction");
        ResultSet rst1 = pstmt1.executeQuery();
        while (rst1.next()) {
            transaction_ID = rst1.getInt("newID");
        }
        rst1.close();
        
        // Get the amount based on the provided booking_param
        
        // ... (same code as before)

        // Get the amount based on the provided booking_param
        PreparedStatement pstmt2 = conn.prepareStatement("SELECT rental_price AS amount1 FROM logistics WHERE equipment_ID = ?");
        pstmt2.setInt(1, expense_param);
        ResultSet rst2 = pstmt2.executeQuery();
        while (rst2.next()) {
            amount = rst2.getFloat("amount1");
        }
        rst2.close();
        booking_ID = booking_param;

        // Insert the new transaction into the database
        PreparedStatement pstmt3 = conn.prepareStatement("INSERT INTO expense_transaction(transaction_ID, booking_ID, amount) VALUES(?,?,?)");
        pstmt3.setInt(1, transaction_ID);
        pstmt3.setInt(2, booking_ID);
        pstmt3.setFloat(3, amount);
        pstmt3.executeUpdate();

        // Close resources
        pstmt1.close();
        pstmt2.close();
        pstmt3.close();
        conn.close();

        return 1;  // Return the calculated amount
    } catch (Exception e) {
        System.err.println(e.getMessage());
        return 0;
    }
}

    
    
    public int getID(){
        try{
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful!");
            // SQL statements
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(transaction_ID) + 1 AS newID FROM expense_transaction");
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()){
                transaction_ID = rst.getInt("newID");
            }
                    
            return transaction_ID;
        
        
            } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
        
 
    }
    
    public int updateAmount_paid(float amount_param ,int transact_param){
        try {
            // Connect to the database
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful!");
            // SQL statements
            
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE expense_transaction SET amount_paid = ? WHERE transaction_ID = ?;");

            // Set parameter values
            pstmt.setFloat(1, amount_param );
            pstmt.setInt(2, transact_param);

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }
    
    
    
    
    
    
    public transactions(){}
    
    
    
    
    
    public static void main(String args[]){
        transactions t = new transactions();
        t.createTransaction(20233, 3);
        System.out.println(t.amount);
        
        System.out.println(t.booking_ID);
        System.out.println(t.transaction_ID);
        t.updateAmount_paid(45000, 2023);
    
    }
    
}
