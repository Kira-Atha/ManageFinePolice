package be.pirbaert.API;

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
import be.pirbaert.POJOs.Administrator;
import be.pirbaert.POJOs.Chief;
import be.pirbaert.POJOs.Fine;
import be.pirbaert.POJOs.Policeman;
import be.pirbaert.POJOs.Registration;
import be.pirbaert.POJOs.TaxCollector;
import be.pirbaert.POJOs.TypeVehicle;
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
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createVehicle(
			@FormParam("id_type") int id_type,
			@FormParam("id_registration") int id_registration)
	{
		if(id_type ==0) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		Registration reg = null;
		if(id_registration!=0) {
			reg = Registration.getRegistration(id_registration);
		}
		TypeVehicle type = TypeVehicle.getType(id_type);
		Vehicle vehicle = new Vehicle(reg,type);
	
		if(vehicle.create()) {
			return Response
					.status(Status.CREATED)
					.header("Location","vehicle"+vehicle.getId())
					.build();
		}else {
			return Response.status(Status.CONFLICT).build();
		}
	}
}