package be.pirbaert.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.pirbaert.POJOc.Charged;
import be.pirbaert.POJOc.Fine;

public class SendLetter extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SendLetter() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("SignIn");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <String> errors = new ArrayList<String>();
		Fine fine = Fine.getFine(Integer.parseInt(request.getParameter("sendLetter")));
		fine.setLetterSent(true);
		System.out.println("DANS LA SERVLET => "+fine.isLetterSent());
		if(fine.update()) {
			response.sendRedirect("ConsultFines");
		}else {
			errors.add("Can't send letter...");
			request.setAttribute("previous", request.getHeader("referer"));
			request.setAttribute("errors", errors);
		}
	}
}