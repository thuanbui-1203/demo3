<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Activate Account</title>
</head>
<body>
<h2>Activate Account</h2>

<%-- Display error message if activation fails --%>
<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% if (errorMessage != null && !errorMessage.isEmpty()) { %>
<p style="color: red;"><%= errorMessage %>
</p>
<% } %>

<form action="ActiveAccountServlet?loginLink=<%= request.getParameter("loginlink") %>" method="post">
    New Password: <input type="password" name="newPassword" required/><br/>
    Confirm New Password: <input type="password" name="confirmNewPassword" required/><br/>

    <input type="submit" value="Activate Account"/>
</form>
</body>
</html>
