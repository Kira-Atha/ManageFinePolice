package be.pirbaert.API;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.pirbaert.POJOs.Account;
import be.pirbaert.POJOs.Administrator;
import be.pirbaert.POJOs.Chief;
import be.pirbaert.POJOs.Policeman;
import be.pirbaert.POJOs.TaxCollector;

@Path("/account")
public class APIAccount{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getAccount(@PathParam("id") int id) {
		Account account = Account.getAccount(id);
		
		return Response
				.status(Status.OK)
				.entity(account)
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAccount() {
		List<Account> allAccounts = Account.getAllAccounts();
		
		return Response
				.status(Status.OK)
				.entity(allAccounts)
				.build();
	}
	
	
	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createAccount(
			@FormParam("personnelNumber") String personnelNumber,
			@FormParam("password") String password,
			@FormParam("type") String type) {
		
		Account account;
		
		switch(type) {
		
		case "Administrator" :
			account = new Administrator(personnelNumber,password);
			break;
		
		case "Policeman" :
			account = new Policeman(personnelNumber,password);
			break;
		
		case "Chief" :
			account = new Chief(personnelNumber,password);
			break;
			
		case "TaxCollector" :
			account = new TaxCollector(personnelNumber,password);
			break;
		
		default :
			return Response.status(Status.BAD_REQUEST).build();
		
		}
		
		if(account.save()) {
			return Response
					.status(Status.CREATED)
					.header("Location","account"+account.getId())
					.build();
		}
		else return Response.status(Status.CONFLICT).build();
				
		
		
	}
	
	@GET
	@Path("/test")
	public boolean test() {
		
		
		Chief acTest = new Chief("ccDDcc1","testtest");

			
		return acTest.save();
		
	}
}
