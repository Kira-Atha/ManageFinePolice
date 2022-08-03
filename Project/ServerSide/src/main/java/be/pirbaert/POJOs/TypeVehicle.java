package be.pirbaert.POJOs;

import java.io.Serializable;
import java.util.List;

import be.pirbaert.DAOs.DAO;
import be.pirbaert.DAOs.FactoryDAO;

public class TypeVehicle implements Serializable{
	private static final long serialVersionUID = -974036176869820237L;
	private String name;
	private int id;
	private static FactoryDAO afd = new FactoryDAO();
	private static DAO<TypeVehicle> typeVehicleDAOs= afd.getTypeVehicleDAO();
	
	public TypeVehicle() {}
	
	public TypeVehicle(int id, String name) {
		this.setId(id);
		this.setName(name);
	}
	
	
	public static List<TypeVehicle> getAllTypes() {
		return typeVehicleDAOs.findAll();
	}
	
	public static TypeVehicle getType(int id) {
		return typeVehicleDAOs.find(id);
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
