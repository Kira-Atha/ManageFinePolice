package be.pirbaert.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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


public class ViolationMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViolationMenu() {
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
			}else if(session.getAttribute("account") instanceof Chief){
				account = (Chief) session.getAttribute("account");
			}else {
				auth = false;
				response.sendRedirect("SignIn");
			}
		}
		
		if(auth){
			List<Violation> violations = Violation.getAllViolations();
			request.setAttribute("violations", violations);
			
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/violation/ViolationMenu.jsp");
			dispatcher.forward(request, response);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if( request.getParameter("delete") != null) {	
			deleteViolation(request.getParameter("delete"),request,response);
		}
	}
	
	private void deleteViolation(String id , HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Violation violations = new Violation();
		violations.setId(Integer.valueOf(id));
		violations.delete();
		doGet(request,response);
	}
}
