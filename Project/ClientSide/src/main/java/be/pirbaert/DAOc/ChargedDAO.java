package be.pirbaert.DAOc;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.type.TypeReference;

import be.pirbaert.POJOc.Administrator;
import be.pirbaert.POJOc.Charged;
import be.pirbaert.POJOc.Chief;
import be.pirbaert.POJOc.Policeman;
import be.pirbaert.POJOc.TaxCollector;

public class ChargedDAO extends DAO<Charged>{

	public ChargedDAO() {}

	@Override
	public boolean create(Charged obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Charged obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Charged obj) {
		// TODO Auto-generated method stub
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
