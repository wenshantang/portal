<%@ page language="java" pageEncoding="UTF-8"%>
<%
	String ctx = request.getContextPath();
	request.setAttribute("ctx", ctx);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="0; url=${ctx}/error/404" />
</head>
<body>
</body>
</html>