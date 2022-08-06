package be.pirbaert.POJOs;
public class Administrator extends Account {
	private static final long serialVersionUID = 1L;
	
	public Administrator(int id,String personelNumber, String password) {
		super(id,personelNumber, password);
		this.setType(this.getClass().getSimpleName());
	}
	
	public Administrator(int id,String personelNumber) {
		super(id,personelNumber);
		this.setType(this.getClass().getSimpleName());
	}

	public Administrator(String personelNumber, String password) {
		super(personelNumber, password);
		 this.setType(this.getClass().getSimpleName());
	}

}
