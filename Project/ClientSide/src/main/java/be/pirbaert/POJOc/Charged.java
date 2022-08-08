package be.pirbaert.POJOc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.pirbaert.DAOc.AccountDAO;
import be.pirbaert.DAOc.ChargedDAO;

public class Charged implements Serializable {
	private static final long serialVersionUID = 3895177306712728609L;
	private int id;
	private String firstname;
	private String lastname;
	private String address;
	private static ChargedDAO chargedDAOc = new ChargedDAO();
	private List <Fine> fines;
	
	public Charged() {}
	
	public Charged(int id, String firstname, String lastname, String address) {
		this.setId(id);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setAddress(address);
		fines = new ArrayList<Fine>();
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public boolean addFine(Fine fine) {
		if(!this.fines.contains(fine)) {
			this.fines.add(fine);
			
		}
		return false;
	}
	
	public static Charged getCharged(int id) {
		return chargedDAOc.find(id);
	}
	public boolean create() {
		return chargedDAOc.create(this);
	}
	
	public static List<Charged> getAllChargeds(){
		return chargedDAOc.findAll();
	}
}
