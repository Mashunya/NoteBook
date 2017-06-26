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
        <form action="/notebook/command" method="post">
            <ul>
                <li>
                    <h3>Initialize add record command</h3>
                </li>
                <li>
                    <label for="text">Text:</label>
                    <textarea name="spr_text" id = "text"></textarea>
                </li>
                <li>
                    <label for="title">Title:</label>
                    <input type="text" name="spr_title" id = "title" />
                </li>
                <li>
                    <label for="author">Author:</label>
                    <input type="text" name="spr_author" id = "author" />
                </li>
                <li>
                    <label for="type">Type:</label>
                    <input type="text" name="spr_type" id = "type" />
                </li>
                <li>
                    <label for="deadline">Type:</label>
                    <input type="date" name="spr_deadline" data-date-format="${dateFormat}" id = "deadline" />
                </li>
                <li class="center">
                    <button name="command" class="submit" value="add">Submit</button>
                </li>
            </ul>
        </form>
    </div>
</body>
</html>
