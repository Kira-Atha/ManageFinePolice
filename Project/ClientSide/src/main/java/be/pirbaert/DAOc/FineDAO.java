package be.pirbaert.DAOc;

import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import be.pirbaert.POJOc.Account;
import be.pirbaert.POJOc.Charged;
import be.pirbaert.POJOc.Fine;
import be.pirbaert.POJOc.Violation;

public class FineDAO extends DAO<Fine> {

	public FineDAO() {}

	@Override
	public boolean create(Fine fine) {
		MultivaluedMap<String, String> params = new MultivaluedMapImpl();
		SimpleDateFormat dayMonthYear = new SimpleDateFormat("dd/MM/yyyy");
		String id_charged = "0";
		String ids_violation="";
		
		for(Violation violation : fine.getViolations()) {
			ids_violation+=String.valueOf(violation.getId())+";";
		}
		
		// Retirer le dernier ";"
		StringBuffer sb = new StringBuffer(ids_violation);
		sb.deleteCharAt(sb.length()-1);
		params.add("ids_violation", sb.toString());
		params.add("id_policeman", String.valueOf(fine.getPoliceman().getId()));
		params.add("id_vehicle", String.valueOf(fine.getVehicle().getId()));
		params.add("comment", fine.getCommentary());
		params.add("date", dayMonthYear.format(fine.getDate()));
		if(!Objects.isNull(fine.getCharged())) {
			id_charged = String.valueOf(fine.getCharged().getId());
		}

		params.add("id_charged", id_charged);
		ClientResponse res = this.getResource()
				.path("fine")
				.post(ClientResponse.class,params);
		System.out.println("DAO côté client => "+res.getStatus());
		if(res.getStatus() == 201) {
			return true;
		}
		return false;
	}
// cas decline
	@Override
	public boolean delete(Fine fine) {
		ClientResponse responseJSON = this.getResource()
				.path("fine")
				.path(String.valueOf(fine.getId()))
				.delete(ClientResponse.class);
		
		if(responseJSON.getStatus() == 204) {
			return true;
		}
		return false;
	}

	//S'il y a update, c'est forcément pour mettre validated à true
	@Override
	public boolean update(Fine fine) {
		MultivaluedMap<String,String> paramsPut = new MultivaluedMapImpl();
		//int boolValue = fine.isValidated()?1:0;
		//paramsPut.add("validated",String.valueOf(boolValue));
		
		paramsPut.add("id_fine", String.valueOf(fine.getId()));
		ClientResponse responseJSON = this.getResource()
				.path("fine")
				.put(ClientResponse.class,paramsPut);
		
		if(responseJSON.getStatus() == 204) return true;
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
