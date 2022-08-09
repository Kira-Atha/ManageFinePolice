package be.pirbaert.POJOc;

import java.util.ArrayList;
import java.util.List;

public class Policeman extends Account{
	private static final long serialVersionUID = 2142714243766177482L;
	private List <Fine> fines;

	public Policeman() {}
	public Policeman(int id,String personelNumber,String password) {
		super(id,personelNumber,password);
		setFines(new ArrayList<Fine>());
		this.type=this.getClass().getSimpleName();
	}
	public Policeman(String personelNumber,String password) {
		super(personelNumber,password);
		setFines(new ArrayList<Fine>());
		this.type=this.getClass().getSimpleName();
	}

	
	public List <Fine> getFines() {
		// Chercher en DB pour compléter
		return fines;
	}

	public void setFines(List <Fine> fines) {
		this.fines = fines;
	}
}
