package org.com1028.codeTest.lr00341;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;



/**
 * 
 * Class is designed to create, connect to, and mess with the database. Relies heavily on JDBC. 
 * Possibly to a fault.
 * 
 * @author Lukas
 *
 */

public class DatabaseModifier {
	/** The default JDBC driver for connecting to MYSQL databases. */
	static final String JDBCDriver = "com.mysql.jdbc.Driver";
	
	/** The default URL for connecting to the database.*/
	static String DBURL = "jdbc:mysql://localhost:3306/"; 
	//Not final because I need to change it later. Doing it like this means that it doesn't error if the DB doesn't exist.
	
	/**Username and password for the database. Root with no password, but they need to be declared either way. I.. should probably fix this for the real thing.*/
	static final String User = "root";
	static final String Pass = "";
	
	/**Declares that there's a connection object but sets it to null for now.*/
	static Connection conn = null;
	
	
	/**
	 * 
	 * Constructor. Checks for the database's existence and creates it if it doesn't have one already.
	 * 
	 * Plus all required tables, of course.
	 * 
	 * No Parameters needed because there's only the one DB to connect to.
	 * 
	 */
	public DatabaseModifier() {
		boolean dbExists = false;
		//So I can make statements and see if any have been made. This will be done in most methods.
		Statement stmt = null;
		
		//Checks if it exists, of course. This code has been moved to an additional method because it feels cleaner.
	    dbExists = doesDatabaseExist();
	    if (dbExists == true) {
	    	System.out.println("Database found");
    		DBURL = "jdbc:mysql://localhost:3306/UsersAndAccidents"; 

	    }
	    
	    
	    if (dbExists == false) {
	    	try {
	    		//Attempt to register a JDBC driver
	    		Class.forName("com.mysql.jdbc.Driver");
			
	    		//Open a connection
	    		System.out.println("Connecting...");
	    		conn = DriverManager.getConnection(DBURL, User, Pass);
			
	    		//Make a Query
	    		System.out.println("Making Database...");
	    		stmt = conn.createStatement();
	    		String sql = "CREATE DATABASE UsersAndAccidents;";
	    		//This sends through the statement. DO NOT - I REPEAT - DO NOT EXECUTE STRINGS AS RAW SQL FOR INPUTS.
	    		stmt.executeUpdate(sql);
	    		//Standard SQl formatting required. Caps are for my own readability and do not carry over.
	    		System.out.println("Done.");
	    		
	    		//Closing to make things cleaner. And so I can reopen with a new address.
	    		conn.close();
	    		stmt.close();

	    		
	    		//Reconnecting to the database that now exists.
	    		DBURL = "jdbc:mysql://localhost:3306/UsersAndAccidents"; 
	    		conn = DriverManager.getConnection(DBURL, User, Pass);
	    		stmt = conn.createStatement();
	    		
	    		System.out.println("Database Made. Now creating tables.");
	    		//sql = "CREATE TABLE modules (moduleID INT NOT NULL, moduleName VARCHAR(20), passGrade DECIMAL(10, 2) NOT NULL, PRIMARY KEY (moduleID));";
				//sql template up there from an old project.
				
	    		//Making the first table.
				sql = "CREATE TABLE users (userID INT NOT NULL, userName VARCHAR(20) NOT NULL, password VARCHAR(20) NOT NULL, PRIMARY KEY (userID))";
	    		stmt.executeUpdate(sql);
	    		//And the second. This one is just a thing that exists for now, I may change its structure. If this doesn't work I have a good idea of how to fix the foreign key thing but it should work fine being kept blank.
				sql = "CREATE TABLE accidents (accidentID INT NOT NULL, accidentData VARCHAR(20), lat VARCHAR(10), long VARCHAR(10), userID INT, PRIMARY KEY (accidentID), FOREIGN KEY (userID) REFERENCES users (userID))";
	    		stmt.executeUpdate(sql);
	    		
	    		
	    		//Error catching!
	    	}catch(SQLException se){
			      //Handle errors for JDBC
			      se.printStackTrace();
			   }catch(Exception e){
			      //Handle errors for Class.forName
			      e.printStackTrace();
			   }finally{
				  System.out.println("Done.");
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            stmt.close();
			         //Because closing the statement doesn't makes sense if it hasn't been used.
			      }catch(SQLException se2){
			      }
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			 
			  }
	    	
		
	    }
	    //End Method.
	
	}
	
	/**
	 * 
	 * Method used to search through all available databases and see if the one being checked exists.
	 * 
	 * @return true if the database exists, false if it doesn't.
	 */
	
	public boolean doesDatabaseExist() {
		
		boolean dbExists = false;
		//So I can make statements and see if any have been made.
		
		String dbName = "usersanddatabases";
	    try{
	        Class.forName(JDBCDriver); //Register JDBC Driver

	        System.out.println("Creating a connection and testing if DB exists...");
	        conn = DriverManager.getConnection(DBURL, User, Pass); //Open a connection

	        ResultSet resultSet = conn.getMetaData().getCatalogs();

	        while (resultSet.next()) {

	          String databaseName = resultSet.getString(1);
	            if(databaseName.equals(dbName)){
	                dbExists = true;
	            }
	        }
		      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }

	    }
	    catch(Exception e){
	        e.printStackTrace();
	    }
	    
	    return dbExists;
		
	
	}
	

	
	/**
	 * 
	 * Adds a new Accident to the database. In progress.
	 */
	
	
	public void addModuleToDatabase(int moduleID, String moduleName, double passMark) {

		//This is staying blank until I get a good idea of what I need. Apologies.
		try {
    		//this class *needs* a try for some reason, so the code will be a lot longer than it would be otherwise.

    		
		
    	}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
			   try{
		         if(stmt!=null)
			            stmt.close();
			         //Because closing the statement makes zero sense if it hasn't been used.
			      }catch(SQLException se2){
			      }
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			 }
		
	}
	
		/**
	 * 
	 * Check if an accident exists in the database, then kills it with fire.
	 * 
	 * @param accidentID
	 * 		ID of the accident being deleted.
	 */
	
	
	public void deleteAccidentFromDatabase(int accidentID) {
		String sql = null;
		Statement stmt = null;
		try {
    		//this class *needs* a try for some reason, so the code will be a lot longer than it would be otherwise.
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("Connecting...");
    		conn = DriverManager.getConnection(DBURL, User, Pass);
    		stmt = conn.createStatement();
    		sql = "DELETE FROM accidents WHERE accidentID = " + accidentID + ";";
    		stmt.executeUpdate(sql);
    		
		
    	}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
			   try{
		         if(stmt!=null)
			            stmt.close();
			         //Because closing the statement makes no sense if it hasn't been used.
			      }catch(SQLException se2){
			      }
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			 }
		
	}
	
	
		/**
	 * 
	 * Takes a user's ID, name and password and makes them LIVE! In our data at least.
	 * 
	 * @param userID
	 * 		Unique identification number. Cannot be the same as any other in the Database.
	 * 
	 * @param userName
	 * 		First name or full name. Generally full.
	 * @param password
	 * 		Yes. THAT password.
	 * 
	 * 
	 */

	public void addUserToDatabase(int userID, String userName, String password) {
		Statement stmt = null;
		if (userID == 0) throw new IllegalArgumentException();
		if (userName == null) throw new IllegalArgumentException();
		if (password == null) throw new IllegalArgumentException();
		try {
    		//this class *needs* a try for some reason, so the code will be a lot longer than it would be otherwise.
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("Connecting...");
    		conn = DriverManager.getConnection(DBURL, User, Pass);
    		stmt = conn.createStatement();
    		String sql = "INSERT INTO users (userID, userName, password) VALUES ('" + userID + "', '" + userName + "', '" + password + "');";
    		stmt.executeUpdate(sql);
    		
		
    	}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
			   try{
		         if(stmt!=null)
			            stmt.close();
			         //Because closing the statement makes no sense if it hasn't been used.
			      }catch(SQLException se2){
			      }
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			 }


		
	}
	
	
	/**
	 * 
	 * Deletes a User from database. Also deletes all accidents they created. Careful! For the love of god don't try this with a null userID you might kill everything.
	 * 
	 * @param userID
	 * 		ID of the user to be deleted.
	 */
	
	public void deleteUserFromDatabase(int userID) {
		Statement stmt = null;
		if (userID = null) throw new IllegalArgumentException();
		try {
    		//this class *needs* a try for some reason, so the code will be a lot longer than it would be otherwise.
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("Connecting...");
    		conn = DriverManager.getConnection(DBURL, User, Pass);
    		stmt = conn.createStatement();
    		String sql = "DELETE FROM users WHERE userID = " + userID + ";";
    		stmt.executeUpdate(sql);
    		sql = "DELETE FROM accidents WHERE userID = " + userID + ";";
    		stmt.executeUpdate(sql);
    		
		
    	}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
			   try{
		         if(stmt!=null)
			            stmt.close();
			      }catch(SQLException se2){
			      }
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			 }
		
	}
	

	

	/**
	 * Retrieves any record.
	 * 
	 * @param recordID
	 * 		The ID of the record requested. Accident or User.
	 * @param accident
	 * 		boolean. True means it's an accident record, false means it isn't. I could have just used an ENUM, but effort.
	 * @return ArrayList<Object> objectList
	 * 		Whichever record was requested as as a list of arbitrary objects.
	 */
	
	public ArrayList<Object> getRecord(int recordID, boolean accident) {
		if (recordID == 0) throw new IllegalArgumentException();
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;
		ArrayList<Object> objectList = new ArrayList<Object>();
		try {
    		//this class *needs* a try for some reason, so the code will be a lot longer than it would be otherwise.
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("Connecting...");
    		conn = DriverManager.getConnection(DBURL, User, Pass);
    		stmt = conn.createStatement();
    		if (accident == true) {
        		sql = "SELECT userID, userName FROM users WHERE userID = " + recordID + ";";
    			rs = stmt.executeQuery(sql);
    			rs.next();
    			System.out.println("Adding user data to Array.");
    			objectList.add(rs.getInt("userID"));
    			objectList.add(rs.getString("userName"));
    			//nopenopenope you're not getting passwords this way. I'll make a separate method for it. 
    			rs.close();

    		}
    		if (accident == false) {
    			sql = "SELECT * FROM accidents WHERE accidentID = " + recordID + ";";
				//Yes, selecting * is dumb. I am aware.
    			rs = stmt.executeQuery(sql);
    			rs.next();
    			System.out.println("Adding accident data to Array...");
    			objectList.add(rs.getInt("accidentID"));
    			objectList.add(rs.getString("accidentData"));
    			objectList.add(rs.getString("lat"));
				objectList.add(rs.getString("long"));
				objectList.add(rs.getString("userID"));
    		}
    		

			
		
    	}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
			   try{
		         if(stmt!=null)
			            stmt.close();
			         //Because closing the statement makes sense if it hasn't been used.
			      }catch(SQLException se2){
			      }
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			 }
		
		if (objectList.get(0) == null) throw new NullPointerException();
		return objectList;
	}
	

	/**
	 * Retrieves any users Password.
	 * 
	 * @param userID
	 * 		The ID of the user requested.
	 * @return ArrayList<Object> objectList
	 * 		Whichever record was requested as as a list of arbitrary objects.
	 */
	
		public ArrayList<Object> getPassword(int userID) {
		if (userID == 0) throw new IllegalArgumentException();
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;
		ArrayList<Object> objectList = new ArrayList<Object>();
		try {
    		//this class *needs* a try for some reason, so the code will be a lot longer than it would be otherwise.
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("Connecting...");
    		conn = DriverManager.getConnection(DBURL, User, Pass);
    		stmt = conn.createStatement();

        	sql = "SELECT userID, password FROM users WHERE userID = " + userID + ";";
    		rs = stmt.executeQuery(sql);
    		rs.next();
    		System.out.println("Adding user data to Array.");
    		objectList.add(rs.getString("password"));
			//Really need to find a way to encrypt this...
    		rs.close();

    		

			
		
    	}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
			   try{
		         if(stmt!=null)
			            stmt.close();
			         //Because closing the statement makes sense if it hasn't been used.
			      }catch(SQLException se2){
			      }
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			 }
		
		if (objectList.get(0) == null) throw new NullPointerException();
		return objectList;
	}
	
	/**
	 * 
	 * Edits something in a particular record.
	 * 
	 * @param recordID
	 * 		ID of the record you're changing.
	 * @param tableName
	 * 		The name of the table you're going after. Student or Module.
	 * @param parameter
	 * 		The name of the parameter you're editing.
	 * @param newInfo 
	 * 		whatever you're replacing the particular piece of data with. Is a string because the only changeable records in Student and Module are VARCHAR which accept strings.
	 */
	
	public void editRecord(int recordID, String tableName, String parameter, Object newInfo, boolean student) {
		Statement stmt = null;
		try {
    		//this class *needs* a try for some reason, so the code will be a lot longer than it would be otherwise.
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("Connecting...");
    		conn = DriverManager.getConnection(DBURL, User, Pass);
    		stmt = conn.createStatement();
    		String type = "studentID";
    		if (student == false) type = "moduleID";
    		String sql = "UPDATE " + tableName + " SET " + parameter + " = '" + newInfo + "' WHERE " + type + " = " + recordID + ";";
    		//Example of the above - UPDATE students SET studentName = Johnny Foreigner WHERE studentID = 11111;. Keeping this example despite it being totally inappropriate because it's funny to me.
    		stmt.executeUpdate(sql);
    		
		
    	}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
			   try{
		         if(stmt!=null)
			            stmt.close();
			         //Because closing the statement makes sense if it hasn't been used.
			      }catch(SQLException se2){
			      }
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			 }
		

	}
	

	
	/**
	 * 
	 * Formulates a Query to extract all points a user has created. 
	 * 
	 * @param userID
	 *	The ID of the user being searched for.
	 * 
	 * @return The list of all accident IDs the user in question has submitted. Also details.
	 */
	
	public HashMap<Integer, Number> getStudentModuleMarks(int userID) {
		Statement stmt = null;
		String sql = null;
		ResultSet rs = null;
		HashMap<Integer, Number> MarkList = new HashMap<Integer, Number>();
		try {
			//this class *needs* a try for some reason, so the code will be a lot longer than it would be otherwise.
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting...");
			conn = DriverManager.getConnection(DBURL, User, Pass);
			stmt = conn.createStatement();
			sql = "Select accidentID, accidentData FROM accidents WHERE userID = " + userID + ";";
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int key = rs.getInt(1);
				double value = rs.getDouble(2);
				MarkList.put(key, value);
			}
			rs.close();
			
			
		//Error handling stuff 
		}catch(SQLException se){
		      //Handle errors for JDBC
		      se.printStackTrace();
		   }catch(Exception e){
		      //Handle errors for Class.forName
		      e.printStackTrace();
		   }finally{
			   try{
		         if(stmt!=null)
			            stmt.close();
			         //Because closing the statement doesn't make sense if it hasn't been used.
			      }catch(SQLException se2){
			      }
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }
			 }
		return MarkList;
	}

	/**
	 * 
	 * @return DBURL
	 * 		the URL of the database. This method isn't actually used, but it might be useful in the future.
	 */
	public String getDatabaseID() {
		return DBURL;
	}
		
}
