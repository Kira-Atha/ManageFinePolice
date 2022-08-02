<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <!-- Il faut ajouter les librairies pour utiliser les ArrayList -->
<%@page import="java.util.ArrayList" %>
<%! 
	private String setErrorInRed(String error){
		return "<font color=\"red\">" + error + "<font/>";
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Error</title>
	</head>
	<body>
		<%
			ArrayList<String> errors = (ArrayList<String>)request.getAttribute("errors");
		%>
		<h2>
			The following errors occurred : 
		</h2>
		
		<ul>
			<%
				for(String error : errors){
					
			%>	
			<li>	
				<%= 
					this.setErrorInRed(error)
				%>
			</li>
			<% 
				}
			%>
		</ul>
		<!--  Plutôt une redirection vers la page précédente  -->
		<input type="submit" name="back" id="back" value=back/>
	</body>
</html>