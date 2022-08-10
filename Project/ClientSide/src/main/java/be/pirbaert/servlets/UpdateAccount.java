package be.pirbaert.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.pirbaert.POJOc.Account;
import be.pirbaert.POJOc.Administrator;
import be.pirbaert.POJOc.Chief;
import be.pirbaert.POJOc.Policeman;
import be.pirbaert.POJOc.TaxCollector;

/**
 * Servlet implementation class UpdateAccount
 */
@WebServlet("/UpdateAccount")
public class UpdateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		Account account = Account.getAccount(id);
		request.setAttribute("account", account);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/admin/UpdateAccount.jsp");			
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Account account = null;
		
		switch(String.valueOf(request.getParameter("type"))) {
			case "Administrator":
				account = new Administrator(Integer.valueOf(request.getParameter("id")),request.getParameter("personelNumber"),request.getParameter("password"));
				break;
			case "Policeman":
				account = new Policeman(Integer.valueOf(request.getParameter("id")),request.getParameter("personelNumber"),request.getParameter("password"));
				break;
			case "Chief":
				account = new Chief(Integer.valueOf(request.getParameter("id")),request.getParameter("personelNumber"),request.getParameter("password"));
				break;
			case "TaxCollector":
				account = new TaxCollector(Integer.valueOf(request.getParameter("id")),request.getParameter("personelNumber"),request.getParameter("password"));
				break;
		}
		
		if(account != null) {
			if(account.update()) {
				String manageAccountUrl = String.format("http://%s:%s%s/ManageAccount",
						request.getServerName(), request.getServerPort(),request.getContextPath());
				
				response.sendRedirect(manageAccountUrl);
			}
			else doGet(request,response);

		}
		else doGet(request,response);

	}

}
