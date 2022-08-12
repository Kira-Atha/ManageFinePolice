<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Administrator Menu</title>
	
</head>
<body>
	<form action="SignIn" class="form" method="GET">
		<input type="submit" value="Logout"/>
	</form>
	<h1 style="text-align : center" >Administrator Menu</h1>
	<div style="text-align : center;justify-content: center;display: flex;">
		<button onclick="location.href='/ClientSide/ManageAccount'">Manage user account</button>
	</div>
	<div style="text-align : center;justify-content: center;display: flex;">
		<button onclick="location.href='/ClientSide/ViolationMenu'">Manage violation type</button>
	</div>
	<div style="text-align : center;justify-content: center;display: flex;">
		<button onclick="location.href='/ClientSide/VehicleTypeManage'">Manage vehicle type</button>
	</div>
</body>
</html>