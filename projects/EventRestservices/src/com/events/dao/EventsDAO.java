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

	
	private final String INSERT_SQL = "insert into events (data) values( ? )" ;
	
	private final String UPDATE_SQL = "update events SET data= ? where id = ? ";
	
	private final String SELECT_SQL = "select * from events where id = ?";
	
	private final String SELECTALL_SQL = "select * from events";
	
	private final String DELETE_SQL = "delete from events where id = ?" ;
	
	private final String DELETEALL_SQL = "delete from events" ;
	
	
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

	


	public  int createEvent(Event event)
	{

		
		ArrayList<String> params = new ArrayList<String>();
		
		params.add(event.getData());
		return (executeUpdateQuery(INSERT_SQL, params));

	} 
	
	public  void updateEvent(Event event) 
	{

		//String query = "update events SET data='"+ event.getData() + "' where id = " + id.intValue();
        ArrayList<String> params = new ArrayList<String>();
        params.add(event.getData());
        System.out.println("Update Id is" +new Integer(event.getId()).toString());
       	params.add(new Integer(event.getId()).toString());
		System.out.println("update query ="+UPDATE_SQL);
		executeUpdateQuery(UPDATE_SQL,params);

	}

	public  void deleteEvent(Integer id) 
	{
		//String query =  "delete from events where id = " + id.intValue();
		if (id != null){
			ArrayList<String> params = new ArrayList<String>();
		    params.add(id.toString());
		    System.out.println("DELETE Query is ="+ DELETE_SQL);
		     executeUpdateQuery(DELETE_SQL,params);
		  
		}			
		
	}
	public  void deleteAllEvents() 
	{
		    System.out.println("DELETE ALL Query is ="+ DELETEALL_SQL);
		     executeUpdateQuery(DELETEALL_SQL,null);
		  
				
	}

	public  Event getEvent(Integer id) 
	{

		Event event=null;
		String query = "";
		if (id != null){
			ArrayList<String> params = new ArrayList<String>();
		    params.add(id.toString());
		
		  System.out.println("Query is ="+SELECT_SQL);

		ResultSet rs = executeQuery(SELECT_SQL, params);
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
		}
        closeConnection();
		return event; 
		

	}


	public  ArrayList<Event> getAllEvents() 
	{

		
		

		System.out.println(" Select ALL Query is =" + SELECTALL_SQL);
		
		ArrayList<Event> events = new ArrayList<Event>();

		ResultSet rs = BaseDAO.executeQuery(SELECTALL_SQL, null);
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





