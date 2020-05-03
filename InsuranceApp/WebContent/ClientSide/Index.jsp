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
	<c:import url="header.jsp" />
	<h1>inside index.html</h1>
	<div class="container">

		<c:forEach items="${cat }" var="category">
			<font color="blue" size="5"> <c:out
					value=" ${category.gettitle() }" />
			</font> &nbsp;&nbsp;&nbsp;&nbsp;
			<br>
			<br>
		<div class="row">
				<c:forEach items="${pol}" var="p">
					<c:if test="${p.getId()==category. getId() }">
						<div class="col-lg-4 col-md-4 mb-4">
							<div class="card h-100">
								<div class="card-body">
									<h4 class="card-title">
									<a href="#">
									
									<c:out value="${p.getTitle()}" />
									</a>
									</h4>

									<p class="card-text">card text</p>
									<c:out value="${p.getDiscription()}" escapeXml="False" />
								</div>

								<div class="card-footer">
									<small class="text-muted">
									<a
										href="ClientServlet?id=buy&pid=${p.getId()}&ptitle=${p.getTitle()}"
										class="btn btn-primary">Buy Policy !!</a>
									
									 <a
										href="ClientServlet?id=delete&cid=${category.getId()}&pid=${p.getId()}&ptitle=${p.getTitle()}"
										class="btn btn-danger">Delete Policy!!</a>
										 &nbsp;&nbsp;
										  
										 &nbsp;&nbsp; 
									</small>
								</div>

							</div>
						</div>
					</c:if>
				</c:forEach>
			</div>

		</c:forEach>
		<br> <br> <br>
		
	</div>


</body>

</html>