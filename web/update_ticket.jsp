<%-- 
    Document   : update_ticket
    Created on : 11 19, 23, 9:18:57 PM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@pageimport = "java.util.*, concertmanagement.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="index.css">
        <title>Update Ticket</title>
    </head>
    <body>
        <h1>Update Ticket Tier</h1>
        <jsp:useBean id="ticketsBean" class="concertmanagement.tickets" scope="session" />
        <% ticketsBean.load_deletable_tickets(); %>
        <form action="update_ticket_processing.jsp">
            Ticket ID: 
            <select name="update_ticket_id" id="update_ticket_id" required> 
                    <% for (int i=0; i < ticketsBean.ticket_List.size(); i++) {
                        tickets T = new tickets();
                        T = ticketsBean.ticket_List.get(i);                                %>
                        <option value="<%=T.ticket_ID%>"><%=T.getCombinedInfo()%></option>                   
                    <% } %>
                </select><br>
            New Ticket Tier: 
            <select name= "update_ticket_tier" id= "update_ticket_tier">
                <option value ="bronze">Bronze</option>
                <option value ="silver">Silver</option>
                <option value ="gold">Gold</option>
                <option value ="platinum">Platinum</option>
            </select>
            <input type="submit" value ="Submit">
        </form>
    </body>
</html>
