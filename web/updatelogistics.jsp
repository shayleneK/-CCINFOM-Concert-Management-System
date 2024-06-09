<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*, equipments.*" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function validateForm() {
                var equipmentID = document.getElementById("equipment_ID").value;
                var logisticType = document.getElementById("logistic_type").value;
                var description = document.getElementById("description").value;
                var supplierID = document.getElementById("supplier_ID").value;
                var rentalPrice = document.getElementById("rental_price").value;
                var status = document.getElementById("status").value;

                if (equipmentID === "" || logisticType === "" || description === "" || supplierID === "" || rentalPrice === "" || status === "") {
                    alert("All fields must be filled out");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <h1>Update Equipment</h1>

        <jsp:useBean id="logisticBean" class="equipments.logistics" scope="session" />

        <% logisticBean.loadLogistics(); %>
        <form action="updatelogistics2.jsp" method="POST" onsubmit="return validateForm()">
            <h3> Choose Equipment to update: </h3>

            <select name="equipment_ID" id="equipment_ID">
                <% for (int i = 0; i < logisticBean.logisticsList.size(); i ++) {
                    logistics L = logisticBean.logisticsList.get(i); %>

                    <option value="<%= L.equipment_ID %>"><%= L.equipment_ID %> </option>
                <% } %>
            </select>
            <select id="logistic_type" name="logistic_type">
                <option value="SPEAKER">Speakers</option>
                <option value="MICROPHONE">Microphone</option>
                <option value="LIGHTS">Lights</option>
                <option value="PROJECTORS">Projectors</option>
                <option value="CAMERAS">Cameras</option>
                <option value="BAND">Bands</option>
                <option value="INSTRUMENTS">Instruments</option>
                <option value="DANCERS">Dancers</option>
                <option value="VOCALISTS">Vocalists</option>
            </select>
            <input type="text" id="description" name="description" placeholder="description">
            <input type="text" id="supplier_ID" name="supplier_ID" placeholder="supplier ID">
            <input type="text" id="rental_price" name="rental_price" placeholder="price">
            <select name="status" id="status">
                <option value="A">Available</option>
                <option value="NA">Not Available</option>
            </select>

            <!-- create a dropdown list -->

            <input type="submit" value="Update Equipment">
        </form>

    </body>
</html>
