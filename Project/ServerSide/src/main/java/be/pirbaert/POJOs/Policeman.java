package be.pirbaert.POJOs;

import java.util.ArrayList;
import java.util.List;

public class Policeman extends Account{
	private List <Fine> fines;

	public Policeman() {
		fines = new ArrayList<Fine>();
	}
}
