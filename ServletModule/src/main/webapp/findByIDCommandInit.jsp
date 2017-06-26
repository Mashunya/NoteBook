<%--
  Created by IntelliJ IDEA.
  User: Маша
  Date: 22.06.2017
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NoteBook</title>
    <link href="/resources/css/style.css" rel="stylesheet" />
</head>
<body>
<div class="center">
    <form action="/command" method="post">
        <ul>
            <li>
                <h3>Initialize find record by ID command</h3>
            </li>
            <li>
                <label for="id">ID:</label>
                <input type="text" name="serv_ID" id = "id" />
            </li>
            <li class="center">
                <button name="command" value="findByID" class="submit">Submit</button>
            </li>
        </ul>
    </form>
</div>
</body>
</html>
