
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, equipments.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VIEW</title>
    </head>
    <body>
        <h1>VIEW Equipment</h1>
        
        
   <jsp:useBean id="logisticBean" class="equipments.logistics" scope="session" />

    <%   
        logisticBean.getAllEquipments();
    %>
    
        <%for(int i = 0; i < logisticBean.logisticsList.size(); i++) { %>
        <p> ------------------------------------------------------------------------</p>
        <p>  Equipment_ID: <%= logisticBean.logisticsList.get(i).equipment_ID %></p>
        <p>Logistic Type: <%= logisticBean.logisticsList.get(i).logistic_type %></p>
        <p>Description: <%= logisticBean.logisticsList.get(i).description %></p>
        <p>Supplier ID: <%= logisticBean.logisticsList.get(i).supplier_ID %></p>
        <p>Rental Price: <%= logisticBean.logisticsList.get(i).rental_price %></p>
        <p>Status: <%= logisticBean.logisticsList.get(i).status %></p>
        <br><br>
        <% }
        %>
        
        <a href="index.html">Go Back to Menu</a>
        
    </body>
</html>