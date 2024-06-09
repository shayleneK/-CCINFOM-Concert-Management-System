
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
        int v_equipment_ID = Integer.parseInt(request.getParameter("equipment_ID"));
        logisticBean.equipment_ID = v_equipment_ID;

        logistics view = logisticBean.viewLogistic();
    %>

    <% if (view != null) { %>
        <p>Equipment ID: <%= view.equipment_ID  %></p>
        <p>Logistic Type: <%= view.logistic_type %></p>
        <p>Description: <%= view.description %></p>
        <p>Supplier ID: <%= view.supplier_ID %></p>
        <p>Rental Price: <%= view.rental_price %></p>
        <p>Status: <%= view.status %></p>
        
        <a href="index.html">Go Back to Menu</a>
    <% } else { %>
        <h1> View Not Found!! </h1>
    <% } %>


        
    </body>
</html>