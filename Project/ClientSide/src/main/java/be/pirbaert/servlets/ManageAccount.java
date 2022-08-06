package be.pirbaert.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.pirbaert.POJOc.Account;

/**
 * Servlet implementation class ManageAccount
 */
@WebServlet("/ManageAccount")
public class ManageAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Account> accounts = Account.getAllAccounts();
		request.setAttribute("accounts", accounts);
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/admin/ManageAccount.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if( request.getParameter("delete") != null) {
			
			deleteAccount(request.getParameter("delete"),request,response);
		}
		
		
	}
	
	private void deleteAccount(String id , HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account account = new Account();
		account.setId(Integer.valueOf(id));
		account.delete();
		doGet(request,response);
	}

}
