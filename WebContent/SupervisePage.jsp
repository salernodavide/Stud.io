<%@page import="logic.bean.LibrBean"%>
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
        <a class="navbar-brand" href="#">Biblioteca <%out.println(((LibrBean) session.getAttribute("libraryBean")).getName()); %></a>
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
              <a><input class="btn btn-success mx-auto" type="submit" role="button" value="Recent students"></a>
              </form>
            </li>
            <li class="nav-link">
             <a class="btn btn-secondary" href="ReportListLibrary.jsp">Reports</a></li>
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
    <h1 align=center><br><br>Recent students</h1>
    <br>
    
    <form action="${pageContext.request.contextPath}/SuperviseServlet" name="SuperviseForm" method="GET">
     <div id="supervise" class="form-group" >
       <c:forEach items="${usernameList}" var= "usernameList">
        <button class="btn btn-outline-success mx-auto" role="button" type="submit"  name ="${usernameList}" style="width: 300px; height: 90px;"> <font style="verdana" size = "6" > ${usernameList}</font></button>		    
             
        </c:forEach>
       
       </div>
       </form>
    
 

</body>
</html>






















