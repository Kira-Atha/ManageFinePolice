<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="be.pirbaert.POJOc.Violation"%>
<%@page import="be.pirbaert.POJOc.Chief"%>
<%@page import="be.pirbaert.POJOc.Administrator"%>
<% Violation violation = (Violation)request.getAttribute("violation");%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Update Violation</title>
</head>
<body>
	<form action="SignIn" class="form" method="GET">
		<input type="submit" value="Logout"/>
	</form>
<button onclick="location.href='/ClientSide/ViolationMenu'">back</button>
	<h1 style="text-align : center">Update Violation</h1>
	
	<%if(session.getAttribute("account") instanceof Administrator){ %>
		<form action="ViolationUpdate" class="form" method="POST">
		<input type="hidden" id="id" name="id" value="<%= violation.getId()%>" />
			<table border="1">
				<tr>
					<td>Name : </td>
					<td>
						<input type="text" id="name" name="name" value="<%= violation.getName()%>"/>
						
					 </td>
				</tr>
				<tr>
					<td>Price</td>
					<td>
						<input type="number" name="price"  value="<%= violation.getPrice()%>"/>
					 </td>
				</tr>
				<tr>
					<td>Description</td>
					<td>
						<input type="text" name="description" value="<%=violation.getDescription()%>"/>
					 </td>
				</tr>
				<tr>
					 <td colspan="2" align="center">
					 	<button type="submit" name="update" value="update">Update Violation</button> 
					 </td>
				</tr>
			</table>
		</form>
	<%}else if(session.getAttribute("account") instanceof Chief){ %>
		<form action="ViolationUpdate" class="form" method="POST">
		<input type="hidden" id="id" name="id" value="<%= violation.getId()%>" />
			<table border="1">
				<tr>
					<td>Name: </td>
					<td>
						<input type="hidden" name ="name" value="<%=violation.getName()%>"/>
						<%= violation.getName()%>
					 </td>
				</tr>
				<tr>
					<td>Price</td>
					<td>
						<input type="number" name="price"  value="<%= violation.getPrice()%>"/>
					 </td>
				</tr>
				<tr>
					<td>Description</td>
					<td>
						<input type="hidden" name ="description" value="<%=violation.getDescription()%>"/>
						<%= violation.getDescription() %>
					 </td>
				</tr>
				<tr>
					 <td colspan="2" align="center">
					 	<button type="submit" name="update" value="update">Update Violation</button> 
					 </td>
				</tr>
			</table>
		</form>
	<%}%>
</body>
</html>