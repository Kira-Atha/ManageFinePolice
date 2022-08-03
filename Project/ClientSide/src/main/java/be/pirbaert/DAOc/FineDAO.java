package be.pirbaert.DAOc;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import be.pirbaert.POJOc.Charged;
import be.pirbaert.POJOc.Fine;

public class FineDAO extends DAO<Fine> {

	public FineDAO() {}

	@Override
	public boolean create(Fine obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Fine obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Fine obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Fine find(int id) {
		String responseJSON = this.getResource()
				.path("fine")
				.path(String.valueOf(id))
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		try {
			return this.getMapper().readValue(responseJSON,Fine.class);
				
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Fine> findAll() {
		String responseJSON = this.getResource()
				.path("fine")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		try {
			return this.getMapper().readValue(responseJSON, new TypeReference<List<Fine>>(){});
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
