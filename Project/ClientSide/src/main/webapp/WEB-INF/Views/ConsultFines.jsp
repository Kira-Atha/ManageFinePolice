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
			/* */
			.span_consult_fines{	
			}
			.span_add_fine{
			}
			.span_add_charged{
			}
			.span_add_vehicle{
			}
			.span_add_violations{
			}
			th,td{
			    margin:auto;
			    border:1px solid black;
			    width:auto;
			    padding : 5px;
			}
			th{
				background-color : rgb(172, 134, 224);
				font-weight : bold;
			}
			table{
				border-collapse : collapse;
			}
			#btn_logout{
				position:absolute;
				top:0%;
				right:0%;
			}
			body{
			    background-color: rgba(129, 189, 236, 0.46);
			    font-family: Georgia,"Times New Roman",sans-serif;
			    font-size: 15px;
			}
		</style>
	</head>
	<body>
		<form action="SignIn" class="form" method="GET">
			<input type="submit" class="btn" id="btn_logout" value="Logout"/>
		</form>
<!--  CONSULT FINE POUR LES CONTRAVENTION d'UN CHEF OU D4UN MEMBRE DE SON EQUIPE -->
		<%if(session.getAttribute("account") instanceof Chief){%>
			<form action="ViolationMenu" method="GET">
				<button type="submit" class="btn" id="btn_updateViolationChief">Update violation</button> 
			</form>
			<%if(!Objects.isNull(session.getAttribute("finesToChief")) && ((ArrayList<Fine>)session.getAttribute("finesToChief")).size() >0){
				ArrayList<Fine> finesToChief = (ArrayList<Fine>)session.getAttribute("finesToChief");%>
				<span class="span_consult_fines">
				<p>Consult fines</p>
					<table>
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
						
					<%for(Fine fine : finesToChief){
						
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
								<td><%= fine.getCharged().getFirstname() %> <%= fine.getCharged().getLastname() %></td>
							<%}%>
							<% if(!Objects.isNull(fine.getVehicle().getRegistration())){%>
								<td><%= fine.getVehicle().getType().getName()%> => <%=fine.getVehicle().getRegistration().getSerialNumber()%></td>
							<%}else{%>
								<td><%= fine.getVehicle().getType().getName()%> => NO REGISTRATION</td>
							<%}%>
							<td><%= fine.getPoliceman().getPersonnelNumber()%></td>
							<td><%= fine.getTotalPrice() %></td>
							<td><%if(!fine.isValidated()){ %>
								<form action="AcceptDeclineFine" method="POST">
									<button type="submit" class="btn" name="accept" value=<%=fine_id %>>Accept</button> 
									<button type="submit" class="btn" name="decline" value=<%=fine_id %>>Decline</button> 
								</form>
								<%}else{%>
									<p> Already accepted </p>
								<%}%>
							</td>
						</tr>
					<%}%>
					</table>
				<%}else{%>
					<p>No fines were registered by your team</p>
				<%}%>
					</span>
<!-- TAX COLLECTOR -->				
			<%}else if(session.getAttribute("account") instanceof TaxCollector){
				ArrayList<Fine> allFinesAccepted = (ArrayList<Fine>)session.getAttribute("allFinesAccepted");
				if(allFinesAccepted.size()>0){%>
				<span class="span_consult_fines">
				<p>Consult fines</p>
					<table>
						<tr>
							<th>Date</th>
							<th>Comment</th>
							<th>Violations</th>
							<th>Charged</th>
							<th>Vehicle</th>
							<th>Policeman</th>
							<th>TotalPrice</th>
						</tr>
						
					<%for(Fine fine : allFinesAccepted){
						String fine_id = String.valueOf(fine.getId());%>	
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
								<td><%= fine.getCharged().getFirstname() %> <%= fine.getCharged().getLastname() %></td>
							<%}%>
							<td><%= fine.getVehicle().getType().getName() %></td>
							<td><%= fine.getPoliceman().getPersonnelNumber()%></td>
							<td><%= fine.getTotalPrice() %></td>
							<td>
							<%if(!Objects.isNull(fine.getCharged())){ 
								if(!fine.isLetterSent()){%>
									<form action="SendLetter" method="POST">
										<button type="submit" class="btn" id="btn_sendLetter" name="sendLetter" value=<%=fine.getId() %>>send letter</button> 
									</form>
							<%	}else{%>
									<p>Letter already sent</p>
								<%}
							}else{%>
								<p>Impossible to send a letter to "UNKNOWN"</p>
							<%}%>
							</td>
						</tr>
					<%}%>
					</table>
				<%}else{%>
					<p>No accepted fines were recorded</p>
				<%}%>
					</span>
			<%}%>
<!--  ADD  -->
			<%if(!Objects.isNull(session.getAttribute("allViolations"))){
				ArrayList<Violation> allViolations = (ArrayList<Violation>)session.getAttribute("allViolations");%>
			<%}%>
			
			<%if(!Objects.isNull(session.getAttribute("allVehicles"))){
				ArrayList<Vehicle> allVehicles = (ArrayList<Vehicle>)session.getAttribute("allVehicles");%>
				
			<%}%>
		
		
		<%if(session.getAttribute("account") instanceof Policeman){%>
			<p>ADD FINE</p>
			<span class="span_add_fine">
				<form action="AddFine" method="POST">
					<table>
						<tr>
							<td><fieldset>
								<legend>Comment</legend>
									<textarea name="comment" rows="4" cols="50"></textarea>
							</fieldset></td>
						</tr>
						<tr>
							<td>
							<span class="span_add_charged">
							<fieldset>
								<legend>Charged</legend>
								<select name="charged">
									<%if(!Objects.isNull(session.getAttribute("allChargeds")) && ((ArrayList<Charged>)session.getAttribute("allChargeds")).size() >0){
										ArrayList<Charged> allChargeds = (ArrayList<Charged>)session.getAttribute("allChargeds");%>
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
							</fieldset>
							</span>
							</td>
						</tr>
						
						<tr>
							<td>
							<span class="span_add_vehicle">
							<fieldset>
								<legend>Vehicle</legend>
								<select name="vehicle">
								<%if(!Objects.isNull(session.getAttribute("allVehicles")) && ((ArrayList<Vehicle>)session.getAttribute("allVehicles")).size() >0){
									ArrayList<Vehicle> allVehicles = (ArrayList<Vehicle>)session.getAttribute("allVehicles");
									for(Vehicle vehicle : allVehicles){%>
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
							</fieldset>
							</span>
							</td>
						</tr>
						
						<tr>
							<td>
							<span class="span_add_violations">
							<fieldset>
								<legend>Violations</legend>
								<%if(!Objects.isNull(session.getAttribute("allViolations")) && ((ArrayList<Violation>)session.getAttribute("allViolations")).size()>0){
									ArrayList<Violation> allViolations = (ArrayList<Violation>)session.getAttribute("allViolations");
									for(Violation violation : allViolations){
										if(!violation.getName().equals("Insurance default")){
										String description = violation.getDescription();
									%>
										<input type="checkbox" name="violation" value=<%=violation.getId()%>/> <abbr title="<%=description%>"><%=violation.getName()%></abbr><br>
									
									<%}
									}%>
									<p> Not in the list? Ask admin to add one</p>
									<%}else{%>
										<p>No violations were recorded, please ask admin for add one</p>
									<%}%>
							</fieldset>
							</span>
							</td>
						</tr>
						
						<tr>
							 <td colspan="2" align="center">
							 	<button class="btn" type="submit" name="add" value="fine">Add fine</button>  
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