<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	function setUrl() {
		var url = location.search; //获取url中"?"符后的字串 
		$("#requestUrl").attr("value",url);
	}
	$(document).ready(function(){
		  setUrl();
	});
</script>
</head>
<body >
	登录
	<form action="/loginForm" method="post">
		<input type="text" name="username" id="username" /> 
		<input type="password" name="password" id="password" /> 
		<input type="hidden" id="requestUrl" name="requestUrl" >
		<input type="submit" value="登录" />
	</form>
</body>
</html>