package be.pirbaert.DAOc;

import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import be.pirbaert.POJOc.Account;
import be.pirbaert.POJOc.Administrator;
import be.pirbaert.POJOc.Chief;
import be.pirbaert.POJOc.Policeman;
import be.pirbaert.POJOc.TaxCollector;

public class AccountDAO extends DAO<Account> {

	public AccountDAO() {
	}

	// -> API
	@Override
	public boolean create(Account obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Account obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Account obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account find(int id) {
		String responseJSON = this.getResource()
				.path("account")
				.path(String.valueOf(id))
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(responseJSON);
			
			System.out.println(obj.get("type"));
			switch(String.valueOf(obj.get("type"))) {
				case "Administrator":
					return this.getMapper().readValue(responseJSON,Administrator.class);
				case "Policeman":
					return this.getMapper().readValue(responseJSON,Policeman.class);
				case "Chief":
					return this.getMapper().readValue(responseJSON,Chief.class);
				case "TaxCollector":
					return this.getMapper().readValue(responseJSON,TaxCollector.class);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public String findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
