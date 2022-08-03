package be.pirbaert.API;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.pirbaert.POJOs.Registration;

@Path("/registration")
public class APIRegistration {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getRegistration(@PathParam("id") int id) {
		Registration registration = Registration.getRegistration(id);
		
		return Response
				.status(Status.OK)
				.entity(registration)
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRegistration() {
		List<Registration> allRegistrations = Registration.getAllRegistrations();
		
		return Response
				.status(Status.OK)
				.entity(allRegistrations)
				.build();
	}
}