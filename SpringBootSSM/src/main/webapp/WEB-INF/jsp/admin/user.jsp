<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>第一次SpringBoot页面跳转</title>
</head>
<body>
${msg} <br>
<c:forEach var="i" items="${user}">
    <c:out value="${i.id}" />
    <c:out value="${i.name}" />
    <c:out value="${i.password}" />
    <c:out value="${i.status}" />
    <c:out value="${i.email}" />
    <c:out value="${i.mobile}" /> <br>
</c:forEach>
</body>
</html>