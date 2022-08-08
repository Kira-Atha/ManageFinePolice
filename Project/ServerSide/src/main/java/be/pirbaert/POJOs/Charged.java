package be.pirbaert.POJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import be.pirbaert.DAOs.DAO;
import be.pirbaert.DAOs.FactoryDAO;

public class Charged implements Serializable{
	private static final long serialVersionUID = -2228334829855218584L;
	private int id;
	private String firstname;
	private String lastname;
	private String address;
	private List <Fine> fines;
	private static FactoryDAO adf = new FactoryDAO();
	private static DAO<Charged>chargedDAOs = adf.getChargedDAO();
	private static DAO<Fine> fineDAOs = adf.getFineDAO();
	
	public Charged() {}
	
	public Charged(int id, String firstname, String lastname, String address) {
		this.setId(id);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setAddress(address);
		fines = new ArrayList<Fine>();
	}
	
	public Charged(String firstname, String lastname, String address) {
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
			
			//DAO CREATE
			/*
			if(fineDAOs.create(fine)) {
				return true;
			}*/
		}
		return false;
	}
	
	public static Charged getCharged(int id) {
		return chargedDAOs.find(id);
	}
	
	public static List<Charged> getAllChargeds(){
		return chargedDAOs.findAll();
	}
	public boolean create() {
		if(!Objects.isNull(this)) {
			return(chargedDAOs.create(this));
		}
		return false;
	}
}
