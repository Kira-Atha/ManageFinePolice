package be.pirbaert.POJOs;

import java.util.ArrayList;
import java.util.List;

public class Chief extends Policeman {
	private List<Policeman> subordinates;
	
	public Chief(int id,String personelNumber, String password) {
		super(id,personelNumber, password);
		this.setType(this.getClass().getSimpleName());
		
	}
}
