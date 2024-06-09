<%-- 
    Document   : view_artistinfo_processing
    Created on : 11 20, 23, 9:23:45 PM
    Author     : Julianne Wong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, concertmanagement.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=h2, initial-scale=1.0">
        <link rel="stylesheet" href="index.css">   
        <title>View Artist Info</title>
    </head>
    <body>
            <jsp:useBean id="A" class="concertmanagement.artists" scope="session"/>
            
            <h1>Artist Information</h1><br><br>
            
            
             <% String s_artist_ID = request.getParameter("artist_ID");
                int v_artist_ID = Integer.parseInt(s_artist_ID);
                A.artist_info(v_artist_ID);
             
                String first_name = A.first_name;
                String last_name = A.last_name;
                String email = A.email;
                String manager_name = A.manager_last_name +" " + A.manager_first_name;
                int artist_rate = (int)A.artist_rate;
                String manager_num = A.manager_num;
                String manager_mail = A.manager_mail;
             
             %>
             
            Artist Name:            <%= first_name +" "+ last_name%> <br>
            Artist Email:           <%= email%> <br>
            Artist Rate:            <%= artist_rate%> <br>
            Manager:                <%= manager_name%> <br>
            Manager Contact Number: <%= manager_num%> <br>
            Manager Email:          <%= manager_mail%> <br>
            
                           
            
                   
            <a href="performers.html">            Return to Performer Options Menu</a>

    </body>
</html>
