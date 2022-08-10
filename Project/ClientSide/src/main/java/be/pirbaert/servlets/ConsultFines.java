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
		if(session==null) {
			out.println("No session");
		}else {
			account = (Account) session.getAttribute("account");
			if(session.getAttribute("account") instanceof Chief) {
				account = (Chief) session.getAttribute("account");
				if(account != null) {
					System.out.println("C'est un chief");
					System.out.println("Chief "+account.getPersonnelNumber()+" "+account.getClass().getSimpleName());
				}
			}else if(session.getAttribute("account") instanceof Policeman) {
				account = (Policeman) session.getAttribute("account");
				if(account != null) {
					System.out.println("Police "+account.getPersonnelNumber()+" "+account.getClass().getSimpleName());
				}
			}
		}
		
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
		List<TypeVehicle> allTypes = TypeVehicle.getAllTypes();
		
		request.setAttribute("allFines", allFines);
		request.setAttribute("allViolations", allViolations);
		request.setAttribute("allChargeds", allChargeds);
		request.setAttribute("allVehicles", allVehicles);
		request.setAttribute("allRegistrations", allRegistrations);
		request.setAttribute("allTypes", allTypes);	
		
		if(!Objects.isNull(account)) {
			request.setAttribute("previous", request.getHeader("referer"));
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/ConsultFines.jsp");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect("SignIn");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		Account account = null;
		if(session==null) {
			out.println("No session");
		}else {
			account = (Account) session.getAttribute("account");
			if(session.getAttribute("account") instanceof Chief) {
				account = (Chief) session.getAttribute("account");
			}else if(session.getAttribute("account") instanceof Policeman) {
				account = (Policeman) session.getAttribute("account");
			}
		}
		//out.print(account);
		
	// CHIEF valider fine =>
		if(!Objects.isNull(request.getParameter("fine_id"))) {
			Fine fine = null;
			fine = Fine.getFine(Integer.parseInt(request.getParameter("fine_id")));
			System.out.println("Je veux valider");
			//  add accept_fine(Fine fine) in pojo chief)
			//account.accept_fine(fine); => UPDATE
		}
		List <String> errors = new ArrayList<String>();
		
		String choice = request.getParameter("add");
		if(!Objects.isNull(choice)) {
			switch(choice) {
			
			case "charged":
				// OK
				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname");
				String address = request.getParameter("address");
				session.setAttribute("firstname",firstname);
				session.setAttribute("lastname",lastname);
				session.setAttribute("address",address);
				response.sendRedirect("AddCharged");
				break;
			case "fine":
				String commentary = request.getParameter("comment");
				Charged charged = null;
				Vehicle vehicle = null;
				List <Violation> violations = null;
				if(Integer.parseInt(request.getParameter("charged"))!=0) {
					charged = Charged.getCharged(Integer.parseInt(request.getParameter("charged")));
				}
				// Cas impossible, mais bon
				if(request.getParameter("vehicle") == null) {
					errors.add("Vehicle can't be null");
				}else {
					vehicle = Vehicle.getVehicle(Integer.parseInt(request.getParameter("vehicle")));
				}
				if(Objects.isNull(request.getParameterValues("violation"))) {
					errors.add("The fine must contain at least one violation");
				}else {
					String[] idViolations = request.getParameterValues("violation");
					violations = new ArrayList<Violation>();
					for(int i=0;i<idViolations.length;i++) {
						violations.add(Violation.getViolation(Integer.parseInt(removeLastCharOptional(idViolations[i]))));
					}
				}
				
				if(errors.size()>0) {
					request.setAttribute("previous", request.getHeader("referer"));
					request.setAttribute("errors", errors);
					getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
				}else {
					Date in = new Date();
					LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(),ZoneId.systemDefault());
					Date localToDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
					
					Fine fine = new Fine(0,violations,(Policeman)account,vehicle,commentary,localToDate,charged);
					
					if(fine.create()) {
						System.out.println("A été créé");
					}else {
						errors.add("Can't create fine");
						request.setAttribute("previous", request.getHeader("referer"));
						request.setAttribute("errors", errors);
						getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
					}
				}
				break;
			}
		}
	}
	public static String removeLastCharOptional(String s) {
	    return Optional.ofNullable(s)
	      .filter(str -> str.length() != 0)
	      .map(str -> str.substring(0, str.length() - 1))
	      .orElse(s);
	    }
}