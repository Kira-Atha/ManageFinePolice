package be.pirbaert.DAOc;
import java.sql.Connection;
import javax.naming.NamingException;

import be.pirbaert.POJOc.Account;
import be.pirbaert.POJOc.Administrator;
import be.pirbaert.POJOc.Charged;
import be.pirbaert.POJOc.Chief;
import be.pirbaert.POJOc.Fine;
import be.pirbaert.POJOc.Policeman;
import be.pirbaert.POJOc.Registration;
import be.pirbaert.POJOc.TaxCollector;
import be.pirbaert.POJOc.TypeVehicle;
import be.pirbaert.POJOc.Vehicle;
import be.pirbaert.POJOc.Violation;


public class FactoryDAO extends AbstractFactoryDAO{

	
	protected static Connection conn = null;
	
	
	public FactoryDAO(){
		try {
			conn = DbConnection.getInstance();
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
	}

	@Override
	public DAO<Account> getAccountDAO() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DAO<Administrator> getAdministratorDAO() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DAO<Charged> getChargedDAO() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DAO<Chief> getChiefDAO() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DAO<Fine> getFineDAO() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DAO<Policeman> getPolicemanDAO() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DAO<Registration> getRegistrationDAO() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DAO<TaxCollector> getTaxCollectorDAO() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DAO<TypeVehicle> getTypeVehicleDAO() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DAO<Vehicle> getVehicleDAO() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public DAO<Violation> getViolationDAO() {
		// TODO Auto-generated method stub
		return null;
	}
}