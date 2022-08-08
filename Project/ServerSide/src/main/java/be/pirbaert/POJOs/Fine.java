package be.pirbaert.POJOs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import be.pirbaert.DAOs.DAO;
import be.pirbaert.DAOs.FactoryDAO;
import be.pirbaert.DAOs.FineDAO;


public class Fine implements Serializable {
	private static final long serialVersionUID = 7830843808655380688L;
	private int id;
	private String commentary ;
	private Date date;
	private boolean validated;
	private List<Violation> violations;
	private Policeman policeman;
	private Charged charged;
	private Vehicle vehicle;
	private float totalPrice =0;
	private static FactoryDAO afd = new FactoryDAO();
	private static DAO<Fine> fineDAOs = afd.getFineDAO();
	private static DAO<Charged> chargedDAOs = afd.getChargedDAO();
	private static DAO<Vehicle> vehicleDAOs = afd.getVehicleDAO();
	
	public Fine() {}
	
	public Fine(int id,List<Violation> violations,Policeman policeman,Vehicle vehicle,String commentary,Date date,Charged charged) {
		this.violations= new ArrayList<Violation>();
		this.id=id;
		for(Violation violation : violations) {
			this.violations.add(violation);
		}
		this.policeman = policeman;
		this.charged = charged;
		this.vehicle = vehicle;
		this.commentary = commentary;
		this.date = date;
		this.validated = false;
		this.totalPrice = this.getTotalPrice();
	}
	// Si le véhicule n'est pas dans la liste des choix, alors le fine va le créer
	public Fine(int id,List<Violation> violations,Policeman policeman,String commentary,Date date,Charged charged,Registration registration,TypeVehicle type) {
		this.violations= new ArrayList<Violation>();
		this.id=id;
		for(Violation violation : violations) {
			this.violations.add(violation);
		}
		this.policeman = policeman;
		this.commentary = commentary;
		this.date = date;
		if(!Objects.isNull(charged)) {
			this.charged = charged;
		}
		this.validated = false;
		this.totalPrice = this.getTotalPrice();
		this.setVehicle(new Vehicle(0,registration,type));
	}
	
	public Fine(List<Violation> violations,Policeman policeman,Vehicle vehicle,String commentary,Date date,Charged charged) {
		this.violations= new ArrayList<Violation>();
		for(Violation violation : violations) {
			this.violations.add(violation);
		}
		this.policeman = policeman;
		this.vehicle = vehicle;
		this.commentary = commentary;
		this.date = date;
		this.validated = false;
		this.totalPrice = this.getTotalPrice();
	}
	
	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isValidated() {
		return validated;
	}

	public void setValidated(boolean validated) {
		this.validated = validated;
	}

	public List<Violation> getViolations() {
		return violations;
	}

	public void setViolations(List<Violation> violations) {
		this.violations = violations;
	}

	public Policeman getPoliceman() {
		return policeman;
	}

	public void setPoliceman(Policeman policeman) {
		this.policeman = policeman;
	}

	public Charged getCharged() {
		return charged;
	}

	public void setCharged(Charged charged) {
		this.charged = charged;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getTotalPrice(){
		if(!Objects.isNull(violations)) {
			for(Violation violation : violations) {
				if(!Objects.isNull(violation)) {
					this.totalPrice += violation.getPrice();
				}
			}
		}
		return this.totalPrice;
	}
	
	public static Fine getFine(int id) {
		return fineDAOs.find(id);
	}
	
	public static List<Fine> getAllFines(){
		return fineDAOs.findAll();
	}
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		}
			
		if((o == null) || (o.getClass() != this.getClass())) {
			return false;
		}

		final Fine test = (Fine)o;
		return this.getId() == (test.getId());
	}
	@Override
	public int hashCode() {
		return this.getId();
	}
}
