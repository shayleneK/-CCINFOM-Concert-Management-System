<%-- 
    Document   : update_artist
    Created on : 11 20, 23, 4:45:05 PM
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
        <title>Select Artist and Update Data</title>
    </head>
    <body>
        <form action="update_artist.jsp">
            <jsp:useBean id="A" class="concertmanagement.artists" scope="session"/>
            
            <h1>Select Artist to Update Data</h1><br><br>
            
            Artist:<select id="artist_ID" name="artist_ID"><br>
                <% 
                    A.all_info_artist();
                    for(int i=0; i<A.artist_ID_list.size(); i++){
                %>
                <option value="<%=A.artist_ID_list.get(i)%>"><%= A.first_name_list.get(i) + " " + A.last_name_list.get(i)%></option><br>
                <% }
                %>
            </select><br><br>
            
                            
            <input type="submit" value="Select Artist">
        </form>
    </body>
</html>
