<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Sales Report</title>
</head>
<body>
<h2>Sales Report</h2>
<!-- Form for selecting timeline -->
<table border="1">
    <tr>
        <th>Date</th>
        <th>Total Amount Received</th>
        <th>Number of Orders</th>
        <th>Number of Products</th>
        <th>Actions</th>
    </tr>
    <% List<SalesInfo> salesInfoList = (List<SalesInfo>) request.getAttribute("salesInfoList"); %>
    <% for (SalesInfo salesInfo : salesInfoList) { %>
    <tr>
        <td><%= salesInfo.getDate() %>
        </td>
        <td><%= salesInfo.getTotalAmountReceived() %>
        </td>
        <td><%= salesInfo.getNumberOfOrders() %>
        </td>
        <td><%= salesInfo.getNumberOfProducts() %>
        </td>
        <td><a href="viewOrderDetails.jsp?orderId=<%= salesInfo.getOrderId() %>">View Details</a></td>
    </tr>
    <% } %>
</table>
</body>
</html>
