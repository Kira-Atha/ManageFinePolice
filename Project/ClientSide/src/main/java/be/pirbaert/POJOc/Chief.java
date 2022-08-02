package be.pirbaert.POJOc;

import java.util.ArrayList;
import java.util.List;

public class Chief extends Account{
	private List<Policeman> subordinates;
	
	public Chief(String personelNumber,String password) {
		this.setPersonnelNumber(personelNumber);
		this.setPassword(password);
		this.subordinates = new ArrayList<Policeman>();
	
	}

}
