<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@page import="be.pirbaert.POJOc.Policeman"%>
<%@page import="be.pirbaert.POJOc.Chief"%>
<%@page import="java.util.ArrayList"%>
<% ArrayList<Policeman> policemans = (ArrayList<Policeman>)request.getAttribute("policemans"); %>
<% ArrayList<Chief> chiefs = (ArrayList<Chief>)request.getAttribute("chiefs"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Add a Subordinate</title>
</head>
<body>
	<form action="SignIn" class="form" method="GET">
		<input type="submit" value="Logout"/>
	</form>
	<button onclick="location.href='/ClientSide/MenuAdmin'">back</button>
	<h1 style="text-align : center">Add a Subordinate</h1>
	<form action="SubordinateAdd" class="form" method="POST">
		<table border="1">
			<tr>
				<td>Chief : </td>
				<td>
					<select name="chief">
							<%for(Chief chief : chiefs	){%>
								<option value=<%=chief.getId()%>><%=chief.getPersonnelNumber()%></option>
							<%}%>
					</select>
					
				 </td>
			</tr>
			<tr>
				<td>Subordinate : </td>
				<td>
					<select name="subordinate">
							<%for(Policeman subordinate : policemans	){%>
								<option value=<%=subordinate.getId()%>><%=subordinate.getPersonnelNumber()%></option>
							<%}%>
					</select>
					
				 </td>
			</tr>
			<tr>
				 <td colspan="2" align="center">
				 	<button type="submit" name="add" value="add">Update Vehicle Type</button> 
				 </td>
			</tr>
		</table>
	</form>
</body>
</html>