<%--
  Created by IntelliJ IDEA.
  User: Surface Pro 4
  Date: 2018/10/22
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: nhat
  Date: 4/20/18
  Time: 7:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error 404</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
<br>
<h1 align="center" style="padding-top: 30px"> We're Sorry! :( </h1>
<h1 align="center">Resource not found</h1>
<div style="text-align: center; padding-top: 30px">
    <form action="/products" method="post">
        <input type="hidden" name="action">
        <button class="btn btn-secondary" type="submit">Return</button>
    </form>
</div>
</body>
</html>