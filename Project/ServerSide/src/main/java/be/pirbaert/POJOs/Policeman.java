package be.pirbaert.POJOs;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import be.pirbaert.DAOs.DAO;

public class Policeman extends Account{
	private static final long serialVersionUID = 8562421127622269285L;
	private List <Fine> fines;
	private static DAO<Fine> fineDAOs = afd.getFineDAO();
	
	public Policeman(int id,String personelNumber, String password) {
		super(id,personelNumber, password);
		this.setType(this.getClass().getSimpleName());
		setFines(new ArrayList<Fine>());
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

	public List <Fine> getFines() {
		return fines;
	}

	public void setFines(List <Fine> fines) {
		this.fines = fines;
	}
	
	public boolean createFine(Fine fine) {
		if(!Objects.isNull(fine)) {
			return fineDAOs.create(fine);
		}
		return false;
	}
}
