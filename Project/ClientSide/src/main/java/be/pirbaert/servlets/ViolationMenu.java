package be.pirbaert.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.pirbaert.POJOc.Violation;



/**
 * Servlet implementation class ViolationMenu
 */
@WebServlet("/ViolationMenu")
public class ViolationMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViolationMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Violation> violations = Violation.getAllViolations();
		request.setAttribute("violations", violations);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/violation/ViolationMenu.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
