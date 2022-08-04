<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="be.pirbaert.POJOc.Fine"%>
<%@page import="be.pirbaert.POJOc.Chief"%>
<%@page import="be.pirbaert.POJOc.Violation"%>
<%@page import="be.pirbaert.POJOc.Policeman"%>
<%@page import="be.pirbaert.POJOc.Vehicle"%>
<%@page import="be.pirbaert.POJOc.Charged"%>
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
	<!--  CONSULT + ACCEPT -->
		<%if(request.getAttribute("account") instanceof Chief){
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
										<%=violation.getName() %>
									<%}%>
							</td>
							<td><%= fine.getCharged().getFirstname() %></td>
							<td><%= fine.getVehicle().getType().getName() %></td>
							<td>POLICEMAN BIDON</td>
						<!-- QUAND POLICEMAN SERA RECUPERE l'afficher lui  fine.getPoliceman().getPersonnelNumber() -->
							<td><%= fine.getTotalPrice() %></td>
							<td>
								<form action="ConsultFines" method="POST">
								<!-- Mettre l'objet en session ? Envoyer l'objet à la servlet ou l'id  -->
									<input type="hidden" name="fine_id" value=<%=fine_id%>>
									<input type="submit" name="submit" id="submit" value="Accept"/>
								</form>
							</td>
						</tr>
					<%}%>
					</table>
				<%}else{%>
					<p>No fines were recorded</p>
				<%}%>
			<%}%>
		<!--  ADD  -->
			<%if(!Objects.isNull(request.getAttribute("allViolations"))){
				ArrayList<Violation> allViolations = (ArrayList<Violation>)request.getAttribute("allViolations");%>
			<%}%>
			
			<%if(!Objects.isNull(request.getAttribute("allVehicles"))){
				ArrayList<Vehicle> allVehicles = (ArrayList<Vehicle>)request.getAttribute("allVehicles");%>
				
			<%}%>
		</span>
		
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
									ArrayList<Charged> allChargeds = (ArrayList<Charged>)request.getAttribute("allChargeds");
									
									for(Charged charged : allChargeds){%>
										<option value=<%=charged%>><%=charged.getFirstname()+ " "+charged.getLastname()%> </option>
									<%}%>
							</select>
							<button type="submit" name="add" value="charged">Add charged</button> 
								<%}else{%>
									<p>No chargeds were recorded</p>
						<!-- PERMETTRE L'AJOUT -->
									<button type="submit" name="add" value="charged">Add charged</button> 
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
										<option value=<%=vehicle%>><%=vehicle.getType().getName()+"=>"+vehicle.getRegistration().getSerialNumber()%></option>
									<%}%>
							</select>
							<button type="submit" name="add" value="vehicle">Add Vehicle</button> 
							<%}else{%>
									<p>No vehicles were recorded</p>
						<!-- PERMETTRE L'AJOUT -->
									<input type="submit" value="Add vehicle"/> 
								<%}%>
						</fieldset></td>
					</tr>
					
					<tr>
						<td><fieldset>
							<legend>Violations</legend>
							<%if(!Objects.isNull(request.getAttribute("allViolations"))){
								ArrayList<Violation> allViolations = (ArrayList<Violation>)request.getAttribute("allViolations");
								for(Violation violation : allViolations){
									String description = violation.getDescription();
								%>
									<input type="checkbox" name="violation" value=<%=violation%>/> <abbr title="<%=description%>"><%=violation.getName()%></abbr><br>
								<%}
								}else{%>
									<p>No violations were recorded, please ask admin to add one</p>
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
	</body>
	</html>