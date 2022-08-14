<%-- 
    Document   : updateProduct
    Created on : Mar 9, 2022, 7:42:10 PM
    Author     : LamHung
--%>

<%@page import="sample.product.ProductDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--        <link href="css/styleUpdate.css" rel="stylesheet" >-->
        <script>
            function onlyOne(checkbox) {
                var checkboxes = document.getElementsByName('status');
                checkboxes.forEach((item) => {
                    if (item !== checkbox)
                        item.checked = false;
                }
                );
            }
        </script>
        <title>Update Product Page</title>
    </head>
    <body>
        
        <div class="update">
            <h1>Update your product</h1>
            <form action="MainController" method="POST">
                <table>
                    <%
                        ProductDTO product = (ProductDTO) request.getAttribute("updateProduct");
                        String search = request.getParameter("search");
                        if (search == null) {
                            search = "";
                        }
                    %>      
                    <tr>
                        <td>Product ID</td>
                        <td><input type="text" name="productID" value="<%=product.getProductID()%>" readonly="" /></td>
                    </tr>
                    <tr>
                        <td>Product Name</td>
                        <td><input type="text" name="productName" value="<%=product.getProductName()%>" /></td>
                    </tr>
                    <tr>
                        <td>Image URL</td>
                        <td><input type="text" name="image" value="<%=product.getImage()%>" /></td>
                    </tr>
                    <tr>
                        <td>Price</td>
                        <td><input type="text" name="price" value="<%=product.getPrice()%>" /></td>
                    </tr>
                    <tr>
                        <td>Quantity</td>
                        <td><input type="text" name="quantity" value="<%=product.getQuantity()%>" /></td>
                    </tr>
                    <tr>
                        <td>Category ID</td>
                        <td><input type="text" name="categoryID" value="<%=product.getCategoryID()%>" /></td>

                    </tr>
                    <tr>
                        <td>Import Date</td>
                        <td><input type="date" name="importDate" value="<%=product.getImportDate()%>" /></td>
                    </tr>
                    <tr>
                        <td>Using Date</td>
                        <td><input type="date" name="usingDate" value="<%=product.getUsingDate()%>" /></td>
                    </tr>
                    <tr>
                        <td>Status</td>
                        <td>
                            <input type="checkbox" name="status" value="<%= product.getStatus()%>" checked="checked" onclick="onlyOne(this)" >
                            <label for="status">True</label>
                            <input type="checkbox" name="status" value="false" onclick="onlyOne(this)" >
                            <label for="status">False</label>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="submit" name="action" value="Update"/>
                            <input type="button" value="Back" onclick="history.back()"/> 
                            <input type="hidden" name="search" value="<%= search%>"/>
                        </td>
                    </tr>
                </table>

            </form>
        </div>
    </body>
</html>
