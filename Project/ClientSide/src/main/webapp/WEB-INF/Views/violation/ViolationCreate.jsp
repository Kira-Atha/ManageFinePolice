<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="be.pirbaert.POJOc.Violation"%>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Create Violation</title>
</head>
<body>
	<form action="SignIn" class="form" method="GET">
		<input type="submit" value="Logout"/>
	</form>
	<button onclick="location.href='/ClientSide/ViolationMenu'">back</button>
	<h1 style="text-align : center">Create Violation</h1>
	<div justify-content: center;display: flex;">
			<%
			try{
	
				if ((boolean)request.getAttribute("error"))
				{
					out.println("<span>Error in inforamtion</span>");
				}
			}catch(Exception e){
				
			}
			
			%>
			<form action="ViolationCreate" class="form" method="POST">
				<table border="1">
					<tr>
						<td>Name : </td>
						<td>
							<input type="text" id="name" name="name"/>
							
						 </td>
					</tr>
					<tr>
						<td>Price : </td>
						<td>
							<input type="number" name="price"/>
						 </td>
					</tr>
					<tr>
						<td>Description : </td>
						<td>
							<input type="text" name="description"/>
						 </td>
					</tr>
					<tr>
						 <td colspan="2" align="center">
						 	<button type="submit" name="create" value="create">Add Violation</button> 
						 </td>
					</tr>
				</table>
			</form>
		</div>
</body>
</html>