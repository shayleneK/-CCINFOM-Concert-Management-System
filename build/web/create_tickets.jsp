<%-- 
    Document   : create_tickets
    Created on : 11 19, 23, 12:02:21 PM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@pageimport = "java.util.*, concertmanagement.*"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Create Ticket</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=deviSce-width, initial-scale=1.0">
        <link rel="stylesheet" href="index.css">
    </head>
    <body>
        <h1>Select the concert you want to generate tickets for</h1>
        <a> Note this will generate all tickets for the chosen tier</a>
        <jsp:useBean id="concertBean" class="concertmanagement.concerts" scope="session" />
        <% concertBean.loadConcerts(); %>
        <form method="POST" action="ticket_processing.jsp">
            Concert Name: 
                <select name="concerts" id="concerts"> 
                    <% for (int i=0; i < concertBean.Conlist.size(); i++) {%>
                        <option value="<%=concertBean.Conlist.get(i).concert_ID%>"><%=concertBean.Conlist.get(i).concert_name%></option>                   
                    <% } %>
                </select><br>
            Ticket tier:
            <select name= "ticket_tier" id= "ticket_tier">
                <option value ="bronze">Bronze</option>
                <option value ="silver">Silver</option>
                <option value ="gold">Gold</option>
                <option value ="platinum">Platinum</option>
            </select><br>
            Quantity:
                <input type="text" id="quantity" name="quantity" required><br>
            <div><input type="submit" value="Submit"></div>
        </form>
    </body>
</html>
