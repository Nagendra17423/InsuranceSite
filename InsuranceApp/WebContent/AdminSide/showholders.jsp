<%@page import="com.beans.Category"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
<center>
	<c:import url="header.jsp" />
	<h1>inside showholders.html</h1>
	<div class="container">
	
	<c:forEach items="${user }" var="user">
		<div class="row">
						<div class="col-lg-4 col-md-4 mb-4">
							<div class="card h-100">
								<div class="card-body">
									<h4 class="card-title">
									<a href="#">
									<c:out value=" ${title}" />
									
									</a>
									</h4>

									
									<c:out value="${user.getName()}" />
								</div>

								

							</div>
						</div>
					
				
			</div>

		</c:forEach>
		<br> <br> <br>
		<!--   new category  -->
	</div>

</center>
</body>

</html>