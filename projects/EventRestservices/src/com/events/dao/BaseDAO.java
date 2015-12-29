package com.events.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

		executeUpdateQuery(tableCreateQuery, null);
	}    



	public static Connection getConnection(){

		//if (connection == null){
			createConnection();

		//}
		return connection;
	}

	//Use this for database inserts/updates/deletes..
	public static int executeUpdateQuery(String query, ArrayList<String> params ){

		try
		{
			// get a database connection
			Connection conn = getConnection();
			if (conn != null){
				PreparedStatement pstatement = conn.prepareStatement(query);
				pstatement.setQueryTimeout(30);  // set timeout to 30 sec.
				if (params != null){
				for(int i = 0; i < params.size(); i++){
					
					String param = params.get(i);
					
					if (param != null){
						pstatement.setString(i+1, param);
					}
 			    }
				}
				int rowid= pstatement.executeUpdate();
				ResultSet rs = pstatement.getGeneratedKeys();
				if (rs != null){
					
					rowid = rs.getInt(1);
				}
				
				return rowid;

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
        return 0;
	}

	public static ResultSet executeQuery(String query, ArrayList<String> whereParams){

		ResultSet rs=null;
		try
		{

			// get a database connection
			Connection conn = getConnection();
			if (conn != null){
				PreparedStatement pstatement = conn.prepareStatement(query);
				pstatement.setQueryTimeout(30);  // set timeout to 30 sec.
				if (whereParams != null){
				for(int i = 0; i < whereParams.size(); i++){
					
					String whereParam = whereParams.get(i);
					
					if (whereParam != null){
						pstatement.setString(i+1, whereParam);
					}
 			    }
				}
		        rs = pstatement.executeQuery();
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
