package com.events.service;

import java.util.ArrayList;

import com.events.model.Event;

public interface EventService {
	
	public int createEvent(Event event);
	
	public void updateEvent( Event event);
	
	public void deleteEvent(Integer Id);
	
	public void deleteAllEvents();
	
	public Event getEvent(Integer Id);
	
	public ArrayList<Event> getAllEvents();
	

}
