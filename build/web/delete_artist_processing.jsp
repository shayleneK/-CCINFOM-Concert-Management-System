<%-- 
    Document   : delete_artist_processing
    Created on : 11 21, 23, 12:06:20 AM
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
        <title>Delete Artist</title>
    </head>
    <body>
            <jsp:useBean id="A" class="concertmanagement.artists" scope="session"/>
            <%  
                
                String artistIDParameter = request.getParameter("artist_ID");
                int v_artist_ID = Integer.parseInt(artistIDParameter);
                A.artist_ID = v_artist_ID;
                
                int status = A.delete_artist();
            
                if (status == 1){
            %>
            <h1>Artist Deleted Successful</h1>
            <% } else {
            %>
            <h1>Artist Deleted Failed</h1>
            <%     }
            %>
            <a href="performers.html">            Return to Performer Options Menu</a>
    </body>
</html>
