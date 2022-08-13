package be.pirbaert.DAOc;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import be.pirbaert.POJOc.Administrator;
import be.pirbaert.POJOc.Charged;
import be.pirbaert.POJOc.Chief;
import be.pirbaert.POJOc.Policeman;
import be.pirbaert.POJOc.TaxCollector;

public class ChargedDAO extends DAO<Charged>{

	public ChargedDAO() {}

	@Override
	public boolean create(Charged charged) {
		MultivaluedMap<String,String> params = new MultivaluedMapImpl();
		params.add("firstname", charged.getFirstname());
		params.add("lastname", charged.getLastname());
		params.add("address", charged.getAddress());
		ClientResponse res = this.getResource()
				.path("charged")
				.post(ClientResponse.class,params);
		if(res.getStatus() == 201) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Charged obj) {
		return false;
	}

	@Override
	public boolean update(Charged obj) {
		return false;
	}

	@Override
	public Charged find(int id) {
		String responseJSON = this.getResource()
				.path("charged")
				.path(String.valueOf(id))
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		try {
			return this.getMapper().readValue(responseJSON,Charged.class);
				
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Charged> findAll() {
		List<Charged> allChargeds;
		String responseJSON = this.getResource()
				.path("charged")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);

		try {
			allChargeds= this.getMapper().readValue(responseJSON, new TypeReference<List<Charged>>(){});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return allChargeds;
	}
}
