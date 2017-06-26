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
<form action="/notebook/command" method="post">
    <a href="/notebook/init?commandName=add" class="button">Add</a>
    <a href="/notebook/init?commandName=delete" class="button">Delete</a>
    <a href="/notebook/init?commandName=findByID" class="button">FindByID</a>
    <button name="command" value="findAll" class="submit">Find All</button>
    <button name="command" value="about" class="submit">About</button>
</form>
</body>
</html>
