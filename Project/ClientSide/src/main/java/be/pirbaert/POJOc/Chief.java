package be.pirbaert.POJOc;

import java.util.ArrayList;
import java.util.List;

public class Chief extends Policeman{
	private static final long serialVersionUID = 4053951674963753923L;
	private List<Policeman> subordinates;
	
	public Chief() {}
	public Chief(int id,String personelNumber,String password) {
		super(id,personelNumber,password);
		this.setSubordinates(new ArrayList<Policeman>());
		this.type=this.getClass().getSimpleName();
	}
	public Chief(String personelNumber,String password) {
		super(personelNumber,password);
		this.setSubordinates(new ArrayList<Policeman>());
		this.type=this.getClass().getSimpleName();
	}

	public List<Policeman> getSubordinates() {
		return subordinates;
	}

	public void setSubordinates(List<Policeman> subordinates) {
		this.subordinates = subordinates;
	}

}
