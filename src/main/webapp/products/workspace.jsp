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
    <link rel="stylesheet" href="css/workspace.css">
</head>
<body>
<h1 align="center">Danh Sách sản phẩm</h1>
<div>
    <br>
    <br>
    <br>
    <form class="form-group search-form" action="/products" method="get">
        <input class="form-control search-input" type="text" placeholder="Search">
        <input type="hidden" name="action" value="search">
        <button type="submit" class="btn btn-primary search-btn">Seacrh</button>
        <a href="/products?action=add"
           class="btn btn-primary search-btn">Add</a>
    </form>
    <table class="table table-striped table-bordered">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Code</th>
            <th scope="col">Tên Sản Phẩm</th>
            <th scope="col">Giá thành</th>
            <th scope="col">Nơi sản xuất</th>
            <th scope="col">Sửa</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product" varStatus="count">
            <tr>
                <th scope="row">${count.count}</th>
                <td><c:out value="${product.getCode()}"></c:out></td>
                <td><c:out value="${product.getName()}"></c:out></td>
                <td><c:out value="${product.getPrice()}"></c:out></td>
                <td><c:out value="${product.getOrigin()}"></c:out></td>
                <td>
                    <a href="/products?action=edit&code=${product.getCode()}"
                       class="btn btn-primary">Edit</a>
                    </button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%
        if (request.getAttribute("passStatus") != null) {
            request.setAttribute("passStatus", null);
            response.sendRedirect("/products");
        }
        ;%>
</div>
</body>
</html>