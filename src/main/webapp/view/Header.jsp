<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="/common/taglib.jsp"%>
<%@page import="model.Account"%>
<%@page import="model.Cart"%>
<%@page import="java.util.Map"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.ProductCart"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="css/homeStyle.css" rel="stylesheet" />


</head>
<body>

	<%
	Account acc = (Account) session.getAttribute("acc");
	%>
	<!-- Navigation-->
	<nav class="navbar navbar-expand-md navbar-dark bg-dark">
		<div class="container">
			<a class="navbar-brand" href="index.html">Simple Ecommerce</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarsExampleDefault"
				aria-controls="navbarsExampleDefault" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse justify-content-end"
				id="navbarsExampleDefault">
				<ul class="navbar-nav m-auto">
					<li class="nav-item"><a class="nav-link" href="home">Home</a>
					</li>
					<li class="nav-item active"><a class="nav-link"
						href="category">Categories <span class="sr-only">(current)</span></a>
					</li>
					
					<%
					Cart cart = (Cart) session.getAttribute("cart");
					int count = 0;
					if (cart != null) {
						count = cart.getProductCart().size();
					}
					%>
					
					<%
					if (acc == null) {
					%>
					<li class="navci-item"><a class="nav-link"
						href="cart?accID=-1">Cart</a></li>
					<li class="nav-item"><a class="nav-link" href="login">Log
							in</a></li>
					<%
					} else {
					%>
					<li class="navci-item"><a class="nav-link"
						href="cart?accID=<%=acc.getId()%>">Cart</a></li>
					<li class="nav-item"><a class="nav-link" href="home">Hello
							<%=acc.getUser()%>
					</a></li>
					<%
						if(acc.getIdSell()!=0){
							
							%>
							<li class="nav-item"><a class="nav-link" href="manage-product">Manage Product</a></li>
							<%
						}else{
							%>
							<li class="nav-item"><a class="nav-link" href="sell">Sell</a></li>
							<%
						}
					%>
					
					<li class="nav-item"><a class="nav-link" href="logout">Log
							out</a></li>

					<%
					}
					%>





				</ul>

				<form action="search" class="form-inline my-2 my-lg-0" method="post">
					<div class="input-group input-group-sm">
						<input type="text" class="form-control" aria-label="Small" name="search"
							aria-describedby="inputGroup-sizing-sm" placeholder="Search..." >
						<div class="input-group-append">
							<button type="submit" class="btn btn-secondary btn-number">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
					</form>



					<a class="btn btn-success btn-sm ml-3" href="cart"> <i
						class="fa fa-shopping-cart"></i> Cart <span
						class="badge badge-light" style="color: black"><%=count%></span></a>
					<c:if test="${sessionScope.acc!=null }">
						<a class="btn btn-success btn-sm ml-3" href="profile"> <i
							class="fa fa-profile"></i> Profile
						</a>
					</c:if>


				
			</div>
		</div>
	</nav>
</body>
</html>
