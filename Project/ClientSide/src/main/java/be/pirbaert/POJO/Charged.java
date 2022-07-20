package be.pirbaert.POJO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Charged {
	
	private String firstname;
	private String lastname;
	private String address;
	
	private List <Fine> fines;

	public Charged(Fine fine) {
		fines = new ArrayList<Fine>();
	}

	public boolean addFine(Fine fine) {
		this.fines.add(fine);
		return false;
	}
}
