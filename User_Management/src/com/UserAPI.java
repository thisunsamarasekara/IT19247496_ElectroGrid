package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;

/**
 * Servlet implementation class UserAPI
 */
@WebServlet("/UserAPI")
public class UserAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    User userObj = new User();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = userObj.insertUser(request.getParameter("name"),      
				request.getParameter("phone_number"),
				request.getParameter("address"),
				request.getParameter("Email")); 
				response.getWriter().write(output);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method

		Map paras = getParasMap(request); 
		 
		 String output = userObj.updateUser(paras.get("hidCustomerIDSave").toString(),     
		    		paras.get("name").toString(),     
		    		paras.get("phone_number").toString(), 
		    		paras.get("address").toString(),
		    		paras.get("Email").toString()); 
		 
		 			response.getWriter().write(output);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		 
		 String output = userObj.deleteUser(paras.get("UserID").toString());  
		 
		 response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
			private static Map getParasMap(HttpServletRequest request)
			{
			 Map<String, String> map = new HashMap<String, String>();
			try
			 { 
			 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			 String queryString = scanner.hasNext() ?
			 scanner.useDelimiter("\\A").next() : "";
			 scanner.close();
			 String[] params = queryString.split("&");
			 for (String param : params)
			 { 
			
			String[] p = param.split("=");
			 map.put(p[0], p[1]);
			 }
			 }
			catch (Exception e)
			 {
			 }
			return map;
			}

	}