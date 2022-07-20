package be.pirbaert.POJO;

import java.util.ArrayList;
import java.util.List;

public class Policeman extends Account{
	private List <Fine> fines;

	public Policeman(String personelNumber,String password) {
		setFines(new ArrayList<Fine>());
		this.setPersonnelNumber(personelNumber);
		this.setPassword(password);
	}

	
	public List <Fine> getFines() {
		// Chercher en DB pour compléter
		return fines;
	}

	public void setFines(List <Fine> fines) {
		this.fines = fines;
	}
}
