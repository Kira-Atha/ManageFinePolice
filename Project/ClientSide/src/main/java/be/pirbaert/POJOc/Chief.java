package be.pirbaert.POJOc;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Chief extends Policeman{
	private static final long serialVersionUID = 4053951674963753923L;
	private List<Policeman> subordinates;
	
	public Chief() {}
	public Chief(int id,String personelNumber,String password) {
		super(id,personelNumber,password);
		this.subordinates = new ArrayList<Policeman>();
		this.type=this.getClass().getSimpleName();
	}
	public Chief(String personelNumber,String password) {
		super(personelNumber,password);
		this.setSubordinates(new ArrayList<Policeman>());
		this.type=this.getClass().getSimpleName();
	}

	
	public List<Policeman> getSubordinates() {
		this.subordinates = new ArrayList<Policeman>();
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
	
	public void setSubordinates(List<Policeman> subordinates) {
		this.subordinates = subordinates;
	}
	
	public boolean acceptDeclineFine(String choice,Fine fine) {
		if(choice.equals("accept") && fine.update()) {
			return true;
		}else if(choice.equals("decline") && fine.delete()) {
			return true;
		}
		return false;
	}
}