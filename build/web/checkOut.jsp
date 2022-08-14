<%-- 
    Document   : user
    Created on : Mar 8, 2022, 4:12:56 AM
    Author     : LamHung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/styleCheckout.css" rel="stylesheet" >
        <title>Checkout Success Page</title>
    </head>
    <body>
        <h1>You ordered succeed</h1>
        <h2>Thank you for buying at my shop</h2> 
        <div style="text-align:center"></div>
        <button>
            <a href="MainController?action=ListAll">Back to Home</a>
        </button>  
        <form action="MainController" method="POST">
            <input type="submit" name="action" value="Logout" />
        </form>
        </div>
    </body>
</html>
