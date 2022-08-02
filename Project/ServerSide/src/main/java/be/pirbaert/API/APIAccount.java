package be.pirbaert.API;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.pirbaert.POJOs.Account;

@Path("/account")
public class APIAccount extends API {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getAccount(@PathParam("id") int id) {
		System.out.print("API account");
		Account account = Account.getAccount(id);
		
		return Response
				.status(Status.OK)
				.entity(account)
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAccount() {
		return Response.status(Status.OK).build();
		
	}
}
