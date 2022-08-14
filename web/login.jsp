<%-- 
    Document   : login
    Created on : Mar 7, 2022, 10:01:49 PM
    Author     : LamHung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/stylelogin.css" rel="stylesheet" >
        <title>Welcome to FoodStore</title>
    </head>
    <body>
        <div class="login">
        <h1>Sign In With</h1>
        <form action="MainController" method="POST">
            User ID   <input type="text" name="userID" required="" placeholder="userID" /> </br>
            Password <input type="password" name="password" required="" placeholder="password" /> </br>
            <input type="submit" name="action" value="Login"/>
            <input type="reset" value="Reset" />
        </form>
        </div>
       <h3>${requestScope.ERROR}</h3>
    </body>
</html>
