package be.pirbaert.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.pirbaert.POJOc.Account;
import be.pirbaert.POJOc.Administrator;
import be.pirbaert.POJOc.Chief;
import be.pirbaert.POJOc.Policeman;
import be.pirbaert.POJOc.TaxCollector;

public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignIn() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Réinit la session à chaque passage ici 
		HttpSession session = request.getSession();
		if(!session.isNew()) {
			session.invalidate();
			session = request.getSession();
		}
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/SignIn.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String personelNumber = request.getParameter("personelNumber");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		List <String> errors = new ArrayList<String>();
		if(personelNumber == null) {
			errors.add("Personel number field was null");
		}else {
			if(personelNumber.equals("")) {
				errors.add("Personel number field was empty");
			}
		}
		if(password==null) {
			errors.add("Password field was null");
		}else {
			if(password.equals("")) {
				errors.add("Password field was empty");
			}
		
		}
		if(errors.size()>0) {
			request.setAttribute("previous", request.getRequestURI());
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
		}else {
			Account account = Account.signIn(personelNumber,password);

			if(!Objects.isNull(account)){
				HttpSession session = request.getSession();
				if(!session.isNew()) {
					session.invalidate();
					session = request.getSession();
				}
		//Dispatch au bon endroit selon le type de compte
				session.setAttribute("account", account);
				if(account instanceof Chief) {
					response.sendRedirect("ConsultFines");
				}else if(account instanceof Policeman) {
					response.sendRedirect("ConsultFines");
				}
				if(account instanceof Administrator) {
					response.sendRedirect("MenuAdmin");
				}
				if(account instanceof TaxCollector) {
					response.sendRedirect("ConsultFines");
				}
			}else {
				errors.add("Incorrect account");
				request.setAttribute("previous", request.getRequestURI());
				request.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
			}
		}
	}
}
