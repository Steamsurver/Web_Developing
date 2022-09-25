<%@ page language="java" contentType="text/html; charset=Windows-1251" pageEncoding="Windows-1251" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<title>Sign In</title>
	</head>
	
	
	
	
	<body>
		<form name="signUpForm" method="get" action="SignUpServlet">
			<p align="center"><br>New login: </p>
			<p align="center">
				<input type="text" name="login" size="40">
			</p>
			
			<p align="center"><br>New password: </p>
			<p align="center">
				<input type="text" name="password" size="40">
			</p>
			
			<p align="center"><br>New id: </p>
			<p align="center">
				<input type="text" name="id" size="40">
			</p>
			
			<p align="center">
				<input type="submit" size="20" value="Ready">
			</p>
			
		</form>
	</body>
</html>