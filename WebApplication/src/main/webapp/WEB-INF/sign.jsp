<%@ page language="java" contentType="text/html; charset=Windows-1251" pageEncoding="Windows-1251" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<title>Sign In</title>
	</head>
	
	
	
	
	<body>
		<form name="signForm" method="get" action="SignInServlet">
		
			<p align="center"><br>Login: </p>
			<p align="center">
				<input type="text" name="login" size="40" value=<%= request.getParameter("login")%>>
			</p>
			
			<p align="center"><br>Password: </p>
			<p align="center">
				<input type="text" name="password" size="40" value=<%= request.getParameter("password")%>>
			</p>
			
			<p align="center">
				<input type="submit" size="20" value="Send">
				<br> <%= request.getAttribute("messege")%>
			</p>
		</form>		
		
		<form name="getSignUpForm" method="post" action="SignUpServlet">
			<p align="center"><input type="submit" size="20" value="SignUp"></p>
		</form>	
		
	</body>
</html>