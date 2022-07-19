package be.pirbaert.POJO;

import java.util.Date;

public class Fine {
	private String commentary ;
	private Date date;
	private boolean validated;
	private Violation[] violations;
	private Policeman policeman;
	private Charged charged;
	private Vehicle vehicle;
	
	
	public float getTotalPrice(){
		int price = 0;
				
		for(Violation violation : violations) {
			price += violation.getPrice();
		}
		
		return price;
	}
}
