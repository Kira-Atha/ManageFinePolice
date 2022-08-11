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
		
		if(auth) {
			List<Fine> allFines = Fine.getAllFines();
			List<Fine> allFinesAccepted = new ArrayList<Fine>();
			for (Fine fine : allFines) {
				if(fine.isValidated()) {
					allFinesAccepted.add(fine);
				}
			}
			List<Violation> allViolations = Violation.getAllViolations();
			List<Charged> allChargeds = Charged.getAllChargeds();
			List<Vehicle> allVehicles = Vehicle.getAllVehicles();
			List<Registration> allRegistrations = Registration.getAllRegistrations();
			List<Registration> allRegistrationsWithoutVehicle = new ArrayList<Registration>();
			List<Registration> allRegistrationsInVehicle = new ArrayList<Registration>();
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
			List<TypeVehicle> allTypes = TypeVehicle.getAllTypes();
			
			
			request.setAttribute("allFines", allFines);
			request.setAttribute("allViolations", allViolations);
			request.setAttribute("allChargeds", allChargeds);
			request.setAttribute("allVehicles", allVehicles);
			request.setAttribute("allRegistrationsWithoutVehicle", allRegistrationsWithoutVehicle);
			request.setAttribute("allTypes", allTypes);	
			request.setAttribute("allFinesAccepted", allFinesAccepted);
			session.setAttribute("allViolations", allViolations);
			
			request.setAttribute("previous", request.getHeader("referer"));
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/ConsultFines.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}