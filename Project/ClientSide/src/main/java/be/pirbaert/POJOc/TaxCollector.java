package be.pirbaert.POJOc;

public class TaxCollector extends Account{
	private static final long serialVersionUID = -3688448704580512L;
	
	public TaxCollector() {}
	public TaxCollector(int id,String personelNumber,String password) {
		super(id,personelNumber,password);
		
		this.type=this.getClass().getSimpleName();
	}
	public TaxCollector(String personelNumber,String password) {
		super(personelNumber,password);
		this.type=this.getClass().getSimpleName();
	}
}
