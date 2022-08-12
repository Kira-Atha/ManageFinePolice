package be.pirbaert.API;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import be.pirbaert.POJOs.Account;
import be.pirbaert.POJOs.Charged;
import be.pirbaert.POJOs.Fine;
import be.pirbaert.POJOs.Policeman;
import be.pirbaert.POJOs.Vehicle;
import be.pirbaert.POJOs.Violation;

@Path("/fine")
public class APIFine {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getFine(@PathParam("id") int id) {
		Fine fine = Fine.getFine(id);
		
		return Response
				.status(Status.OK)
				.entity(fine)
				.build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllFine() {
		List<Fine> allFines = Fine.getAllFines();
		
		return Response
				.status(Status.OK)
				.entity(allFines)
				.build();
	}
	
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response createFine(
			@FormParam("ids_violation") String ids_violation,
			@FormParam("id_policeman") int id_policeman,
			@FormParam("id_vehicle") int id_vehicle,
			@FormParam("comment")String comment,
			@FormParam("date") String s_date,
			@FormParam("id_charged")int id_charged,
			@FormParam("validated")int validated)
	{
		Policeman policeman = (Policeman) Account.getAccount(id_policeman);
		Charged charged = null;
		if(id_charged!=0) {
			charged = Charged.getCharged(id_charged);
		}
		// Les ids ont reçus sous forme de string, split pour en faire un tableau, puis add dans une liste pour le constructeur de fine
		String[] ids = ids_violation.split(";");
		List<Violation> violations = new ArrayList<Violation>();
		for(int i = 0; i < ids.length;i++) {
			violations.add(Violation.getViolation(Integer.parseInt(ids[i])));
		}
		  
	    Date date=null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(s_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Fine fine = new Fine(violations,policeman,Vehicle.getVehicle(id_vehicle),comment,date,charged);
		//System.out.println("API CHARGED IS : "+fine.getCharged().getFirstname());
		fine.setValidated(intToBool(validated));
		
		if(policeman.createFine(fine)) {
			return Response
					.status(Status.CREATED)
					.header("Location","fine"+fine.getId())
					.build();
		}else {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
//accept
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateFine(
			@FormParam("id_fine") int id_fine,
			@FormParam("validated") int validated,
			@FormParam("letterSent")int letterSent){
		Fine fine = Fine.getFine(id_fine);

		fine.setValidated(intToBool(validated));
		fine.setLetterSent(intToBool(letterSent));
		if(fine.update()) {
			return Response
					.status(Status.NO_CONTENT)
					.build();
		}
		else return Response.status(Status.NOT_FOUND).build();
	}
//decline
	@DELETE
	@Path("{id}")
	public Response deleteFine(@PathParam("id") int id) {
		Fine fine = Fine.getFine(id);
		if(fine.delete()) {
			return Response.status(Status.NO_CONTENT).build();
		}
		else return Response.status(Status.NOT_FOUND).build();
	}

	
	public static boolean intToBool(int val) {
	    if (val <= 0) {
	    	return false;
	    }
	    return true;
	}
}
