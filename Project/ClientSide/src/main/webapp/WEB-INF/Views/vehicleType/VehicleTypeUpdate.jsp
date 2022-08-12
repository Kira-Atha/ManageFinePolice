<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@page import="be.pirbaert.POJOc.TypeVehicle"%>
<% TypeVehicle typeVehicle = (TypeVehicle)request.getAttribute("typeVehicle");%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Update Vehicle Type</title>
</head>
<body>
	<form action="SignIn" class="form" method="GET">
		<input type="submit" value="Logout"/>
	</form>
	<button onclick="location.href='/ClientSide/VehicleTypeManage'">back</button>
	<h1 style="text-align : center">Update Vehicle Type</h1>
	<form action="VehicleTypeUpdate" class="form" method="POST">
	<input type="hidden" id="id" name="id" value="<%= typeVehicle.getId()%>" />
		<table border="1">
			<tr>
				<td>Name : </td>
				<td>
					<input type="text" id="name" name="name" value="<%= typeVehicle.getName()%>"/>
					
				 </td>
			</tr>
			<tr>
				 <td colspan="2" align="center">
				 	<button type="submit" name="update" value="update">Update Vehicle Type</button> 
				 </td>
			</tr>
		</table>
	</form>
</body>
</html>