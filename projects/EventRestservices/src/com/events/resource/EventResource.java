package com.events.resource;



/**
 * @author guns
 */

import java.net.URI;
import java.util.ArrayList;

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
import com.events.service.EventService;
import com.events.service.EventServiceImpl;
import com.events.dao.EventsDAO;;
@Path("/events")
public class EventResource{


	

    private EventService eventService = EventServiceImpl.getInstance();

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces("application/json")
	public Response createEvents(Event event)  throws Exception {

		String output = event.toString();
		System.out.println("post Json ="+ output);

		
		eventService.createEvent(event);

		String result = "@Produces(\"application/json\") Output: \n\n event added \n\n" + event;
		return Response.status(200).entity(result).build();
		 //return Response.status(201)
	      //          .contentLocation(new URI("/events/1")).build();
	}

	@Path("{id}")
	@GET
	@Produces("application/json")
	public Response getEvents(@PathParam("id") Integer id)  throws Exception {

	    Event event = 	eventService.getEvent(id); 		
	    
	    if(event == null){
	    	return Response.status(404).entity("Resource not exist for event id").build();
	    }
	    
	    String result = "@Produces(\"application/json\")" + eventService.getEvent(id); ;
		
		return Response.status(200).entity(result).build();
	}


	@GET
	@Produces("application/json")
	public Response getAllEvents()  throws Exception {

		
		ArrayList<Event> events = eventService.getAllEvents();
		if (events.isEmpty()){
			return Response.status(404).entity("Resource not exist - No events in the system").build();
		}

		String result = "@Produces(\"application/json\") Output: \n\n event is \n\n" +  events ;
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
		eventService.updateEvent(id,event);

		String result = "@Produces(\"application/json\") Output: \n\n event updated \n\n" + output;
		return Response.status(200).entity(result).build();
	}



	@Path("{id}")
	@DELETE
	@Produces("application/json")
	public Response deleteEvents(@PathParam("id") Integer id)  throws Exception {

		
		
		eventService.deleteEvent(id);

		String result = "@Produces(\"application/json\") Output: \n\n events deleted is \n\n" + id  ;
		return Response.status(200).entity(result).build();
	}


	@DELETE
	@Produces("application/json")
	public Response deleteAllEvents()  throws Exception {

		
		eventService.deleteAllEvents();

		String result = "@Produces(\"application/json\") Output: \n\n events deleted is \n\n";
		return Response.status(200).entity(result).build();
	}


}
