
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, equipments.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>booking_processing</title>
    </head>
    <body>      
   
        <jsp:useBean id="transactBean" class="equipments.transactions" scope="session" />
       <jsp:useBean id="employeeBean" class="equipments.employees" scope="session" />
        

         <%   
        int v_transact_ID = Integer.parseInt(request.getParameter("transaction_ID"));
        float v_amount_paid = Float.parseFloat(request.getParameter("amount_paid"));
        int v_employee_ID = Integer.parseInt(request.getParameter("employee_ID"));


            transactBean.updateAmount_paid(v_amount_paid, v_transact_ID);
            String employee_email = employeeBean.getEmail(v_employee_ID);
        
            %>
            
    
        <h1> Transaction Successful </h1>
        <h3> Complete details will be sent to <%= employee_email %>  </h3>
                
        <a href =" index.html" >Go back to Menu </a> 
            
        
        
        
      
       
        
    </body>
</html>


