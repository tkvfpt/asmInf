<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<h1 style="text-align:center">Admin</h1>

<span style="color:red;margin-left:560px">${requestScope.errorMessage}</span>	


	<form class="form-horizontal" style="margin-left:400px" action="admin" method="post">
  <div class="form-group">
    <label class="control-label col-sm-2" for="email">UserName</label>
    <div class="col-sm-10">
      <input name="username" style="width:250px" class="form-control" id="email" placeholder="Enter UserName">
    </div>
  </div>
  <div class="form-group">
    <label class="control-label col-sm-2" for="pwd">Password:</label>
    <div class="col-sm-10"> 
      <input type="password" style="width:250px" name="password" class="form-control" id="pwd" placeholder="Enter password">
    </div>
  </div>
  
  <div class="form-group"> 
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" value="login" name="action" class="btn btn-default">Log In</button>
    </div>
  </div>
</form>
</body>
</html>