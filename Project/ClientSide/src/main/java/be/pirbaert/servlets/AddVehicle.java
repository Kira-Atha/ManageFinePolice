package be.pirbaert.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
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
import be.pirbaert.POJOc.TypeVehicle;
import be.pirbaert.POJOc.Vehicle;

public class AddVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddVehicle() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		Account account = null;
		if(session==null) {
			out.println("No session");
		}else {
			account = (Account) session.getAttribute("account");
			if(session.getAttribute("account") instanceof Policeman) {
				account = (Policeman) session.getAttribute("account");
			}else if(session.getAttribute("account") instanceof Chief) {
				account = (Chief) session.getAttribute("account");
			}
		}
		
		if(!Objects.isNull(account)) {
			request.setAttribute("previous", request.getHeader("referer"));
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/AddVehicle.jsp");
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
			if(session.getAttribute("account") instanceof Policeman) {
				account = (Policeman) session.getAttribute("account");
			}else if(session.getAttribute("account") instanceof Chief) {
				account = (Chief) session.getAttribute("account");
			}
		}

		List<String> errors = new ArrayList<String>();

		String choice = request.getParameter("add");
		switch(choice) {
			case "registration":
				String serialNumber = request.getParameter("serialNumber");
				session.setAttribute("serialNumber", serialNumber);
				response.sendRedirect("AddRegistration");
				break;
			case "vehicle":
				Registration registration = null;
				if(Integer.parseInt(request.getParameter("registration")) != 0) {
					registration = Registration.getRegistration(Integer.parseInt(request.getParameter("registration")));
				}
				TypeVehicle type = TypeVehicle.getType(Integer.parseInt(request.getParameter("type")));
				Vehicle vehicle = new Vehicle(0,registration,type);
				if(vehicle.create()) {
					List<Vehicle> allVehicles = (List<Vehicle>) session.getAttribute("allVehicles");
					allVehicles.add(vehicle);
					session.setAttribute("allVehicles", allVehicles);
					List<Registration> allRegistrationsWithoutVehicle = (List<Registration>) session.getAttribute("allRegistrationsWithoutVehicle");
					allRegistrationsWithoutVehicle.remove(registration);
					session.setAttribute("allRegistrationsWithoutVehicle", allRegistrationsWithoutVehicle);
					response.sendRedirect("ConsultFines");
				}else {
					errors.add("Vehicle not created => Already exist");
					request.setAttribute("previous", request.getHeader("referer"));
					request.setAttribute("errors", errors);
					getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
				}
				break;
		}
	}
}
