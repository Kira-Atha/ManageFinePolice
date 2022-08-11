<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Sign in</title>
	</head>
	<body >
		<h1 style="text-align : center" >Page de connection</h1>
		<div style="text-align : center;justify-content: center;display: flex;">
			<form action="SignIn" id="form_signIn" method="POST">
				<table border="1">
					<tr>
						<td>Personel Number </td>
						<td>
							<input type="text" name="personelNumber" size="20" />
						 </td>
					</tr>
					<tr>
						<td>Password </td>
						<td>
							<input type="password" name="password" size="20" />
						 </td>
					</tr>
					<tr>
						 <td colspan="2" align="center">
						 	<input type="submit" value="Sign in"/> 
						 </td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>