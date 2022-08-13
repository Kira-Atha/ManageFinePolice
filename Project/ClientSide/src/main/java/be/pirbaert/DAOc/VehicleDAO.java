package be.pirbaert.DAOc;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Objects;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import be.pirbaert.POJOc.TypeVehicle;
import be.pirbaert.POJOc.Vehicle;

public class VehicleDAO extends DAO<Vehicle> {

	public VehicleDAO() {
	}

	@Override
	public boolean create(Vehicle vehicle) {
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.add("id_type",String.valueOf(vehicle.getType().getId()));
		String id_registration = "0";
		if(!Objects.isNull(vehicle.getRegistration())) {
			id_registration = String.valueOf(vehicle.getRegistration().getId());
		}
		params.add("id_registration",id_registration);
		ClientResponse res = this.getResource()
				.path("vehicle")
				.post(ClientResponse.class,params);
		if(res.getStatus() == 201) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Vehicle obj) {
		return false;
	}

	@Override
	public boolean update(Vehicle obj) {
		return false;
	}

	@Override
	public Vehicle find(int id) {
		String responseJSON = this.getResource()
				.path("vehicle")
				.path(String.valueOf(id))
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		try {
			return this.getMapper().readValue(responseJSON,Vehicle.class);
				
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Vehicle> findAll() {
		String responseJSON = this.getResource()
				.path("vehicle")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		try {
			return this.getMapper().readValue(responseJSON, new TypeReference<List<Vehicle>>(){});
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
