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

import be.pirbaert.POJOs.TypeVehicle;
import be.pirbaert.POJOs.Violation;

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
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createTypeVehicle(
			@FormParam("name") String name ){
		
		TypeVehicle typeVehicle = new TypeVehicle(name);
		
		
		if(typeVehicle.save()) {
			return Response
					.status(Status.CREATED)
					.header("Location","typeVehicle"+typeVehicle.getId())
					.build();
		}
		else return Response.status(Status.CONFLICT).build();
		
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteTypeVehicle(
			@PathParam("id") int id ){
		
		TypeVehicle typeVehicle = new TypeVehicle();
		typeVehicle.setId(id);
		
		
		if(typeVehicle.delete()) {
			return Response.status(Status.NO_CONTENT).build();
		}
		else return Response.status(Status.NOT_FOUND).build();
		
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTypeVehicle(			
			@FormParam("id") int id,
			@FormParam("name") String name) {
		
		TypeVehicle typeVehicle = new TypeVehicle(id,name);
		
		
		if(typeVehicle.update()) {
			return Response
					.status(Status.NO_CONTENT)
					.build();
		}
		else return Response.status(Status.NOT_FOUND).build();
		
	}
}