<%@page import="model.Product"%>
<%@page import="model.Order"%>
<%@page import="model.OrderDetail"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="css/order.css" rel="stylesheet" />
<title>Order Detail</title>
</head>
<body>
	<div class="card mt-50 mb-50">
		<div class="col d-flex">
			<span class="text-muted" id="orderno">order #546924</span>
		</div>
		<div class="gap">
			<div class="col-2 d-flex mx-auto"></div>
		</div>
		<div class="title mx-auto">Thank you for your order!</div>
		<div class="main">
			<span id="sub-title"><p>
					<b>Payment Summary</b>
				</p> </span>
			<%
			HashMap<Integer, OrderDetail> listItems = (HashMap<Integer, OrderDetail>) session.getAttribute("listItems");

			Order order = (Order) session.getAttribute("order");
			if (order != null) {
				for (Integer key : listItems.keySet()) {
					Product product = listItems.get(key).getProduct();
			%>
			<div class="row row-main">
				<div class="col-3">
					<img class="img-fluid" src="<%=product.getImage()%>">
				</div>
				<div class="col-6">
					<div class="row d-flex">
						<p>
							<b><%=product.getName()%></b>
						</p>
					</div>
					<div class="row d-flex">
						<p class="text-muted"><%=product.getTitle()%></p>
					</div>
				</div>
				<div class="col-3 d-flex justify-content-end">
					<p>
						<b>$ <%=product.getPrice()%></b>
					</p>
				</div>
			</div>
			<%
			}
			%>
			<hr>
			<div class="total">
				<div class="row">
					<div class="col">
						<b> Total:</b>
					</div>
					<div class="col d-flex justify-content-end">
						<b>$<%=order.getTotalMoney() %></b>
					</div>
				</div>
				<a href="home">
				<button class="btn d-flex mx-auto" >Track your order </button>
				</a>
			</div>
			<%
			}
			%>




		</div>
	</div>
</body>
</html>