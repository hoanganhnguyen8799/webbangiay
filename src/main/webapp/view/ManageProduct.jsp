<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>Manage Product</title>
</head>
<body>

	<header> <nav class="navbar navbar-expand-md navbar-dark"
		style="background-color: tomato">
	<div>
		<a href="home" class="navbar-brand"> Home</a>
	</div>

	<ul class="navbar-nav">
		<li><a href="manage-product" class="nav-link">List Products</a></li>
	</ul>
	</nav> </header>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Products</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/manage-product?action=new" class="btn btn-success">Add
					New Product </a>
			</div>
			<br>
			<form action="manage-product" method="post">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>ID</th>
							<th>Name</th>
							<th>Title</th>
							<th>Image</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<!--   for (Todo todo: todos) {  -->
						<c:forEach var="product" items="${sessionScope.listProduct}">

							<tr>
								<td><c:out value="${product.id}" /></td>
								<td><c:out value="${product.name}" /></td>
								<td><c:out value="${product.title}" /></td>
								<td><img alt="product-image" src="${product.image }" width="50px"  height="55px">
								</td>
								<td><a
									href="<%=request.getContextPath()%>/manage-product?action=edit&id=<c:out value='${product.id}' />">Edit</a>
									&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="manage-product?action=delete&id=<c:out value='${product.id}' />">Delete</a></td>
							</tr>
						</c:forEach>
						<!-- } -->
					</tbody>

				</table>
		</form>

		</div>
	</div>
</body>
</html>