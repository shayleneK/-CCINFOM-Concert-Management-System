<%-- 
    Document   : attendees_earning_per_performer
    Created on : 11 21, 23, 12:29:00 AM
    Author     : Julianne Wong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, concertmanagement.*, java.time.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=h2, initial-scale=1.0">
        <link rel="stylesheet" href="index.css">
        <title>Artist Attendees and Earnings</title>
    </head>
    <body>
            <jsp:useBean id="A" class="concertmanagement.artists" scope="session"/>
            
            <h1>Artist Attendees and Earnings Summary</h1><br>
            
            
             <% A.generate_report1();
                                
                ArrayList<Integer> artist_ID_list = A.artist_ID_list;
                ArrayList<String> first_name_list = A.first_name_list;
                ArrayList<String> last_name_list = A.last_name_list;
                ArrayList<Integer> total_attendees_list = A.total_attendees_list;
                ArrayList<Float> total_earnings_list = A.total_earnings_list;
            
                for (int i = 0; i < artist_ID_list.size(); i++) {
                        String first_name = first_name_list.get(i);
                        String last_name = last_name_list.get(i);
                        int attendee_count = total_attendees_list.get(i);
                        float earnings = total_earnings_list.get(i);                       
             
             %>
                Artist Name: <%= first_name %> <%= last_name %><br>
                Total Attendees: <%= attendee_count %><br>
                Total Earnings: <%= earnings %><br><br>
            <%
                    }
                
            %>

        
            <a href="index.html">Return to Main Menu</a>

    </body>
</html>
