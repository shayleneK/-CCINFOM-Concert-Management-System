<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registering equipment</title>
</head>
<body>
<form action="checkout2.jsp" method="post">
    <jsp:useBean id="transactionBean" class="equipments.transactions" scope="session"/>
    <jsp:useBean id="logisticsBean" class="equipments.logistics" scope="session"/>
    <%
        //getParameter is the ID in the HTML
        int v_booking_ID = Integer.parseInt(request.getParameter("booking_ID"));
        int v_expense_ID = Integer.parseInt(request.getParameter("expense_ID"));
        int v_employee_ID = Integer.parseInt(request.getParameter("employee_ID"));

        int status = transactionBean.createTransaction(v_booking_ID, v_expense_ID);

        if (status == 1) {
            logisticsBean.updateStatus(v_expense_ID);
            float localPrice = transactionBean.amount;
            int localTransaction = transactionBean.transaction_ID;
    %>

    <h1>Checkout Page</h1>
    <p>Equipment Type: <%= transactionBean.transaction_ID %></p>
    <p>Booking ID: <%= v_booking_ID %></p>
    <p>Price: <%= localPrice %></p>
    <p>Enter payment here:</p>
    <input type="text" name="amount_paid" id="amount_paid">
    <input type="hidden" name="transaction_ID" value="<%= localTransaction %>">
    <input type="hidden" name="employee_ID" value="<%= v_employee_ID %>">
            
    <input type="submit" value="Checkout" onclick="return validateAmountPaid(<%= localPrice %>);">

    <%
        } else {
    %>
    <h1>Registering Failed !!</h1>
    <p>Equipment Type: <%= transactionBean.transaction_ID %></p>
    <p>Booking ID: <%= v_booking_ID %></p>
    <p>Price: <%= transactionBean.amount %></p>
    <%
        }
    %>
     
</form>

<script>
    function validateAmountPaid(localPrice) {
        var amountPaid = parseFloat(document.getElementById("amount_paid").value);

        if (isNaN(amountPaid) || amountPaid <= 0 || amountPaid < localPrice) {
            alert("Please enter a valid amount greater than or equal to the transaction amount.");
            return false;
        }
        return true;
    }
</script>

</body>
</html>
