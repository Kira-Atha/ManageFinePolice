<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%String back = (String)request.getAttribute("previous");%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>ADD vehicle</title>
	</head>
	<body>
		<form action="AddVehicle" method="POST">
			<table border="1">
				<tr>
					<td> Personel Number </td>
					<td>
						<input type="text" name="personelNumber" id="personelNumber" value="" size="20" />
					 </td>
				</tr>
				<tr>
					<td> Password </td>
					<td>
						<input type="text" name="password" id="password" value="" size="20" />
					 </td>
				</tr>
				<tr>
					 <td colspan="2" align="center">
					 	<input type="submit" name="submit" id="submit" value="SignIn"/> 
					 </td>
				</tr>
			</table>
		</form>
		<form action="<%=back%>" method="GET">
			<input type="submit" value=back>
		</form>
	</body>
	
</html>