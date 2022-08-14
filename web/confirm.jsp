<%-- 
    Document   : confirm
    Created on : Mar 12, 2022, 3:30:09 PM
    Author     : LamHung
--%>

<%@page import="sample.user.UserDTO"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="sample.product.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/styleconfirm.css" rel="stylesheet">
        <title>Checkout Page</title>
    </head>
    <body>
        <div class='container'>
            <div class='window'>
                <div class='order-info'>
                    <input class="bck-btn" type="button" value="Back" onclick="history.back()" style='position:absolute;top:0%;left:0%;'/>  
                    <div class='order-info-content'>
                        
                        <%
                            Cart cart = (Cart) session.getAttribute("CART");
                            if (cart != null) {
                                if (cart.getCart().size() > 0) {
                        %>
                        <h2>Order Summary</h2>
                        <%
                            double total = 0;
                            for (ProductDTO product : cart.getCart().values()) {
                                total += product.getPrice() * product.getQuantity();
                        %>
                        <div class='line'></div>
                        <table class='order-table'>
                            <tbody>
                                <tr>
                                    <td><img src="<%= product.getImage()%>" class='full-width'></img>
                                    </td>
                                    <td>
                                        <br> <span class='thin'><%= product.getProductName()%></span><br><br>
                                        Quantity:<span class='thin small'> <%= product.getQuantity()%></span>
                                    </td>

                                </tr>
                                <tr>
                                    <td>
                                        <div class='price'> 
                                            <%= product.getPrice() * product.getQuantity()%>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>

                        </table>
                        <%
                            }
                        %>
                        <div class='line-total'></div>
                        <div class='order-table'>
                            <span style='float:left;'>
                                TOTAL <br>

                            </span>
                            <span style='float:right; text-align:right;'>
                                <%= total%>
                            </span><br>

                        </div>  
                    </div>
                    <%
                            }
                        }
                    %>

                </div>
                <div class='credit-info'>
                    <div class='credit-info-content'>
                        <%
                            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
                            if (loginUser == null || !loginUser.getRoleID().equals("US")) {
                                response.sendRedirect("login.jsp");
                                return;
                            }
                        %>
                        <img src='https://media.discordapp.net/attachments/856179981934919731/952156640218599484/khac-dau-icon-1-02.png' height='80' class='credit-card-image' id='credit-card-image'></img>
                        User ID
                        <input class='input-field' type="text" name="userID" value="<%= loginUser.getUserID()%>" readonly=""> </input>
                        User Name
                        <input class='input-field' type="text" name="userName" value="<%= loginUser.getFullName()%>" readonly=""></input>
                        Address
                        <input class='input-field' type="text" name="address" value="<%= loginUser.getAddress()%>" required=""></input>
                        Email
                        <input class='input-field' type="text" name="email" value="<%= loginUser.getEmail()%>" readonly=""></input>
                        <table class='half-input-table'>
                            <tr>
                                <td> Phone
                                    <input class='input-field' type="text" name="phone" value="<%= loginUser.getPhone()%>" required=""></input>
                                </td>
                            </tr>
                        </table>
                        <form action="MainController">
                            <input class='pay-btn' type="submit" name="action" value="Checkout" />
                        </form>
                    </div>
                </div>
            </div>               
        </div>
    </body>
</html>
