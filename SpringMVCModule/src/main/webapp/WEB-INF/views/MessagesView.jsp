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
    <link href="/resources/css/style.css" rel="stylesheet" />
</head>
<body>
    <h1>Result</h1>
    <div class="message">
        <c:forEach items="${result.messages}" var="message" varStatus="varStatus">
            <pre class="${message.status}">${message.status}: ${message.message}</pre>
        </c:forEach>
    </div>
    <a href="/notebook/" class="button">На главную</a>
</body>
</html>
