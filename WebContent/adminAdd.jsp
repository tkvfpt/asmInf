<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Insert</title>
<link href="css/style.css" type="text/css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<h1>Add Item</h1>
		<form action="admin" method="post" enctype="multipart/form-data" >	
		<img style="float:left;" id="img" width="25%" height="20%"/>
		<div style="width:50px;margin-left:280px;margin-top:50px">		
			<label>Code:</label> <input type="text" name="product-code"/>
			<label>Name:</label> <input name="product-name" type="text"  onfocus="getBtnValue(this)"/>
			<label>Price:</label> <input name="product-price" type="text"  onfocus="getBtnValue(this)"/>
			<label>Quantity:</label> <input name="product-quantity" type="text" onfocus="getBtnValue(this)"/>
			<input style="display:none" class="btn btn-success" name="action" type="submit" value="Add" id="btnAdd" />
	</div>
	<input style="clear:both;margin-top:6%" type="button" value="Choose" class="btn btn-info" onclick="activateInputFile()"/>
	<input style="display:none;" id="inputFile" type="file" size = "2" value="upload" name="fileUpload" onchange="readURL(this)"/>
	</form>	
</body>
</html>
<script>
var value;
function getBtnValue(component){
	value=component.value;
}
function showBtn(component){
	if(component.value!=""){
		document.getElementById("btnAdd").style.display="block";
	}
}
	function checkUpdateBtn(component){
		var newValue=component.value;
			if(newValue!=value){
					document.getElementById("btnAdd").style.display="block";
				}
		}
function readURL(input){
	 if (input.files && input.files[0]) {
         var reader = new FileReader();

         reader.onload = function (e) {
             $('#img')
                 .attr('src', e.target.result)
                 .width(200)
                 .height(200);
         };

         reader.readAsDataURL(input.files[0]);
     }
}
function activateInputFile(){
	document.getElementById("btnAdd").style.display="block";	
	document.getElementById("inputFile").click();
	
}
</script>