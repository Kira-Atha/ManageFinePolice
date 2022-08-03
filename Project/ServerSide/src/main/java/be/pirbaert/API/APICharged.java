package be.pirbaert.API;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.pirbaert.POJOs.Charged;

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
}
