<!doctype html public "-//w3c//dtd html 4.0 transitional//en"
                      "http://www.w3.org/TR/REC-html40/strict.dtd">

<!-- For SQL statements. -->
<%@ page import="java.sql.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
  <head><title>Music Database</title></head>
  <body>
    <h1>Demo Music Database</h1>
	<a href="insert.jsp">Add Artist</a>
	<br>
	<br>
	<!-- connect to databas and query full artist table -->
	<sql:setDataSource var="music" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost/music"
     user="root"  password="pass"/>
	<sql:query dataSource="${music}" var="result">
	SELECT * from artists;
	</sql:query>
	  
	<!-- display artist table -->
	<table border="1" width="100%">
	<tr>
	
	<th>Artist</th>
	<th>Year Formed</th>
	<th>Country</th>
	<th>Edit</th>
	</tr>
	<c:forEach var="row" items="${result.rows}">
	<tr>
	<form action="DeleteArtist" method="post">
	<td><c:out value="${row.name}"/></td>
	<td><c:out value="${row.yearformed}"/></td>
	<td><c:out value="${row.country}"/></td>
	<td>
		
		<button type="submit" name="delete" value="${row.artistID}">delete</button>
	</td>
		
		</form>
	</tr>
	
	</c:forEach>
	</table>
	


  </body>
</html>
