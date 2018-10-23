<%--
  Created by IntelliJ IDEA.
  User: Surface Pro 4
  Date: 2018/10/23
  Time: 8:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>add a product</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <style>
        div {
            width: 30%;
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
    <form class="form-group" action="/products" method="post">
        <h2 align="center">Add</h2>
        <br>
        <br>
        <h6>Code</h6>
        <input class="form-control" type="text" name="code" placeholder="code" value="" id="code">
        <h6>Tên sản phẩm</h6>
        <input class="form-control" type="text" name="name" placeholder="name (*)">
        <h6>Giá sản phẩm</h6>
        <input class="form-control" type="text" name="price" placeholder="price (*)">
        <h6>Nơi sản xuất</h6>
        <input class="form-control" type="text" name="origin" placeholder="origin (*)">
        <h6>Link ảnh</h6>
        <input class="form-control" type="text" name="picture" placeholder="picture">
        <input type="hidden" name="action" value="add">
        <h6 style="color: red"><%=error%>
        </h6>
        <a href="/products"
           class="btn btn-secondary">Cancel</a>
        <button type="submit" class="btn btn-primary">Add</button>
    </form>
</div>
</body>
<script>
    function checker() {
        if (/^[0-9]*$/.test(document.getElementById("code").value)) {
            document.getElementById("code").style.color = "black";
        } else {
            document.getElementById("code").style.color = "red";
        }
        setTimeout(checker, 100);
    }
    checker();
</script>
</html>
