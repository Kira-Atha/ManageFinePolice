package be.pirbaert.API;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.pirbaert.POJOs.TypeVehicle;

@Path("/typeVehicle")
public class APITypeVehicle {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getTypeVehicle(@PathParam("id") int id) {
		TypeVehicle typeVehicle = TypeVehicle.getType(id);
		
		return Response
				.status(Status.OK)
				.entity(typeVehicle)
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTypeVehicle() {
		List<TypeVehicle> allTypeVehicles = TypeVehicle.getAllTypes();
		
		return Response
				.status(Status.OK)
				.entity(allTypeVehicles)
				.build();
	}
}