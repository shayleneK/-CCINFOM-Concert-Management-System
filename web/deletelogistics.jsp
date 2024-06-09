<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, equipments.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Equipment</title>
    </head>
    <body>
        <h1>Delete Equipment</h1>
        
        
        <jsp:useBean id="logisticBean" class="equipments.logistics" scope="session" />
        
        <% logisticBean.loadLogistics(); %>
        <form action ="deletelogistics2.jsp" method="POST">
        <h3> Choose Equipment to Delete: </h3>
        
        <select name="equipment_ID" id="equipment_ID">
            
            <% for (int i = 0; i < logisticBean.logisticsList.size(); i ++) {
                logistics L = new logistics();
                
                L = logisticBean.logisticsList.get(i);              %>
                
                <option value ="<%= L.equipment_ID %>"><%= L.equipment_ID %> </option>
            <% } %>
            
        </select>
    
        <input type="submit" value="Delete Equipment">
          </form>

    </body>
</html>


