<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@page import="be.pirbaert.POJOc.Account"%>
<%@page import="java.util.ArrayList"%>

<% ArrayList<Account> accounts = (ArrayList<Account>)request.getAttribute("accounts"); %>
		

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Gestion compte utilisateur</title>
</head>
<body>
	<button onclick="location.href='/ClientSide/CreateAccount'">Créer un compte</button>
	<h1 style="text-align : center">Liste des comptes</h1>
	<div justify-content: center;display: flex;">
		
			
			<%for(Account account : accounts){
			
				%>
				<form action="ManageAccount" method="POST">
					<fieldset>
						<legend><%= account.getId() %></legend>
						<p>PN :<%= account.getPersonnelNumber() %> </p>
						<p>TYPE :<%= account.getType() %> </p>
						<button type="submit" name="delete" value="<%= account.getId() %>">DELETE</button>
					</fieldset>
				</form>
				<%
			} %>
	</div>
</body>
</html>