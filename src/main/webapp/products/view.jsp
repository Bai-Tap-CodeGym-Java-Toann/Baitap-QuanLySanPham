<%--
  Created by IntelliJ IDEA.
  User: Surface Pro 4
  Date: 2018/10/23
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>view</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <style>
        div {
            width: 50%;
            margin: auto;
            padding-top: 50px;
        }

        h1 {
            font-size: 3em;
            text-align: center;
        }
    </style>
</head>
<body>
<div align="center">
    <img src="${product.getPicture()}" class="img-circle img-responsive"
         align="center" width="150" height="150">
</div>
<%
    String log = "";
    String button = "Return";
    if (request.getAttribute("log") != null) {
        log = (String) request.getAttribute("log");
        button ="Ok";
    }
%>
<br>
<div>
    <ul class="list-group-flush">
        <li class="list-group-item"><b>Mã sản phẩm  : </b> ${product.getCode()}</li>
        <li class="list-group-item"><b>Tên sản phẩm : </b> ${product.getName()}</li>
        <li class="list-group-item"><b>Giá thành    : </b> ${product.getPrice()}</li>
        <li class="list-group-item"><b>Nơi sản xuất : </b> ${product.getOrigin()}</li>
    </ul>
</div>
<h6 align="center" style="color: green"><%=log%>
<div style="text-align: center">
    <form action="/products" method="post">
        <input type="hidden" name="action">
        <button class="btn btn-primary" type="submit"><%=button%></button>
    </form>
</div>
</body>
</html>
