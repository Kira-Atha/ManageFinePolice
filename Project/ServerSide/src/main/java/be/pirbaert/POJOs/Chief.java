package be.pirbaert.POJOs;

import java.util.ArrayList;
import java.util.List;

public class Chief extends Policeman {
	private static final long serialVersionUID = -4394891119807101120L;
	private List<Policeman> subordinates;
	
	public Chief(int id,String personelNumber, String password) {
		super(id,personelNumber, password);
		this.setType(this.getClass().getSimpleName());
		this.setSubordinates(new ArrayList<Policeman>());
		
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

	public List<Policeman> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Policeman> subordinates) {
		this.subordinates = subordinates;
	}
}
