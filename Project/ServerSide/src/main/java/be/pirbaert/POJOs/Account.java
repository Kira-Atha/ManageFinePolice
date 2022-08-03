package be.pirbaert.POJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import be.pirbaert.DAOs.DAO;
import be.pirbaert.DAOs.FactoryDAO;
import be.pirbaert.POJOs.Account;


public abstract class Account implements Serializable{
	private static final long serialVersionUID = -5650518956213649548L;
	private int id;
	private String personnelNumber ;
	private String password ;
	private static FactoryDAO afd = new FactoryDAO();
	private static DAO<Account> accountDAOs = afd.getAccountDAO();
	protected String type;
	
	public Account() {}
	
	public Account(int id,String personelNumber, String password) {
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
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String toString() {
		return this.personnelNumber+" "+this.password+" "+this.type;
	}
}