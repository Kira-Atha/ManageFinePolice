package be.pirbaert.DAOc;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;

import be.pirbaert.POJOc.Registration;
import be.pirbaert.POJOc.TypeVehicle;

public class TypeVehicleDAO extends DAO<TypeVehicle> {

	public TypeVehicleDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(TypeVehicle obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(TypeVehicle obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(TypeVehicle obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TypeVehicle find(int id) {
		String responseJSON = this.getResource()
				.path("typeVehicle")
				.path(String.valueOf(id))
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		try {
			return this.getMapper().readValue(responseJSON,TypeVehicle.class);
				
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<TypeVehicle> findAll() {
		String responseJSON = this.getResource()
				.path("typeVehicle")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		try {
			return this.getMapper().readValue(responseJSON, new TypeReference<List<TypeVehicle>>(){});
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
