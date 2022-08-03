package be.pirbaert.DAOc;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import be.pirbaert.POJOc.Vehicle;
import be.pirbaert.POJOc.Violation;

public class ViolationDAO extends DAO<Violation> {

	public ViolationDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Violation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Violation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Violation obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Violation find(int id) {
		String responseJSON = this.getResource()
				.path("violation")
				.path(String.valueOf(id))
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		try {
			return this.getMapper().readValue(responseJSON,Violation.class);
				
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Violation> findAll() {
		String responseJSON = this.getResource()
				.path("violation")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		try {
			return this.getMapper().readValue(responseJSON, new TypeReference<List<Violation>>(){});
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
