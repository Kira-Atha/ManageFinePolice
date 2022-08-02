package be.pirbaert.POJOs;

import java.util.ArrayList;
import java.util.List;
import be.pirbaert.DAOs.DAO;
import be.pirbaert.DAOs.FactoryDAO;
import be.pirbaert.POJOs.Account;


public abstract class Account {
	private String personnelNumber ;
	private String password ;
	private static FactoryDAO afd;
	private static DAO<Account> accountDAO = afd.getAccountDAO();
	
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
	
	public static Account getAccount(int id) {
		return accountDAO.find(id);
	}
	public static Account getMember(int id) {
		Account account = null;
		
		return account;
	}
	public static List<Account> getAllAccount(){
		//Chercher tous les comptes existants en DB et return
		List<Account> listeBidon = new ArrayList<Account>();
		
		// return accountDAO.findAll();
		return listeBidon; 
	}
}