<%-- 
    Document   : insert
    Created on : Mar 9, 2022, 10:32:14 PM
    Author     : LamHung
--%>

<%@page import="sample.product.ProductError"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--        <link href="css/styleCreate.css" rel="stylesheet" >-->
        <title>Create Product Page</title>
    </head>
    <body>
        <div class="create">
        <h1>Create product</h1>
                <% 
            ProductError productError = (ProductError)request.getAttribute("PRODUCT_ERROR");
            if (productError == null) {
                productError = new ProductError();
            }
        %>
        <form action="MainController" method="POST">
            <table>
                <tr>
                    <td>Product ID</td>
                    <td><input type="text" name="productID" value="" /></td>
                    <td><%= productError.getProductID()%></td>
                </tr>
                <tr>
                    <td>Product Name</td>
                    <td><input type="text" name="productName" value="" /></td>
                    <td><%= productError.getProductName() %></td>
                </tr>
                <tr>
                    <td>Image URL</td>
                    <td><input type="text" name="image" value="" /></td>
                     <td><%= productError.getImage() %></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input type="text" name="price" value="" /></td>
                     <td><%= productError.getPrice() %></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input type="text" name="quantity" value="" /></td>
                     <td><%= productError.getQuantity() %></td>
                </tr>
                <tr>
                    <td>Category ID</td>
                    <td><input type="text" name="categoryID" value="" /></td>
                     <td><%= productError.getCategoryID() %></td>
                    
                </tr>
                <tr>
                    <td>Import Date</td>
                    <td><input type="date" name="importDate" value="" /></td>
                     <td><%= productError.getImportDate()%></td>
                </tr>
                <tr>
                    <td>Using Date</td>
                    <td><input type="date" name="usingDate" value="" /></td>
                     <td><%= productError.getUsingDate() %></td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td><input type="checkbox" name="status" disabled="disabled" checked="checked">
                        <input type="hidden" name="status"  value="true"/>
                        <label for="status">True</label>
                        <input type="checkbox" name="status" value="" disabled="disabled">
                        <label for="status">False</label>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <input type="submit" name="action" value="Create"/>
                        <input type="reset" value="Reset"/>
                        <input type="button" value="Back" onclick="history.back()"/> 
                    </td>
                </tr>
            </table>

        </form>
        </div>
    </body>
</html>
