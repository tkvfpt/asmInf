<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@page import="model.*,java.util.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product Detail</title>
<link href="css/style1.css" type="text/css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<%
	DefaultHB hb=new DefaultHB("Products");
	List<Products> listP;
	try{
		hb.beginTransaction();
		 listP=hb.getEntity("Products","where e.code like '"+request.getParameter("code")+"'");
		hb.commitTransaction();
	}finally{
		hb.closeTransaction();
	}
%>

<c:set var="item" value="<%=listP.get(0)%>"></c:set>
<h1 style="color:lightgreen;text-align:center">Update: ${item.name }</h1>
<div class="link">
	<a href="admin?action=Search&product-name=">Back</a>
</div>
<br/>
		<form action="admin" method="post" enctype="multipart/form-data" >
	
		<img style="float:left;" src="getImage.jsp?code=${item.code}" width="20%" height="20%"/>

	<div style="width:250px;margin-left:280px;">		
			<label>Code:</label> <input class="form-control" type="text" name="product-code" value="${item.code}" readonly="readonly"/>
			<label>Name:</label> <input class="form-control" name="product-name" type="text" value="${item.name}" onblur="checkUpdateBtn(this)" onfocus="getBtnValue(this)"/>
			<label>Price:</label> <input class="form-control" name="product-price" type="text" value="${item.price}" onblur="checkUpdateBtn(this)" onfocus="getBtnValue(this)"/>
			<label>Quantity:</label> <input class="form-control" name="product-quantity" type="text" value="${item.quantity}" onblur="checkUpdateBtn(this)" onfocus="getBtnValue(this)"/>
			<input style="display:none" class="btn btn-success" name="action" type="submit" value="Update" id="btnUpdate" />
			<input type="submit" class="btn btn-danger" name="action" value="Delete"/>				
	</div>
	<p id="ImageName"></p>
	<input style="margin-left:5%" type="button" class="btn btn-info" value="Choose Image" onclick="activateInputFile()">
	<input style="display:none" id="inputFile" type="file" size = "2" value="upload" class="input-file" name="fileUpload" onfocus="showBtn(this)"/>
	</form>	
	
</body>
</html>
<script>
var value;
document.getElementById("inputFile").onchange=function(){
	var path=this.value;
	var fileName = path.match(/[^\/\\]+$/);
	document.getElementById("ImageName").innerHTML="Image name: <b>"+fileName+"</b>";
}	
function getBtnValue(component){
	value=component.value;
}
function showBtn(component){
	if(component.value!=""){
		document.getElementById("btnUpdate").style.display="block";
	}
}
	function checkUpdateBtn(component){
		var newValue=component.value;
			if(newValue!=value){
					document.getElementById("btnUpdate").style.display="block";
				}
		}
function activateInputFile(){
	document.getElementById("btnUpdate").style.display="block";	
	document.getElementById("inputFile").click();
	
}	
</script>