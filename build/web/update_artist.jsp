<%-- 
    Document   : update_artist
    Created on : 11 20, 23, 6:48:38 PM
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
        <title>Update Artist Page</title>
    </head>
    <body>
        <form action="update_artist_processing.jsp">
            <jsp:useBean id="A" class="concertmanagement.artists" scope="session"/>
            
            <h1>Edit Artist Information</h1><br><br>
            
            
             <% String s_artist_ID = request.getParameter("artist_ID");
                int v_artist_ID = Integer.parseInt(s_artist_ID);
                A.artist_info(v_artist_ID);
             
                String first_name = A.first_name;
                String last_name = A.last_name;
                String email = A.email;
                String manager_name = A.manager_last_name +" " + A.manager_first_name;
                int artist_rate = (int)A.artist_rate;
             
             %>
            
            Artist Selected: <%= first_name +" "+ last_name%> <br>
            Current Manager: <%= manager_name%> <br><br>
                           
            First Name: <input type="text" id="first_name" name="first_name" value=<%= first_name%>><br>
            Last Name:<input type="text" id="last_name" name="last_name" value=<%= last_name%>><br>
            Email:<input type="text" id="email" name="email" value=<%= email%>><br>
            Manager:<select id="manager_id" name="manager_id">
                    <option value=1000001>Serana Lawson</option>
                    <option value=1000002>Xavier Bennett</option>
                    <option value=1000003>Mia Chambers</option>
                    <option value=1000004>Donovan Hayes</option>
                    <option value=1000005>Jasmine Harper</option>
                    <option value=1000006>Nolan Mitchell</option>
                    <option value=1000007>Isabella Parker</option>
                    <option value=1000008>Lucas Sullivan</option>
                    <option value=1000009>Ava Anderson</option>
                    <option value=1000010>Nathan Carter</option>
            </select><br>
            Rate:<input type="number" id="artist_rate" name="artist_rate" value=<%= artist_rate%>><br><br>
                   
            <input type="submit" value="Select Artist">
        </form>
    </body>
</html>
