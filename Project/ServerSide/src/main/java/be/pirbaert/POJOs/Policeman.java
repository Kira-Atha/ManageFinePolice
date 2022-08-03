package be.pirbaert.POJOs;

import java.util.ArrayList;
import java.util.List;

public class Policeman extends Account{
	private List <Fine> fines;
	
	public Policeman(int id,String personelNumber, String password) {
		super(id,personelNumber, password);
		this.setType(this.getClass().getSimpleName());
		setFines(new ArrayList<Fine>());
	}

	public List <Fine> getFines() {
		return fines;
	}

	public void setFines(List <Fine> fines) {
		this.fines = fines;
	}

}
