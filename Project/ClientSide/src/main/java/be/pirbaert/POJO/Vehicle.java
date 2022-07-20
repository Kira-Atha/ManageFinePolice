package be.pirbaert.POJO;

import java.util.ArrayList;
import java.util.List;

public class Vehicle {
	
	private List <Fine> fines;
	private Registration registration;
	private TypeVehicle type;
	
	public Vehicle() {
		this.fines = new ArrayList<Fine>();
	}
}
