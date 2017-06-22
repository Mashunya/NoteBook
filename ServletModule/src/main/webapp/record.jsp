<%--
  Created by IntelliJ IDEA.
  User: Маша
  Date: 22.06.2017
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NoteBook</title>
</head>
<body>
<h1>Result</h1>
<p>${result.record.recordID}</p>
<p>${result.record.recordText}</p>
<p>${result.record.author}</p>
<p>${result.record.title}</p>
<fmt:formatDate value="${result.record.deadlineDate}" pattern="dd.MM.yyyy" />
<fmt:formatDate value="${result.record.createdDate}" pattern="dd.MM.yyyy" />
<fmt:formatDate value="${result.record.updatedDate}" pattern="dd.MM.yyyy" />
<c:forEach items="${result.messages}" var="message" varStatus="varStatus">
    <pre>${varStatus.index}: ${message.message}, ${message.status}</pre>
</c:forEach>
</body>

</body>
</html>
