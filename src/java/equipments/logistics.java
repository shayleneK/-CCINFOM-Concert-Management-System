/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package equipments;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ccslearner
 */
public class logistics {
    public int equipment_ID;
    public String logistic_type;
    public String description;
    public int supplier_ID;
    public float rental_price;
    public String status;
    public ArrayList<logistics> filteredEquipments = new ArrayList<>();
    public ArrayList<logistics> availableEquipments = new ArrayList<>();

    
    public ArrayList<Integer> equipment_IDList = new ArrayList<>();
    public ArrayList<String> logistic_typeList = new ArrayList<>();
    ArrayList<String> descriptionList = new ArrayList<>();
    ArrayList<Integer> supplier_IDList = new ArrayList<>();
    ArrayList<Float> rental_priceList = new ArrayList<>();
    ArrayList<String> availabiltyList = new ArrayList<>();
    public ArrayList<logistics>   logisticsList  = new ArrayList<> ();


    public logistics() {}

    public int registerEquipment() {
        try {
            // Connect to the database
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful!");
            // SQL statements
            
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(equipment_ID) + 1 AS newID FROM logistics");
            ResultSet rst = pstmt.executeQuery();
            while (rst.next()){
                equipment_ID = rst.getInt("newID");
            }
                    
                    
            pstmt = conn.prepareStatement("INSERT INTO logistics(equipment_ID,logistic_type, description, supplier_ID, rental_price, status) VALUES(?,?,?,?,?,?)");

            // Set parameter values
            pstmt.setInt(1, equipment_ID);
            pstmt.setString(2, logistic_type);
            pstmt.setString(3, description);
            pstmt.setInt(4, supplier_ID);
            pstmt.setFloat(5, rental_price);
            pstmt.setString(6,status);
            
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
    
    
    public int updateLogistics(){
        try {
            // Connect to the database
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful!");
            // SQL statements
            
  
            
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE logistics SET logistic_type = ?, description = ?, supplier_ID = ?, rental_price = ?, status = ? WHERE equipment_ID = ?");

            // Set parameter values
            pstmt.setString(1, logistic_type);
            pstmt.setString(2, description);
            pstmt.setInt(3, supplier_ID);
            pstmt.setFloat(4, rental_price);
            pstmt.setString(5,status);
            pstmt.setInt(6, equipment_ID);

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }

    }
    
        public int updateStatus(int expense_param){
        try {
            // Connect to the database
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful!");
            // SQL statements
            
  
            
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE logistics SET status = ? WHERE equipment_ID = ?");

            // Set parameter values
            pstmt.setString(1, "NA");
            pstmt.setInt(2, expense_param);

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }

    }
    
    public int deleteLogistics(){
        try {
            // Connect to the database
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful!");
            // SQL statements
            
            
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM logistics WHERE equipment_ID = ?");

            // Set parameter values
            pstmt.setInt(1, equipment_ID);

            pstmt.executeUpdate();
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }
    
    
    public logistics viewLogistic(){
        try {
            // Connect to the database
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful!");
            // SQL statements
            
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM logistics WHERE equipment_ID = ?");

            // Set parameter values
            pstmt.setInt(1, equipment_ID);
            
            ResultSet rs = pstmt.executeQuery();
            
            logistics L = new logistics();
            
            
            while (rs.next()) {
                L.equipment_ID = rs.getInt("equipment_ID");
                L.logistic_type = rs.getString("logistic_type");
                L.description = rs.getString("description");
                L.supplier_ID = rs.getInt("supplier_ID");
                L.rental_price = rs.getFloat("rental_price");
                L.status = rs.getString("status");
            }
                
                
            rs.close();
            pstmt.close();
            conn.close();
            return L;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
 
    }      
            
            
    public int loadLogisticsView() {
        try {
            // Connect to the database
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
             PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM logistics");
            // 5. Supply the statement with values
            // 6. Execute the SQL Statement
            ResultSet rs = pstmt.executeQuery();   
            
            // 7. Get the results
            logisticsList.clear();
            while (rs.next()) {
                logistics L = new logistics();
                L.equipment_ID = rs.getInt("equipment_ID");
                L.logistic_type = rs.getString("logistic_type");
                L.description = rs.getString("description");
                L.supplier_ID = rs.getInt("supplier_ID");
                L.rental_price = rs.getFloat("rental_price");
                L.status = rs.getString("status");
                logisticsList.add(L);
            }
            rs.close();
            pstmt.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());  
            return 0;
        }           
    }
    
    
    
    
    
    public int loadLogistics() {
        try {
            // Connect to the database
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
             PreparedStatement pstmt = conn.prepareStatement("SELECT equipment_ID FROM logistics");
            // 5. Supply the statement with values
            // 6. Execute the SQL Statement
            ResultSet rs = pstmt.executeQuery();   
            
            // 7. Get the results
            logisticsList.clear();
            while (rs.next()) {
                logistics L = new logistics();
                L.equipment_ID = rs.getInt("equipment_ID");
                logisticsList.add(L);
            }
            rs.close();
            pstmt.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());  
            return 0;
        }           
    }
    
    
    public void getFilteredEquipments(String filter_param) {

    try {
        Connection conn;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
        System.out.println("Connection Successful!");

        PreparedStatement pstmt;
        
        if (filter_param == null || filter_param.isEmpty()) {
            pstmt = conn.prepareStatement("SELECT * FROM logistics");
        } else {
          
            pstmt = conn.prepareStatement("SELECT * FROM logistics WHERE logistic_type = ?");
            pstmt.setString(1, filter_param);
        }

        ResultSet rs = pstmt.executeQuery();

        // 7. Get the results
        filteredEquipments.clear();
        while (rs.next()) {
            logistics L = new logistics();
            L.equipment_ID = rs.getInt("equipment_ID");
            L.description = rs.getString("description");
            L.logistic_type = rs.getString("logistic_type");
            L.rental_price = rs.getFloat("rental_price");
            L.status = rs.getString("status");
            L.supplier_ID = rs.getInt("supplier_ID");

            filteredEquipments.add(L);
        }

        System.out.println("Successful!");

        pstmt.close();
        conn.close();
        
        
    } catch (SQLException e) {
        System.out.println(e.getMessage());
       
    }
}

    
    public void getAvailableOnly() {

    try {
        Connection conn;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
        System.out.println("Connection Successful!");

        PreparedStatement pstmt;
        
          
            pstmt = conn.prepareStatement("SELECT * FROM logistics WHERE status = ?");
            pstmt.setString(1, "A");
        

        ResultSet rs = pstmt.executeQuery();

        // 7. Get the results
        filteredEquipments.clear();
        while (rs.next()) {
            logistics L = new logistics();
            L.equipment_ID = rs.getInt("equipment_ID");
            L.description = rs.getString("description");
            L.logistic_type = rs.getString("logistic_type");
            L.rental_price = rs.getFloat("rental_price");
            L.status = rs.getString("status");
            L.supplier_ID = rs.getInt("supplier_ID");

            availableEquipments.add(L);
        }

        System.out.println("Successful!");

        pstmt.close();
        conn.close();
        
        
    } catch (SQLException e) {
        System.out.println(e.getMessage());
       
    }
}
    
    
    
    
    
    
    
    
    public void getAllEquipments() {

    try {
        Connection conn;
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
        System.out.println("Connection Successful!");

        PreparedStatement pstmt;
         
            pstmt = conn.prepareStatement("SELECT * FROM logistics");

        ResultSet rs = pstmt.executeQuery();

        // 7. Get the results
        logisticsList.clear();
        while (rs.next()) {
            logistics L = new logistics();
            L.equipment_ID = rs.getInt("equipment_ID");
            L.description = rs.getString("description");
            L.logistic_type = rs.getString("logistic_type");
            L.rental_price = rs.getFloat("rental_price");
            L.status = rs.getString("status");
            L.supplier_ID = rs.getInt("supplier_ID");

            logisticsList.add(L);
        }

        System.out.println("Successful!");

        pstmt.close();
        conn.close();
        
        
    } catch (SQLException e) {
        System.out.println(e.getMessage());
       
    }
}

     public int loadEmployees() {
        try {
            // Connect to the database
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
             PreparedStatement pstmt = conn.prepareStatement("SELECT equipment_ID FROM logistics");
            // 5. Supply the statement with values
            // 6. Execute the SQL Statement
            ResultSet rs = pstmt.executeQuery();   
            
            // 7. Get the results
            logisticsList.clear();
            while (rs.next()) {
                logistics L = new logistics();
                L.equipment_ID = rs.getInt("equipment_ID");
                logisticsList.add(L);
            }
            rs.close();
            pstmt.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());  
            return 0;
        }           
    }
     
     
     
     
      public int loadConcerts() {
        try {
            // Connect to the database
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
             PreparedStatement pstmt = conn.prepareStatement("SELECT equipment_ID FROM logistics");
            // 5. Supply the statement with values
            // 6. Execute the SQL Statement
            ResultSet rs = pstmt.executeQuery();   
            
            // 7. Get the results
            logisticsList.clear();
            while (rs.next()) {
                logistics L = new logistics();
                L.equipment_ID = rs.getInt("equipment_ID");
                logisticsList.add(L);
            }
            rs.close();
            pstmt.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());  
            return 0;
        }           
    }
    
   
    
   
    
    
    
    
 
     public static void main(String[] args) {
        logistics A  = new logistics();
       
        A.loadLogisticsView();
        
        System.out.println(A.logisticsList);
    }
}
