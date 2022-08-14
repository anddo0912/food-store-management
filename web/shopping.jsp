<%-- 
    Document   : shopping
    Created on : Mar 8, 2022, 3:10:51 PM
    Author     : LamHung
--%>

<%@page import="java.util.List"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Page</title>
        <link rel="stylesheet" href="css/styleShop.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        
    </head>
    <body>

        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("US")) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <h1>Welcome <%= loginUser.getFullName()%> to my shop</h1>
        
        <!--phan logout-->   
            <div class="conner-ld">
                <form action="MainController" method="POST">
                    <input type="submit" name="action" value="Logout" />
                </form>
            </div>
        
        <div id="outer-row">
            <!--Phan search-->
            <div class="inner-ld">
                <form action="MainController">
                    Search <input type="text" name="search" value="<%= search%>"  required="" />
                    <input type="submit" name="action" value="Search" />
                </form> 
            </div>
            
            <div class="inner-ld">
                <form action="MainController">
                    <input type="submit" name="action" value="ListAll" />
                </form>
            </div>
                    
            <div class="inner-ld">
                <form action="MainController">
                    <input type="submit" name="action" value="View"/>  
                </form>
            </div>
        </div>
        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (message == null) {
                message = "";
            }
        %>
        <h1> <%= message%></h1>

        <%
            List<ProductDTO> listProduct = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
            if (listProduct != null) {
                if (listProduct.size() > 0) {
        %>

        <%
            for (ProductDTO product : listProduct) {
        %>

        <form action="MainController">
            <div class="row-img">
                <div class="col-md-4">
                    <img src="<%= product.getImage()%>"  alt="" class="img-responsive" />
                    <h3>Product Name : <%= product.getProductName()%> </h3>
                    <label for="quantity">Quantity</label>
                    <input type="number" name="quantity" value="1" min="1" max="<%= product.getQuantity()%>" required="" />
                    <p>Price: <%= product.getPrice()%> </p>
                    <input type="submit" name="action" value="AddToCart"/>
                    <input type="hidden" name="search" value="<%= search%>"/>
                    <input type="hidden" name="productID" value="<%= product.getProductID()%>"/>
                </div>
            </div>          
        </form>
        <%
            }
        %>
        <%
                }
            }
        %>


    </body>
</html>
