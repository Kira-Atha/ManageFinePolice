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
import be.pirbaert.POJOc.Charged;
import be.pirbaert.POJOc.Chief;
import be.pirbaert.POJOc.Policeman;

public class AddCharged extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AddCharged() {
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
		
		String firstname = (String) session.getAttribute("firstname");
		String lastname = (String) session.getAttribute("lastname");
		String address = (String) session.getAttribute("address");
		
		List <String> errors = new ArrayList<String>();
		if(firstname == null) {
			errors.add("Firstname field was null");
		}else {
			if(firstname.equals("")) {
				errors.add("Firstname field was empty");
			}
			if(!firstname.matches("^[a-zA-Z]{4,}$")) {
				errors.add("Firstname must contains min 4 characters and don't contain special characters and can't contain number");
			}
		}
		if(lastname == null) {
			errors.add("Lastname field was null");
		}else {
			if(lastname.equals("")) {
				errors.add("Lastname field was empty");
			}
			if(!lastname.matches("^[a-zA-Z]{4,}$")) {
				errors.add("Lastname must contains min 4 characters and don't contain special characters and can't contain number");
			}
		}
		if(address == null) {
			errors.add("Address field was null");
		}else {
			if(address.equals("")) {
				errors.add("Address field was empty");
			}
		}
		if(errors.size()>0) {
			request.setAttribute("previous", request.getHeader("referer"));
			request.setAttribute("errors", errors);
			if(auth) {
				getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
			}
		}else {
			Charged charged = new Charged(0,firstname,lastname,address);
			if(charged.create()) {
				response.sendRedirect("ConsultFines");
			}else {
				errors.add("Charged not created");
				request.setAttribute("previous", request.getHeader("referer"));
				request.setAttribute("errors", errors);
				if(auth) {
					getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
}
