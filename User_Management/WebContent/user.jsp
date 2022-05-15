<%@page import="com.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/user.js"></script>
</head>
<body style="background-color:#eff2e9";>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			      
				    <form id="formCustomer" name="formCustomer" method="post" action="user.jsp"> 
				        <center> <h1 style="color:blue;">User Management</h1></center>
			         <br>
			        <br>
				        
					 Name:  
 	 				<input id="name" name="name" type="text"  class="form-control form-control-sm">
					<br>Phone Number:   
  					<input id="phone_number" name="phone_number" type="text" class="form-control form-control-sm">   
  					<br>Address:   
  					<input id="address" name="address" type="text"  class="form-control form-control-sm">
					<br>Email:   
  					<input id="Email" name="Email" type="text"  class="form-control form-control-sm">
					<br>  
					<center><input id="btnSave" name="btnSave" type="button" value="SAVE" class="btn btn-primary"> </center> 
					<input type="hidden" id="hidCustomerIDSave" name="hidCustomerIDSave" value=""> 
				</form>
				
				 <div id="alertSuccess" class="alert alert-success"> </div>
				
			       <div id="alertError" class="alert alert-danger"></div>
				
			   <br>
				<div id="divCustomerGrid">
					<%
						User userObj = new User();
						out.print(userObj.readUser());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>