package be.pirbaert.DAOc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import be.pirbaert.POJOc.Account;
import be.pirbaert.POJOc.Administrator;
import be.pirbaert.POJOc.Chief;
import be.pirbaert.POJOc.Policeman;
import be.pirbaert.POJOc.TaxCollector;
import be.pirbaert.POJOc.Violation;

public class AccountDAO extends DAO<Account> {

	public AccountDAO() {
	}

	@Override
	public boolean create(Account obj) {
		MultivaluedMap<String,String> paramsPost = new MultivaluedMapImpl();
		paramsPost.add("personnelNumber",obj.getPersonnelNumber());
		paramsPost.add("password",obj.getPassword());
		paramsPost.add("type",obj.getType());
		
		ClientResponse responseJSON = this.getResource()
				.path("account")
				.post(ClientResponse.class, paramsPost);
		
		if(responseJSON.getStatus() == 201) return true;
		
		return false;
	}

	@Override
	public boolean delete(Account obj) {
		ClientResponse responseJSON = this.getResource()
				.path("account")
				.path(String.valueOf(obj.getId()))
				.delete(ClientResponse.class);
		
		if(responseJSON.getStatus() == 204) return true;
		
		return false;
	}

	@Override
	public boolean update(Account obj) {
		
		MultivaluedMap<String,String> paramsPost = new MultivaluedMapImpl();
		
		paramsPost.add("personnelNumber",obj.getPersonnelNumber());
		paramsPost.add("password",obj.getPassword());
		paramsPost.add("id",String.valueOf(obj.getId()));
		
		ClientResponse responseJSON = this.getResource()
				.path("account")
				.put(ClientResponse.class,paramsPost);
		
		if(responseJSON.getStatus() == 204) return true;
		
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

				switch(String.valueOf( account.get("type"))) {
					case "Administrator":
						allAccounts.add( this.getMapper().readValue(account.toJSONString(),Administrator.class));
						break;
					case "Chief":
						allAccounts.add( this.getMapper().readValue(account.toJSONString(),Chief.class));
						break;
					case "Policeman":
						allAccounts.add( this.getMapper().readValue(account.toJSONString(),Policeman.class));
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
			
			switch(String.valueOf(obj.get("type"))) {
				case "Administrator":
					return this.getMapper().readValue(responseJSON,Administrator.class);
				case "Chief":
					return this.getMapper().readValue(responseJSON,Chief.class);
				case "Policeman":
					return this.getMapper().readValue(responseJSON,Policeman.class);
				case "TaxCollector":
					return this.getMapper().readValue(responseJSON,TaxCollector.class);
			}
			
		}catch(Exception e) {
//			e.printStackTrace();
			return null;
		}
		return null;
		
	}
	
	public List<Chief> findAllChief() {
		String responseJSON = this.getResource()
				.path("account/chief")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
		
		try{
			
			return  this.getMapper().readValue(responseJSON,new TypeReference<List<Chief>>(){});
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Policeman> findAllPoliceman() {
		String responseJSON = this.getResource()
				.path("account/policeman")
				.accept(MediaType.APPLICATION_JSON)
				.get(String.class);
	
		try{
			
			return  this.getMapper().readValue(responseJSON,new TypeReference<List<Policeman>>(){});
					
			

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean setChief(Policeman policeman, int id_chief) {
		MultivaluedMap<String,String> paramsPost = new MultivaluedMapImpl();
		paramsPost.add("id_policeman",String.valueOf(policeman.getId()));
		paramsPost.add("id_chief",String.valueOf(id_chief));
		
			
		ClientResponse responseJSON = this.getResource()
				.path("account/addChief")
				.post(ClientResponse.class, paramsPost);

		if(responseJSON.getStatus() == 201) return true;
		
		return false;
	}
}
