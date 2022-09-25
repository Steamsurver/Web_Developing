<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>title</title>
</head>


<body>
	<form name="addForm" method="get" action="TableAdditionServlet">
		
		<p align="center"><br>Press user id, name and secondName for addition to data base: </p>
		<p align="center">
			<input type="text" name="user_id" size="40">
			<input type="text" name="user_name" size="40">
			<input type="text" name="user_secondName" size="40">
			<input type="submit" size="20" value="Add User">
		</p>
	</form>
	
</body>


</html>