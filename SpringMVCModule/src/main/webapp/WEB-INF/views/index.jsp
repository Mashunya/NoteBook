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
</head>
<body>
<form action="/notebook/command" method="post">
    <a href="/notebook/add">Add</a>
    <a href="deleteCommandInit.jsp">Delete</a>
    <a href="findByIDCommandInit.jsp">FindByID</a>
    <button name="command" value="findAll">Find All</button>
    <button name="command" value="about">About</button>
</form>
</body>
</html>