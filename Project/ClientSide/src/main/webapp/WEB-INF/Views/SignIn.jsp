<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Sign in</title>
		<style>
			.btn{
				width:auto;
			    background-color: rgba(0, 0, 144, 0.41);
			    box-shadow: 3px 3px 2px 1px rgba(0, 0, 45, 0.3);
			    border-radius: 100% 100% 100% 100%;
			    line-height:50px;
			    text-align:center;
			    vertical-align:middle;
			    color:white;
			}
			th,td{
			    margin:auto;
			    border:1px solid black;
			    width:auto;
			}
		</style>
	</head>
	<body >
		<h1 style="text-align : center" >Page de connection</h1>
		<div style="text-align : center;justify-content: center;display: flex;">
			<form action="SignIn" id="form_signIn" method="POST">
				<table>
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
						 	<input type="submit" class="btn" value="Sign in"/> 
						 </td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>