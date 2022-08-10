package be.pirbaert.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.pirbaert.POJOc.Charged;

public class SendLetter extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SendLetter() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Charged charged = Charged.getCharged(Integer.parseInt(request.getParameter("sendLetter")));
		PrintWriter out = response.getWriter();
		out.print("Letter send to "+charged.getLastname() +" "+charged.getFirstname());
	}
}
