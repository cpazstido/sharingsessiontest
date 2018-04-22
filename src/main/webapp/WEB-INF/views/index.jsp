<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String staticPath = request.getContextPath();
    request.setAttribute("staticPath", staticPath);
    request.setAttribute("rootPath", staticPath);
%>
<html>
<head>
</head>
<body>
<a href="/add">添加session</a>
<h1>sessionID:<%=session.getId() %></h1>
<h1>name:${user}</h1>
</body>
</html>