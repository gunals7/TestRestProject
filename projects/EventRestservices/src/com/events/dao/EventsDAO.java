package com.events.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.events.model.Event;



/**
 * @author guns
 *
 */
public class EventsDAO extends BaseDAO
{

	private static EventsDAO instance = null;
	
	private EventsDAO(){
		BaseDAO.initDataSource();
	}

	
	
	public static EventsDAO getInstance(){

		if (instance == null){
		   //Thread safe 	
		   synchronized(EventsDAO.class){	
			  if (instance == null) {
			     instance = new EventsDAO();
			  }
		   }	
		}
		return instance;
	}

	


	public  void createEvent(Event event)
	{

		String insertQuery = "insert into events (data) values(' "+ event.getData() + "' )";
		executeUpdateQuery(insertQuery);

	} 
	
	public  void updateEvent(Integer id, Event event) 
	{

		String query = "update events SET data='"+ event.getData() + "' where id = " + id.intValue();
		System.out.println("update query ="+query);
		executeUpdateQuery(query);


	}

	public  void deleteEvent(Integer id) 
	{
		String query =  "delete from events where id = " + id.intValue();
		System.out.println("delete query ="+query);
		executeUpdateQuery(query);
		
	}
	public  void deleteAllEvents() 
	{
		String query = " delete from events";
		System.out.println("delete query ="+query);
		executeUpdateQuery(query);
		
	}

	public  Event getEvent(Integer id) 
	{

		Event event=null;
		String query = "";
		if (id != null){
			query =  "select * from events where id = " + id.intValue();
		}

		System.out.println("Query is ="+query);

		ResultSet rs = executeQuery(query);
		try {
			while(rs.next())
			{
				// read the result set
				String data =  rs.getString("data");
				int eventId = rs.getInt("id");
				event = new Event(eventId,data);
			}
			return event;
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is foun
			System.err.println(e.getMessage());
		}
        closeConnection();
		return event; 

	}


	public  ArrayList<Event> getAllEvents() 
	{

		
		String query = "select * from events";


		System.out.println("Query is ="+query);
		
		ArrayList<Event> events = new ArrayList<Event>();

		ResultSet rs = BaseDAO.executeQuery(query);
		try {
			while(rs.next())
			{
				// read the result set
				
				
				Event evt = new Event(rs.getInt("id"), rs.getString("data"));
				events.add(evt);
			}
			return events;
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is foun
			System.err.println(e.getMessage());
		}
		closeConnection();
		return events; 
	}

	
}





