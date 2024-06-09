<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, equipments.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search by ID</title>
    </head>
    <body>
        <h1>Search by ID</h1>
        
        
        <jsp:useBean id="logisticBean" class="equipments.logistics" scope="session" />
        
        <% logisticBean.loadLogisticsView(); %>
        <form action ="viewlogistics2.jsp" method="POST">
        <h3> Choose Equipment to View: </h3>
        
        <select name="equipment_ID" id="equipment_ID">
            
            <% for (int i = 0; i < logisticBean.logisticsList.size(); i ++) {
                logistics L = new logistics();
                
                L = logisticBean.logisticsList.get(i);              %>
                
                <option value ="<%= L.equipment_ID %>"><%= L.equipment_ID %> </option>
            <% } %>
            
        </select>
    
        <input type="submit" value= "View Equipment">
          </form>

    </body>
</html>


