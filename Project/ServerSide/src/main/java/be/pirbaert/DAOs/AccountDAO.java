package be.pirbaert.DAOs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import be.pirbaert.POJOs.Account;
import be.pirbaert.POJOs.Administrator;
import be.pirbaert.POJOs.Chief;
import be.pirbaert.POJOs.Policeman;
import be.pirbaert.POJOs.TaxCollector;

public class AccountDAO extends DAO<Account> {

	public AccountDAO(Connection connection) {
		super(connection);
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
			proc.setInt(1, obj.getId());
			

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
		if (obj.getPassword().length() == 0) return updatePersonelNumber(obj);		
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
						account = new Chief(result.getInt("IdAccount"),result.getString("PersonelNumber"));
						break;
					case "Policeman":
						account = new Policeman(result.getInt("IdAccount"),result.getString("PersonelNumber"));
						((Policeman)account).setChief((Chief)Account.getAccount(result.getInt("IdChief")));
						break;
					case "Administrator":
						account = new Administrator(result.getInt("IdAccount"),result.getString("PersonelNumber"));
						break;
					case "TaxCollector":
						account = new TaxCollector(result.getInt("IdAccount"),result.getString("PersonelNumber"));
						break;
				}
			}
			result.close();
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
					account = new Chief(result.getInt("IdAccount"),result.getString("PersonelNumber"));
					allAccounts.add(account);
					break;
				case "Policeman":
					account = new Policeman(result.getInt("IdAccount"),result.getString("PersonelNumber"));
					((Policeman)account).setChief((Chief)Account.getAccount(result.getInt("IdChief")));
					allAccounts.add(account);
					break;
				case "Administrator":
					account = new Administrator(result.getInt("IdAccount"),result.getString("PersonelNumber"));
					allAccounts.add(account);
					break;
				case "TaxCollector":
					account = new TaxCollector(result.getInt("IdAccount"),result.getString("PersonelNumber"));
					allAccounts.add(account);
					break;
				}
			}
			result.close();
		}catch(SQLException e) {
			
		}
		//System.out.println(allAccounts.get(0));
		return allAccounts;
	}
	
	public List<Chief> findAllChief() {
		List <Chief> allChiefs = new ArrayList <Chief>();
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Account WHERE TYPEACCOUNT = 'Chief'");
			while(result.next()) {
				allChiefs.add(new Chief(result.getInt("IdAccount"),result.getString("PersonelNumber")));
				
			}
			result.close();
		}catch(SQLException e) {
			
		}
		return allChiefs;
	}

	public List<Policeman> findAllPoliceman() {
		List <Policeman> allPolicemans = new ArrayList <Policeman>();
		ResultSet result = null;
		try{
			result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM Account WHERE TYPEACCOUNT = 'Policeman'");
			while(result.next()) {
						Policeman policeman = new Policeman(result.getInt("IdAccount"),result.getString("PersonelNumber"));
						policeman.setChief((Chief)Account.getAccount(result.getInt("IdChief")));
						allPolicemans.add(policeman);
			}
			result.close();
		}catch(SQLException e) {
			
		}
		return allPolicemans;
	}
	
	public Account connect(String personelNumber, String password) {
		Account account = null;
		CallableStatement proc = null;

		try {
			
			proc = this.connect.prepareCall("{call manage_account.connect_account(?,?,?,?)}");
			proc.setString(1, personelNumber);
			proc.setString(2, password);
			proc.registerOutParameter(3, Types.CHAR);
			proc.registerOutParameter(4, Types.NUMERIC);
			
			proc.executeQuery();

			switch(proc.getString(3)) {
			
			case "Chief":
				account = new Chief(proc.getInt(4),personelNumber);
				break;
			case "Policeman":
				account = new Policeman(proc.getInt(4),personelNumber);
				break;
			case "Administrator":
				account = new Administrator(proc.getInt(4),personelNumber);
				break;
			case "TaxCollector":
				account = new TaxCollector(proc.getInt(4),personelNumber);
				break;
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return account;
		
	}
	
	public boolean setChief(Policeman policeman, int id_chief) {
		CallableStatement proc = null;
		
		try {
			proc = this.connect.prepareCall("{call manage_account.set_chief(?,?)}");
			proc.setInt(1, id_chief);
			proc.setInt(2, policeman.getId());
			

			proc.executeQuery();

					
			return true;
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;

		}
	}
}