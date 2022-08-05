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

import be.pirbaert.POJOs.Charged;
import be.pirbaert.POJOs.Fine;
import be.pirbaert.POJOs.Registration;
import be.pirbaert.POJOs.TypeVehicle;
import be.pirbaert.POJOs.Vehicle;

@Path("/charged")
public class APICharged {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getCharged(@PathParam("id") int id) {
		Charged charged = Charged.getCharged(id);
		
		return Response
				.status(Status.OK)
				.entity(charged)
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllCharged() {
		List<Charged> allChargeds = Charged.getAllChargeds();
		
		return Response
				.status(Status.OK)
				.entity(allChargeds)
				.build();
	}
	
	/*
	@POST
	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createCharged(
			@FormParam("firstname") String firstname,
			@FormParam("lastname") String lastname,
			@FormParam("address") String address,
			@FormParam("fine")Fine fine)
	{
		if(Objects.isNull(firstname) || Objects.isNull(lastname)||Objects.isNull(address) || firstname =="" || lastname =="" || address=="") {
			return Response.status(Status.BAD_REQUEST).build();
		}
		Charged charged = new Charged(firstname,lastname,address);
	
		if(fine.createCharged(charged)) {
			return Response
					.status(Status.CREATED)
					.header("Location","charged"+charged.getId())
					.build();
		}else {
			return Response.status(Status.CONFLICT).build();
		}
	}
	*/
}
