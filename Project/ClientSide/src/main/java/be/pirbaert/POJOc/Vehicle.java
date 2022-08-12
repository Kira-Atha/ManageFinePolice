package be.pirbaert.POJOc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import be.pirbaert.DAOc.VehicleDAO;


public class Vehicle implements Serializable{
	private static final long serialVersionUID = 5524624060193895070L;
	private int id;
	private Registration registration;
	private TypeVehicle type;
	private static VehicleDAO vehicleDAOc = new VehicleDAO();
	
	public Vehicle() {}
	
	public Vehicle(int id,Registration registration,TypeVehicle type) {
		this.id = id;
		this.registration = registration;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return vehicleDAOc.find(id);
	}
	
	public static List<Vehicle>getAllVehicles() {
		return vehicleDAOc.findAll();
	}
	
	public boolean create() {
		List <Vehicle> allVehicles = Vehicle.getAllVehicles();
		for(Vehicle veh : allVehicles) {
			if(this.equals(veh)) {
				//existe déjà
				return false;
			}
		}
		return vehicleDAOc.create(this);
	}
	
	// Pour le cas où je veux ajouter un camion sans plaque alors qu'il existe déjà
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
			
		if((o == null) || (o.getClass() != this.getClass())) {
			return false;
		}

		final Vehicle test = (Vehicle)o;
		//System.out.println("IN THE EQUALS Client => "+this.getRegistration().getSerialNumber());
		if(!Objects.isNull(this.getRegistration())) {
			if(Objects.isNull(test.getRegistration())){
				return false;
			}
			return this.getId() == (test.getId()) || (this.getRegistration().getSerialNumber().toLowerCase().equals(test.getRegistration().getSerialNumber().toLowerCase()) && this.getType().equals(test.getType()));
		}else {
			return this.getId() == (test.getId()) || this.getType().equals(test.getType());
		}
	}
	@Override
	public int hashCode() {
		if(!Objects.isNull(this.getRegistration())) {
			return this.getId()+this.getType().getName().hashCode()+this.getRegistration().getSerialNumber().hashCode();
		}else {
			return this.getId()+this.getType().getName().hashCode();
		}
	}	
}
