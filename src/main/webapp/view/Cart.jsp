<%@page import="model.ProductCart"%>
<%@page import="java.util.Set"%>
<%@page import="model.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/cart.css">
<title>Insert title here</title>
</head>
<body>

	<div class="card">
		<form action="order" method="post">
		
			<div class="row">
				<div class="col-md-8 cart">
					<div class="title">
						<div class="row">
							<div class="col">
								<h4>
									<b>Shopping Cart</b>
								</h4>
							</div>
							<%
							Cart cart = (Cart) session.getAttribute("cart");
							int items = 0;
							if (cart != null) {
								items = cart.getProductCart().size();
							%>
							<div class="col align-self-center text-right text-muted"><%=items%></div>
							<%
							}
							%>


						</div>
					</div>
					<%
					double totalMoney = 0;
					if (cart != null) {
						Set<Integer> keySet = cart.getProductCart().keySet();
						totalMoney = cart.getTotalMoney();
						for (Integer key : keySet) {
							ProductCart productCart = cart.getProductCart().get(key);
					%>

					<div class="row border-top border-bottom">
						<div class="row main align-items-center">
							<div class="col-2">
								<img class="img-fluid"
									src="<%=productCart.getProduct().getImage()%>">
							</div>
							<div class="col">
								<div class="row text-muted">Shirt</div>
								<div class="row"><%=productCart.getProduct().getName()%></div>
							</div>
							<div class="col">
								<a href="action?action=reduce&id=<%= key %>">-</a>
								<a href="#" class="border"><%=productCart.getQuantity()%></a>
								<a href="action?action=increase&id=<%= key %>">+</a>
							</div>
							<div class="col">
								&euro;
								<%=productCart.getProduct().getPrice()%>
								<a
									href="delete-pro-cart?pid=<%=productCart.getProduct().getId()%>"><span
									class="close">&#10005;</span></a>
							</div>
						</div>
					</div>

					<%
					}

					}
					%>
					<%
					String err = request.getParameter("messC");
					if (err != null && err.equals("-1"))
						out.println("<p style='color: red' >Khong co san pham trong Cart</p>");
					%>
					
					<div class="back-to-shop">
						<a href="home">&leftarrow;</a><span class="text-muted">Back
							to shop</span>
					</div>
				</div>
				<div class="col-md-4 summary">
					<div>
						<h5>
							<b>Summary</b>
						</h5>
					</div>
					<hr>
					<div class="row">
						<div class="col" style="padding-left: 0;">ITEMS 3</div>
						<div class="col text-right">
							&euro;
							<%=totalMoney%></div>
					</div>
					
						<p>SHIPPING</p>
						<select><option class="text-muted">Standard-Delivery-
								&euro;5.00</option></select>
						<p>ADDRESS</p>
						<input id="inputAddress" name="address" placeholder="Address"
							type="text">


					
					<div class="row"
						style="border-top: 1px solid rgba(0, 0, 0, .1); padding: 2vh 0;">
						<div class="col">TOTAL PRICE</div>
						<div class="col text-right">
							&euro;<%=totalMoney + 5.0%></div>
					</div>

					
					<button class="btn" type="submit">CHECKOUT</button>


				</div>
			</div>
		</form>
	</div>

</body>
</html>