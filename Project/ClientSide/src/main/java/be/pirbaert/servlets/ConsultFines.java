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
import be.pirbaert.POJOc.Fine;
import be.pirbaert.POJOc.Policeman;

/**
 * Servlet implementation class ConsultFines
 */
@WebServlet("/ConsultFines")
public class ConsultFines extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ConsultFines() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		
		if(session==null) {
			out.println("No session");
		}else {
			System.out.println("JE SUIS SUR LA PAGE");
			if(session.getAttribute("account") instanceof Policeman) {
				Policeman account = (Policeman) session.getAttribute("account");
				if(account != null) {
					out.println(account.getPersonnelNumber()+" "+account.getClass().getSimpleName());
				}
			}else if(session.getAttribute("account") instanceof Chief) {
				Chief account = (Chief) session.getAttribute("account");
				if(account != null) {
					out.println(account.getPersonnelNumber()+" "+account.getClass().getSimpleName());
				}
			}
		}
		
		List<Fine> allFines = Fine.getAllFines();
		for(Fine fine : allFines) {
			System.out.println(fine);
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/ConsultFines.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
