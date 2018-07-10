<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"  %>
<html>
<body>
<h2>Hello World!</h2>
<table>
<tr>
    <td>社团编号</td>
    <td>密码</td>
    <td>社团名</td>
    <td>社团类型</td>
    <td>负责人名称</td>
    <td>社团状态</td>
</tr>

<c:forEach items="${asList}" var = "asList">
<tr>
    <td>${asList.id }</td>
    <td>${asList.password }</td>
    <td>${asList.asname}</td>
    <td>${asList.type}</td>
    <td>${asList.name}</td>
    <td>${asList.status}</td>
</tr>
</c:forEach> 
</table>
</body>
</html>
