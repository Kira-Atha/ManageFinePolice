package be.pirbaert.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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

public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignIn() {
        super();
    }

    /*
    @Override
    public void init()throws ServletException {
    	super.init();
    	
    }
    */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account account = Account.getAccount(1);
		//System.out.println("One => "+account.getPersonnelNumber());
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/Views/SignIn.jsp");
		dispatcher.forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String personelNumber = request.getParameter("personelNumber");
		String password = request.getParameter("password");
		PrintWriter out = response.getWriter();
		List <String> errors = new ArrayList<String>();
		if(personelNumber == null) {
			errors.add("Personel number field was null");
		}else {
			if(personelNumber.equals("")) {
				errors.add("Personel number field was empty");
			}
			if(!personelNumber.matches("^[0-9a-zA-Z]{5,}$")) {
				errors.add("Personel number must contains min 5 characters and don't contain special characters");
			}
		}
		if(password==null) {
			errors.add("Password field was null");
		}else {
			if(password.equals("")) {
				errors.add("Password field was empty");
			}
			if(!password.matches("^[0-9a-zA-Z]{4,}$")) {
				errors.add("Password must contains min 5 characters and don't contain special characters");
			}
		}
		if(errors.size()>0) {
			// Solution 1 : Redirection vers une page d'erreur et revenir en arri�re 
			request.setAttribute("errors", errors);
			getServletContext().getRequestDispatcher("/WEB-INF/Views/errors.jsp").forward(request, response);
			
			// Solution 2 : Afficher les erreurs sous le formulaire ????
			//TODO
		}
		Account account;
		// Policeman est un objet temporaire car Account n'est pas instanciable l'id�e est de r�cup�rer le bon objet lorsue le compte est correct en DB
		account = new Policeman(0,personelNumber,password);
		Account accountToConnect = account.signIn();
		if(!Objects.isNull(accountToConnect)){
			ServletContext context = getServletContext();
			context.setAttribute("account", accountToConnect);
			
			// G�rer session
			
			HttpSession session = request.getSession();
			if(!session.isNew()) {
				session.invalidate();
				// Je veux la recr�er
				session = request.getSession();
			}
			session.setAttribute("account", accountToConnect);
			// Pour r�cup les values du compte connect� dans la page qui suit
			request.setAttribute("account", accountToConnect);
			if(accountToConnect instanceof Chief) {
				//Renvoyer vers la premi�re page des chefs => Pourra consulter, lui
				RequestDispatcher dispatch = request.getRequestDispatcher("ConsultFines");
				dispatch.forward(request, response);
				//getServletContext().getRequestDispatcher("/ConsultFines").forward(request, response);
			}
			if(accountToConnect instanceof Policeman) {
				//Renvoyer vers la premi�re page des policiers => ConsultFines. Mais comme policeman, ne verra pas toutes les fines
				getServletContext().getRequestDispatcher("/ConsultFines").forward(request, response);
			}
			if(accountToConnect instanceof Administrator) {
				//Renvoyer vers la premi�re page des admins ( Gestion des comptes ? )
				getServletContext().getRequestDispatcher("nombidon").forward(request, response);
			}
		}else {
			out.print("Incorrect account");
		}
	}

}
