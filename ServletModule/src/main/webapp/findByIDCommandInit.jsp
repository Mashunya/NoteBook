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
    <form action="/command" method="post">
        <label>RecordID: <input type="number" name="serv_ID" /></label><br/>
        <button name="serv_command" value="findByID">Send</button>
    </form>
</body>
</html>
