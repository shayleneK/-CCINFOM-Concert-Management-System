<%-- 
    Document   : search_artist_events
    Created on : 11 20, 23, 9:58:32 PM
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
        <title>Artist Events</title>
    </head>
    <body>
            <jsp:useBean id="A" class="concertmanagement.artists" scope="session"/>
            
            <h1>Artist Events</h1><br><br>
            
            
             <% String s_artist_ID = request.getParameter("artist_ID");
                int v_artist_ID = Integer.parseInt(s_artist_ID);
                A.artist_info(v_artist_ID);
                A.events_artist();
             
                String first_name = A.first_name;
                String last_name = A.last_name;
                
                
                
                ArrayList<String> concertNameList = A.concert_name_list;
                ArrayList<LocalDateTime> concertDatetimeList = A.concert_datetime_list;
            %>

            <b>Artist: <%= first_name +" "+ last_name %></b><br><br>

            <b>Events:</b><br><br>
            <%
                if (concertNameList != null && !concertNameList.isEmpty() && concertDatetimeList != null && !concertDatetimeList.isEmpty()) {
                    for (int i = 0; i < concertNameList.size(); i++) {
                        String concertName = concertNameList.get(i);
                        LocalDateTime concertDatetime = concertDatetimeList.get(i);
            %>
                <%= concertName %> - <%= concertDatetime %><br><br>
            <%
                    }
                } else {
            %>
               No events available.<br><br>
            <%
                }
            %>

        
            <a href="performers.html">            Return to Performer Options Menu</a>

    </body>
</html>
