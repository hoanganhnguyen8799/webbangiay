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
<title>Insert title here</title>
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
	<hr>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${requestScope.product != null}">
					<form action="edit-product" method="post">
				</c:if>
				<c:if test="${requestScope.product == null}">
					<form action="add-new-product" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${product != null}">
            			Edit Product
            		</c:if>
						<c:if test="${product == null}">
            			Add new product
            		</c:if>
					</h2>
				</caption>

				<c:if test="${product != null}">

					<fieldset class="form-group">
						<label>Product ID</label> <input type="text" name="id"
							value="<c:out value='${product.id}' />" class="form-control">
					</fieldset>
				</c:if>

				<fieldset class="form-group">
					<label>Name</label> <input type="text"
						value="<c:out value='${product.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Title</label> <input type="text"
						value="<c:out value='${product.title}' />" class="form-control"
						name="title">
				</fieldset>

				<fieldset class="form-group">
					<label>Price</label> <input type="text"
						value="<c:out value='${product.price}' />" class="form-control"
						name="price">
				</fieldset>
				<fieldset class="form-group">
					<label>Image</label> <input type="text" value="${product.image}"
						class="form-control" name="image">
				</fieldset>
				<fieldset class="form-group">
					<label>Description</label> <input type="text"
						value="<c:out value='${product.description}' />"
						class="form-control" name="description">
				</fieldset>
				<fieldset class="form-group">
					<label>Category</label> <select id="inputState"
						class="form-control" name="cateID"
						onchange="onAppSelection(this);">
						<c:if test="${product==null }">
							<c:forEach items="${listC}" var="i">


								<option value="${i.cid}">${i.cname}</option>




							</c:forEach>
						</c:if>
						<c:if test="${product!=null }">
							<c:forEach items="${listC}" var="i">

								<c:if test="${product.cateID ==i.cid}">
									<option selected value="${i.cid}">${i.cname}</option>
								</c:if>
								<c:if test="${product.cateID !=i.cid}">
									<option value="${i.cid}">${i.cname}</option>
								</c:if>




							</c:forEach>
						</c:if>
					</select>


					<script type="text/javascript">
						function onAppSelection(selectBox) {
							// clear selection
							for (var i = 0; i <= selectBox.length; i++) {
								var selectedNode = selectBox.options[i];
								if (selectedNode != null) {
									<!--
									selectedNode.removeAttribute("id");
									-->
									selectedNode.removeAttribute("selected");
								}
							}
							// assign  id and selected
							var selectedNode = selectBox.options[selectBox.selectedIndex];
							if (selectedNode != null) {
								<!--
								selectedNode.setAttribute("id", "app_id");
								-->
								selectedNode.setAttribute("selected", "");
							}
						}
					</script>
					<!--  -->
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>