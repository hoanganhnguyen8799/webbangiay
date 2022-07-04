<%@page import="model.Cart"%>
<%@page import="java.util.Map"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.ProductCart"%>
<%@page import="java.util.HashMap"%>
<%@page import="model.Account"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
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
	
	
	<jsp:include page="/view/Header.jsp"></jsp:include>
	
	<section class="jumbotron text-center">
		<div class="container">
			<h1 class="jumbotron-heading">E-COMMERCE CATEGORY</h1>
			<p class="lead text-muted mb-0">Le Lorem Ipsum est simplement du
				faux texte employé dans la composition et la mise en page avant
				impression. Le Lorem Ipsum est le faux texte standard de
				l'imprimerie depuis les années 1500, quand un peintre anonyme
				assembla ensemble des morceaux de texte pour réaliser un livre
				spécimen de polices de texte...</p>
		</div>
	</section>
	<div class="container"></div>
	<div class="container">
		<div class="row">
			<div class="col-12 col-sm-3">
				<div class="card bg-light mb-3">
					<div class="card-header bg-primary text-white text-uppercase">
						<i class="fa fa-list"></i> Categories
					</div>
					<ul class="list-group category_block">
						<c:forEach items="${listC}" var="c">
							<li class="list-group-item"><a href="category?cid=${c.cid}">${c.cname}</a></li>
						</c:forEach>


					</ul>
				</div>
				<div class="card bg-light mb-3">
					<div class="card-header bg-success text-white text-uppercase">Last
						product</div>
					<div class="card-body">
						<img class="img-fluid" src="${lastP.image }" />
						<h5 class="card-title">${lastP.title}</h5>
						<p class="card-text">${lastP.description }</p>
						<p class="bloc_left_price">${lastP.price}$</p>
					</div>
				</div>
			</div>
			<div class="col">
				<div class="row">
					<c:if test="${listP!=null }">
						<c:forEach items="${listP}" var="p">
							<div class="col-12 col-md-6 col-lg-4">
								<div class="card">
									<img class="card-img-top" src="${p.image }"
										alt="Card image cap">
									<div class="card-body">
										<h4 class="card-title">
											<a href="product-detail?pid=${p.id}" title="View Product">${p.name}</a>
										</h4>
										<p class="card-text">${p.title }</p>
										<div class="row">
											<div class="col">
												<p class="btn btn-danger btn-block">${p.price }$</p>
											</div>
											<div class="col">
												<a href="add-2-cart?pid=${p.id}"
													class="btn btn-success btn-block">Add to cart</a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</c:if>



					<div class="col-12">
						<nav aria-label="...">
							<ul class="pagination">
								<li class="page-item disabled"><a class="page-link"
									href="#" tabindex="-1">Previous</a></li>
									<c:forEach var="i" begin="1" end="${pageNumber}" >
										<li class="page-item"><a class="page-link" href="paging?index=${i}">${i}</a></li>
									</c:forEach>
									
								<li class="page-item"><a class="page-link" href="#">Next</a>
								</li>
							</ul>
						</nav>
					</div>
				</div>
			</div>

		</div>
	</div>

	<jsp:include page="/view/Footer.jsp"></jsp:include>
</body>
</html>
