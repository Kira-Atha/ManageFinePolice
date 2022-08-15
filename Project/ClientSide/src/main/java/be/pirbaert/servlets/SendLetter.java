package be.pirbaert.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.pirbaert.POJOc.Account;
import be.pirbaert.POJOc.Charged;
import be.pirbaert.POJOc.Chief;
import be.pirbaert.POJOc.Fine;
import be.pirbaert.POJOc.Policeman;
import be.pirbaert.POJOc.TaxCollector;

public class SendLetter extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SendLetter() {
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
			if(session.getAttribute("account") instanceof TaxCollector) {
				account = (TaxCollector) session.getAttribute("account");
			}else {
				// Les autres n'ont rien à faire ici
				response.sendRedirect("SignIn");
			}
		}
		List <String> errors = new ArrayList<String>();
		Fine fine = Fine.getFine(Integer.parseInt(request.getParameter("sendLetter")));
		Fine oldFine = fine;
		fine.setLetterSent(true);
		if(fine.update()) {
			List<Fine> allFinesAccepted = (List<Fine>) session.getAttribute("allFinesAccepted");
			allFinesAccepted.remove(oldFine);
			allFinesAccepted.add(fine);
			session.setAttribute("allFinesAccepted",allFinesAccepted);
			response.sendRedirect("ConsultFines");
		}else {
			errors.add("Can't send letter...");
			request.setAttribute("previous", request.getHeader("referer"));
			request.setAttribute("errors", errors);
		}
	}
}