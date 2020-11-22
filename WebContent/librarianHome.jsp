<%@page import="logic.bean.LibrBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@ page import="logic.control.LibraryMainPageController" %>   
<%@ page import="java.lang.String" %>   
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>      
   

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <title>Stud.io</title>
    <link href="css/style.css" type="text/css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet" media="screen">

    
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = new google.visualization.arrayToDataTable([
          ['Num', "% on total"],
          ['Busy', ${libraryBean.getPostiOccupati()}],
          ['Booked', ${booked.size()}],
          ['Free', ${free}]
       ]);
 
        var options = {
          is3D: true,
          colors: ['#B22222', '#FFA500', '#008000']
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
        chart.draw(data, options);
      }
      
    </script>


</head>
 <body>
 

    <!-- navbar -->
    <div class="container">
      <nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand">Biblioteca ${libraryBean.getName()}</a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
  
          <ul class="navbar-nav ml-auto"> <!-- "ml-auto" consente di allineare item a destra-->
         
            <li class="nav-link active">
            <form action="UpdateSeatsServlet" name="UpdateSeatsForm" method="GET">
              <a><input class="btn btn-success mx-auto" type="submit" role="button" value="Update seats"></a>
              </form>
            </li>
            <li class="nav-link">
              <a class="btn btn-secondary" href="#">Time table</a>
            </li>
            <li class="nav-link">
              <a class="btn btn-secondary" href="#">Noticeboard</a>
            </li>
            <li class="nav-link">
             <form action="SuperviseServlet" name="SuperviseForm" method="GET">
              <a><input class="btn btn-secondary" type="submit" role="button" value="Recent students"></a>
             </form>
            <li class="nav-link">
             <a class="btn btn-secondary" href="ReportListLibrary.jsp">Reports</a>
            </li>
            <li class="nav-link">
              <a class="btn btn-secondary" href="#">Statistics</a>
            </li>
            <li class="nav-link">
              <a class="btn btn-secondary" href="#">Settings</a>
            </li>
            <li class="nav-link">
              <a class="btn btn-secondary" href="index.html">Log out</a>
            </li>
           </ul>
          </div>
        </nav>
     </div>
    <!-- END navbar -->


    <!-- CONTENT -->
    
   
     <div class="row">
 		<div class="col-6" id="updateSeatsContentColSx">
 			<div class="row">
			    <div class="container" id="titleUpdateSeats">
			    	<h1 align=center><br><br>Update seats:</h1> 
			    </div>
			</div>
			
				<div class="container" id="updateSeats" style=" text-align: center;">
					<div id="piechart_3d" style="width: 700px; height: 350px; align-content: center;">
					</div>
					<!-- button + e - da inserire -->
				    <form action="${pageContext.request.contextPath}/UpdateSeatsServlet" name=updateSeatsBtn method="post">
					<div class="container" id="buttonUpdateSeats" style=" text-align: center; vertical-align: top;">
					    <button class="btn btn-outline-success mx-auto" type="submit" value="+" role="button" type="submit" name ="+" style="width: 140px; height: 60px;"> <font style="verdana" size = "5" >+</font></button>
				        <button class="btn btn-outline-success mx-auto" type="submit" value="-" role="button" type="submit" name ="-" style="width: 140px; height: 60px;"> <font style="verdana" size = "5" >-</font></button>
					</div>
				    </form>
			    </div>
			
		</div>
	 
	
	
	
		<div class="col-6">

			    <div class="container" id="titleBookings">
			    	<h1 align=center><br><br>Bookings:</h1> 
			    </div>
		

			    <div class="table-wrapper-scroll-y my-custom-scrollbar" id="tableBookingsDiv">

				    <table id="tableBookings" class="table table-striped table-bordered table-sm" style="text-align: center;">

					  <thead>
					    <tr>
					      <th>Username</th>
					      <th>Time</th>
					      <th>Check</th>
					    </tr>
					  </thead>
               
					  <tbody>
                       <c:forEach items="${booked}" var= "bookBean">
                        <tr>
					      <td>
					        <label>${bookBean.getUsernameStud()}</label>
					      </td>
					      <td>
					      <label>${bookBean.getOrarioPrenotazione()}</label>
					      </td>
					      <td><button type="button" class="btn btn-outline-success btn-rounded btn-sm m-0">V</button><button type="button" class="btn btn-outline-danger btn-rounded btn-sm m-0">X</button></td>
					    </tr>
                       </c:forEach>

					  </tbody>

					</table>
				</div>
		</div>
      </div>


    <!-- jQuery e plugin JavaScript  -->
   <script src="http://code.jquery.com/jquery.js"></script>
   <script src="js/bootstrap.min.js"></script>

  </body>
</html>

