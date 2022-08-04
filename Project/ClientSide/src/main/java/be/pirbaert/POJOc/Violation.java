package be.pirbaert.POJOc;

import java.io.Serializable;
import java.util.List;

import be.pirbaert.DAOc.ViolationDAO;


public class Violation implements Serializable {
	private static final long serialVersionUID = 3642694548064734723L;
	private int id;
	private String name;	
	private String description;	
	private float price;
	private static ViolationDAO violationDAOc = new ViolationDAO();
	
	public Violation() {}
	
	public Violation(int id,String name,String description,float price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String decription) {
		this.description = decription;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	public static Violation getViolation(int id) {
		return violationDAOc.find(id);
	}
	
	public static List<Violation> getAllViolations() {
		return violationDAOc.findAll();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}

