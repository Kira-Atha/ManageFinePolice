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
import be.pirbaert.POJOc.Policeman;

/**
 * Servlet implementation class SubordonateAdd
 */
@WebServlet("/SubordinateAdd")
public class SubordinateAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubordinateAdd() {
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
			List<Chief> chiefs = Chief.getAllChief();
			List<Policeman> policemans = Policeman.getAllPoliceman();
						
			request.setAttribute("chiefs", chiefs);
			request.setAttribute("policemans", policemans);
			
			
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/admin/SubordinateAdd.jsp");			
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		int id_policeman = Integer.valueOf(request.getParameter("subordinate"));
		int id_chief = Integer.valueOf(request.getParameter("chief"));
		
		Policeman policeman = (Policeman) Policeman.getAccount(id_policeman);
		
		request.setAttribute("error", true);
		
		if(policeman.saveChief(id_chief)) {
			
			String menuUrl = String.format("http://%s:%s%s/MenuAdmin",
					request.getServerName(), request.getServerPort(),request.getContextPath());
			
			response.sendRedirect(menuUrl);
		}
		else doGet(request,response);	}

}
