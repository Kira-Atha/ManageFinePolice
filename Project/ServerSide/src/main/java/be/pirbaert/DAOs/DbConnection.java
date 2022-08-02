package be.pirbaert.DAOs;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class DbConnection{

	private static Connection instance = null;
	private String connectionString = "jdbc:oracle:thin:@193.190.64.10:1522:XE_CHAR";
	
	// NE PAS LAISSER EN BRUT, TEST. UTILISER CONTEXTE D'UN SERVLET ?
	private String username = "STUDENT03_13";
	private String password = "galex";
	
	
	private DbConnection() throws NamingException{

		try{
			Context ctx = new InitialContext();
			connectionString = (String) ctx.lookup("java:comp/env/connectionString");
			username = (String) ctx.lookup("java:comp/env/usernameDB");
			password = (String) ctx.lookup("java:comp/env/passwordDB") ;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			instance = DriverManager.getConnection(
					connectionString,
					username,
					password
					);
		}
		catch(ClassNotFoundException ex){
			System.out.println("Classe de driver introuvable" + ex.getMessage());
			System.exit(0);
		}
		catch (SQLException ex) {
			System.out.println("Erreur JDBC : " + ex.getMessage());
		}
		catch(NamingException ne) {
			ne.printStackTrace();
		}
	}
	
	public static Connection getInstance() throws NamingException {
		if(instance == null){
			new DbConnection();
		}
		return instance;
	}
}