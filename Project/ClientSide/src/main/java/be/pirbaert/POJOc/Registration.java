package be.pirbaert.POJOc;

import java.io.Serializable;
import java.util.List;
import be.pirbaert.DAOc.RegistrationDAO;

public class Registration implements Serializable{
	private static final long serialVersionUID = -27936165462947993L;
	private int id;
	private String serialNumber;
	//private Vehicle vehicle;
	private static RegistrationDAO registrationDAOc = new RegistrationDAO();
	
	public Registration() {}
	
	public Registration(int id, String serialNumber) {
		this.setId(id);
		this.setSerialNumber(serialNumber);
		//this.setVehicle(vehicle);
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
		return registrationDAOc.find(id);
	}
	
	public static List<Registration> getAllRegistrations(){
		return registrationDAOc.findAll();
	}
	
}

