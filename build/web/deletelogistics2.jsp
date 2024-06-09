
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
            
            int status= logisticBean.deleteLogistics();
            
            System.out.println(status);

            if(status == 1){
        %>

        <h1> Deleting successful!! </h1>
        <a href="index.html">Go Back to Menu</a>

        <%
            }else{
        %>
        <h1> Registering Failed !! </h1>
    <%  }

        %>
        
    </body>
</html>