package be.pirbaert.POJOs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Fine {
	private String commentary ;
	private Date date;
	private boolean validated;
	private List<Violation> violations;
	private Policeman policeman;
	private Charged charged;
	private Vehicle vehicle;
	
	public Fine(Violation violation,Policeman policeman,Vehicle vehicle) {
		this.violations= new ArrayList<Violation>();
		this.addViolation(violation);
		this.policeman = policeman;
		this.vehicle = vehicle;
	}
	public float getTotalPrice(){
		int price = 0;
				
		for(Violation violation : violations) {
			price += violation.getPrice();
		}
		
		return price;
	}
	
	public boolean addViolation(Violation violation) {
		//DAO
		this.violations.add(violation);
		/*
		if(DAOOK) {
			return true;
		}
		*/
		return false;
	}
}
