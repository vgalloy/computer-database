<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id:
						${computer.getId()}</div>
					<h1>Edit Computer</h1>

					<form action="editComputer" method="POST"
						onsubmit="return checkPostForm(document)">
						<input type="hidden" name="computerId" id="computerId"
							value="${computer.getId()}" />
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									name="computerName" type="text" class="form-control"
									id="computerName" value="${computer.getName()}">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									name="introduced" type="text" class="form-control"
									id="introduced" value="${computer.getIntroduced()}">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									name="discontinued" type="text" class="form-control"
									id="discontinued" value="${computer.getDiscontinued()}">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<c:forEach var="i" begin="0" end="${companies.size() - 1}">
										<option
											<c:if test="${companies.get(i).getId() == computer.getCompanyName()}">
										selected
										</c:if>
											value="${i}">${companies.get(i).getName()}</option>
									</c:forEach>
								</select>
							</div>



						</fieldset>
						<div class="actions pull-right">
							<input id="editButton" type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script src="js/jquery.min.js"></script>
	<script src="js/myFunction.js"></script>
</body>
</html>