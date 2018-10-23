<%--
  Created by IntelliJ IDEA.
  User: Surface Pro 4
  Date: 2018/10/22
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <style>
        div {
            width: 35%;
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
<div>
    <%
        String error = "";
        if (request.getAttribute("error") != null) {
            error = (String) request.getAttribute("error");
        }
    %>
    <div>
        <img src="${product.getPicture()}" class="img-circle img-responsive"
             align="center" width="150" height="150">
    </div>
    <br>
    <h6 align="center">Code: ${product.getCode()}</h6>
    <form class="form-group" action="/products" method="post" id="form">
        <br>
        <h6>Tên sản phẩm</h6>
        <input class="form-control" type="text" name="name" placeholder="${product.getName()}">
        <h6>Giá sản phẩm</h6>
        <input class="form-control" type="text" name="price" placeholder="${product.getPrice()}">
        <h6>Nơi sản phâm</h6>
        <input class="form-control" type="text" name="origin" placeholder="${product.getOrigin()}">
        <h6>Link ảnh</h6>
        <input class="form-control" type="text" name="picture" placeholder="${product.getPicture()}">
        <input type="hidden" name="action" value="edit">
        <input type="hidden" name="code" value="${product.getCode()}">
        <h6 style="color: red"><%=error%>
        </h6>
        <button type="button" onclick="checkAndSend();"
                class="btn btn-danger">Delete
        </button>
        <a href="/products"
           class="btn btn-secondary">Cancel</a>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>
<script>
    function checkAndSend() {
        if (confirm("Delete: ${product.getCode()} - ${product.getName()} ?")) {
            window.location.href = "/products?action=delete&code=${product.getCode()}";
        }
    }
</script>
</body>
</html>
