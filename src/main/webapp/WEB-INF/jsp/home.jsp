<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://shiro.apache.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<s:user>
		当前用户为【<s:principal/>】-------<a href="${pageContext.request.contextPath }/user/loginOut">退出</a>
			<a href="${pageContext.request.contextPath}/user/findProList">查看所有供应商</a>
	</s:user>
</body>
</html>