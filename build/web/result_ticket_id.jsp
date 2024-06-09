<%-- 
    Document   : result_ticket_id
    Created on : 11 20, 23, 12:06:26 AM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@pageimport = "java.util.*, concertmanagement.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="index.css">
        <title>Search by ID</title>
    </head>
    <body>
        <h1>Search Results</h1>
        <jsp:useBean id="ticketBean" class="concertmanagement.tickets" scope="session" />
        <% 
            String v_ticket_ID = request.getParameter("filter_ID_ticket");
            int n_ticket_ID = Integer.parseInt(v_ticket_ID);
            ticketBean.ticket_ID = n_ticket_ID;
            int status = ticketBean.load_existing_ticket(); 
            if(status == 1) { %>
            <ul>
                <% for(tickets ticket : ticketBean.ticket_List) { %>
                <li><%= ticket.getCombinedInfo()%></li>
                <% } %>
            </ul>
                <a href="tickets.html">Ticket Menu</a><br>
                <h1>Searching Tickets Successful!</h1>
            <% } else { %>
                <a href="tickets.html">Ticket Menu</a><br>
                <h1>Searching Tickets Failed!</h1>
            <% } %>
            
    </body>
</html>
