<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    

<%@page import="be.pirbaert.POJOc.TypeVehicle"%>
<%@page import="java.util.ArrayList"%>

<% ArrayList<TypeVehicle> typeVehicles = (ArrayList<TypeVehicle>)request.getAttribute("typeVehicles"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Vehicle Type List</title>
</head>
<body>
	<form action="SignIn" class="form" method="GET">
		<input type="submit" value="Logout"/>
	</form>
	<button onclick="location.href='/ClientSide/MenuAdmin'">back</button>
	<h1 style="text-align : center">Vehicle Type List</h1>
	<button onclick="location.href='/ClientSide/VehicleTypeCreate'">Create Vehicle Type</button>
	<div justify-content: center;display: flex;">
	<%for(TypeVehicle typeVehicle : typeVehicles){%>
		<fieldset>
			<form action="VehicleTypeManage" method="POST">
				<legend><%= typeVehicle.getId() %></legend>
				<p>Name : <%= typeVehicle.getName() %> </p>
				<button type="submit" name="delete" value="<%= typeVehicle.getId() %>">DELETE</button>
			</form>
			<%
				String updateUrl = String.format("location.href='/ClientSide/VehicleTypeUpdate?id=%s'",typeVehicle.getId());
				out.println("<button onclick="+updateUrl+">UPDATE</button>");
			%>
		</fieldset>
		<%}%>
	</div>
</body>
</html>