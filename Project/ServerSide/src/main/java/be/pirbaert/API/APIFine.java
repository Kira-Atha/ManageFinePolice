package be.pirbaert.API;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.pirbaert.POJOs.Account;
import be.pirbaert.POJOs.Fine;

@Path("/fine")
public class APIFine {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getFine(@PathParam("id") int id) {
		Fine fine = Fine.getFine(id);
		
		return Response
				.status(Status.OK)
				.entity(fine)
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllFine() {
		List<Fine> allFines = Fine.getAllFines();
		
		return Response
				.status(Status.OK)
				.entity(allFines)
				.build();
	}
}
