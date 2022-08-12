package be.pirbaert.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import be.pirbaert.POJOc.Account;
import be.pirbaert.POJOc.Charged;
import be.pirbaert.POJOc.Chief;
import be.pirbaert.POJOc.Fine;
import be.pirbaert.POJOc.Policeman;
import be.pirbaert.POJOc.Registration;
import be.pirbaert.POJOc.TaxCollector;
import be.pirbaert.POJOc.Vehicle;
import be.pirbaert.POJOc.Violation;

public class AddFine extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AddFine() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Impossible de passer ici autrement que par POST, si GET => User tente de passer par lien
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
			}else if(session.getAttribute("account") instanceof Policeman) {
				account = (Policeman) session.getAttribute("account");
			}else if(session.getAttribute("account") instanceof TaxCollector) {
				// Tax collector n'a rien à faire ici
				response.sendRedirect("SignIn");
			}
		}
		
		String choice = request.getParameter("add");
		if(!Objects.isNull(choice) && choice.equals("charged")) {
				String firstname = request.getParameter("firstname");
				String lastname = request.getParameter("lastname");
				String address = request.getParameter("address");
				session.setAttribute("firstname",firstname);
				session.setAttribute("lastname",lastname);
				session.setAttribute("address",address);
				response.sendRedirect("AddCharged");
		}else if(!Objects.isNull(choice) && choice.equals("fine")) {
			List <String> errors = new ArrayList<String>();
			String commentary;
			
			if(((String)request.getParameter("comment")).length() == 0) {
				commentary ="N/C";
			}else {
				commentary = request.getParameter("comment");
			}
			Charged charged = null;
			Vehicle vehicle = null;
			List <Violation> violations = null;
			if(Integer.parseInt(request.getParameter("charged"))!=0) {
				charged = Charged.getCharged(Integer.parseInt(request.getParameter("charged")));
			}
			// Cas impossible
			if(request.getParameter("vehicle") == null) {
				errors.add("Vehicle can't be null");
			}else {
				vehicle = Vehicle.getVehicle(Integer.parseInt(request.getParameter("vehicle")));
			}
			if(Objects.isNull(request.getParameterValues("violation"))) {
				errors.add("The fine must contain at least one violation");
			}

			if(errors.size()>0) {
				request.setAttribute("previous", request.getHeader("referer"));
				request.setAttribute("errors", errors);
				getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
			}else {
				String[] idViolations = request.getParameterValues("violation");
				violations = new ArrayList<Violation>();
				for(int i=0;i<idViolations.length;i++) {
					violations.add(Violation.getViolation(Integer.parseInt(removeLastCharOptional(idViolations[i]))));
				}
				if(Objects.isNull(vehicle.getRegistration())) {
					// Si pas de plaque associée au véhicule, d'office une violation de type Insurance Default ajoutée, 
					// La chercher dans la liste de toutes les violations et l'ajouter dans la liste de violations qui servira au const
					// de fine
					List<Violation> allViolations = (List<Violation>)session.getAttribute("allViolations");
					for(Violation vio : allViolations) {
						if(vio.getName().equals("Insurance default")) {
							violations.add(vio);
							break;
						}
					}
				}
				Date in = new Date();
				LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(),ZoneId.systemDefault());
				Date localToDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
				
				Fine fine = new Fine(0,violations,(Policeman)account,vehicle,commentary,localToDate,charged);
		// Si le chef ajoute une contravention, elle est automatiquement acceptée ( il n'a pas de chef pour accepter )
				if(account instanceof Chief) {
					fine.setValidated(true);
				}
				
				if(((Policeman)account).createFine(fine)) {
					// Si c'est un chief qui créé, la liste doit être mise à jour avec la nouvelle contravention. Dans le cas de policeman,
					// on s'en fiche il ne les consulte pas. 
					if(account instanceof Chief) {
						List <Fine> finesToChief = (List<Fine>) session.getAttribute("finesToChief");
						finesToChief.add(fine);
						session.setAttribute("finesToChief", finesToChief);
					}
					
					System.out.println("Le fine a été créé ( msg temp)");
					request.setAttribute("fine", fine);
					getServletContext().getRequestDispatcher("/WEB-INF/Views/TotalPrice.jsp").forward(request, response);
				}else {
					errors.add("Can't create fine");
					request.setAttribute("previous", request.getHeader("referer"));
					request.setAttribute("errors", errors);
					getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
				}
			}
		}
	}
	public static String removeLastCharOptional(String s) {
	    return Optional.ofNullable(s)
	      .filter(str -> str.length() != 0)
	      .map(str -> str.substring(0, str.length() - 1))
	      .orElse(s);
	}
}