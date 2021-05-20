<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>NewEmployee</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark">

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/"
					class="btn btn-dark">Employees</a></li>
			</ul>


		</nav>
	</header>
	<div class="container">

		<c:if test="${employee!=null}">
			<form action="<%=request.getContextPath()%>/update" method="post">
		</c:if>

		<c:if test="${employee==null}">
			<form action="<%=request.getContextPath()%>/insert" method="post">
		</c:if>

		<c:if test="${employee!=null}">
			<div class="form-group">
				<label>ID</label> <input value="${employee.id}" name="id"
					class="form-control" readonly />
			</div>
		</c:if>

		<c:if test="${employee==null}">
			<div class="form-group">
				<label>ID</label> <input value="${employee.id}" name="id"
					class="form-control" />
			</div>
		</c:if>


		<div class="form-group">
			<label>NAME</label> <input type="text" value="${employee.name}"
				name="name" class="form-control" />
		</div>

		<div class="form-group">
			<label>EMAIL</label> <input type="email" value="${employee.email}"
				name="email" class="form-control">
		</div>

		<div class="form-group">
			<label>SALARY</label> <input type="number" value="${employee.salary}"
				name="salary" class="form-control" />
		</div>

		<div class="form-group">

			<input type="submit" class="form-control btn btn-dark" />
		</div>
		</form>
	</div>
</body>
</html>