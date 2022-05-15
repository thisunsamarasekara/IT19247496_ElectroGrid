$(document).ready(function() 
{  
		$("#alertSuccess").hide();  
	    $("#alertError").hide(); 
}); 
 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateCustomerForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT"; 

	$.ajax( 
	{  
			url : "UserAPI",  
			type : type,  
			data : $("#formCustomer").serialize(),  
			dataType : "text",  
			complete : function(response, status)  
			{   
				onCustomerSaveComplete(response.responseText, status);  
			} 
	}); 
}); 


function onCustomerSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divCustomerGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidCustomerIDSave").val("");  
	$("#formCustomer")[0].reset(); 
} 

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidCustomerIDSave").val($(this).closest("tr").find('#hidCustomerIDUpdate').val());     
	$("#name").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#phone_number").val($(this).closest("tr").find('td:eq(1)').text());
	$("#address").val($(this).closest("tr").find('td:eq(2)').text());
	$("#Email").val($(this).closest("tr").find('td:eq(3)').text());     
}); 

//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "UserAPI",   
		type : "DELETE",   
		data : "UserID=" + $(this).data("customerid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onCustomerDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onCustomerDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divCustomerGrid").html(resultSet.data); 
			
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}
		

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}

//CLIENT-MODEL========================================================================= 
function validateCustomerForm() 
{  
	// NAME  
	if ($("#name").val().trim() == "")  
	{   
		return "Insert Name.";  
	} 
	
	//PHONE-------------------------------
	 var tmpPhone = $("#phone_number").val().trim();
	if (!$.isNumeric(tmpPhone)) 
	 {
	 return "Insert Phone No.";
	 }

	// ADDRESS------------------------  
	if ($("#address").val().trim() == "")  
	{   
		return "Insert Address.";  
	} 
	
	
	
	//EMAIL------------------------  
	if ($("#Email").val().trim() == "")  
	{   
		return "Insert Email.";  
	} 
	
	
	

	return true; 
}
