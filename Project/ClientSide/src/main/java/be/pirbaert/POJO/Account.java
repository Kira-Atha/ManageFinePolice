package be.pirbaert.POJO;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {
	
	private String personnelNumber ;
	private String password ;
	
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
		List <Account> allAccount = getAllAccount();
		Account toCast = null;
		
		for(Account account : allAccount) {
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
			}
		}
		return toCast;
	}
	public List<Account> getAllAccount(){
		//Chercher tous les comptes existants en DB et return
		List<Account> listeBidon = new ArrayList<Account>();
		
		// return accountDAO.findAll();
		return listeBidon; 
	}
}
