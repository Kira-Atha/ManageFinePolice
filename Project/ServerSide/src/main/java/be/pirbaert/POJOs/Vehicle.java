package be.pirbaert.POJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import be.pirbaert.DAOs.DAO;
import be.pirbaert.DAOs.FactoryDAO;

public class Vehicle implements Serializable{
	private static final long serialVersionUID = 5524624060193895070L;
	private int id;
	private List <Fine> fines;
	private Registration registration;
	private TypeVehicle type;
	private static FactoryDAO afd = new FactoryDAO();
	private static DAO<Vehicle> vehicleDAOs = afd.getVehicleDAO();
	
	public Vehicle() {}
	public Vehicle(int id,Registration registration,TypeVehicle type) {
		this.id = id;
		this.registration = registration;
		this.type = type;
		this.fines = new ArrayList<Fine>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Fine> getFines() {
		return fines;
	}

	public void setFines(List<Fine> fines) {
		this.fines = fines;
	}

	public Registration getRegistration() {
		return registration;
	}

	public void setRegistration(Registration registration) {
		this.registration = registration;
	}

	public TypeVehicle getType() {
		return type;
	}

	public void setType(TypeVehicle type) {
		this.type = type;
	}
	public static Vehicle getVehicle(int id) {
		return vehicleDAOs.find(id);
	}
	
	public static List<Vehicle>getAllVehicles() {
		return vehicleDAOs.findAll();
	}
}
