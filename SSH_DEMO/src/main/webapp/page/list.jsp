<%@page contentType="text/html; charset=UTF-8" language="java"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<meta content="text/html;charset=utf-8">
	</head>
	<body>
		<p>姓名列表</p>
		<table border="1">
			<c:forEach items="${persons}" var="p">
				<tr>
					<td>姓：${p.firstName}</td>
					<td>名：${p.lastName}</td>
				</tr>
			</c:forEach>
		</table>
		<h3>添加用户</h3>
		<form action="save.action">
			姓：<input name="person.firstName" ><br/>
			名：<input name="person.lastName" ><br/>
			<input type="submit" value="添加">
		</form>
	</body>
</html>