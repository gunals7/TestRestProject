package com.events.resource;



/**
 * @author guns
 */

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.events.model.Event;
import com.events.dao.EventsDAO;;
@Path("/events")
public class EventResource{


	@Path("{json}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response createEvents(Event event)  throws Exception {

		String output = event.toString();
		System.out.println("post Json ="+ output);

		//jsonObject
		EventsDAO.getInstance().createEvent(event.getData());

		String result = "@Produces(\"application/json\") Output: \n\n event added \n\n" + output;
		return Response.status(200).entity(result).build();
	}

	@Path("{id}")
	@GET
	@Produces("application/json")
	public Response getEvents(@PathParam("id") Integer id)  throws Exception {


		Event event = EventsDAO.getInstance().getEvent(id);

		String result = "@Produces(\"application/json\")" + event ;
		return Response.status(200).entity(result).build();
	}


	@GET
	@Produces("application/json")
	public Response getAllEvents()  throws Exception {


		String rs = EventsDAO.getInstance().getAllEvents();

		String result = "@Produces(\"application/json\") Output: \n\n event is \n\n" + rs ;
		return Response.status(200).entity(result).build();
	}


	@Path("{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response updatEvents(@PathParam("id") Integer id,Event event)  throws Exception {

		String output = event.toString();
		System.out.println("post Json ="+ output);

		//jsonObject
		EventsDAO.getInstance().updateEvent(id,event.getData());

		String result = "@Produces(\"application/json\") Output: \n\n event updated \n\n" + output;
		return Response.status(200).entity(result).build();
	}



	@Path("{id}")
	@DELETE
	@Produces("application/json")
	public Response deleteEvents(@PathParam("id") Integer id)  throws Exception {



		EventsDAO.getInstance().deleteEvent(id);

		String result = "@Produces(\"application/json\") Output: \n\n events deleted is \n\n" + id  ;
		return Response.status(200).entity(result).build();
	}


	@DELETE
	@Produces("application/json")
	public Response deleteAllEvents()  throws Exception {


		EventsDAO.getInstance().deleteAllEvents();

		String result = "@Produces(\"application/json\") Output: \n\n events deleted is \n\n";
		return Response.status(200).entity(result).build();
	}


}
