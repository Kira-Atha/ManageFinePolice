<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="be.pirbaert.POJOc.Account"%>
 
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Create Account</title>
</head>
<body>

	<h1 style="text-align : center">Creer un compte</h1>
	<div justify-content: center;display: flex;">
		<%
		try{

			if ((boolean)request.getAttribute("error"))
			{
				out.println("<span>Error in inforamtion</span>");
			}
		}catch(Exception e){
			
		}
		
		%>
		<form action="CreateAccount" class="form" method="POST">
				<table border="1">
					<tr>
						<td>PersonelNumber : </td>
						<td>
							<input type="text" id="personelNumber" name="personelNumber"/>
							
						 </td>
					</tr>
					<tr>
						<td>Password</td>
						<td>
							<input type="password" name="password"/>
						 </td>
					</tr>
					<tr>
						<td>Type</td>
						<td>
							<input type="radio" value="Administrator" id="Administrator" name="type"/>
							<label for="Administrator">Administrator</label>
							
							<input type="radio" value="Policeman" id="Policeman" name="type"/>
							<label for="Policeman">Policeman</label>
							
							<input type="radio" value="Chief" id="Chief" name="type"/>
							<label for="Chief">Chief</label>
							
							<input type="radio" value="TaxCollector" id="TaxCollector" name="type"/>
							<label for="TaxCollector">Tax Collector</label>
						 </td>
					</tr>
					<tr>
						 <td colspan="2" align="center">
						 	<button type="submit" name="create" value="create">Add Account</button> 
						 </td>
					</tr>
				</table>
			</form>
	</div>
</body>
</html>