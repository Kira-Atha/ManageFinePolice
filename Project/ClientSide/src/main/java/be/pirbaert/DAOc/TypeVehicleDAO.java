package be.pirbaert.DAOc;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import be.pirbaert.POJOc.Registration;
import be.pirbaert.POJOc.TypeVehicle;

public class TypeVehicleDAO extends DAO<TypeVehicle> {

	public TypeVehicleDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(TypeVehicle obj) {
		MultivaluedMap<String,String> paramsPost = new MultivaluedMapImpl();
		paramsPost.add("name",obj.getName());
		
		ClientResponse responseJSON = this.getResource()
				.path("typeVehicle")
				.post(ClientResponse.class, paramsPost);
		
		if(responseJSON.getStatus() == 201) return true;
		
		return false;
	}

	@Override
	public boolean delete(TypeVehicle obj) {
		ClientResponse responseJSON = this.getResource()
				.path("typeVehicle")
				.path(String.valueOf(obj.getId()))
				.delete(ClientResponse.class);
		
		if(responseJSON.getStatus() == 204) return true;
		
		return false;
	}

	@Override
	public boolean update(TypeVehicle obj) {
		MultivaluedMap<String,String> paramsPost = new MultivaluedMapImpl();
		paramsPost.add("name",obj.getName());
		paramsPost.add("id",String.valueOf(obj.getId()));
		
		ClientResponse responseJSON = this.getResource()
				.path("typeVehicle")
				.put(ClientResponse.class,paramsPost);
		
		if(responseJSON.getStatus() == 204) return true;
		
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
