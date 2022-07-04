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


</head>
<body>
	<c:redirect url="/view/index.jsp"></c:redirect>
	<form action="profile" method="post" enctype="multipart/form-data">
			<div class="card">
				<div class="info">
					<span>Edit form</span>
					<button id="savebutton" type="submit">edit</button>
				</div>
				<div class="forms">
					<div class="inputs">
						<span>Name</span> <input type="text" value="" name="name">
					</div>
					<div class="inputs">
						<span>Phone Number</span> <input type="text" value="" name="phone">
					</div>
					<div class="inputs">
						<span>Email</span> <input type="text" value="" name="email">
					</div>
					<div class="inputs">
						<span>Avatar</span> <input type="file" value="images/${image }"
							name="image">
					</div>
					<img alt="" src="images/${image}"> <br>


				</div>
			</div>
		</form>
	
</body>
</html>
