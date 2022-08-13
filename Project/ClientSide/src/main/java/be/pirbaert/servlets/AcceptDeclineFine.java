package be.pirbaert.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.pirbaert.POJOc.Account;
import be.pirbaert.POJOc.Chief;
import be.pirbaert.POJOc.Fine;
import be.pirbaert.POJOc.Policeman;
import be.pirbaert.POJOc.TaxCollector;

public class AcceptDeclineFine extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AcceptDeclineFine() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("SignIn");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		PrintWriter out = response.getWriter();
		Account account = null;
		if(session==null) {
			out.println("No session");
		}else {
			account = (Account) session.getAttribute("account");
			if(session.getAttribute("account") instanceof Chief) {
				account = (Chief) session.getAttribute("account");
			}else {
				response.sendRedirect("SignIn");
			}
		}
		List <String> errors = new ArrayList<String>();
		String choice = null;
		String id_fine = null;
		Fine fine = null;
		Fine oldFine = null;
		if(!Objects.isNull(request.getParameter("accept"))) {
			choice ="accept";
			id_fine = request.getParameter("accept");
			fine = Fine.getFine(Integer.parseInt(id_fine));
			oldFine = fine;
			fine.setValidated(true);
		}else if(!Objects.isNull(request.getParameter("decline"))) {
			choice = "decline";
			id_fine = request.getParameter("decline");
			fine = Fine.getFine(Integer.parseInt(id_fine));
		}
		
		if(((Chief)account).acceptDeclineFine(choice, fine)) {
			List<Fine> finesToChief = (List<Fine>) session.getAttribute("finesToChief");
			if(choice.equals("accept")) {
				finesToChief.remove(oldFine);
				finesToChief.add(fine);
			}else if(choice.equals("decline")) {
				finesToChief.remove(fine);
			}
			session.setAttribute("finesToChief", finesToChief);
			response.sendRedirect("ConsultFines");
		}else {
			errors.add("Can't accept/decline this fine");
			request.setAttribute("previous", request.getHeader("referer"));
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
		}
	}
}