<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="be.pirbaert.POJOc.Violation"%>
<%@page import="java.util.ArrayList"%>

<% ArrayList<Violation> violations = (ArrayList<Violation>)request.getAttribute("violations"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Violation list</title>
</head>
	<body>
		<button onclick="window.history.go(-1)">back</button>
		<h1 style="text-align : center">Violation list</h1>
		<button onclick="location.href='/ClientSide/ViolationCreate'">Create Violation</button>
		
		<div justify-content: center;display: flex;">
			
				
				<%for(Violation violation : violations){
				
					%>
					<fieldset>
					
						<form action="ViolationMenu" method="POST">
							<legend><%= violation.getId() %></legend>
							<p>Name : <%= violation.getName() %> </p>
							<p>Price : <%= violation.getPrice() %> </p>
							<p>Description : <%= violation.getDescription() %> </p>
							<button type="submit" name="delete" value="<%= violation.getId() %>">DELETE</button>
							
							
						</form>
						<% 
								String updateUrl = String.format("location.href='/ClientSide/ViolationUpdate?id=%s'",violation.getId());
								out.println("<button onclick="+updateUrl+">UPDATE</button>"); %>
					</fieldset>
						
					<%
				} %>
		</div>
	</body>
</html>