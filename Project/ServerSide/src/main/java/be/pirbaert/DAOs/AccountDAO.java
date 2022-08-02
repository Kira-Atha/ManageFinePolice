package be.pirbaert.DAOs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import be.pirbaert.POJOs.Account;
import be.pirbaert.POJOs.Administrator;
import be.pirbaert.POJOs.Chief;
import be.pirbaert.POJOs.Policeman;
import be.pirbaert.POJOs.TaxCollector;

public class AccountDAO extends DAO<Account> {

	public AccountDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

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
		Account account = null;
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Account WHERE IDACCOUNT ="+id);
			if(result.next()) {
				switch(result.getString("TypeAccount")) {
					case "Chief":
						account = new Chief(result.getInt(id),result.getString("PersonelNumber"),result.getString("Password"));
					case "Policeman":
						account = new Policeman(result.getInt(id),result.getString("PersonelNumber"),result.getString("Password"));
					case "Administrator":
						account = new Administrator(result.getInt(id),result.getString("PersonelNumber"),result.getString("Password"));
					case "TaxCollector":
						account = new TaxCollector(result.getInt(id),result.getString("PersonelNumber"),result.getString("Password"));
				}
			}
		}catch(SQLException e) {
			
		}
		System.out.println("DAOs ACCOUNT => "+account);
		return account;
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}