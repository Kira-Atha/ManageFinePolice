package be.pirbaert.API;

import java.util.List;

import javax.ws.rs.GET;
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
}
