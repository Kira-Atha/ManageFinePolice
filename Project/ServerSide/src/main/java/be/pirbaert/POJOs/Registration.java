package be.pirbaert.POJOs;

import java.io.Serializable;
import java.util.List;

import be.pirbaert.DAOs.DAO;
import be.pirbaert.DAOs.FactoryDAO;

public class Registration implements Serializable{
	private static final long serialVersionUID = -279361654562947993L;
	private int id;
	private String serialNumber;
	private static FactoryDAO afd = new FactoryDAO();
	private static DAO<Registration> registrationDAOs = afd.getRegistrationDAO();
	
	public Registration() {}
	
	public Registration(int id, String serialNumber) {
		this.setId(id);
		this.setSerialNumber(serialNumber);
	}
	
	public Registration(String serialNumber) {
		this.setSerialNumber(serialNumber);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	public static Registration getRegistration(int id) {
		return registrationDAOs.find(id);
	}
	
	public static List<Registration> getAllRegistrations(){
		return registrationDAOs.findAll();
	}
	
	public boolean save() {
		return registrationDAOs.create(this);
	}
	
	public boolean delete() {
		return registrationDAOs.delete(this);

	}
	
	public boolean update() {
		return registrationDAOs.update(this);

	}
}
