<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Index</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

  <div class="col-lg-6">
  <form action="admin" method="get">
    <div class="input-group">
    
      <input type="text" name="product-name" class="form-control" placeholder="Input product name...">
      <span class="input-group-btn">
        <button name="action" class="btn btn-default" value="search" type="submit">Search</button>
      </span>
      
    </div><!-- /input-group -->
    </form>
  </div><!-- /.col-lg-6 -->
  <ul class="nav nav-tabs">
    <li class="active"><a href="#">Products List</a></li>
    <li><a href="users?user-name=&action=Search">Users</a></li>
  </ul>
  <br>
    <table class="table table-striped">
    <thead>
      <tr>
        <th>Image</th>
        <th>Code</th>
        <th>Product name</th>
        <th>Price</th>
        <th>Quantity</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="item" items="${requestScope.listP}">
      	<tr>
      		<form action="admin" method="get">
      			<td style="width:150px!important"><img class="img-rounded" src="getImage.jsp?code=${item.code}" height="100px" width="100px"/></td>
      			<td style="padding-top:50px">${item.code}</td>
      			<td style="padding-top:50px">${item.name}</td>
      			<td style="padding-top:50px">${item.price}</td>
      			<td style="padding-top:50px">${item.quantity}</td>
      			<td style="padding-top:50px"><a href="adminPDetail.jsp?code=${item.code}">Detail</a></td>
      		</form>
      	</tr>
      </c:forEach>
      <tr>
      	<td style="text-decoration:underline"><a href="adminAdd.jsp"><img src="images/plus.jpg" width="15%" height="10%"/>Add</a></td>
      </tr>
    </tbody>
  </table>
  

</body>
</html>