<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Product"%>

<!DOCTYPE html>
<html >
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <meta http-equiv="X-UA-Compatible" content="ie=edge" />
        <title>Error</title>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400" rel="stylesheet" />
        <link href="css/all.min.css" rel="stylesheet" />
        <link href="css/templatemo-style.css" rel="stylesheet" />
    </head>
    <body>
        
        <div class="col-md-2" style="margin-left: 50px">
            <a href="home" target="_parent" class="tm-register">
                <i> <-Back to home page </i>
            </a>
        </div>
        <h2 style="color: red">${requestScope.ms}</h2>
        <a href="logout" target="_parent" class="tm-register">
            <i> -> or logout this account then login another account! </i>
        </a>
    </body>
</html>