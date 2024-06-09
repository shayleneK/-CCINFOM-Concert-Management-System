<%-- 
    Document   : ticket_transac_processing
    Created on : 11 20, 23, 7:24:25 PM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="index.css">
        <title>Transaction Processed</title>
    </head>
    <body>
        <jsp:useBean id="cusBean" class="concertmanagement.customers" scope="session" />
        <jsp:useBean id="transacBean" class="concertmanagement.ticket_transaction" scope="session" />
        <% 
                String customer_ID = request.getParameter("customer_ID");
                String first_name = request.getParameter("first_name");
                String last_name = request.getParameter("last_name");
                String email = request.getParameter("email");
                String v_payment = request.getParameter("payment");
                int n_ticket_ID = (Integer) session.getAttribute("ticketID");
                int n_price = (Integer) session.getAttribute("price");
                int n_payment;
                
                try {
                    n_payment = Integer.parseInt(v_payment);
                    if(n_payment != n_price) {
                    %>
                    <h1>Payment Incorrect!</h1>
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
                
                cusBean.first_name = first_name;
                cusBean.last_name = last_name;
                cusBean.email = email;
                cusBean.ticket_ID = n_ticket_ID;
                cusBean.register_customer();
                transacBean.customer_ID = cusBean.customer_ID;
                transacBean.ticket_ID = n_ticket_ID;
                transacBean.amount_paid = n_payment;
                transacBean.amount = n_price;
                
                int status = transacBean.ticket_transaction();
                if(status == 1) { %>
                <h1>Ticket Transaction Successful!</h1>
                <a href="tickets.html">Ticket Menu</a><br>
            <% } else { %>
                <h1>Ticket Transaction Failed!</h1>
                <a href="tickets.html">Ticket Menu</a><br>
            <% } %>
    </body>
</html>
