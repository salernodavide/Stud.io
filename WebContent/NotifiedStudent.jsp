
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

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
					
					<li class="nav-link">
					  <form action="StudentStateServlet"  method="GET">
					    <a><input class="btn btn-success mx-auto" type="submit" role="button" value="Messages"></a>
                      </form></li>
					<li class="nav-link"><a class="btn btn-secondary"
						href="index.html">Log out</a></li>
				</ul>
			</div>
		</nav>
	</div>

	<!-- END navbar -->
    <h1 align="center"><br><br>Reported account</h1>
     <br>
    
    <form action="StudentStateServlet" name="NotifiedForm" method="POST">
     <div id="supervise" class="form-group" >
      	

			    <div class="table-wrapper-scroll-y my-custom-scrollbar">

				    <table class="table table-striped table-bordered table-sm" style="text-align: center;">

					  <thead>
					    <tr>
					      <th><font style="verdana" size = "5" >Title</font> </th>
					      <th><font style="verdana" size = "5" >Text</font> </th>
					      <th><font style="verdana" size = "5" >For explanations</font></th>
					    </tr>
					  </thead>
               
					  <tbody>
                       <c:forEach items="${messages}" var= "messages">
                        <tr>
					      <td>
					        <font style="verdana" size = "5" > ${messages.getTitle()}</font> 
					      </td>
					      <td>
					      <font style="verdana" size = "5" > ${messages.getText()}</font> 
					      </td>
					      <td>
                           <font style="verdana" size = "5" > ${messages.getLibrarianId()}</font>
                          </td>
					    </tr>
                       </c:forEach>

					  </tbody>

					</table>
				</div>
		 <div align="center">
			<button class="btn btn-outline-success mx-auto" role="button" type="submit"  name ="Ok" > <font style="verdana" size = "6" >I understood the messages</font></button>
		 </div>	
       
               
       </div>
       </form>
</body>
</html>