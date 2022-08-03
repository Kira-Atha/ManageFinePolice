package be.pirbaert.POJOs;

import java.util.ArrayList;
import java.util.List;
import be.pirbaert.DAOs.DAO;
import be.pirbaert.DAOs.FactoryDAO;
import be.pirbaert.POJOs.Account;


public abstract class Account {
	private int id;
	private String personnelNumber ;
	private String password ;
	private static FactoryDAO afd = new FactoryDAO();
	private static DAO<Account> accountDAOs = afd.getAccountDAO();
	
	public Account() {}
	
	public Account(int id,String personelNumber, String password) {
		// Sera modifié grâce aux appels DB
		this.id = id;
		this.personnelNumber = personelNumber;
		this.password = password;
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
	
	public static Account getAccount(int id) {
		return accountDAOs.find(id);
	}
	public static List<Account> getAllAccount(){
		//Chercher tous les comptes existants en DB et return
		List<Account> listeBidon = new ArrayList<Account>();
		
		// return accountDAO.findAll();
		return listeBidon; 
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "MEC => "+this.personnelNumber+this.password;
	}
}