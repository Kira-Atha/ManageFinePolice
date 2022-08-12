package be.pirbaert.DAOs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import be.pirbaert.POJOs.Registration;
import be.pirbaert.POJOs.Vehicle;

public class RegistrationDAO extends DAO<Registration> {

	public RegistrationDAO(Connection connection) {
		super(connection);
	}

	@Override
	public boolean create(Registration obj) {
		CallableStatement procedure = null;
		int new_id = 0;
		try {
			procedure = this.connect.prepareCall("{call manage_registration.create_registration(?,?)}");
			procedure.setString(1,obj.getSerialNumber());
			procedure.registerOutParameter(2, Types.NUMERIC);
			
			procedure.executeQuery();
			new_id=procedure.getInt(2);
			
			if(new_id!=0) {
				obj.setId(new_id);
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	@Override
	public boolean delete(Registration obj) {
		CallableStatement proc = null;
		
		
		try {
			proc = this.connect.prepareCall("{call  manage_registration.delete_registration(?)}");
			proc.setInt(1, obj.getId());
			

			proc.executeQuery();
					
			return true;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}

	@Override
	public boolean update(Registration obj) {
		CallableStatement proc = null;

		try {
			proc = this.connect.prepareCall("{call manage_registration.update_registration(?,?)}");
			proc.setInt(1, obj.getId());
			proc.setString(2, obj.getSerialNumber());
			
	
			proc.executeQuery();
			
			return true;
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
	
		}
	}

	@Override
	public Registration find(int id) {
		Registration registration = null;
		ResultSet result = null;
		PreparedStatement preparedStatement = null;
		
		try{
			preparedStatement = this.connect.prepareStatement("SELECT * FROM Registration where IdRegistration=?");
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();
			if(result.next()) {
				registration=new Registration(result.getInt("IDREGISTRATION"),result.getString("SERIALNUMBER"));
			}
			result.close();
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
				registration=new Registration(result.getInt("IDREGISTRATION"),result.getString("SERIALNUMBER"));
				allRegistrations.add(registration);
			}
			result.close();
		}catch(SQLException e) {
			return null;
		}
		return allRegistrations;
	}

}
