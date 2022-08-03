package be.pirbaert.DAOs;
import javax.naming.NamingException;

import be.pirbaert.POJOs.*;

public abstract class AbstractFactoryDAO {
	public static final int DAO_FACTORY = 0;
	public static final int XML_DAO_FACTORY = 1;
	
	public abstract DAO<Account> getAccountDAO();
	//public abstract DAO<Administrator> getAdministratorDAO();
	public abstract DAO<Charged> getChargedDAO();
	//public abstract DAO<Chief> getChiefDAO();
	public abstract DAO<Fine> getFineDAO();
	//public abstract DAO<Policeman> getPolicemanDAO();
	public abstract DAO<Registration> getRegistrationDAO();
	//public abstract DAO<TaxCollector> getTaxCollectorDAO();
	public abstract DAO<TypeVehicle> getTypeVehicleDAO();
	public abstract DAO<Vehicle> getVehicleDAO();
	public abstract DAO<Violation> getViolationDAO();

	public static AbstractFactoryDAO getFactory(int type) throws NamingException{
		switch(type){
		case DAO_FACTORY:
			return new FactoryDAO();
			default:
				return null;
		}
	}
}