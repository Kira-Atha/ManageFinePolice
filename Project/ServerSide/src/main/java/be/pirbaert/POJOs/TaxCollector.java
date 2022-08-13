package be.pirbaert.POJOs;

public class TaxCollector extends Account{
	private static final long serialVersionUID = -7253989458148531214L;


	public TaxCollector(int id,String personelNumber, String password) {
		super(id,personelNumber, password);
		this.setType(this.getClass().getSimpleName());
		
	}
	
	public TaxCollector(int id,String personelNumber) {
		super(id,personelNumber);
		this.setType(this.getClass().getSimpleName());
		
	}


	public TaxCollector(String personelNumber, String password) {
		super(personelNumber, password);
		this.setType(this.getClass().getSimpleName());

	}
}