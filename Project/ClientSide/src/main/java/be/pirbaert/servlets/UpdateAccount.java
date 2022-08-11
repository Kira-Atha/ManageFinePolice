package be.pirbaert.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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
import be.pirbaert.POJOc.TaxCollector;



public class UpdateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UpdateAccount() {
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
			}else {
				auth = false;
				response.sendRedirect("SignIn");
			}
		}
		if(auth) {
			int id = Integer.valueOf(request.getParameter("id"));
			Account accountToUpdate = Account.getAccount(id);
			request.setAttribute("account", accountToUpdate);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/admin/UpdateAccount.jsp");			
			dispatcher.forward(request, response);
		}
	}

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
