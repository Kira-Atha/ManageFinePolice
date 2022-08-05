package be.pirbaert.DAOs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
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
		CallableStatement proc = null;
		
		int id = 0;

		try {
			proc = this.connect.prepareCall("{call manage_account.create_account(?,?,?,?)}");
			proc.setString(1, obj.getPersonnelNumber());
			proc.setString(2, obj.getPassword());
			proc.setString(3, obj.getType());
			proc.registerOutParameter(4, Types.NUMERIC);
			

			proc.executeQuery();

			
			id = proc.getInt(4);
			
			if(id != 0) {
				obj.setId(id);
				return true;
			}
			
			return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}

	@Override
	public boolean delete(Account obj) {
		CallableStatement proc = null;
		
		
		try {
			proc = this.connect.prepareCall("{call manage_account.delete_account(?)}");
			proc.setString(1, obj.getPersonnelNumber());
			

			proc.executeQuery();

					
			return true;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}

	@Override
	public boolean update(Account obj) {
		
		if (obj.getPassword() == null) return updatePersonelNumber(obj);		
		return updateBoth(obj);
		
		
	}
	
	private boolean updatePersonelNumber(Account obj) {
		CallableStatement proc = null;
		
		
		try {
			proc = this.connect.prepareCall("{call manage_account.change_personelNumber(?,?)}");
			proc.setInt(1, obj.getId());
			proc.setString(2, obj.getPersonnelNumber());
			

			proc.executeQuery();

					
			return true;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
		
	}
		
	private boolean updateBoth(Account obj) {
		CallableStatement proc = null;
		
		
		try {
			proc = this.connect.prepareCall("{call manage_account.change_account(?,?,?)}");
			proc.setInt(1, obj.getId());
			proc.setString(2, obj.getPersonnelNumber());
			proc.setString(3, obj.getPassword());
			

			proc.executeQuery();

					
			return true;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
		
	}

	@Override
	public Account find(int id) {
		Account account = null;
		ResultSet result = null;
		PreparedStatement preparedStatement = null;

		try{
			

			preparedStatement = this.connect.prepareStatement("SELECT * FROM Account WHERE IDACCOUNT =?");
			preparedStatement.setInt(1, id);
			
			result = preparedStatement.executeQuery();
			
			if(result.next()) {
				switch(result.getString("TypeAccount")) {
					case "Chief":
						account = new Chief(result.getInt("IdAccount"),result.getString("PersonelNumber"),result.getString("Password"));
						break;
					case "Policeman":
						account = new Policeman(result.getInt("IdAccount"),result.getString("PersonelNumber"),result.getString("Password"));
						break;
					case "Administrator":
						account = new Administrator(result.getInt("IdAccount"),result.getString("PersonelNumber"),result.getString("Password"));
						break;
					case "TaxCollector":
						account = new TaxCollector(result.getInt("IdAccount"),result.getString("PersonelNumber"),result.getString("Password"));
						break;
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		//System.out.println(account.getClass().getSimpleName());
		return account;
	}

	@Override
	public List<Account> findAll() {
		List <Account> allAccounts = new ArrayList <Account>();
		Account account = null;
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Account");
			while(result.next()) {
				switch(result.getString("TypeAccount")) {
					case "Chief":
						account = new Chief(result.getInt("IdAccount"),result.getString("PersonelNumber"),result.getString("Password"));
						allAccounts.add(account);
						break;
					case "Policeman":
						account = new Policeman(result.getInt("IdAccount"),result.getString("PersonelNumber"),result.getString("Password"));
						allAccounts.add(account);
						break;
					case "Administrator":
						account = new Administrator(result.getInt("IdAccount"),result.getString("PersonelNumber"),result.getString("Password"));
						allAccounts.add(account);
						break;
					case "TaxCollector":
						account = new TaxCollector(result.getInt("IdAccount"),result.getString("PersonelNumber"),result.getString("Password"));
						allAccounts.add(account);
						break;
				}
			}
		}catch(SQLException e) {
			
		}
		//System.out.println(allAccounts.get(0));
		return allAccounts;
	}
}