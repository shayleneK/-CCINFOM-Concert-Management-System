<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, equipments.*" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function validateDates() {
                var startDate = document.getElementById("booking_date_start").value;
                var endDate = document.getElementById("booking_date_end").value;

                if (startDate >= endDate) {
                    alert("Booking date start must be before booking date end.");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <h1>Update Equipment</h1>
        <jsp:useBean id="logisticBean" class="equipments.logistics" scope="session" />
        <jsp:useBean id="concertBean" class="equipments.concerts" scope="session"/>
        <jsp:useBean id="employeeBean" class="equipments.employees" scope="session" />
        
        <% logisticBean.getAvailableOnly(); %>
        
        <form action="bookings2.jsp" method="POST" onsubmit="return validateDates();">
            <br>
            <p>Choose Equipment to Book:</p>
            <select name="expense_ID" id="expense_ID">
                <% for (int i = 0; i < logisticBean.availableEquipments.size(); i++) {
                    logistics L = logisticBean.availableEquipments.get(i); %>
                    <option value="<%= L.equipment_ID %>"><%= L.equipment_ID %></option>
                <% } %>
            </select>
            
            <p>Booking date start</p> <input type="date" name="booking_date_start" id="booking_date_start">
            <p>Booking date end</p> <input type="date" name="booking_date_end" id="booking_date_end">
            
            <% employeeBean.loadEmployees(); %>
            <p>Managing Employee:</p>
            <select name="employee_ID" id="employee_ID">
                <% for (int i = 0; i < employeeBean.employeeList.size(); i++) {
                    employees E = employeeBean.employeeList.get(i); %>
                    <option value="<%= E.employee_ID %>"><%= E.employee_last_name %></option>
                <% } %>
            </select>
            
            <% concertBean.loadFutureConcerts(); %>
            <br>
            <p>Choose Concert that needs this Booking:</p>
            <select name="concert_ID" id="concert_ID">
                <% for (int i = 0; i < concertBean.futureConcerts.size(); i++) {
                    concerts C = concertBean.futureConcerts.get(i); %>
                    <option value="<%= C.concert_ID %>"><%= C.concert_name %></option>
                <% } %>
            </select>
            
            <input type="submit" value="Booking">
        </form>
    </body>
</html>
