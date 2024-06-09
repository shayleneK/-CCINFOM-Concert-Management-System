<%-- 
    Document   : ticket_processing
    Created on : 11 19, 23, 1:47:27 AM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@pageimport = "java.util.*, concertmanagement.*"%>
<link rel="stylesheet" href="index.css">
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ticket Processing</title>
    </head>
    <body>
        <jsp:useBean id="C" class="concertmanagement.tickets" scope="session" />
        <%
            String v_concert_ID = request.getParameter("concerts");
            String v_ticket_tier = request.getParameter("ticket_tier");
            String v_quantity = request.getParameter("quantity");
            int n_concert_ID = Integer.parseInt(v_concert_ID);
            int quantity = -1;
            try {
                    quantity = Integer.parseInt(v_quantity);
                    if(quantity < 1) {
                    %>
                    <h1>Quantity Invalid!</h1>
                    <a href="tickets.html">Ticket Menu</a><br>
                    <%
                    return;
                    }
                } catch (NumberFormatException e) {
                    %>
                    <h1>Payment Invalid!</h1>
                    <a href="tickets.html">Ticket Menu</a><br>
                    <%
                    return; 
                }
            C.concert_ID = n_concert_ID;
            C.ticket_tier = v_ticket_tier;
            int status = 0;
            C.get_ticket_tier_ID();
            if(C.check_max() == 20) {
            %>
            <h1>Max Tickets Already Generated for Concert!</h1>
            <a href="tickets.html">Ticket Menu</a><br>
            <%
                return;
            } else if(C.check_max() + quantity > 20) {
            %>
            <h1>Quantity Inputted over Max!</h1>
            <a href="tickets.html">Ticket Menu</a><br>
            <%
                return;
            }
            for( int i = 0; i < quantity; i++) {
                status = C.register_ticket();
            }
            
            if(C.check_max() == 0) {
        %>
        <%
            } else if(status == 1) {
        %>
        <h1>Registering Tickets Successful!</h1>
        <a href="tickets.html">Ticket Menu</a><br>
        <%  } else if(status == 0) { 
        %>
        <h1> Registering Tickets Failed! </h1>
        <a href="tickets.html">Ticket Menu</a><br>
        <% } %>
    </body>
</html>
