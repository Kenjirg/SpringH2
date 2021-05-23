<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>忘記密碼</h5><BR>
	<div>
		<h5>請輸入帳號</h5><BR>
		<form action="sendEmail">
			<label>帳號：</label>
			<input type="text" name="username" >
			<input type="submit" value="送出">
		</form>
	</div>
</body>
</html>