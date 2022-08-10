<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="be.pirbaert.POJOc.Account"%>   
<% Account account = (Account)request.getAttribute("account"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Account</title>
</head>
<body>
	<button onclick="window.history.go(-1)">back</button>
	<h1 style="text-align : center">Update account</h1>
	<form action="UpdateAccount" class="form" method="POST">
	<input type="hidden" id="id" name="id" value="<%= account.getId()%>" />
	<input type="hidden" id="type" name="type" value="<%= account.getType()%>" />
	
			<table border="1">
				<tr>
					<td>PersonelNumber : </td>
					<td>
						<input type="text" id="personelNumber" name="personelNumber" value="<%= account.getPersonnelNumber()%>"/>
						
					 </td>
				</tr>
				<tr>
					<td>Password (let empty if you don't want change the password)</td>
					<td>
						<input type="password" name="password"/>
					 </td>
				</tr>
				<tr>
					 <td colspan="2" align="center">
					 	<button type="submit" name="update" value="update">Update Account</button> 
					 </td>
				</tr>
			</table>
		</form>
</body>
</html>