<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="logic.bean.ReportBean"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stud.io</title>
<link href="css/style.css" type="text/css" rel="stylesheet">
<link href="css/bootstrap.css" rel="stylesheet" media="screen">
</head>
<body>

	<!-- navbar -->
	<div class="container">
		<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
			<a class="navbar-brand" href="#">${studentBean.getName()} ${studentBean.getSurname()}</a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ml-auto">
					<!-- "ml-auto" consente di allineare item a destra-->
					<li class="nav-link"><a class="btn btn-success mx-auto"
						href="studentHome.jsp">Search<span class="sr-only">(current)</span></a>
					</li>
					<li class="nav-link"><a class="btn btn-secondary"
						href="#">Bookmarks</a></li>
					<li class="nav-link"><a class="btn btn-secondary"
						href="#">Messages</a></li>
					<li>
					<li class="nav-link"><a class="btn btn-secondary"
						href="#">Settings</a></li>
					<li>
					<li class="nav-link"><a class="btn btn-secondary"
						href="index.html">Log out</a></li>
				</ul>
			</div>
		</nav>
	</div>

	<!-- CONTENT -->


	<div>

		<div class="container" id="Report details:">
			<h1 align=center>
				<br>
				<br>Report details:
			</h1>
		</div>

		<div class="container" id="Report details:">
			<label for="reportTitle">Title</label> 
			<input
				readonly class="form-control" id="reportTitle" name="reportTitle" type="text"
				value="<%out.println(((ReportBean)request.getSession().getAttribute("selectedReport")).getTitle());%>">
		
			<div class="form-group">
				<label for="reportDescription">Description</label>
				 <textarea
				readonly class="form-control" id="reportDescription" name="reportDescription"><%=((ReportBean)request.getSession().getAttribute("selectedReport")).getDescription()%></textarea>
			</div>
		</div>

	</div>


	<!-- jQuery e plugin JavaScript  -->
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>


