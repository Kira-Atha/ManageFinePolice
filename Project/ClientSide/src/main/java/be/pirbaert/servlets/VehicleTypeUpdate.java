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
import be.pirbaert.POJOc.TypeVehicle;

/**
 * Servlet implementation class VehicleTypeUpdate
 */
@WebServlet("/VehicleTypeUpdate")
public class VehicleTypeUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VehicleTypeUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
			TypeVehicle typeVehicle = TypeVehicle.getType(id);
			request.setAttribute("typeVehicle", typeVehicle);
			
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/vehicleType/VehicleTypeUpdate.jsp");			
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		int id = Integer.valueOf(request.getParameter("id"));
		String name = request.getParameter("name");

		
		TypeVehicle typeVehicle = new TypeVehicle(id,name);
		
		request.setAttribute("error", true);
	
		if(typeVehicle.update()) {
			String VehicleTypeManageUrl = String.format("http://%s:%s%s/VehicleTypeManage",
					request.getServerName(), request.getServerPort(),request.getContextPath());
			
			response.sendRedirect(VehicleTypeManageUrl);
		}
		else doGet(request,response);

	}

}
