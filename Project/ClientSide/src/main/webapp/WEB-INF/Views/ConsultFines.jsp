<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="be.pirbaert.POJOc.Fine"%>
<%@page import="be.pirbaert.POJOc.Chief"%>
<%@page import="be.pirbaert.POJOc.Violation"%>
<%@page import="be.pirbaert.POJOc.Policeman"%>
<%@page import="be.pirbaert.POJOc.Vehicle"%>
<%@page import="be.pirbaert.POJOc.Charged"%>
<%@page import="be.pirbaert.POJOc.TaxCollector"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Objects"%>

<%
	String back = (String)request.getAttribute("previous");
	SimpleDateFormat dayMonthYear = new SimpleDateFormat("dd/MM/yyyy");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Consult fines</title>
	</head>
	<body>
	<!--  CONSULT + ACCEPT CHIEF -->
	
		<%if(session.getAttribute("account") instanceof Chief){
			if(!Objects.isNull(request.getAttribute("allFines"))){
				ArrayList<Fine> allFines = (ArrayList<Fine>)request.getAttribute("allFines");%>
				<span id="span_consult_fines">
				<p>Consult fines</p>
					<table border="1">
						<tr>
							<th>Date</th>
							<th>Comment</th>
							<th>Violations</th>
							<th>Charged</th>
							<th>Vehicle</th>
							<th>Policeman</th>
							<th>TotalPrice</th>
							<th>Status</th>
						</tr>
						
					<%for(Fine fine : allFines){
						String fine_id = String.valueOf(fine.getId());
					%>	
						<tr>
							<td><%= dayMonthYear.format(fine.getDate()) %></td>
							<td><%= fine.getCommentary() %></td>
							<td> <% for(Violation violation : fine.getViolations()){%>
										<%=violation.getName()%> 
									<%}%>
							</td>
							<%
							if(Objects.isNull(fine.getCharged())){%>
								<td>- - UNKNOWN - -</td>
							<%}else{%>
								<td><%= fine.getCharged().getFirstname() %></td>
							<%}%>
							<td><%= fine.getVehicle().getType().getName() %></td>
							<td><%= fine.getPoliceman().getPersonnelNumber()%></td>
							<td><%= fine.getTotalPrice() %></td>
							<td>
								<form action="DownstreamFine" method="POST">
									<button type="submit" name="accept" value=<%=fine_id %>>Accept</button> 
									<button type="submit" name="decline" value=<%=fine_id %>>Decline</button> 
								</form>
							</td>
						</tr>
					<%}%>
					</table>
				<%}else{%>
					<p>No fines were recorded</p>
				<%}%>
			<%}else if(session.getAttribute("account") instanceof TaxCollector){%>
				<p>Coucou tax collector<p>
								
				
				
				
			<%}%>
		<!--  ADD  -->
			<%if(!Objects.isNull(request.getAttribute("allViolations"))){
				ArrayList<Violation> allViolations = (ArrayList<Violation>)request.getAttribute("allViolations");%>
			<%}%>
			
			<%if(!Objects.isNull(request.getAttribute("allVehicles"))){
				ArrayList<Vehicle> allVehicles = (ArrayList<Vehicle>)request.getAttribute("allVehicles");%>
				
			<%}%>
		</span>
		
		<%if(session.getAttribute("account") instanceof Policeman){%>
			<p>ADD FINE</p>
			<span id="span_add_fine">
				<form action="ConsultFines" method="POST">
					<table>
						<tr>
							<td><fieldset>
								<legend>Comment</legend>
									<textarea name="comment" rows="4" cols="50"></textarea>
							</fieldset></td>
						</tr>
						<!-- SELECT WITH OPTION -->
						<tr>
							<td><fieldset>
								<legend>Charged</legend>
								<select name="charged">
									<%if(!Objects.isNull(request.getAttribute("allChargeds"))){
										ArrayList<Charged> allChargeds = (ArrayList<Charged>)request.getAttribute("allChargeds");%>
										<option value="0">- - UNKNOWN - -</option><%
										for(Charged charged : allChargeds){%>
											<option value=<%=charged.getId()%>><%=charged.getFirstname()+ " "+charged.getLastname()%> </option>
										<%}%>
											
								</select>
										<p>Not in the list? Add a charged</p>
										<%@ include file="AddCharged.jsp" %>
									<%}else{%>
										<p>No chargeds were recorded</p>
										<%@ include file="AddCharged.jsp" %>
									<%}%>
							</fieldset></td>
						</tr>
						
						<tr>
							<td><fieldset>
								<legend>Vehicle</legend>
								<select name="vehicle">
								<%if(!Objects.isNull(request.getAttribute("allVehicles"))){
									ArrayList<Vehicle> allVehicles = (ArrayList<Vehicle>)request.getAttribute("allVehicles");
									for(Vehicle vehicle : allVehicles){%>
									<!--  Oui mais dans le cas où il n'y a pas de numéro de plaque ?  -->
										<%if(!Objects.isNull(vehicle.getRegistration())){ %>
											<option value=<%=vehicle.getId()%>><%=vehicle.getType().getName()+"=>"+vehicle.getRegistration().getSerialNumber()%></option>
										<%}else{%>
											<option value=<%=vehicle.getId()%>><%=vehicle.getType().getName()+"=> NO REGISTRATION."%></option>
										<%}%>
									<%}%>
								</select>
									<p>Not in the list ? Add vehicle</p>
									<%@ include file="AddVehicle.jsp" %>
								<%}else{%>
										<p>No vehicles were recorded</p>
										<%@ include file="AddVehicle.jsp" %>
									<%}%>
							</fieldset></td>
						</tr>
						
						<tr>
							<td><fieldset>
							<!--  JAVA SCRIPT POUR CALCULER LE TOTAL ? -->
								<legend>Violations</legend>
								<%if(!Objects.isNull(request.getAttribute("allViolations"))){
									ArrayList<Violation> allViolations = (ArrayList<Violation>)request.getAttribute("allViolations");
									for(Violation violation : allViolations){
										String description = violation.getDescription();
									%>
										<input type="checkbox" name="violation" value=<%=violation.getId()%>/> <abbr title="<%=description%>"><%=violation.getName()%></abbr><br>
									
									<%}%>
									<p> Not in the list? Ask admin to add one</p>
									<%}else{%>
										<p>No violations were recorded, please ask admin for add one</p>
									<%}%>
							</fieldset></td>
						</tr>
						
						<tr>
							 <td colspan="2" align="center">
							 	<button type="submit" name="add" value="fine">Add fine</button>  
							 </td>
						</tr>
					</table>
				</form>
			</span>
		<form action="<%=back%>" method="GET">
			<input type="submit" value=back>
		</form>
		<%}%>
	</body>
	</html>