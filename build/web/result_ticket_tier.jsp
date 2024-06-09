<%-- 
    Document   : result_ticket_tier
    Created on : 11 19, 23, 11:27:32 PM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@pageimport = "java.util.*, concertmanagement.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="index.css">
        <title>Search Result</title>
    </head>
    <body>
        <h1>Search Results</h1>
        <jsp:useBean id="ticketBean" class="concertmanagement.tickets" scope="session" />
        <% 
            String v_concert_ID = request.getParameter("filter_type_ticket");
            int n_concert_ID = Integer.parseInt(v_concert_ID);
            ticketBean.concert_ID = n_concert_ID;
            int status = ticketBean.load_filtered_tickets(); %>
            
        <% if (status == 1) { %>
        <ul>
            <% for (tickets ticket : ticketBean.ticket_List) { %>
                <li><%= ticket.getCombinedInfo() %></p>
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
