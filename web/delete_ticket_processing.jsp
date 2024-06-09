<%-- 
    Document   : delete_ticket_processing
    Created on : 11 19, 23, 6:52:50 PM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@pageimport = "java.util.*, concertmanagement.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="index.css">
        <title>Delete Ticket</title>
    </head>
    <body>
        <jsp:useBean id="C" class="concertmanagement.tickets" scope="session" />
        <%
            String v_ticket_ID = request.getParameter("tickets_list_delete");
            int n_ticket_ID = Integer.parseInt(v_ticket_ID);
            C.ticket_ID = n_ticket_ID;
            int status = C.delete_ticket();
            if(status == 1) {
        %>
        <h1>Deleting Tickets Successful!</h1>
        click <a href="index.html">here to go back to HOME page</a><br>
        <%  } else { 
        %>
        <h1> Deleting Tickets Failed! </h1>
        click <a href="index.html">here to go back to HOME page</a><br>
        <% } %>
    </body>
</html>
