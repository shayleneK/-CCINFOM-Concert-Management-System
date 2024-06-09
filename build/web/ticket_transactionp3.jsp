<%-- 
    Document   : ticket_transitionp3
    Created on : 11 20, 23, 6:37:44 PM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="index.css">
        <title>Customer Details</title>
    </head>
    <body>
        <h1>Customer Details</h1>
            <jsp:useBean id="cusBean" class="concertmanagement.customers" scope="session" />
            <jsp:useBean id="ticBean" class="concertmanagement.tickets" scope="session" />
            <% 
                cusBean.load_customer_IDs();
                String v_ticket_ID = request.getParameter("selectedTicket");
                int n_ticket_ID = Integer.parseInt(v_ticket_ID);
                cusBean.ticket_ID = n_ticket_ID;
                ticBean.ticket_ID = n_ticket_ID;
                int price = ticBean.get_price();
                session.setAttribute("ticketID", n_ticket_ID);
                session.setAttribute("price", price);

            %>
            <a>Price: <%= price %></a>
            
            <form action="ticket_transac_processing.jsp">
            Customer First Name: <input type="text" id="first_name" name="first_name" required><br>
            Customer Last Name: <input type="text" id="last_name" name="last_name" required><br>
            Customer Email: <input type="text" id="email" name="email" required><br>
            Amount Paid: <input type="text" id="payment" name="payment" required><br>
            <input type="submit" value ="Submit">
        </form>

    </body>
</html>
