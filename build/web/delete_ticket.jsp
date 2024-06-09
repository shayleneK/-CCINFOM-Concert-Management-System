<%-- 
    Document   : delete_ticket
    Created on : 11 19, 23, 6:26:49 PM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@pageimport = "java.util.*, concertmanagement.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="ContSent-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="index.css">
        <title>Delete Ticket</title>
    </head>
    <body>
        <h1>Select the Ticket ID of the Ticket you would like to delete</h1>
        <jsp:useBean id="ticketsBean" class="concertmanagement.tickets" scope="session" />
        <% ticketsBean.load_deletable_tickets(); %>
        <form action="delete_ticket_processing.jsp" method="post">
            Ticket ID: 
                <select name="tickets_list_delete" id="tickets"> 
                    <% for (int i=0; i < ticketsBean.ticket_List.size(); i++) {
                        tickets T = new tickets();
                        T = ticketsBean.ticket_List.get(i);                                %>
                        <option value="<%=T.ticket_ID%>"><%=T.getCombinedInfo()%></option>                   
                    <% } %>
                </select><br>
            <div><input type="submit" value="Submit"></div>
        </form>
    </body>
</html>
