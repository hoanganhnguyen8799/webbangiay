<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!------ Include the above in your HEAD tag ---------->

<!DOCTYPE html>
<html lang="en">
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">

<title>eCommerce Product Detail</title>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">

<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="css/homeStyle.css" rel="stylesheet" />
<link href="css/productDetail.css" rel="stylesheet" />
</head>

<body>
	<jsp:include page="/view/Header.jsp"></jsp:include>



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

			<!-- <div class="card"> -->
			<div class="col-12 col-sm-9">
				<!-- <div class="card bg-light mb-3"> -->
				<div class="card bg-light mb-3">
					<div class="wrapper row">
						<div class="preview col-md-6">

							<div class="preview-pic tab-content">
								<div class="tab-pane active" id="pic-1">
									<img src="${p.image}" />
								</div>
								<div class="tab-pane" id="pic-2">
									<img src="${p.image}" />
								</div>
								<div class="tab-pane" id="pic-3">
									<img src="${p.image}" />
								</div>
								<div class="tab-pane" id="pic-4">
									<img src="${p.image}" />
								</div>
								<div class="tab-pane" id="pic-5">
									<img src="${p.image}" />
								</div>
							</div>
							<ul class="preview-thumbnail nav nav-tabs">
								<li class="active"><a data-target="#pic-1"
									data-toggle="tab"><img src="${p.image}" /></a></li>
								<li><a data-target="#pic-2" data-toggle="tab"><img
										src="${p.image}" /></a></li>
								<li><a data-target="#pic-3" data-toggle="tab"><img
										src="${p.image}" /></a></li>
								<li><a data-target="#pic-4" data-toggle="tab"><img
										src="${p.image}" /></a></li>
								<li><a data-target="#pic-5" data-toggle="tab"><img
										src="${p.image}" /></a></li>
							</ul>

						</div>
						<div class="details col-md-6">
							<h3 class="product-title">${p.name}</h3>
							<div class="rating">
								<div class="stars">
									<span class="fa fa-star checked"></span> <span
										class="fa fa-star checked"></span> <span
										class="fa fa-star checked"></span> <span class="fa fa-star"></span>
									<span class="fa fa-star"></span>
								</div>
								<span class="review-no">41 reviews</span>
							</div>
							<p class="product-description">${p.description }.</p>
							<h4 class="price">
								current price: <span>${p.price} $</span>
							</h4>
							<p class="vote">
								<strong>91%</strong> of buyers enjoyed this product! <strong>(87
									votes)</strong>
							</p>
							<h5 class="sizes">
								sizes: <span class="size" data-toggle="tooltip" title="small">s</span>
								<span class="size" data-toggle="tooltip" title="medium">m</span>
								<span class="size" data-toggle="tooltip" title="large">l</span>
								<span class="size" data-toggle="tooltip" title="xtra large">xl</span>
							</h5>
							<h5 class="colors">
								colors: <span class="color orange not-available"
									data-toggle="tooltip" title="Not In store"></span> <span
									class="color green"></span> <span class="color blue"></span>
							</h5>
							<div class="action">
								<button class="add-to-cart btn btn-default" type="button">add
									to cart</button>
								<button class="like btn btn-default" type="button">
									<span class="fa fa-heart"></span>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer -->
	<jsp:include page="/view/Footer.jsp"></jsp:include>
</body>
</html>



