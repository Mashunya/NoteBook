<%--
  Created by IntelliJ IDEA.
  User: Маша
  Date: 22.06.2017
  Time: 11:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NoteBook</title>
    <link href="/resources/css/style.css" rel="stylesheet" />
</head>
<body>
<form action="/command" method="post">
    <a href="addCommandInit.jsp" class="button">Add</a>
    <a href="deleteCommandInit.jsp" class="button">Delete</a>
    <a href="findByIDCommandInit.jsp" class="button">FindByID</a>
    <button name="command" value="findAll" class="submit">Find All</button>
    <button name="command" value="about" class="submit">About</button>
</form>
</body>
</html>
