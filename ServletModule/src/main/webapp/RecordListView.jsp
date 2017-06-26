<%--
  Created by IntelliJ IDEA.
  User: Маша
  Date: 22.06.2017
  Time: 9:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NoteBook</title>
    <link href="/resources/css/style.css" rel="stylesheet" />
</head>
<body>
<h1>Result</h1>
<c:forEach items="${result.records}" var="record" varStatus="varStatus">
    <table class="record">
        <tr><td>ID:</td><td>${record.recordID}</td></tr>
        <tr><td>Title:</td><td>${record.title}</td></tr>
        <tr><td>Text:</td><td>${record.recordText}</td></tr>
        <tr><td>Author:</td><td>${record.author}</td></tr>
        <tr><td>Deadline date:</td><td><fmt:formatDate value="${record.deadlineDate}" pattern="${dateFormat}" /></td></tr>
        <tr><td>Created date:</td><td><fmt:formatDate value="${record.createdDate}" pattern="${dateFormat}" /></td></tr>
        <tr><td>Updated date:</td><td><fmt:formatDate value="${record.updatedDate}" pattern="${dateFormat}" /></td></tr>
    </table>
</c:forEach>
<div class="message">
    <c:forEach items="${result.messages}" var="message" varStatus="varStatus">
        <pre class="${message.status}">${message.status}: ${message.message}</pre>
    </c:forEach>
</div>
<a href="index.jsp" class="button">На главную</a>
</body>
</html>