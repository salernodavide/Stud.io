<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="logic.entity.User"
import="logic.entity.Library"%>

<%
	Library selectedLibrary=(Library)request.getAttribute("selectedLibrary");
	request.getSession().setAttribute("currentLibrary", selectedLibrary);
%>

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

	<!-- END navbar -->




	<form action="${pageContext.request.contextPath}/StudentSearchLibrServlet" name="bookBtn" method="GET">
		<div class="row" align="center">
			<div class="container" id="listAttr" align="center">

				<h1 align=center>
					<br>
					<br>Library:
				</h1>

				<div class="table-wrapper-scroll-y my-custom-scrollbar">

					<table id="tableLib"
						class="table table-striped table-bordered table-sm"
						style="content-align: center;">
						<tbody>
							<tr>
								<td><p class="text-justify" align="center">
										Name:
										<%out.println(selectedLibrary.getName());%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Address:
										<%out.println(selectedLibrary.getIndirizzo());%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										City:
										<% out.println(selectedLibrary.getCity());%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Mail:
										<%out.println(selectedLibrary.getMail());%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Phone:
										<% out.println(selectedLibrary.getPhone());%>
									</td>
							</tr>
							<tr>
								<td><p class="text-justify" align="center">
										Capacity:
										<%out.println(String.valueOf(selectedLibrary.getCapacity()));%>
									</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="container" id="librPageButtons">
				<p class="text-center">
					<input class="btn btn-success mx-auto" role="button" id="bookSeat"
						name="bookSeat" type="submit" value="Book seat"> <a
						href="ReportListStudent.jsp" class="btn btn-success mx-auto"
						role="button">Report issue</a> 
						<a href=""class="btn btn-success mx-auto" role="button">Timetable</a> <a
						href="" class="btn btn-success mx-auto" role="button">Noticeboard</a>
					<a href="" class="btn btn-success mx-auto" role="button">Map</a>
				</p>
			</div>
		</div>

	</form>


	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>