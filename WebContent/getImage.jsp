<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="model.*,java.util.List,java.io.*" %>
<%@page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%DefaultHB hb=new DefaultHB("Products");
		try{
			hb.beginTransaction();
			List<Products> listP=hb.getEntity("Products","where e.code like '"+request.getParameter("code")+"'");
			hb.commitTransaction();
			//OutputStream output=response.getOutputStream();
			response.getOutputStream().write(listP.get(0).getImage());
			out.clear();
			out=pageContext.pushBody();
		}finally{
			hb.closeTransaction();
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	%>
</body>
</html>