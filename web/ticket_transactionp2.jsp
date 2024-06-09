<%-- 
    Document   : ticket_transactionp2
    Created on : 11 20, 23, 5:59:36 PM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@pageimport = "java.util.*, concertmanagement.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="index.css">
        <title>Ticket Selection</title>
    </head>
    <body>
        <h1>Enter the ID of the ticket you would like to buy</h1>
        <jsp:useBean id="ticketBean" class="concertmanagement.tickets" scope="session" />
        <% 
            String v_concert_ID = request.getParameter("concerts");
            int n_concert_ID = Integer.parseInt(v_concert_ID);
            ticketBean.concert_ID = n_concert_ID;
            int status = ticketBean.load_available_tickets(); %>
            
        <% if (status == 1) { %>
        <div>
            <form method="POST" action="ticket_transactionp3.jsp">
            <label for="ticketDropdown">Select a Ticket:</label>
            <select id="ticketDropdown" name="selectedTicket">
                <% for (tickets ticket : ticketBean.ticket_List) { %>
                    <option value="<%= ticket.ticket_ID%>">
                        <%= ticket.getCombinedInfo() %>
                    </option>
                    <% } %>
                    </select>
                    <button type="submit">Next (2/3)</button>
            </form>
                
    </div>
    <% } else { %>
        <h1>No Tickets Yet!</h1>
    <% } %>
    </body>
</html>
