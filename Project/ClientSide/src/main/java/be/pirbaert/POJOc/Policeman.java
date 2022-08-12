package be.pirbaert.POJOc;

import java.util.ArrayList;
import java.util.List;

public class Policeman extends Account{
	private static final long serialVersionUID = 2142714243766177482L;
	private List <Fine> fines;
	private Chief chief;
	
	public Policeman() {}
	public Policeman(int id,String personelNumber,String password) {
		super(id,personelNumber,password);
		this.fines = new ArrayList<Fine>();
		this.type=this.getClass().getSimpleName();
	}
	public Policeman(String personelNumber,String password) {
		super(personelNumber,password);
		setFines(new ArrayList<Fine>());
		this.type=this.getClass().getSimpleName();
	}
	
	
	public List <Fine> getFines() {
		List<Fine> allFines = Fine.getAllFines();
		this.fines = new ArrayList<Fine>();
		for(Fine fine : allFines) {
			if(fine.getPoliceman().equals(this)) {
				fines.add(fine);
			}
		}
		return fines;
	}
	
	public void setFines(List <Fine> fines) {
		this.fines = fines;
	}
	
	public Chief getChief() {
		return chief;
	}
	public void setChief(Chief chief) {
		this.chief = chief;
	}
	public boolean createFine(Fine fine) {
		if(fine.create()) {
			this.fines.add(fine);
			return true;
		}
		return false;
	}
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
	// Si c'est un chief, il sera forcément aussi instanceof Policeman, je ne peux pas dire que la classe est différente et que ça retourne faux,
	// Sinon, dans les faits chief != policeman et ça retournera toujours faux quand je voudrais récupérer les fines du chief en passant par GetFines de Policeman 
		if((o == null) || this instanceof Policeman == false || o instanceof Policeman == false) {
			return false;
		}

		final Policeman test = (Policeman)o;
		return this.getId() == (test.getId());
	}
	@Override
	public int hashCode() {
		return this.getId();
	}	
}
