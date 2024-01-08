<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
<h2>Employee List</h2>
<table border="1">
    <tr>
        <th>Avatar</th>
        <th>Full Name</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    <% List<Employee> employees = (List<Employee>) request.getAttribute("employees"); %>
    <% for (Employee employee : employees) { %>
    <tr>
        <td><img src="<%= employee.getAvatarUrl() %>" alt="Avatar" width="50" height="50"></td>
        <td><%= employee.getFullName() %>
        </td>
        <td><%= employee.getStatus() %>
        </td>
        <td>
            <a href="viewEmployee.jsp?id=<%= employee.getId() %>">View</a>
            <a href="lockUnlockEmployeeServlet?id=<%= employee.getId() %>">Lock/Unlock</a>
            <a href="resendLoginEmailServlet?id=<%= employee.getId() %>">Resend Login Email</a>
            <a href="viewSalesInfo.jsp?id=<%= employee.getId() %>">View Sales Info</a>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>