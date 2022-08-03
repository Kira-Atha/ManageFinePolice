package be.pirbaert.POJOc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import be.pirbaert.DAOc.AccountDAO;

public abstract class Account implements Serializable{
	private static final long serialVersionUID = 7895495554055350208L;
	private int id;
	private String personnelNumber ;
	private String password ;
	private static AccountDAO accountDAOc = new AccountDAO();
	protected String type;
	
	public Account() {}
	
	public Account(int id, String personelNumber, String password) {
		this.setId(id);
		this.personnelNumber = personelNumber;
		this.password = password;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPersonnelNumber() {
		return personnelNumber;
	}
	public void setPersonnelNumber(String personnelNumber) {
		this.personnelNumber = personnelNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public Account signIn() {
		List <Account> allAccounts = getAllAccounts();
		Account toCast = null;
		
		for(Account account : allAccounts) {
			if(this.getPassword() == account.getPassword() && this.getPersonnelNumber() == account.getPersonnelNumber()) {
				if(account instanceof Chief){
					toCast = (Chief)account;
				}
				if(account instanceof Policeman){
					toCast = (Policeman)account;
				}
				if(account instanceof Administrator){
					toCast = (Administrator)account;
				}
				if(account instanceof TaxCollector){
					toCast = (TaxCollector)account;
				}
			}
		}
		return toCast;
	}
	
	
	public static Account getAccount(int id) {
		return accountDAOc.find(id);
	}
	public static List<Account> getAllAccounts(){
		return accountDAOc.findAll(); 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
