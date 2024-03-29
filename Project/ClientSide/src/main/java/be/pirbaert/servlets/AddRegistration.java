package be.pirbaert.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.pirbaert.POJOc.Account;
import be.pirbaert.POJOc.Chief;
import be.pirbaert.POJOc.Policeman;
import be.pirbaert.POJOc.Registration;

public class AddRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AddRegistration() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		Account account = null;
		boolean auth = true;
		if(session==null) {
			out.println("No session");
		}else {
			account = (Account) session.getAttribute("account");
			if(session.getAttribute("account") instanceof Policeman) {
				account = (Policeman) session.getAttribute("account");
			}else if(session.getAttribute("account") instanceof Chief) {
				account = (Chief) session.getAttribute("account");
			}else {
				auth = false;
				response.sendRedirect("SignIn");
			}
		}
		
		String serialNumber = (String) session.getAttribute("serialNumber");
		List <String> errors = new ArrayList<String>();
		
		if(serialNumber != null) {
			if(serialNumber.equals("")) {
				errors.add("Serial number is empty");
			}
			if(!serialNumber.matches("^[0-2Tt]{1}-[A-Za-z]{3}-[0-9]{3}$")) {
				errors.add("This is not a belgian registration. Format accepted => [0-ABC-012]");
			}
		}else {
			errors.add("Serial number is null");
		}

		if(errors.size() > 0) {
			request.setAttribute("previous", request.getHeader("referer"));
			request.setAttribute("errors", errors);
			if(auth) {
				getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
			}
		}else {
			Registration registration = new Registration(0,serialNumber);
			if(registration.create()) {
				/*
				System.out.println("a la cr�ation du registration : "+registration.getId());
				List<Registration> allRegistrationsWithoutVehicle = (List<Registration>) session.getAttribute("allRegistrationsWithoutVehicle");
				allRegistrationsWithoutVehicle.add(registration);
				session.setAttribute("allRegistrationsWithoutVehicle", allRegistrationsWithoutVehicle);
				*/
				response.sendRedirect("ConsultFines");
			}else {
				errors.add("Registration not created => Already exist");
				request.setAttribute("previous", request.getHeader("referer"));
				request.setAttribute("errors", errors);
				if(auth) {
					getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("SignIn");
	}
}