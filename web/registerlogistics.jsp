
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*, equipments.* " %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registering equipment </title>
    </head>
    <body>
        <h1>equipments registration</h1>

        
       
        <jsp:useBean id  ="A" class = "equipments.logistics" scope ="session" />
        <%
            //getParameter is the ID in the html
            String v_equipment_type = request.getParameter("logistic_type");
            A.logistic_type = v_equipment_type;
            String v_description = request.getParameter("description");
            
            int v_supplier_ID = Integer.parseInt(request.getParameter("supplier_ID"));
            Float v_price = Float.parseFloat(request.getParameter("rental_price"));
            String v_availability = request.getParameter("status");
            A.description = v_description;
            A.supplier_ID = v_supplier_ID;
            A.rental_price = v_price;
            A.status = v_availability;
            int status= A.registerEquipment();
            
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

            