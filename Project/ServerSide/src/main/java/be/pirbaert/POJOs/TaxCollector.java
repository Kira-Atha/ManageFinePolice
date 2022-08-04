package be.pirbaert.POJOs;

public class TaxCollector extends Account{
	
	public TaxCollector(int id,String personelNumber, String password) {
		super(id,personelNumber, password);
		this.setType(this.getClass().getSimpleName());
		
	}


	public TaxCollector(String personelNumber, String password) {
		super(personelNumber, password);
		this.setType(this.getClass().getSimpleName());

	}
}