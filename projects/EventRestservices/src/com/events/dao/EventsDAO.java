package com.events.dao;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.events.model.Event;



/**
 * @author guns
 *
 */
public class EventsDAO
{

	private static EventsDAO instance = null;
	
	private EventsDAO(){
		DataSourceHelper.initDataSource();
	}

	public static EventsDAO getInstance(){

		if (instance == null){
			instance = new EventsDAO();
		}
		return instance;
	}

	
//	//initiate the Datasource, create the tables if they don't exist.
//	static  {
//
//		initDataSource();
//	}

	public  void createEvent(String data)
	{

		String insertQuery = "insert into events (data) values(' "+ data + "' )";
		DataSourceHelper.executeUpdateQuery(insertQuery);

	} 
	
	public  void updateEvent(Integer id, String data) 
	{

		String query = "update events SET data='"+ data + "' where id = " + id.intValue();
		System.out.println("update query ="+query);
		DataSourceHelper.executeUpdateQuery(query);


	}

	public  void deleteEvent(Integer id) throws ClassNotFoundException
	{
		String query =  "delete from events where id = " + id.intValue();
		System.out.println("delete query ="+query);
		DataSourceHelper.executeUpdateQuery(query);
		
	}
	public  void deleteAllEvents() throws ClassNotFoundException
	{
		String query = " delete from events";
		System.out.println("delete query ="+query);
		DataSourceHelper.executeUpdateQuery(query);
		
	}

	public  Event getEvent(Integer id) 
	{

		Event event=null;
		String query = "";
		if (id != null){
			query =  "select * from events where id = " + id.intValue();
		}

		System.out.println("Query is ="+query);

		ResultSet rs = DataSourceHelper.executeQuery(query);
		try {
			while(rs.next())
			{
				// read the result set
				String data =  rs.getString("data");
				int eventid = rs.getInt("id");
				event = new Event(data);
			}
			return event;
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is foun
			System.err.println(e.getMessage());
		}
        DataSourceHelper.closeConnection();
		return event; 

	}


	public  String getAllEvents() 
	{

		String event="";
		String query = "select * from events";


		System.out.println("Query is ="+query);

		ResultSet rs = DataSourceHelper.executeQuery(query);
		try {
			while(rs.next())
			{
				// read the result set
				event = event + "name = " + rs.getString("data");
				event = event + " id = " + rs.getInt("id");
			}
			return event;
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is foun
			System.err.println(e.getMessage());
		}
		DataSourceHelper.closeConnection();
		return event; 
	}

	
}





