/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipments;

import java.sql.*;

/**
 *
 * @author ccslearner
 */
public class bookings {
    
    public int booking_ID;
    public int expense_ID;
    public String booking_date_start;
    public String booking_date_end;
    public int employee_ID;
    public int concert_ID;
    public float amount;
    
   
    
    
    public bookings(){}
    
    public int createBooking() {
        try {
            // Connect to the database
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful!");
            // SQL statements
            
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(booking_ID) + 1 AS newID FROM bookings");
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()){
                booking_ID = rst.getInt("newID");
            }
                    
                    
            pstmt = conn.prepareStatement("INSERT INTO bookings(booking_ID,expense_ID, booking_date_start, booking_date_end, managing_employee, concert_ID) VALUES(?,?,?,?,?,?)");

            // Set parameter values
            pstmt.setInt(1, booking_ID);
            pstmt.setInt(2, expense_ID);
            pstmt.setString(3, booking_date_start);
            pstmt.setString(4, booking_date_end);
            pstmt.setInt(5, employee_ID);
            pstmt.setInt(6,concert_ID);
            
            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Connection Unsuccesssful");
            return 0;
        }
    }
    
    

  

 public static void main(String[] args) {
        bookings B  = new bookings();
       
    }
}
