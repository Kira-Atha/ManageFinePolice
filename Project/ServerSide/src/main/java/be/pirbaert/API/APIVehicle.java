package be.pirbaert.API;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.pirbaert.POJOs.Vehicle;

@Path("/vehicle")
public class APIVehicle {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getVehicle(@PathParam("id") int id) {
		Vehicle vehicle = Vehicle.getVehicle(id);
		
		return Response
				.status(Status.OK)
				.entity(vehicle)
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllVehicle() {
		List<Vehicle> allVehicles = Vehicle.getAllVehicles();
		
		return Response
				.status(Status.OK)
				.entity(allVehicles)
				.build();
	}
}