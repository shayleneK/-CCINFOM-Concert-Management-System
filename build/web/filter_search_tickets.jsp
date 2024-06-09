<%-- 
    Document   : filter_search_tickets
    Created on : 11 19, 23, 10:27:43 PM
    Author     : ccslearner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="index.css">
        <title>Update Page</title>
    </head>
    <body>
        <h1>Search by Filter</h1>
        <form action="result_ticket_tier.jsp">
            Concert ID: <input type="text" id="filter_type_ticket" name="filter_type_ticket"><br>
            <button type="submit">Search</button>
        </form>    
    </body>
</html>
