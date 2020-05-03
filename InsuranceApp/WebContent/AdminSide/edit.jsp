<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:import url="header.jsp" />
	<h3>Add Policy</h3>
	<div class="container">
		<div class="row">
			<div class="col-lg-12 col-md-12 mb-4">
				<form method="post" action="AdminServlet">
					<input type="hidden" name="id" value="editPolicy"> <input
						type="hidden" name="cid" value="${catid }"> Title: <input
						type="text" class="form-control" name="title">
						 <BR>
						<input type="hidden" name="ptitle" value="${ptitle } ">
						
					<BR> Description: <BR>
					<input type="hidden" name="catid" value="${catid}" >
					<textarea name="description"></textarea>
					<script>
						CKEDITOR.replace('description');
					</script>
					<BR>
					<BR> <input type="submit" value="Add Policy">
				</form>
			</div>
		</div>
	</div>
</body>
</html>