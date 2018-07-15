<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String staticPath = request.getContextPath();
    request.setAttribute("staticPath", staticPath);
    request.setAttribute("rootPath", staticPath);
    String my = request.getRemoteAddr()+":"+request.getLocalPort();
%>
<html>
<head>
</head>
<body>
<h1><%=my%></h1>
<button onclick="buttonClick()">click me!</button>
<a href="${rootPath}/add">添加session</a>
<h1>sessionID:<%=session.getId() %></h1>
<h1>name:${user}</h1>
</body>
</html>
<script>
    function buttonClick() {
        alert(location.href+"?m="+Math.random());
    }
</script>