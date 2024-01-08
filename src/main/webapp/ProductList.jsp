<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<h2>Product List</h2>
<table border="1">
    <tr>
        <th>Barcode</th>
        <th>Product Name</th>
        <th>Import Price</th>
        <th>Retail Price</th>
        <th>Category</th>
        <th>Actions</th>
    </tr>
    <% List<Product> products = (List<Product>) request.getAttribute("products"); %>
    <% for (Product product : products) { %>
    <tr>
        <td><%= product.getBarcode() %>
        </td>
        <td><%= product.getProductName() %>
        </td>
        <td><%= product.getImportPrice() %>
        </td>
        <td><%= product.getRetailPrice() %>
        </td>
        <td><%= product.getCategory() %>
        </td>
        <td>
            <a href="viewProduct.jsp?id=<%= product.getId() %>">View</a>
            <a href="updateProduct.jsp?id=<%= product.getId() %>">Update</a>
            <a href="deleteProductServlet?id=<%= product.getId() %>">Delete</a>
        </td>
    </tr>
    <% } %>
</table>
</body>
</html>
