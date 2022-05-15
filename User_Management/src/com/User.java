package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class User {
	
	 //A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customer", "root", "8433"); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertUser(String name, String phone_number, String address, String Email)  
	{   
		String output = ""; 	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for inserting.";
			} 
	 
			// create a prepared statement 
			String query = " insert into User (`UserID`, `name`,`phone_number`,`address`,`Email`)" + " values (?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, phone_number);
			 preparedStmt.setString(4, address);
			 preparedStmt.setString(5, Email);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newUser = readUser(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Customer.\"}";  
			System.err.println(e.getMessage());   
		} 		
	  return output;  
	} 	

	public String readUser()  
	{   
		String output = ""; 
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			 output = " <table style=\"background-color:#ffffff\"; border=\"1\" class=\"table\"><tr><th>Name</th>"
				 		
				 		+ "<th>Phone</th>"
				 		+ "<th>Address</th>"
				 		+ "<th>Email</th>"
				 		+ "<th>Update</th>"
				 		+ "<th>Remove</th></tr>"; 
	 
			String query = "select * from user";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String UserID = Integer.toString(rs.getInt("UserID")); 
				 String name = rs.getString("name"); 
				 String phone_number = rs.getString("phone_number"); 
				 String address = rs.getString("address");  
				 String Email = rs.getString("Email");  
				 
				// Add into the html table 
				output += "<tr><td><input id=\'hidCustomerIDUpdate\' name=\'hidCustomerIDUpdate\' type=\'hidden\' value=\'" + UserID + "'>" 
				    + name + "</td>";  
				output += "<td>" + phone_number + "</td>";
				output += "<td>" + address + "</td>";
				output += "<td>" + Email + "</td>";
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-info'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-customerid='" + UserID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	   
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the User.";    
			System.err.println(e.getMessage());   
		} 	 
		return output;  
	}
	public String updateUser(String UserID, String name, String phone_number, String address, String Email)    
	{   
		String output = "";  
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE user SET name=?,phone_number=?,address=?,Email=?"+ "WHERE UserID=?";  	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, name);
			 preparedStmt.setString(2, phone_number);
			 preparedStmt.setString(3, address);
			 preparedStmt.setString(4, Email);
			 preparedStmt.setInt(5, Integer.parseInt(UserID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close();  
			String newUser = readUser();    
			output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the user.\"}";   
			System.err.println(e.getMessage());   
		} 	 
	  return output;  
	} 
	
	public String deleteUser(String UserID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 			
			} 
	 
			// create a prepared statement    
			String query = "delete from user where UserID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(UserID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newUser = readUser();    
			output = "{\"status\":\"success\", \"data\": \"" +  newUser + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the User.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}