package be.pirbaert.POJOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import be.pirbaert.DAOs.DAO;


public class Policeman extends Account{
	private static final long serialVersionUID = 8562421127622269285L;
	private List <Fine> fines;
	private int chief;
	private static DAO<Fine> fineDAOs = afd.getFineDAO();
	
	public Policeman(int id,String personelNumber, String password) {
		super(id,personelNumber,password);
		this.fines = new ArrayList<Fine>();
		this.type=this.getClass().getSimpleName();
	}
	
	public Policeman(int id,String personelNumber) {
		super(id,personelNumber);
		this.setType(this.getClass().getSimpleName());
		setFines(new ArrayList<Fine>());
	}
	public Policeman(String personelNumber, String password) {
		super(personelNumber, password);
		this.setType(this.getClass().getSimpleName());
		setFines(new ArrayList<Fine>());
	}

	public void setFines(List <Fine> fines) {
		this.fines = fines;
	}
	
	public int getChief() {
		return chief;
	}
	public void setChief(int chief) {
		this.chief = chief;
	}

	public boolean createFine(Fine fine) {
		boolean success = false;
		if(!Objects.isNull(fine)) {
			// Permet ainsi de set l'id de fine
			success = fineDAOs.create(fine);
			if(success && !this.fines.contains(fine)) {
				this.fines.add(fine);
			}
		}
		return success;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
		if((o == null) || (o.getClass() != this.getClass())) {
			return false;
		}

		final Policeman test = (Policeman)o;
		return this.getId() == (test.getId());
	}
	@Override
	public int hashCode() {
		return this.getId();
	}	
	public boolean saveSetChief(int id_chief) {
		return accountDAOs.setChief(this,id_chief);
	}
	public static List<Policeman > getAll(){
		return accountDAOs.findAllPoliceman();
	}
}
