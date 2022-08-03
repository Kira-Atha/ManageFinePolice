package be.pirbaert.DAOc;

import javax.ws.rs.core.MediaType;

import org.apache.tomcat.util.json.JSONParser;

import be.pirbaert.POJOc.Account;

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
			
			
			System.out.println(responseJSON);
			
			return this.getMapper().readValue(responseJSON,Account.class);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
