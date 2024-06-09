
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
        String v_logistic_type = request.getParameter("logistic_type");
        logisticBean.logistic_type = v_logistic_type;

        logisticBean.getFilteredEquipments(v_logistic_type);
    %>
    
        <%for(int i = 0; i < logisticBean.filteredEquipments.size(); i++) { %>
        <p> ------------------------------------------------------------------------</p>
        <p>  Equipment_ID: <%= logisticBean.filteredEquipments.get(i).equipment_ID %></p>
        <p>Logistic Type: <%= logisticBean.filteredEquipments.get(i).logistic_type %></p>
        <p>Description: <%= logisticBean.filteredEquipments.get(i).description %></p>
        <p>Supplier ID: <%= logisticBean.filteredEquipments.get(i).supplier_ID %></p>
        <p>Rental Price: <%= logisticBean.filteredEquipments.get(i).rental_price %></p>
        <p>Status: <%= logisticBean.filteredEquipments.get(i).status %></p>
        <br><br>
        <% }
        %>
        
        <a href="index.html">Go Back to Menu</a>
        
    </body>
</html>