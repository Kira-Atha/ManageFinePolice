<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.Objects"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Add charged</title>
	</head>
	<body>
		<form action="AddCharged" class="form" method="POST">
			<table border="1">
				<tr>
					<td>Firstname</td>
					<td>
						<input type="text" name="firstname" size="20" />
					 </td>
				</tr>
				<tr>
					<td>Lastname</td>
					<td>
						<input type="text" name="lastname"size="20" />
					 </td>
				</tr>
				<tr>
					<td>Address</td>
					<td>
						<input type="text" name="address"size="20" />
					 </td>
				</tr>
				<tr>
					 <td colspan="2" align="center">
					 	<button type="submit" class="btn" name="add" value="charged">Add Charged</button> 
					 </td>
				</tr>
			</table>
		</form>
	</body>
</html>