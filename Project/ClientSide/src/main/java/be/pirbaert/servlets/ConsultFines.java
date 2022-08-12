package be.pirbaert.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.pirbaert.POJOc.Account;
import be.pirbaert.POJOc.Administrator;
import be.pirbaert.POJOc.Charged;
import be.pirbaert.POJOc.Chief;
import be.pirbaert.POJOc.Fine;
import be.pirbaert.POJOc.Policeman;
import be.pirbaert.POJOc.Registration;
import be.pirbaert.POJOc.TaxCollector;
import be.pirbaert.POJOc.TypeVehicle;
import be.pirbaert.POJOc.Vehicle;
import be.pirbaert.POJOc.Violation;

public class ConsultFines extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConsultFines() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		Account account = null;
		boolean auth = true;
		if(session==null) {
			out.println("No session");
		}else {
			account = (Account) session.getAttribute("account");
			if(session.getAttribute("account") instanceof Chief) {
				account = (Chief) session.getAttribute("account");
			}else if(session.getAttribute("account") instanceof Policeman) {
				account = (Policeman) session.getAttribute("account");
			}else if(session.getAttribute("account") instanceof TaxCollector) {
				account = (TaxCollector) session.getAttribute("account");
			}else {
				auth = false;
				response.sendRedirect("SignIn");
			}
		}
		List<Violation> allViolations = null;
		List<Charged> allChargeds = null;
		List<Vehicle> allVehicles = null;
		List<Registration> allRegistrations = null;
		List<TypeVehicle> allTypes = null;
		List<Fine> finesToChief = null;
		List<Registration> allRegistrationsWithoutVehicle = null;
		List<Registration> allRegistrationsInVehicle;
		List<Fine> allFines;
		List<Fine> allFinesAccepted = null;
		if(auth) {
			// Si allViolations est à null dans la session, premier passage. Si pas à null, cela veut dire que ces listes contiennent déjà les infos
			// ( éviter les passages inutiles en db )
			if(Objects.isNull(session.getAttribute("allViolations"))) {
				allFines = Fine.getAllFines();
				allViolations = Violation.getAllViolations();
				allChargeds = Charged.getAllChargeds();
				allVehicles = Vehicle.getAllVehicles();
				allRegistrations = Registration.getAllRegistrations();
				allTypes = TypeVehicle.getAllTypes();
			// Pour tax collector	
				if(account instanceof TaxCollector) {
					allFinesAccepted = new ArrayList<Fine>();
					for (Fine fine : allFines) {
						if(fine.isValidated()) {
							allFinesAccepted.add(fine);
						}
					}
					session.setAttribute("allFinesAccepted", allFinesAccepted);
				}

			//
				allRegistrationsWithoutVehicle = new ArrayList<Registration>();
				allRegistrationsInVehicle = new ArrayList<Registration>();
				for(Vehicle vehicle  : allVehicles) {
					allRegistrationsInVehicle.add(vehicle.getRegistration());
				}
				
				for(int i = 0; i < allRegistrations.size() ; i++) {
					for(int j=0; j<allVehicles.size();j++) {
						if(!allRegistrationsWithoutVehicle.contains(allRegistrations.get(i)) && !allRegistrationsInVehicle.contains(allRegistrations.get(i))) {
							allRegistrationsWithoutVehicle.add(allRegistrations.get(i));
						}
					}
				}
			// Pour chief
			if(account instanceof Chief) {
				finesToChief = new ArrayList<Fine>();
			// Les contraventions des policiers dont account est le chef	
				for(Policeman police :((Chief)account).getSubordinates()) {
					for(Fine fine : police.getFines()) {
						finesToChief.add(fine);
					}
				}
				finesToChief.addAll(((Chief)account).getFines());
				session.setAttribute("finesToChief", finesToChief);
			}
				session.setAttribute("allViolations", allViolations);
				session.setAttribute("allChargeds", allChargeds);
				session.setAttribute("allVehicles", allVehicles);
				session.setAttribute("allRegistrationsWithoutVehicle", allRegistrationsWithoutVehicle);
				session.setAttribute("allTypes", allTypes);	
				session.setAttribute("allViolations", allViolations);
			}

			request.setAttribute("previous", request.getHeader("referer"));
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/ConsultFines.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}