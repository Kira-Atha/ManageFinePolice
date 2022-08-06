package be.pirbaert.DAOc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.type.TypeReference;

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
			
			//System.out.println(obj.get("type"));
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
	public List<Account> findAll() {
		String responseJSON = this.getResource()
				.path("account")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
	
		List <Account> allAccounts = new ArrayList<Account>();
		try{
			JSONParser parser = new JSONParser();
						
			ArrayList<JSONObject> JSONaccounts = (ArrayList<JSONObject>) parser.parse(responseJSON);
			
			
			for(JSONObject account : JSONaccounts) {
				System.out.print(account);
				switch(String.valueOf( account.get("type"))) {
					case "Administrator":
						allAccounts.add( this.getMapper().readValue(account.toJSONString(),Administrator.class));
						break;
					case "Policeman":
						allAccounts.add( this.getMapper().readValue(account.toJSONString(),Policeman.class));
						break;
					case "Chief":
						allAccounts.add( this.getMapper().readValue(account.toJSONString(),Chief.class));
						break;
					case "TaxCollector":
						allAccounts.add( this.getMapper().readValue(account.toJSONString(),TaxCollector.class));
						break;
					}
			}
			

		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
	return allAccounts;
	
	}
	
	public Account connect(String personelNumber, String password) {
		
		try {
			String responseJSON = this.getResource()
					.path("account/connect")
					.queryParam("personelNumber",personelNumber )
					.queryParam("password", password)
					.accept(MediaType.APPLICATION_JSON)
					.get(String.class);
			
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject)parser.parse(responseJSON);
			
			//System.out.println(obj.get("type"));
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
//			e.printStackTrace();
			return null;
		}
		return null;
		
	}
}
