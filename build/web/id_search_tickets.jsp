<%-- 
    Document   : id_search_tickets
    Created on : 11 19, 23, 11:59:42 PM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@pageimport = "java.util.*, concertmanagement.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="index.css">
        <title>Search ID</title>
    </head>
    <body>
        <h1>Enter the ID of the Ticket to search!</h1>
        <jsp:useBean id="ticketsBean" class="concertmanagement.tickets" scope="session" />
        <form action="result_ticket_id.jsp">
            Ticket: <input type="text" id="filter_ID_ticket" name="filter_ID_ticket"><br>
            <button type="submit">Search</button>
        </form> 
    </body>
</html>
