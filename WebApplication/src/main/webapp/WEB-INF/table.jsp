<%@ page language="java" contentType="text/html; charset=Windows-1251" pageEncoding="Windows-1251" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=Windows-1251">
  	<title>Main page</title>
 </head>

 <body id="body">
	<form name="tableForm" method="get" action="TableServlet">  
		<table id ="uTable" border="tabP" align = "center">
			<thead>
				<tr>
					<th>ID</th>
					<th>NAME</th>
					<th>SECONDNAME</th>
				</tr>
			</thead>
			
			<tbody id="tBody">
			</tbody>
		</table>
		
		
		

		
		<script>
			string = '${table}';
			var array = string.split([',']);
			
			let x_table = document.getElementById('uTable');
			let tbody = document.createElement('tBody');
			let table_row = document.createElement('tr');
			var row_data_1;
			var row_data_2;
			var row_data_3;
			
			
			array['${sizeOfTable}' - 1] = array['${sizeOfTable}' - 1].slice(0, array['${sizeOfTable}' - 1].length-1);
			array[0] = array[0].slice(1);
			for(let i = 0; i < '${sizeOfTable}'; i += 3){
				table_row = document.createElement('tr');
				row_data_1 = document.createElement('td');
				row_data_2 = document.createElement('td');
				row_data_3 = document.createElement('td');
				
				row_data_1.innerHTML = array[i];
				row_data_2.innerHTML = array[i+1];
				row_data_3.innerHTML = array[i+2];
				
				table_row.appendChild(row_data_1);
				table_row.appendChild(row_data_2);
				table_row.appendChild(row_data_3);
				tbody.appendChild(table_row);
			}
			x_table.appendChild(tbody);
		</script>
	</form>
	
	<form name="tableDeleteForm" method="post" action="TableDeleteServlet">
		<p>
		<input type="submit" size="20" value="Delete user" >
		</p>
	</form>
	
	<form name="tableAddForm" method="post" action="TableAdditionServlet">  
		<p>
		<input type="submit" size="20" value="Add new user">
		</p>
	</form>
	<p>
	<%= request.getAttribute("messege")%>
	</p>
 </body>

</html>