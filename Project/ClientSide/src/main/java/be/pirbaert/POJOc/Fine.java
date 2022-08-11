package be.pirbaert.POJOc;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import be.pirbaert.DAOc.FineDAO;


public class Fine implements Serializable {
	private static final long serialVersionUID = 783084380865538688L;
	private int id;
	private String commentary ;
	private Date date;
	private boolean validated;
	private boolean letterSent;
	private List<Violation> violations;
	private Policeman policeman;
	private Charged charged;
	private Vehicle vehicle;
	private static FineDAO fineDAOc = new FineDAO();
	private float totalPrice =0;
	
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
		this.totalPrice = 0;
		this.setLetterSent(false);
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
		this.totalPrice = 0;
		if(!Objects.isNull(violations)) {
			for(Violation violation : violations) {
				if(!Objects.isNull(violation)) {
					this.totalPrice += violation.getPrice();
				}
			}
		}
		return this.totalPrice;
	}
	
	public boolean isLetterSent() {
		return letterSent;
	}

	public void setLetterSent(boolean letterSent) {
		this.letterSent = letterSent;
	}

	public static Fine getFine(int id) {
		return fineDAOc.find(id);
	}
	
	public static List<Fine> getAllFines(){
		return fineDAOc.findAll();
	}
	
	public boolean create() {
		return fineDAOc.create(this);
	}
	
	public boolean update() {
		return fineDAOc.update(this);
	}
	
	public boolean delete() {
		return fineDAOc.delete(this);
	}
	@Override
	public String toString() {
		return " Id du fine : "+this.getId()+" Commentaire : "+this.getCommentary();
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