<%-- 
    Document   : update_artist_processing
    Created on : 11 20, 23, 8:33:41 PM
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
        <title>Update Artist Processing</title>
    </head>
    <body>
            <jsp:useBean id="A" class="concertmanagement.artists" scope="session"/>
            <%  
                String v_first_name = request.getParameter("first_name");
                A.first_name = v_first_name;
                String v_last_name = request.getParameter("last_name");
                A.last_name = v_last_name;
                String v_email = request.getParameter("email");
                A.email = v_email;
                String managerIdParameter = request.getParameter("manager_id");
                int v_manager_ID = Integer.parseInt(managerIdParameter);
                A.manager_ID = v_manager_ID;
                String rateParameter = request.getParameter("artist_rate");
                int v_rate = Integer.parseInt(rateParameter);
                A.artist_rate = v_rate;
                int status = A.update_artist();
            
                if (status == 1){
            %>
            <h1>Artist Update Successful</h1>
            <% } else {
            %>
            <h1>Artist Update Failed</h1>
            <%     }
            %>
            <a href="performers.html">            Return to Performer Options Menu</a>
    </body>
</html>
