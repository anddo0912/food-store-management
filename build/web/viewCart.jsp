<%-- 
    Document   : viewCart
    Created on : Mar 11, 2022, 10:31:37 PM
    Author     : LamHung
--%>

<%@page import="sample.product.ProductDTO"%>
<%@page import="sample.product.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/styleCart.css" rel="stylesheet">
        <title>View Cart</title>
    </head>
    <body>
        <h1>This is your Cart</h1>
        <%
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
                if (cart.getCart().size() > 0) {
        %>
        <table class="center" border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Total</th>
                    <th>Edit</th>
                    <th>Remove</th>               
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    double total = 0;
                    for (ProductDTO product : cart.getCart().values()) {
                        total += product.getPrice() * product.getQuantity();
                %> 
            <form action="MainController">
                <tr>
                    <td><%= count++%></td>
                    <td>
                        <%= product.getProductID()%>
                        <input type="hidden" name="productID" value="<%= product.getProductID()%>"/>
                    </td>
                    <td>
                        <img name="imageDisplay" src="<%= product.getImage()%>"  width="200px" height="120px"  alt=""/>
                    </td>
                    <td><%= product.getPrice()%></td>
                    <td>
                        <input type="number" name="quantity" value="<%= product.getQuantity()%>" min="1" required="" />
                    </td>
                    <td><%= product.getPrice() * product.getQuantity()%></td>
                    <td>
                        <input type="submit" name="action" value="Edit" />
                    </td>
                    <td>
                        <input type="submit" name="action" value="Remove" />
                    </td>

                </tr>
            </form>
            <%
                }
            %>

        </tbody>
    </table>
    <h2>Total:<%= total%>$</h2> 
    <%
    } else {
    %>
    <h1>You don't have any items in your cart.</h1>
    <%
            }
        }
    %>

    <%
        String message = (String) request.getAttribute("MESSAGE");
        if (message == null) {
            message = "";
        }
    %>
    <h2> <%= message%></h2>
    <div style="text-align:center">
    <button>
        <a href="MainController?action=ListAll" > Add more </a>
    </button>
    <button>
        <a href="confirm.jsp" >Confirm Info</a>
    </button>
    </div>
</body>
</html>
