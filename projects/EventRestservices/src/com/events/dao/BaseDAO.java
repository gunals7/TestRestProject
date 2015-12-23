package com.events.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author guns
 *
 */

public class BaseDAO {

	private static Connection connection = null;
	public static void createConnection(){

    // This shall be changed to configure connection pool and get connections from the pool.
		try
		{
			Class.forName("org.sqlite.JDBC");

			// create a database connection
			connection = DriverManager.getConnection("jdbc:sqlite:sample.db");

		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();

		}

	}

	public static void initDataSource(){



		String tableCreateQuery = "create table events (id integer PRIMARY KEY ASC AUTOINCREMENT, data string)";

		executeUpdateQuery(tableCreateQuery);
	}    



	public static Connection getConnection(){

		//if (connection == null){
			createConnection();

		//}
		return connection;
	}

	public static void executeUpdateQuery(String query){

		try
		{
			// get a database connection
			Connection conn = getConnection();
			if (conn != null){
				Statement statement = conn.createStatement();
				statement.setQueryTimeout(30);  // set timeout to 30 sec.
				statement.executeUpdate(query);

			}
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
		finally
		{
			closeConnection();

		}

	}

	public static ResultSet executeQuery(String query){

		ResultSet rs=null;
		try
		{

			// get a database connection
			Connection conn = getConnection();
			if (conn != null){
				Statement statement = conn.createStatement();
				statement.setQueryTimeout(30);  // set timeout to 30 sec.

				rs = statement.executeQuery(query);
                return rs;

			}
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is foun
			System.err.println(e.getMessage());
		}
//		finally
//		{
//			closeConnection();
//
//		}
		return rs;
	}

	public static void closeConnection(){

		try
		{
			if(connection != null)
				connection.close();
		}
		catch(SQLException e)
		{
			// connection close failed.
			System.err.println(e);
		}

	}

}
