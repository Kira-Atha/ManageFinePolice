package be.pirbaert.POJOs;

import java.util.ArrayList;
import java.util.List;

public class Chief extends Policeman {
	private List<Policeman> subordinates;
	
	public Chief() {
		this.subordinates = new ArrayList<Policeman>();
	
	}

}
