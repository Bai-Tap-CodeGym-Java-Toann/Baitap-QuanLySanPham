<%--
  Created by IntelliJ IDEA.
  User: Surface Pro 4
  Date: 2018/10/21
  Time: 10:19
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Surface Pro 4
  Date: 2018/10/20
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
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

        input {
            margin-bottom: 15px;
            padding: 10px;
        }
    </style>
</head>
<body>
<%
    String error = "";
    if (request.getAttribute("error") != null) {
        error = (String) request.getAttribute("error");
    }
%>
<div style="text-align: center">
    <form class="form-group" action="/login" method="post">
        <h2>Login</h2>
        <br>
        <input class="form-control" type="text" name="username" placeholder="username">
        <input class="form-control" type="password" name="password" placeholder="password">
        <h6 style="color: red"><%=error%>
        </h6>
        <input type="submit" class="btn btn-primary" value="Login">
    </form>
</div>
</body>
</html>