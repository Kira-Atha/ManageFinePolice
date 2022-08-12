<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="be.pirbaert.POJOc.Fine"%>
<%@page import="java.util.Objects"%>
<!DOCTYPE html>
<!-- Ne fait qu'afficher le prix total de la contravention qui vient d'être créée pour informer l'accusé -->
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Total price</title>
	</head>
	<script type="text/javascript">
		var obj = 'window.location.replace("http://localhost:8080/ClientSide/ConsultFines");';
		setTimeout(obj,5000);
	</script>
<body>
	<%if(!Objects.isNull(request.getAttribute("fine"))){%>
		<%Fine fine = (Fine)request.getAttribute("fine");%>
		<span class="TotalPrice">
			Total price : <%=fine.getTotalPrice()%>
			<p>Wait 5 seconds, you will be automatically redirected</p>
		</span>
	<%}%>
</body>
</html>