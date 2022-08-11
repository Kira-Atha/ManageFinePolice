package be.pirbaert.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.pirbaert.POJOc.Fine;

public class DownstreamFine extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DownstreamFine() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("SignIn");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id_fine = null;
		List<String> errors = new ArrayList<String>();
/*If accepted => boolean validated = true */
		if(!Objects.isNull(request.getParameter("accept"))) {
			id_fine = request.getParameter("accept");
			Fine fineToUpdate = Fine.getFine(Integer.parseInt(id_fine));
			fineToUpdate.setValidated(true);
			if(fineToUpdate.update()) {
				System.out.println("Le fine a été accepté ( msg temp)");
				response.sendRedirect("ConsultFines");
			}else {
				errors.add("Can't accepted this fine");
				request.setAttribute("previous", request.getHeader("referer"));
				request.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
			}
		}
/* If decline => Delete to db */
		if(!Objects.isNull(request.getParameter("decline"))) {
			id_fine = request.getParameter("decline");
			Fine fineToDelete = Fine.getFine(Integer.parseInt(id_fine));
			if(fineToDelete.delete()) {
				System.out.println("Le fine a été décliné ( msg temp)");
				response.sendRedirect("ConsultFines");
			}else {
				errors.add("Can't decline this fine");
				request.setAttribute("previous", request.getHeader("referer"));
				request.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
			}
			
		}
	}
}