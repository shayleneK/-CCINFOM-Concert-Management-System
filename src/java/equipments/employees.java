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
public class employees {
    public int employee_ID;
    public String employee_last_name;
    public String email;
    public ArrayList<employees> employeeList  = new ArrayList<> ();
    
    public employees() {}
    
    public int loadEmployees () {
        try {
            Connection conn;
            //TO DO:
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement pstmt = conn.prepareStatement(("SELECT employee_ID, email, CONCAT(employee_last_name,', ', employee_first_name) AS employee_name FROM employees"));
            ResultSet rs = pstmt.executeQuery();
            
            employeeList.clear();
            while(rs.next()) {
                employees E = new employees();
                E.employee_ID = rs.getInt("employee_ID");
                E.employee_last_name = rs.getString("employee_name");
                E.email = rs.getString("email");
                employeeList.add(E);
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
    public String getEmail(int employee_param){
        try {
            // Connect to the database
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful!");
            // SQL statements
            
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT email AS email_ FROM employees WHERE employee_ID = ?");

            // Set parameter values
            pstmt.setInt(1, employee_param);
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()){
               email = rst.getString("email_");
            }
            
            pstmt.close();
            conn.close();
            return email;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "no email found";
        }
    }
    
    
     public static void main(String[] args) {
        employees E  = new employees();
       
    }
}


