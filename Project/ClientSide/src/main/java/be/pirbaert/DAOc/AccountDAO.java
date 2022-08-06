package be.pirbaert.DAOc;

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
		/*
		try {
			//doesn't work
			//List<Account> allAccounts= this.getMapper().readValue(responseJSON, new TypeReference<List<Account>>(){});
			
			//doesn't work
			List<Account> allAccounts = this.getMapper().readValue(responseJSON, this.getMapper().getTypeFactory().constructCollectionType(List.class, Account.class));
			System.out.println(allAccounts);
			return allAccounts;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		*/
		
		/* DOESN'T WORK
		 * 
		 */
		List <Account> allAccounts = new ArrayList<Account>();
		try{
			System.out.println(responseJSON.getClass().getSimpleName());
			JSONParser parser = new JSONParser();
			
			JSONObject JSONaccount = (JSONObject) parser.parse((String)responseJSON);
			Object modelObject = (Account) JSONaccount.get("account");
			
			if(modelObject instanceof JSONArray) {
				JSONArray itemsArray = (JSONArray)modelObject;
				for(int index = 0;index < itemsArray.size();index++) {
					Account account = null;
					JSONObject modelIterative = (JSONObject) itemsArray.get(index);
					switch(String.valueOf(modelIterative.get("type"))) {
						case "Administrator":
							account = new Administrator(Integer.parseInt((String) modelIterative.get("IdAccount")),(String) modelIterative.get("PersonelNumber"),(String) modelIterative.get("Password"));
							allAccounts.add(account);
							break;
						case "Policeman":
							account = new Policeman(Integer.parseInt((String) modelIterative.get("IdAccount")),(String) modelIterative.get("PersonelNumber"),(String) modelIterative.get("Password"));
							allAccounts.add(account);
							break;
						case "Chief":
							account = new Chief(Integer.parseInt((String) modelIterative.get("IdAccount")),(String) modelIterative.get("PersonelNumber"),(String) modelIterative.get("Password"));
							allAccounts.add(account);
							break;
						case "TaxCollector":
							account = new TaxCollector(Integer.parseInt((String) modelIterative.get("IdAccount")),(String) modelIterative.get("PersonelNumber"),(String) modelIterative.get("Password"));
							allAccounts.add(account);
							break;
					}
					allAccounts.add(account);
				}
				// S'il y en a qu'un
			}else if(modelObject instanceof JSONObject) {
				Account account = null;
				JSONObject modelIterative = (JSONObject) modelObject;
				switch(String.valueOf(modelIterative.get("type"))) {
					case "Administrator":
						account = new Administrator(Integer.parseInt((String) modelIterative.get("IdAccount")),(String) modelIterative.get("PersonelNumber"),(String) modelIterative.get("Password"));
						allAccounts.add(account);
						break;
					case "Policeman":
						account = new Policeman(Integer.parseInt((String) modelIterative.get("IdAccount")),(String) modelIterative.get("PersonelNumber"),(String) modelIterative.get("Password"));
						allAccounts.add(account);
						break;
					case "Chief":
						account = new Chief(Integer.parseInt((String) modelIterative.get("IdAccount")),(String) modelIterative.get("PersonelNumber"),(String) modelIterative.get("Password"));
						allAccounts.add(account);
						break;
					case "TaxCollector":
						account = new TaxCollector(Integer.parseInt((String) modelIterative.get("IdAccount")),(String) modelIterative.get("PersonelNumber"),(String) modelIterative.get("Password"));
						allAccounts.add(account);
						break;
				}
			}
		} catch (ParseException e) {
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
			e.printStackTrace();
			return null;
		}
		return null;
		
	}
}
