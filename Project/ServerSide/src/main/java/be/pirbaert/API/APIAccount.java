package be.pirbaert.API;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/connect")
	public Response connectAccount(
			@QueryParam("personelNumber") String personelNumber,
			@QueryParam("password") String password) {
		
		Account account = Account.connect(personelNumber,password);
		
		if(account != null) 
			
			return Response
					.status(Status.OK)
					.entity(account)
					.build();
		
		return Response
				.status(Status.BAD_REQUEST)
				.build();
		
	}
	
	
	@POST
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
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAccount(
			@FormParam("id") int id,
			@FormParam("personnelNumber") String personnelNumber,
			@FormParam("password") String password) {
		
		Account account  = new Account(id,personnelNumber,password);
		
		
		if(account.update()) {
			return Response
					.status(Status.NO_CONTENT)
					.build();
		}
		else return Response.status(Status.NOT_FOUND).build();
				
		
		
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAccount(@FormParam("personnelNumber") String personnelNumber) {
		Account account = new Account();
		account.setPersonnelNumber(personnelNumber);
		
		if(account.delete()) {
			return Response.status(Status.NO_CONTENT).build();
		}
		else return Response.status(Status.NOT_FOUND).build();
	}

}
