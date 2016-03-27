<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>zoos</title>
	<script type="text/javascript"  src="/springmvc/static/js/jquery-2.2.1.min.js"></script>
	<script type="text/javascript">
		$(function(){
			$("#update").click(function(){
				alert("---")
				var id=$("form input[name='id']").val();
				var zooName=$("form input[name='zooName']").val();
				var address=$("form input[name='address']").val();
				debugger;
				$.ajax({
					url:"zoos/"+id,
					type:"post",
					dataType:"json",
					data:{_method:"put","id":id,"zooName":zooName,"address":address},
					success:function(){
						alert("ok")
					}
				});
			});
		});
		
	</script>
</head>
<body>
	<form action="zoos.do" method="post">
		添加：<br/>
		id:<input name="id" value=""><br/>
		zooName:<input name="zooName" value=""><br/>
		address:<input name="address" value=""><br/>
		<input type="submit" value="新增">
		<input id="update" type="button" value="更新" >
	</form>
</body>
</html>