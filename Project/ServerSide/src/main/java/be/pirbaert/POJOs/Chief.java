package be.pirbaert.POJOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Chief extends Policeman {
	private static final long serialVersionUID = -4394891119807101120L;
	private List<Policeman> subordinates;
	
	public Chief(int id,String personelNumber, String password) {
		super(id,personelNumber, password);
		this.setType(this.getClass().getSimpleName());
		this.setSubordinates(new ArrayList<Policeman>());
		//getSubordinates();
	}
	
	public Chief(int id,String personelNumber) {
		super(id,personelNumber);
		this.setType(this.getClass().getSimpleName());
		setSubordinates(new ArrayList<Policeman>());
	}
	
	public Chief(String personelNumber, String password) {
		super(personelNumber, password);
		this.setType(this.getClass().getSimpleName());		
	}

	/*
	public List<Policeman> getSubordinates() {
		List<Account> allAccounts = Account.getAllAccounts();
		List<Policeman> allPolicemans = new ArrayList<Policeman>();
		
		for(Account account : allAccounts) {
			if(account.getClass().getSimpleName().equals("Policeman")) {
				allPolicemans.add((Policeman)account);
			}
		}
		for(Policeman account : allPolicemans) {
			if(Objects.isNull(account.getChief())) {
				continue;
			}else if(account.getChief().equals(this)) {
				this.subordinates.add((Policeman) account);
			}
		}
		return subordinates;
	}
	*/
	public void setSubordinates(List<Policeman> subordinates) {
		this.subordinates = subordinates;
	}
	
	public static List<Chief> getAll(){
		return accountDAOs.findAllChief();
	}
}
