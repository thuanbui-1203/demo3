<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Salesperson</title>
</head>
<body>
<h2>Register Salesperson</h2>
<form action="./RegisterSalespersonServlet" method="post">
    Full Name: <input type="text" name="fullName" required/><br/>
    Gmail Address: <input type="email" name="gmailAddress" required/><br/>
    <input type="submit" value="Register"/>
</form>
</body>
</html>
