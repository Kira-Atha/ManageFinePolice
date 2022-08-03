package be.pirbaert.POJOc;

import java.io.Serializable;
import java.util.List;
import be.pirbaert.DAOc.TypeVehicleDAO;


public class TypeVehicle implements Serializable{
	private static final long serialVersionUID = -974036176869820237L;
	private String name;
	private int id;
	private static TypeVehicleDAO typeVehicleDAOc= new TypeVehicleDAO();
	
	public TypeVehicle() {}
	
	public TypeVehicle(int id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	
	public static List<TypeVehicle> getAllTypes() {
		return typeVehicleDAOc.findAll();
	}
	
	public static TypeVehicle getType(int id) {
		return typeVehicleDAOc.find(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
