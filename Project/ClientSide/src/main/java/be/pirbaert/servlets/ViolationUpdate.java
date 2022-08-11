package be.pirbaert.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.pirbaert.POJOc.Account;
import be.pirbaert.POJOc.Administrator;
import be.pirbaert.POJOc.Chief;
import be.pirbaert.POJOc.Violation;


public class ViolationUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViolationUpdate() {
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
			if(session.getAttribute("account") instanceof Administrator) {
				account = (Administrator) session.getAttribute("account");
			}else if(session.getAttribute("account") instanceof Chief) {
				account = (Chief) session.getAttribute("account");
			}else {
				auth = false;
				response.sendRedirect("SignIn");
			}
		}
		if(auth) {
			int id = Integer.valueOf(request.getParameter("id"));
			Violation violation = Violation.getViolation(id);
			request.setAttribute("violation", violation);
			
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/violation/ViolationUpdate.jsp");			
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");
		Float price = Float.valueOf(request.getParameter("price"));
		String description = request.getParameter("description");

		
		Violation violation = new Violation(id,name,description,price);
		
		request.setAttribute("error", true);
	
		if(violation.update()) {
			String violationMenuUrl = String.format("http://%s:%s%s/ViolationMenu",
					request.getServerName(), request.getServerPort(),request.getContextPath());
			
			response.sendRedirect(violationMenuUrl);
		}
		else doGet(request,response);

	}
}
