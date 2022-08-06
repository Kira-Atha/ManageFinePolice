package be.pirbaert.POJOs;

import java.util.ArrayList;
import java.util.List;

public class Chief extends Policeman {
	private List<Policeman> subordinates;
	
	public Chief(int id,String personelNumber, String password) {
		super(id,personelNumber, password);
		this.setType(this.getClass().getSimpleName());
		this.subordinates = new ArrayList<Policeman>();
		
	}
	
	public Chief(int id,String personelNumber) {
		super(id,personelNumber);
		this.setType(this.getClass().getSimpleName());
		this.subordinates = new ArrayList<Policeman>();
		
	}
	
	public Chief(String personelNumber, String password) {
		super(personelNumber, password);
		this.setType(this.getClass().getSimpleName());
		
	}
}
