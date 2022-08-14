<%-- 
    Document   : admin
    Created on : Mar 8, 2022, 3:34:57 AM
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
        <link href="css/styleAdmin.css" rel="stylesheet" >
        <title>Admin Pagre</title>
    </head>
    <body>

        <h1>Welcome come:${sessionScope.LOGIN_USER.fullName}</h1>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("AD")) {
                response.sendRedirect("login.jsp");
                return;
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <!--phan logout-->   
            <div class="conner">
                <form action="MainController" method="POST">
                    <input type="submit" name="action" value="Logout" />
                </form>
            </div>
        <!--Phan search-->
        <div id="outer">
            <div class="inner">
                <form action="MainController">
                    Search <input type="text" name="search" value="<%= search%>"  required="" />
                    <input type="submit" name="action" value="Search" />
                </form> 
            </div>
            
            <div class="inner">
                <form action="MainController">
                    <input type="submit" name="action" value="ListAll" />
                </form>
            </div>
            <div class="inner">
                <form action="create.jsp">
                    <input type="submit" name="action" value="Create" />
                </form>
            </div>
        </div>
        <%
            String anou = (String) request.getAttribute("ANOU");
            if (anou == null) {
                anou = "";
            }
        %>
        <%= anou%>

        <%
            List<ProductDTO> listProduct = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
            if (listProduct != null) {
                if (listProduct.size() > 0) {
        %>
        <table class="center" border="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Status</th>
                    <th>Delete</th>
                    <th>Update</th>

                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (ProductDTO product : listProduct) {
                %>
            <form action="UpdateProductController">
                <tr>
                    <td><%= count++%></td>
                    <td>
                        <%= product.getProductID()%>
                        <input type="hidden" name="productID" value="<%= product.getProductID()%>" />
                    </td>
                    <td>
                        <input type="text" name="productName" value="<%= product.getProductName()%>"readonly=""/>
                    </td>
                    <td>
                        <img name="imageDisplay" src="<%= product.getImage()%>"  width="200px" height="120px"  alt=""/>
                    </td>
                    <td>
                        <input type="number" name="price" value="<%= product.getPrice()%>"readonly=""/>
                    </td>
                    <td>
                        <input type="number" name="quantity" value="<%= product.getQuantity()%>"readonly=""/>
                    </td>
                    <td>
                        <input type="boolean" name="status" value="<%= product.getStatus().equals("1") ? true : false%>" readonly=""/>
                    </td>
                    <td>
                        <button>
                            <a href="MainController?action=Delete&productID=<%= product.getProductID()%>&search=<%=search%>">Delete</a>
                        </button>
                    </td>
                    <td>
                        <input type="submit" name="action" value="Update"/>
                        <input type="hidden" name="search" value="<%= search%>"/>
                    </td>
                </tr>
            </form>
            <%
                }
            %>
        </tbody>
    </table>
    <%
            }
        }
    %>
    <%
        String error = (String) request.getAttribute("ERROR");
        if (error == null) {
            error = "";
        }
    %>
    <%= error%>
    <footer class="py-5 bg-dark">
        <div class="container"><p class="m-0 text-center text-white" style="bottom: 0%;left:0%;">Copyright &copy; Nguyen Lam Hung - SE150796</p></div>
    </footer>
</body>
</html>
