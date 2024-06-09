package concertmanagement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Julianne Wong
 */
import java.util.*;
import java.sql.*;
import java.time.*;

public class artists {
    
    public int artist_ID;
    public String first_name;
    public String last_name;
    public String email;
    public int manager_ID;
    public float artist_rate;
    public String manager_last_name;
    public String manager_first_name;
    public int selected_artist_ID;
    public String manager_mail;
    public String manager_num;
    
    public int total_attendees;
    public float total_earnings;
    
    public ArrayList<Integer> artist_ID_list = new ArrayList<>();
    public ArrayList<String> first_name_list = new ArrayList<>();
    public ArrayList<String> last_name_list = new ArrayList<>();
    public ArrayList<String> email_list = new ArrayList<>();
    public ArrayList<Integer> manager_ID_list = new ArrayList<>();
    public ArrayList<Float> artist_rate_list = new ArrayList<>();
    
    public ArrayList<String> concert_name_list = new ArrayList<>();
    public ArrayList<LocalDateTime> concert_datetime_list = new ArrayList<>();
    
    public ArrayList<Integer> total_attendees_list = new ArrayList<>();
    public ArrayList<Float> total_earnings_list = new ArrayList<>();
    
    
    
    public artists(){}
    
    public int generate_report1() {
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");

            // Query for total attendees
            PreparedStatement pstmtAttendees = conn.prepareStatement("SELECT A.artist_ID, A.first_name, A.last_name, COUNT(TT.ticket_ID) AS total_attendees FROM artists A LEFT JOIN concerts C ON A.artist_ID = C.artist_ID LEFT JOIN tickets T ON C.concert_ID = T.concert_ID LEFT JOIN ticket_transactions TT ON T.ticket_ID = TT.ticket_ID GROUP BY A.artist_ID, A.first_name, A.last_name");
            ResultSet rstAttendees = pstmtAttendees.executeQuery();

            artist_ID_list.clear();
            first_name_list.clear();
            last_name_list.clear();
            total_attendees_list.clear();
            total_earnings_list.clear();

            while (rstAttendees.next()) {
                artist_ID = rstAttendees.getInt("artist_ID");
                first_name = rstAttendees.getString("first_name");
                last_name = rstAttendees.getString("last_name");
                total_attendees = rstAttendees.getInt("total_attendees");

                artist_ID_list.add(artist_ID);
                first_name_list.add(first_name);
                last_name_list.add(last_name);
                total_attendees_list.add(total_attendees);
            }

            // Query for total earnings
            PreparedStatement pstmtEarnings = conn.prepareStatement("SELECT A.artist_ID, A.first_name, A.last_name, A.artist_rate + COALESCE(SUM(TT.amount_paid), 0) AS total_earnings FROM artists A LEFT JOIN concerts C ON A.artist_ID = C.artist_ID LEFT JOIN tickets T ON C.concert_ID = T.concert_ID LEFT JOIN ticket_transactions TT ON T.ticket_ID = TT.ticket_ID GROUP BY A.artist_ID, A.first_name, A.last_name");
            ResultSet rstEarnings = pstmtEarnings.executeQuery();

            while (rstEarnings.next()) {
                total_earnings = rstEarnings.getFloat("total_earnings");
                total_earnings_list.add(total_earnings);
            }

            pstmtAttendees.close();
            pstmtEarnings.close();
            conn.close();
            System.out.println("Success");
            return 1;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }


        public int delete_artist(){
            try {
                Connection conn;
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
                System.out.println("Connection Successful");

                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM artists WHERE artist_id = ?");
                pstmt.setInt(1,artist_ID);
                pstmt.executeUpdate();

                pstmt.close();
                conn.close();
                System.out.println("Success");
                return 1;


            } catch (Exception e){
                System.out.println(e.getMessage());
                return 0;
        }
        
    }
    
    public int no_event_artist(){
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT A.artist_ID, A.first_name, A.last_name FROM artists A LEFT JOIN concerts C ON A.artist_id = C.artist_id WHERE C.artist_id IS NULL");
            ResultSet rst = pstmt.executeQuery();
            
            artist_ID_list.clear();
            first_name_list.clear();
            last_name_list.clear();
            
            
            while(rst.next()) {
                artist_ID = rst.getInt("artist_ID");
                first_name = rst.getString("first_name");
                last_name = rst.getString("last_name");
                
                
                artist_ID_list.add(artist_ID);
                first_name_list.add(first_name);
                last_name_list.add(last_name);
                      
            }
            
            
            pstmt.close();
            conn.close();
            System.out.println("Success");
            return 1;
              
            
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
        
        
    }
    
    public int events_artist(){
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT concert_name, concert_datetime FROM concerts WHERE artist_ID = ?");
            pstmt.setInt(1,selected_artist_ID);
            ResultSet rst = pstmt.executeQuery();
            
            concert_name_list.clear();
            concert_datetime_list.clear();
            
            while(rst.next()) {
                String concertName = rst.getString("concert_name");
                java.sql.Timestamp timestamp = rst.getTimestamp("concert_datetime");
                java.time.LocalDateTime concertDatetime = timestamp.toLocalDateTime();

                concert_name_list.add(concertName);
                concert_datetime_list.add(concertDatetime);
                   
            }
            
            
            pstmt.close();
            conn.close();
            System.out.println("Success");
            return 1;
              
            
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
        
        
    }
    
    public int update_artist(){
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("UPDATE artists SET first_name=?, last_name=?, email=?, manager_ID=?, artist_rate=? WHERE artist_ID=?");
            
            pstmt.setString(1, first_name);
            pstmt.setString(2, last_name);
            pstmt.setString(3, email);
            pstmt.setInt(4, manager_ID);
            pstmt.setFloat(5, artist_rate);
            pstmt.setInt(6, selected_artist_ID);
            pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            return 1;
              
            
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
        
        
    }
    
    public int artist_info(int artist_ID){
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT first_name, last_name, email, manager_ID, artist_rate FROM artists WHERE artist_ID = ?");
            pstmt.setInt(1,artist_ID);
            ResultSet rst = pstmt.executeQuery();
            
                first_name = "";
                last_name = "";
                email = "";
                manager_ID = 0;
                artist_rate = 0;
                selected_artist_ID = artist_ID;
            
            
            
            
            while (rst.next()) {
                first_name = rst.getString("first_name");
                last_name = rst.getString("last_name");
                email = rst.getString("email");
                manager_ID = rst.getInt("manager_ID");
                artist_rate = rst.getFloat("artist_rate");

                pstmt = conn.prepareStatement("SELECT manager_last_name, manager_first_name, contactno, email FROM managers WHERE manager_ID = ?");
                pstmt.setInt(1, manager_ID);
                ResultSet rst2 = pstmt.executeQuery();
                if (rst2.next()) {
                    manager_last_name = rst2.getString("manager_last_name");
                    manager_first_name = rst2.getString("manager_first_name");
                    manager_num = rst2.getString("contactno");
                    manager_mail = rst2.getString("email");
                }
                rst2.close();
            }
            
            
            
            pstmt.close();
            conn.close();
            System.out.println("Success");
            return 1;
              
            
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
        
        
    }
    
    public int all_info_artist() {
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT artist_ID, first_name, last_name, email, manager_ID, artist_rate FROM artists");
            ResultSet rst = pstmt.executeQuery();
            
            artist_ID_list.clear();
            first_name_list.clear();
            last_name_list.clear();
            email_list.clear();
            manager_ID_list.clear();
            artist_rate_list.clear();
            
            while(rst.next()) {
                artist_ID = rst.getInt("artist_ID");
                first_name = rst.getString("first_name");
                last_name = rst.getString("last_name");
                email = rst.getString("email");
                manager_ID = rst.getInt("manager_ID");
                artist_rate = rst.getFloat("artist_rate");
                
                artist_ID_list.add(artist_ID);
                first_name_list.add(first_name);
                last_name_list.add(last_name);
                email_list.add(email);
                manager_ID_list.add(manager_ID);
                artist_rate_list.add(artist_rate);
                   
            }
            
            
            pstmt.close();
            conn.close();
            System.out.println("Success");
            return 1;
              
            
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
        
        
    }
    
    public int register_artist() {
        try {
            Connection conn;
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbapp_errd?useTimezone=true&serverTimezone=UTC&user=root&password=12345678");
            System.out.println("Connection Successful");
            
            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(artist_ID) + 1 AS newID FROM artists");
            ResultSet rst = pstmt.executeQuery();
            while(rst.next()) {
                artist_ID = rst.getInt("newID");
            }
            
            pstmt = conn.prepareStatement("INSERT INTO artists (artist_ID, first_name, last_name, email, manager_ID, artist_rate) VALUE (?,?,?,?,?,?)");
            pstmt.setInt(1,artist_ID);
            pstmt.setString(2, first_name);
            pstmt.setString(3, last_name);
            pstmt.setString(4, email);
            pstmt.setInt(5, manager_ID);
            pstmt.setFloat(6, artist_rate);
            pstmt.executeUpdate();
            
            pstmt.close();
            conn.close();
            return 1;
              
            
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 0;
        }
        
    }
    
    public static void main (String args[]) {
        
        artists A = new artists();
        A.generate_report1();
        
        for(int i=0; i<A.artist_ID_list.size(); i++){
            System.out.println(A.artist_ID_list.get(i));
            System.out.println(A.first_name_list.get(i));
            System.out.println(A.last_name_list.get(i));  
            System.out.println(A.total_attendees_list.get(i));  
            System.out.println(A.total_earnings_list.get(i));  
        }
        
                
        
    }
    
}
