package be.pirbaert.API;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
import be.pirbaert.POJOs.Charged;
import be.pirbaert.POJOs.Fine;
import be.pirbaert.POJOs.Policeman;
import be.pirbaert.POJOs.Vehicle;
import be.pirbaert.POJOs.Violation;

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
	
	/*
	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createFine(
			@FormParam("violations") List<Violation> violations,
			@FormParam("policeman") Policeman policeman,
			@FormParam("vehicle") Vehicle vehicle,
			@FormParam("commentary")String comment,
			@FormParam("date") Date date,
			@FormParam("charged")Charged charged)
	{
		if(Objects.isNull(violations) || Objects.isNull(policeman)||Objects.isNull(date)) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		Fine fine = new Fine(violations,policeman,vehicle,comment,date,charged);
	
		if(policeman.createFine(fine)) {
			return Response
					.status(Status.CREATED)
					.header("Location","fine"+charged.getId())
					.build();
		}else {
			return Response.status(Status.CONFLICT).build();
		}
	}
	*/
}
