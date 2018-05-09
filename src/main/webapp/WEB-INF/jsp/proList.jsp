<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<shiro:hasRole name="jingli"><a href="${pageContext.request.contextPath}/user/toadd">添加供应商</a></shiro:hasRole>
	<table border="1">
		<tr>
			<td>供应商编码</td>
			<td>供应商名称</td>
			<td>供应商详细描述</td>
			<td>供应商联系人</td>
			<td>联系电话</td>
			<td>地址</td>
			<td>传真</td>
			
			<shiro:hasRole name="jingli"><td>删除</td></shiro:hasRole>
		</tr>
		<c:forEach items="${pro }" var="p">
			<tr>
				<td>${p.proCode }</td>
				<td>${p.proName }</td>
				<td>${p.proDesc }</td>
				<td>${p.proContact }</td>
				<td>${p.proPhone }</td>
				<td>${p.proAddress }</td>
				<td>${p.proFax }</td>
				<shiro:hasRole name="jingli"><td><a href="${pageContext.request.contextPath}/user/delProduct?id=${p.id}">X</a></td></shiro:hasRole>
			</tr>
		</c:forEach>
	</table>
</body>
</html>