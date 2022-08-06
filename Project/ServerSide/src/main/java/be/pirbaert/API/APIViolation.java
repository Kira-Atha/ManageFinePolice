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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.pirbaert.POJOs.Violation;

@Path("/violation")
public class APIViolation {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getViolation(@PathParam("id") int id) {
		Violation violation = Violation.getViolation(id);
		
		return Response
				.status(Status.OK)
				.entity(violation)
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllViolation() {
		List<Violation> allViolations = Violation.getAllViolations();
		
		return Response
				.status(Status.OK)
				.entity(allViolations)
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createViolation(
			@FormParam("name") String name,
			@FormParam("price") float price,
			@FormParam("description") String description) {
		
		Violation violation = new Violation(name,description,price);
		
		
		
		if(violation.save()) {
			return Response
					.status(Status.CREATED)
					.header("Location","violation"+violation.getId())
					.build();
		}
		else return Response.status(Status.CONFLICT).build();
		
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateViolation(			
			@FormParam("id") int id,
			@FormParam("name") String name,
			@FormParam("price") float price,
			@FormParam("description") String description) {
		
		Violation violation = new Violation(id,name,description,price);
		
		
		if(violation.update()) {
			return Response
					.status(Status.NO_CONTENT)
					.build();
		}
		else return Response.status(Status.NOT_FOUND).build();
		
	}
	@DELETE
	@Path("{id}")
	public Response deleteViolation(@PathParam("id") int id) {
		Violation violation = new Violation();
		violation.setId(id);
		
		if(violation.delete()) {
			return Response.status(Status.NO_CONTENT).build();
		}
		else return Response.status(Status.NOT_FOUND).build();
	}
}
