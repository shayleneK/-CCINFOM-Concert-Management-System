
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, equipments.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>booking_processing</title>
    </head>
    <body>      
        <form action="checkout.jsp" method="post">
   
        <jsp:useBean id="bookingBean" class="equipments.bookings" scope="session" />

         <%   
            
            int v_expense_ID = Integer.parseInt(request.getParameter("expense_ID"));
            String v_booking_date_start = request.getParameter("booking_date_start");
            String v_booking_date_end = request.getParameter("booking_date_end");
            int v_employee_ID = Integer.parseInt(request.getParameter("employee_ID"));
            int v_concert_ID = Integer.parseInt(request.getParameter("concert_ID"));
            
            bookingBean.expense_ID = v_expense_ID;
            bookingBean.booking_date_start = v_booking_date_start;
            bookingBean.booking_date_end = v_booking_date_end;
            bookingBean.employee_ID = v_employee_ID;
            bookingBean.concert_ID = v_concert_ID;
            
            bookingBean.createBooking();
            int v_booking_ID = bookingBean.booking_ID;
            
            %>
            
    
        <h1> Booking is Processed </h1>
        <h3> Proceed to Checkout </h3>
                
        
            <input type="hidden" name="booking_ID" value="<%= bookingBean.booking_ID %>">
            
            <input type="hidden" name="expense_ID" value="<%= bookingBean.expense_ID %>">
            <input type="hidden" name="employee_ID" value="<%= bookingBean.employee_ID %>">
            
            
           
              <input type="submit" value="Checkout">
            
        
        
        
        
        </form>
      
       
        
    </body>
</html>


