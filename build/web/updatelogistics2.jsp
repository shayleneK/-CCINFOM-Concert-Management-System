
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, equipments.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Update Equipment</h1>
        
        
        <jsp:useBean id="logisticBean" class="equipments.logistics" scope="session" />
         <%   
            int v_equipment_ID = Integer.parseInt(request.getParameter("equipment_ID"));
            logisticBean.equipment_ID = v_equipment_ID;
            
            String v_equipment_type = request.getParameter("logistic_type");
            logisticBean.logistic_type = v_equipment_type;
            String v_description = request.getParameter("description");
            
            int v_supplier_ID = Integer.parseInt(request.getParameter("supplier_ID"));
            Float v_price = Float.parseFloat(request.getParameter("rental_price"));
            String v_availability = request.getParameter("status");
            logisticBean.description = v_description;
            logisticBean.supplier_ID = v_supplier_ID;
            logisticBean.rental_price = v_price;
            logisticBean.status = v_availability;
            int status= logisticBean.updateLogistics();
            
            System.out.println(status);

            if(status == 1){
        %>

        <h1> Registering Success !! </h1>
        <a href="index.html">Go Back to Menu</a>

        <%
            }else{
        %>
 <h1> Registering Failed !! </h1>
    <p>Equipment Type: <%= v_equipment_type %></p>
    <p>Description: <%= v_description %></p>
    <p>Supplier ID: <%= v_supplier_ID %></p>
    <p>Price: <%= v_price %></p>
    <p>Availability: <%= v_availability %></p>
    <%}

        %>
        
    </body>
</html>