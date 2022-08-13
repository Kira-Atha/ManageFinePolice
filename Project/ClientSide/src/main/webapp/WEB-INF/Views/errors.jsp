<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			String back = (String)request.getAttribute("previous");
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
		<form action="<%=back%>" method="GET">
			<input type="submit" class="btn" value=back>
		</form>
	</body>
</html>