<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="col-lg-6">
  <form action="users" method="get">
    <div class="input-group">
      <input type="text" name="user-name" class="form-control" placeholder="Input user's name...">
      <span class="input-group-btn">
        <button name="action" class="btn btn-default" value="search" type="submit">Search</button>
      </span>
      
    </div><!-- /input-group -->
    </form>
  </div><!-- /.col-lg-6 -->
	<ul class="nav nav-tabs">
    <li><a href="">Products List</a></li>
    <li class="active"><a href="#">Users List</a></li>
  </ul>
  <br/>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>UserName</th>
        <th>Password</th>
        <th>FullName</th>
        <th>Gender</th>
        <th>Email</th>
        <th>Role</th>
        <th>Phone</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="item" items="${requestScope.listC}">
      	<tr>
      			<td>${item.username}</td>
      			<td>${item.password}</td>
      			<td>${item.fullname}</td>
      			<td>${item.gender}</td>
      			<td>${item.email}</td>
      			<td>${item.role}</td>
      			<td>${item.phone}</td>
      	</tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>