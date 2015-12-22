package com.events.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.events.dao.BaseDAO;
import com.events.dao.EventsDAO;
import com.events.model.Event;

public class EventServiceImpl implements EventService {
	
private static EventService instance = null;
	
	private EventServiceImpl(){
		
	}

	
	
	public static EventService getInstance(){

		if (instance == null){
		   //Thread safe 	
		   synchronized(EventServiceImpl.class){	
			  if (instance == null) {
			     instance = new EventServiceImpl();
			  }
		   }	
		}
		return instance;
	}
	
	
	public  void createEvent(Event event)
	{

		EventsDAO.getInstance().createEvent(event);
	} 
	
	public  void updateEvent(Integer id, Event event) 
	{

		EventsDAO.getInstance().updateEvent(id, event);		


	}

	public  void deleteEvent(Integer id) 
	{
		EventsDAO.getInstance().deleteEvent(id);
	}
	public  void deleteAllEvents() 
	{
		
		EventsDAO.getInstance().deleteAllEvents();
	}

	public  Event getEvent(Integer id) 
	{

		return(EventsDAO.getInstance().getEvent(id));
		

	}


	public  ArrayList<Event> getAllEvents() 
	{

		return(EventsDAO.getInstance().getAllEvents());
		
	}

	

	

}
