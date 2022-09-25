<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>title</title>
</head>


<body>
	<form name="deleteForm" method="get" action="TableDeleteServlet">
		
		<p align="center"><br>Press user id for deleting from data base: </p>
		<p align="center">
			<input type="text" name="del_id" size="40">
		</p>
	 
		<p align="center">
			<input type="submit" size="20" value="Delete User">
		</p>
	</form>
	
</body>


</html>