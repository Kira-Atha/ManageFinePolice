package be.pirbaert.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.pirbaert.POJOc.Violation;


/**
 * Servlet implementation class ViolationUpdate
 */
@WebServlet("/ViolationUpdate")
public class ViolationUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViolationUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		Violation violation = Violation.getViolation(id);
		request.setAttribute("violation", violation);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/violation/ViolationUpdate.jsp");			
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
