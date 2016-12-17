<!doctype html public "-//w3c//dtd html 4.0 transitional//en"
                      "http://www.w3.org/TR/REC-html40/strict.dtd">

<!-- For SQL statements. -->
<%@ page import="java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
  <head>
	<title>Artist Database</title>
	<link rel="stylesheet" type="text/css" href="main.css">
  </head>
  <body>
    <h1>My Favorite Artists</h1>
	<a href="insert.jsp" class="button">Add Artist</a>
	<br>
	<br>
	  <!-- connect to databas and query full artist table -->
	<sql:setDataSource var="music" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/music"
     user="root"  password="pass"/>
	<sql:query dataSource="${music}" var="result">
	SELECT * from artists order by name;
	</sql:query>
	<!-- show artists table -->
	<table border="1" width="100%" class="dbTable">
	<tr>
	
	<th>Artist</th>
	<th>Year Formed</th>
	<th>Country</th>
	<th class="edit">Edit</th>
	</tr>
	<c:forEach var="row" items="${result.rows}">
	<tr>
	
	<td><c:out value="${row.name}"/></td>
	<td><c:out value="${row.yearformed}"/></td>
	<td><c:out value="${row.country}"/></td>
	<td class="edit">
		<!-- buttons to edit or delete entry -->
	<form action="EditArtist" method="post">
		<button type="submit" name="edit" value="${row.artistID}">Edit</button>
		</form>
		<form action="DeleteArtist" method="post">
		<button type="submit" name="delete" value="${row.artistID}">Delete</button>
		</form>
	</td>
		
		
	</tr>
	
	</c:forEach>
	</table>
	


  </body>
</html>
