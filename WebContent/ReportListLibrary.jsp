<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="logic.control.ReportIssueController"%>
	<%@page import="logic.entity.Student" %>
	<%@page import="logic.entity.Library"%>
	<%@page import="logic.entity.User"%>

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
        <a class="navbar-brand">Biblioteca ${libraryBean.getName()}</a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
  
          <ul class="navbar-nav ml-auto"> <!-- "ml-auto" consente di allineare item a destra-->
         
            <li class="nav-link active">
            <form action="UpdateSeatsServlet" name="UpdateSeatsForm" method="GET">
              <a><input class="btn btn-secondary" type="submit" role="button" value="Update seats"></a>
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
            </li>
             <li class="nav-link">
             <a class="btn btn-success mx-auto" href="ReportListLibrary.jsp">Reports</a>
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


	<div>

		<div class="container" id="titleReports">
			<h1 align=center>
				<br>
				<br>Your reports:
			</h1>
		</div>


		<div class="table-wrapper-scroll-y my-custom-scrollbar"
			id="tableReportsDiv">

			<table id="tableBookings"
				class="table table-striped table-bordered table-sm"
				style="text-align: center;">

				<thead>
					<tr>
						<th>Report id</th>
						<th>Object</th>
						<th>User</th>
						<th>Status</th>
						<th></th>
					</tr>
				</thead>
				<!-- creazione controller e inizializzazione della lista di report -->
					<%
					 	request.getSession().setAttribute("reportIssueController", new ReportIssueController((Library)request.getSession().getAttribute("sessionUser")));
						((ReportIssueController)request.getSession().getAttribute("reportIssueController")).getLibraryReports();
					%>
					
					<tbody>
					<% for(Integer i = 0; i < ((ReportIssueController)request.getSession().getAttribute("reportIssueController")).getSessionUser().getReports().size(); i++) { %>
		       			<tr id="<%="report".concat(i.toString())%>">
							<td><label id="<%="reportId".concat((((ReportIssueController)request.getSession().getAttribute("reportIssueController")).getSessionUser().getReports().get(i).getReportId()).toString())%>"><%=((ReportIssueController)request.getSession().getAttribute("reportIssueController")).getSessionUser().getReports().get(i).getReportId()%></label></td>
							<td><label id="<%="reportObject".concat((((ReportIssueController)request.getSession().getAttribute("reportIssueController")).getSessionUser().getReports().get(i).getReportId()).toString())%>"><%=((ReportIssueController)request.getSession().getAttribute("reportIssueController")).getSessionUser().getReports().get(i).getTitle()%></label></td>
							<td><label id="<%="reportUser".concat((((ReportIssueController)request.getSession().getAttribute("reportIssueController")).getSessionUser().getReports().get(i).getReportId()).toString())%>"><%=((ReportIssueController)request.getSession().getAttribute("reportIssueController")).getSessionUser().getReports().get(i).getStudentId()%></label></td>
							<td><label id="<%="reportStatus".concat((((ReportIssueController)request.getSession().getAttribute("reportIssueController")).getSessionUser().getReports().get(i).getReportId()).toString())%>"><%=((ReportIssueController)request.getSession().getAttribute("reportIssueController")).getSessionUser().getReports().get(i).getStatus()%></label></td>
							<td><form action="${pageContext.request.contextPath}/ReportListLibraryServlet" name="btnReport" method="GET">
									<input class="btn btn-outline-success btn-rounded btn-sm m-0"
									id="<%="btnOpenReport".concat((((ReportIssueController)request.getSession().getAttribute("reportIssueController")).getSessionUser().getReports().get(i).getReportId()).toString())%>"
									role="button" name="<%="btnOpen".concat((((ReportIssueController)request.getSession().getAttribute("reportIssueController")).getSessionUser().getReports().get(i).getReportId()).toString())%>" type="submit"
									value="Open">
									<input class="btn btn-outline-danger btn-rounded btn-sm m-0"
									id="<%="btnDeleteReport".concat((((ReportIssueController)request.getSession().getAttribute("reportIssueController")).getSessionUser().getReports().get(i).getReportId()).toString())%>"
									role="button" name="<%="btnDelete".concat((((ReportIssueController)request.getSession().getAttribute("reportIssueController")).getSessionUser().getReports().get(i).getReportId()).toString())%>" type="submit"
									value="Delete"></form></td>
						</tr>
	   				<% } %>

				</tbody>

			</table>
		</div>
	</div>



	<!-- jQuery e plugin JavaScript  -->
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>

