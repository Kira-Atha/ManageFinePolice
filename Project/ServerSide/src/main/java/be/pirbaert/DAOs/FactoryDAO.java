package be.pirbaert.DAOs;
import java.sql.Connection;
import javax.naming.NamingException;

import be.pirbaert.POJOs.*;


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
		return new AccountDAO(conn);
	}

	@Override
	public DAO<Charged> getChargedDAO() {
		return new ChargedDAO(conn);
	}

	@Override
	public DAO<Fine> getFineDAO() {
		return new FineDAO(conn);
	}


	@Override
	public DAO<Registration> getRegistrationDAO() {
		return new RegistrationDAO(conn);
	}

	@Override
	public DAO<TypeVehicle> getTypeVehicleDAO() {
		return new TypeVehicleDAO(conn);
	}


	@Override
	public DAO<Vehicle> getVehicleDAO() {
		return new VehicleDAO(conn);
	}


	@Override
	public DAO<Violation> getViolationDAO() {
		return new ViolationDAO(conn);
	}
}