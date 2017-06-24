<%--
  Created by IntelliJ IDEA.
  User: Маша
  Date: 22.06.2017
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NoteBook</title>
</head>
<body>
    <h1>Result</h1>
    <c:forEach items="${result.messages}" var="message" varStatus="varStatus">
        <pre>${varStatus.index}: ${message.message}, ${message.status}</pre>
    </c:forEach>
    <a href="/notebook/">На главную</a>
</body>
</html>
