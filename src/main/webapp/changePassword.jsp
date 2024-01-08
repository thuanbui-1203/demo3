<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password</title>
</head>
<body>
<h2>Change Password</h2>

<%-- Display error message if password change fails --%>
<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% if (errorMessage != null && !errorMessage.isEmpty()) { %>
<p style="color: red;"><%= errorMessage %>
</p>
<% } %>

<form action="ChangePassword" method="post">
    Old Password: <input type="password" name="oldPassword" required/><br/>
    New Password: <input type="password" name="newPassword" required/><br/>
    Confirm New Password: <input type="password" name="confirmNewPassword" required/><br/>
    <input type="submit" value="Change Password"/>
</form>
</body>
</html>
