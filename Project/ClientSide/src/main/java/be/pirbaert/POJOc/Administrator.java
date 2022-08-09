package be.pirbaert.POJOc;
public class Administrator extends Account {
	private static final long serialVersionUID = -8218863053171331246L;
	
	public Administrator(int id,String personelNumber,String password) {
		super(id,personelNumber,password);
		this.type=this.getClass().getSimpleName();
	}

	public Administrator(String personelNumber,String password) {
		super(personelNumber,password);
		this.type=this.getClass().getSimpleName();
	}
	public Administrator() {}
}
