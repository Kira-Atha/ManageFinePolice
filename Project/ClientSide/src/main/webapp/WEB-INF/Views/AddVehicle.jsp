<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="be.pirbaert.POJOc.TypeVehicle"%>
<%@page import="be.pirbaert.POJOc.Registration"%>
<%@page import="java.util.Objects"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>ADD vehicle</title>
	</head>
	<body>
		<form action="AddVehicle" class="form" method="POST">
			<table border="1">
				<tr>
					<td>Type vehicle</td>
					<td>
						<select name="type">
							<%if(!Objects.isNull(session.getAttribute("allTypes"))){
								ArrayList<TypeVehicle> allTypes = (ArrayList<TypeVehicle>)session.getAttribute("allTypes");%>
								<%
								for(TypeVehicle type : allTypes){%>
									<option value=<%=type.getId()%>><%=type.getName()%> </option>
								<%}%>	
								</select>
								<p> Not in the list ? Ask admin for add type of vehicle.</p>
							<%}else{ %>
								<p> No type were recorded. Ask admin for add type of vehicle.</p>
							<%} %>
						
					 </td>
				</tr>
				<tr>
					<td>Registration </td>
					<td>
						<select name="registration">
							<%if(!Objects.isNull(session.getAttribute("allRegistrationsWithoutVehicle"))){
								ArrayList<Registration> allRegistrationsWithoutVehicle = (ArrayList<Registration>)session.getAttribute("allRegistrationsWithoutVehicle");
								%><option value="0">- - UNKNOWN - -</option>
								<%for(Registration registration : allRegistrationsWithoutVehicle){%>
									<option value=<%=registration.getId()%>><%=registration.getSerialNumber()%></option>
								<%}%>
						</select>
								<p> Not in the list ? Add a registration.</p>
								<%@ include file="AddRegistration.jsp" %>
							<%}else{%>
								<p> Not in the list ? Add a registration.</p>
								<%@ include file="AddRegistration.jsp" %>
							<%}%>
						
					 </td>
				</tr>
				<tr>
					 <td colspan="2" align="center">
					 	<button type="submit" name="add" value="vehicle">Add Vehicle</button> 
					 </td>
				</tr>
			</table>
		</form>
	</body>
</html>