<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/view/Header.jsp"></jsp:include>

	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${requestScope.user != null}">
					<form action="profile?action=update" method="post">
				</c:if>
				<c:if test="${requestScope.user == null}">
					<form action="profile?action=insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${user != null}">
            			Edit User
            		</c:if>
						<c:if test="${user == null}">
            			Update User
            		</c:if>
					</h2>
				</caption>

				<c:if test="${user != null}">
					
					<fieldset class="form-group">
					<label>User ID</label> 
					<input type="text" name="id" value="<c:out value='${user.id}' />" class="form-control">
				</fieldset>
				</c:if>

				<fieldset class="form-group">
					<label>User Name</label> 
					<input type="text"	value="<c:out value='${user.name}' />" class="form-control"	name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>User Email</label> 
					<input type="text"	value="<c:out value='${user.email}' />" class="form-control"	name="email">
				</fieldset>

				<fieldset class="form-group">
					<label>Phone Number</label> <input type="text"
						value="<c:out value='${user.phone}' />" class="form-control"
						name="phone">
				</fieldset>
				<c:if test="${mess!=null}">
					<p style='color: blue' >${mess}</p>
				</c:if>
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>








	<script type="text/javascript">
		var savebutton = document.getElementById('savebutton');
		var readonly = false;
		var inputs = document.querySelectorAll('input[type="text"]');
		savebutton.addEventListener('click', function() {

			for (var i = 0; i < inputs.length; i++) {
				inputs[i].toggleAttribute('readonly');
			}
			;

			if (savebutton.innerHTML == "edit") {
				savebutton.innerHTML = "save";
			} else {
				savebutton.innerHTML = "edit";
			}

		});
	</script>

</body>
</html>