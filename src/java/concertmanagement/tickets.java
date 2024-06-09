package concertmanagement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ccslearner
 */
import java.sql.*;
import java.util.*;

public class tickets {
    public int      ticket_ID;
    public int      concert_ID;
    public int      ticket_tier_ID;
    public int      seat_ID;
    public boolean  is_Sold;
    public String   ticket_tier;
    
    public ArrayList<tickets> ticket_List = new ArrayList<>();
    public ArrayList<Integer> ticket_IDList = new ArrayList<>();  
    public ArrayList<Integer> concert_IDList = new ArrayList<>();
    public ArrayList<Integer> ticket_tier_IDList = new ArrayList<>();


    public tickets() {}
    
    
    public int check_max() {
         try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            
            //1. get Ticket_Tier ID
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(seat_ID) FROM tickets WHERE concert_ID = ?;");
            pstmt.setInt    (1, concert_ID);
            // Use executeQuery for SELECT queries
            ResultSet rs = pstmt.executeQuery();
            int max_seat = -1;
            if(rs.next()){
                max_seat = rs.getInt(1);
            }
            
            if(max_seat == 1020) {
                return 20;
            }
            
            int num = max_seat - 1000;
            pstmt.close();
            conn.close();
            return num;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }   
    }
    
    public int get_ticket_tier_ID() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            
            //1. get Ticket_Tier ID
            PreparedStatement pstmt = conn.prepareStatement("SELECT ticket_tier_ID FROM ticket_tier WHERE tier = ? AND concert_ID = ?;");
            pstmt.setString    (1, ticket_tier);
            pstmt.setInt    (2, concert_ID);
            // Use executeQuery for SELECT queries
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                ticket_tier_ID = rs.getInt(1);
            }
            
            pstmt.close();
            conn.close();
            return 1;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }   
    }
    
    public int get_price(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            
            //1. get Ticket_Tier ID
            PreparedStatement pstmt = conn.prepareStatement("SELECT ticket_tier_ID, concert_ID FROM tickets WHERE ticket_ID = ?;");
            pstmt.setInt   (1, ticket_ID);

            
            // Use executeQuery for SELECT queries
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                ticket_tier_ID = rs.getInt("ticket_tier_ID");
                concert_ID = rs.getInt("concert_ID");
            }

            //2. 
            pstmt = conn.prepareStatement("SELECT price FROM ticket_tier WHERE ticket_tier_ID = ? AND concert_ID = ?;");
            pstmt.setInt(1, ticket_tier_ID);
            pstmt.setInt(2, concert_ID);
            // Use executeQuery for SELECT queries
            ResultSet rst = pstmt.executeQuery();
            // Check if the result set has any rows
            if (rst.next()) {
                int num = rst.getInt("price");
                pstmt.close();
                conn.close();
                return num;
            } else {
                // No rows found
                pstmt.close();
                conn.close();
                return 0;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }   
    }
    
    public int load_tickets(){
        try {
            Connection conn;
            //TO DO:
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement pstmt = conn.prepareStatement(("SELECT ticket_ID FROM tickets"));
            ResultSet rs = pstmt.executeQuery();
            
            ticket_List.clear();
            while(rs.next()) {
                tickets T = new tickets();
                T.ticket_ID = rs.getInt("ticket_ID");
                ticket_List.add(T);
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
    
    public int load_deletable_tickets(){
        try {
            Connection conn;
            //TO DO:
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement pstmt = conn.prepareStatement(("SELECT ticket_ID FROM tickets WHERE isSold = false"));
            ResultSet rs = pstmt.executeQuery();
            
            ticket_List.clear();
            while(rs.next()) {
                tickets T = new tickets();
                T.ticket_ID = rs.getInt("ticket_ID");
                ticket_List.add(T);
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
    
    public int load_available_tickets() {
        try {
            Connection conn;
            //TO DO:
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement pstmt = conn.prepareStatement(("SELECT ticket_ID FROM tickets WHERE isSOLD = false AND concert_ID = ?"));
            pstmt.setInt(1, concert_ID);
            ResultSet rs = pstmt.executeQuery();
            
            ticket_List.clear();
            while(rs.next()) {
                tickets T = new tickets();
                T.ticket_ID = rs.getInt("ticket_ID");
                ticket_List.add(T);
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
    
    public int load_filtered_tickets(){
        try {
            Connection conn;
            //TO DO:
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement pstmt = conn.prepareStatement(("SELECT ticket_ID FROM tickets WHERE concert_ID = ?"));
            pstmt.setInt(1, concert_ID);
            ResultSet rs = pstmt.executeQuery();
            
            ticket_List.clear();
            while(rs.next()) {
                tickets T = new tickets();
                T.ticket_ID = rs.getInt("ticket_ID");
                ticket_List.add(T);
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
    
    public int load_existing_ticket(){
        try {
            Connection conn;
            //TO DO:
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement pstmt = conn.prepareStatement(("SELECT ticket_ID FROM tickets WHERE ticket_id = ?"));
            pstmt.setInt(1, ticket_ID);
            ResultSet rs = pstmt.executeQuery();
            
            ticket_List.clear();
            while(rs.next()) {
                tickets T = new tickets();
                T.ticket_ID = rs.getInt("ticket_ID");
               ticket_List.add(T);
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
    
    public String getCombinedInfo() {
        try {
            String concert_name = "";
            String seat_number = "";
            String tier = "";
            
            Connection conn;
            //TO DO:
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            PreparedStatement pstmt = conn.prepareStatement(("SELECT ticket_ID, concert_ID, seat_ID, ticket_tier_ID, isSold FROM tickets WHERE ticket_ID = ?"));
            pstmt.setInt(1, ticket_ID);
            ResultSet rs = pstmt.executeQuery();
            
            
            while (rs.next()) {
                this.ticket_ID = rs.getInt("ticket_ID");
                this.concert_ID = rs.getInt("concert_ID");
                this.seat_ID = rs.getInt("seat_ID");
                this.ticket_tier_ID = rs.getInt("ticket_tier_ID");
                this.is_Sold = rs.getBoolean("isSold");
            }
            String is_sold_string = "Not Sold";
            if(is_Sold == true) {
                is_sold_string = "Sold";
            }
            
            if(is_Sold == false) {
                is_sold_string ="Not Sold";
            }
            
            //Get concert name
            pstmt = conn.prepareStatement(("SELECT concert_name FROM concerts WHERE concert_ID = ?"));
            pstmt.setInt(1, concert_ID);
            ResultSet rsc = pstmt.executeQuery();
            
            
            while (rsc.next()) {
                concert_name = rsc.getString("concert_name");
            }
            //Get seat
            pstmt = conn.prepareStatement(("SELECT seat_number FROM seats WHERE seat_ID = ?"));
            pstmt.setInt(1, seat_ID);
            ResultSet rss = pstmt.executeQuery();
            
            
            while (rss.next()) {
                seat_number = rss.getString("seat_number");
            }
            //Get tier
            pstmt = conn.prepareStatement(("SELECT tier FROM ticket_tier WHERE ticket_tier_ID = ?"));
            pstmt.setInt(1, ticket_tier_ID);
            ResultSet rst = pstmt.executeQuery();
            
            
            while (rst.next()) {
                tier = rst.getString("tier");
            }
            
            String info = (ticket_ID + " " + concert_name + " " + seat_number + " " + tier + " " + is_sold_string);
            rs.close();
            pstmt.close();
            conn.close();
            return info;
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    
    public int register_ticket() {
        try {
            //1. COnnect
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            
            //2. Prepared Statement
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ticket_ID) + 1 AS newID FROM tickets");
            ResultSet rst = pstmt.executeQuery();
            
            while(rst.next()) {
                ticket_ID = rst.getInt("newID");
            }
            
            // 3. Prepared Statement to get the new seat_ID
            
            pstmt = conn.prepareStatement("SELECT MAX(seat_id) + 1 AS newSeatID FROM tickets WHERE concert_ID = ?");
            pstmt.setInt(1, concert_ID);
            ResultSet rstSeatID = pstmt.executeQuery();

            rstSeatID.next();
            seat_ID = rstSeatID.getInt("newSeatID");

            if (seat_ID != 0) {
                seat_ID = rstSeatID.getInt("newSeatID");
            } else {
                // Set seat_ID to a default value or handle it as needed
                seat_ID = 1001;
            }
            System.out.println(seat_ID);
            //4. Save
            pstmt = conn.prepareStatement("INSERT INTO tickets VALUES (?, ?, ?, ?, ?)");
            pstmt.setInt(1, ticket_ID);
            pstmt.setInt(2, concert_ID);
            pstmt.setInt(3, seat_ID);
            is_Sold = false;
            pstmt.setBoolean(4, is_Sold);
            pstmt.setInt(5, ticket_tier_ID);
            pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            
            return 1;
        } catch (Exception e) {
             System.out.println(e.getMessage());
             return 0;
        }
    }
    
    public int mod_tickets() {           // Method modify a Record
        try {
            // 1. Instantiate a connection variable
            Connection conn;     
            // 2. Connect to your DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            // 3. Indicate a notice of successful connection
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT concert_ID FROM tickets WHERE ticket_ID = ?");
            pstmt.setInt   (1, ticket_ID);
            ResultSet rst = pstmt.executeQuery();
            if(rst.next()){
                concert_ID = rst.getInt(1);
            }
            
            pstmt = conn.prepareStatement("SELECT ticket_tier_ID FROM ticket_tier WHERE tier = ? AND concert_ID = ?;");
            pstmt.setString    (1, ticket_tier);
            pstmt.setInt    (2, concert_ID);
            // Use executeQuery for SELECT queries
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                ticket_tier_ID = rs.getInt(1);
            }
            
            pstmt = conn.prepareStatement("UPDATE tickets    " +
                                                            "SET    ticket_tier_ID   = ?  " +
                                                            "WHERE  ticket_ID = ? ");
            // 5. Supply the statement with values
            pstmt.setInt (1, ticket_tier_ID );
            pstmt.setInt (2, ticket_ID);
            


            // 6. Execute the SQL Statement
            pstmt.executeUpdate();   
            pstmt.close();
            conn.close();
            return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());  
            return 0;
        }         
    }

    
    public int delete_ticket() {
        try {
            // 1. Instantiate a connection variable
            Connection conn;     
            // 2. Connect to your DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            // 3. Indicate a notice of successful connection
            System.out.println("Connection Successful");
            // 4. Prepare our INSERT Statement
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM tickets WHERE ticket_ID=?");
            // 5. Supply the statement with values
            pstmt.setInt    (1, ticket_ID);
            // 6. Execute the SQL Statement
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
        tickets t = new tickets();
        t.concert_ID = 1000000002;
        t.ticket_tier_ID = 105;
        t.is_Sold = false;
        t.seat_ID = 1001;
        t.register_ticket();
    }
}



