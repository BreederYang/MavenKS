<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/user/addProduct" method="post">
		
		应商编码:<input type="text" name="proCode"><br>
		供应商名称:<input type="text" name="proName"><br>
		供应商详细描述:<input type="text" name="proDesc"><br>
		供应商联系人:<input type="text" name="proContact"><br>
		联系电话:<input type="text" name="proPhone"><br>
		地址:<input type="text" name="proAddress"><br>
		传真:<input type="text" name="proFax"><br>
		 <input type="submit" value="添加">
	</form>
</body>
</html>