<%--
  Created by IntelliJ IDEA.
  User: Surface Pro 4
  Date: 2018/10/22
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ShowCustomer</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="css/workcss.css">
</head>
<body>
<h1 align="center">Welcome ToanDz</h1>
<div>
    <br>
    <ul class="list-group">
        <li class="list-group-item active">
            <p>Code</p>
            <p>Tên Sản Phẩm</p>
            <p>Giá thành</p>
            <p>Nơi sản xuất</p>
            <p></p>
        </li>
        <c:forEach items="${products}" var="product">
            <li class='list-group-item'>
                <p><c:out value="${product.getCode()}"></c:out></p>
                <p><c:out value="${product.getName()}"></c:out></p>
                <p><c:out value="${product.getPrice()}"></c:out></p>
                    <%--<p class='avatar'>--%>
                    <%--<img src="<c:out value="${customer.getPictureLink()}"></c:out>">--%>
                    <%--</p>--%>
            </li>
        </c:forEach>

    </ul>
    <%
        if (request.getAttribute("passStatus") != null) {
            request.setAttribute("passStatus", null);
            response.sendRedirect("/products");
        }
        ;%>
</div>
</body>
</html>