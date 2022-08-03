package be.pirbaert.DAOc;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import be.pirbaert.POJOc.Fine;
import be.pirbaert.POJOc.Registration;

public class RegistrationDAO extends DAO<Registration> {

	public RegistrationDAO() {
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
		String responseJSON = this.getResource()
				.path("registration")
				.path(String.valueOf(id))
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		try {
			return this.getMapper().readValue(responseJSON,Registration.class);
				
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Registration> findAll() {
		String responseJSON = this.getResource()
				.path("registration")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		try {
			return this.getMapper().readValue(responseJSON, new TypeReference<List<Registration>>(){});
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}



}
