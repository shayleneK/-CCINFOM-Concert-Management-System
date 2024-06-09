<%-- 
    Document   : ticket_transaction
    Created on : 11 20, 23, 5:58:49 PM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="index.css">
        <title>Concert Selection</title>
    </head>
    <body>
        <h1>Select the concert you want to sell a ticket for</h1>
        <jsp:useBean id="concertBean" class="concertmanagement.concerts" scope="session" />
        <% concertBean.loadConcerts(); %>
        <form method="POST" action="ticket_transactionp2.jsp">
            Concert Name: 
                <select name="concerts" id="concerts"> 
                    <% for (int i=0; i < concertBean.Conlist.size(); i++) {%>
                        <option value="<%=concertBean.Conlist.get(i).concert_ID%>"><%=concertBean.Conlist.get(i).concert_name%></option>                   
                    <% } %>
                </select><br>
            <button type="submit">Next (1/3)</button>
        </form>
    </body>
</html>
