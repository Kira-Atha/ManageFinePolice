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

import be.pirbaert.POJOs.Registration;
import be.pirbaert.POJOs.TypeVehicle;

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
	

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createRegistration(
			@FormParam("serialNumber") String serialNumber ){
		
		Registration registration = new Registration(serialNumber);
		
		
		if(registration.save()) {
			return Response
					.status(Status.CREATED)
					.header("Location","registration"+registration.getId())
					.build();
		}
		else return Response.status(Status.CONFLICT).build();
		
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteRegistration(
			@FormParam("id") int id ){
		
		Registration registration = new Registration();
		registration.setId(id);
		
		
		if(registration.delete()) {
			return Response.status(Status.NO_CONTENT).build();
		}
		else return Response.status(Status.NOT_FOUND).build();
		
	}
	

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRegistration(			
			@FormParam("id") int id,
			@FormParam("serialNumber") String serialNumber) {
		
		Registration registration = new Registration(id,serialNumber);
		
		
		if(registration.update()) {
			return Response
					.status(Status.NO_CONTENT)
					.build();
		}
		else return Response.status(Status.NOT_FOUND).build();
		
	}
}