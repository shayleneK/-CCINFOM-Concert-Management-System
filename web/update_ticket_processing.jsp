<%-- 
    Document   : update_ticket_processing
    Created on : 11 19, 23, 9:27:48 PM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@pageimport = "java.util.*, concertmanagement.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="index.css">
        <title>Updating Tickets</title>
    </head>
    <body>
        <jsp:useBean id="C" class="concertmanagement.tickets" scope="session" />
        <%
            String v_ticket_ID = request.getParameter("update_ticket_id");
            int n_ticket_ID = Integer.parseInt(v_ticket_ID);
            String v_ticket_tier = request.getParameter("update_ticket_tier");
            C.ticket_ID = n_ticket_ID;
            C.ticket_tier = v_ticket_tier;
            int status = C.mod_tickets();
            if(status == 1) {
        %>
        <h1>Updating Tickets Successful!</h1>
        <a href="tickets.html">Ticket Menu</a><br>
        <%  } else { 
        %>
        <h1> Updating Tickets Failed! </h1>
        <a href="tickets.html">Ticket Menu</a><br>
        <% } %>
    </body>
</html>
