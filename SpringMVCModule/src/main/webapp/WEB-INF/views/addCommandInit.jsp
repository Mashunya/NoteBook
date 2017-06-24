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
</head>
<body>
    <form action="/notebook/command" method="post">
        <label>Text: <input type="text" name="spr_text" /></label><br/>
        <label>Title: <input type="text" name="spr_title" /></label><br/>
        <label>Author: <input type="text" name="spr_author" /></label><br/>
        <label>Type: <input type="text" name="spr_type" /></label><br/>
        <label>Deadline: <input type="date" name="spr_deadline" data-date-format="${dateFormat}" /></label><br/>
        <button name="command" value="add">Send</button>
    </form>
</body>
</html>
