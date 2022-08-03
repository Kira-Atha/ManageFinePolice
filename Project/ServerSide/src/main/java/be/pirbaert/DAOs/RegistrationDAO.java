package be.pirbaert.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import be.pirbaert.POJOs.Registration;
import be.pirbaert.POJOs.Vehicle;

public class RegistrationDAO extends DAO<Registration> {

	public RegistrationDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Registration obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Registration obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Registration obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Registration find(int id) {
		Registration registration = null;
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Registration where IdRegistration="+id);
			while(result.next()) {
				registration=new Registration(result.getInt("IdRegistration"),result.getString("SerialNumber"));
			}
		}catch(SQLException e) {
			return null;
		}
		return registration;
	}

	@Override
	public List<Registration> findAll() {
		List<Registration> allRegistrations = new ArrayList <Registration>();
		Registration registration = null;
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Registration");
			while(result.next()) {
				registration=new Registration(result.getInt("IdRegistration"),result.getString("SerialNumber"));
				allRegistrations.add(registration);
			}
		}catch(SQLException e) {
			return null;
		}
		return allRegistrations;
	}

}
