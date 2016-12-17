import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class EditArtistServlet extends HttpServlet {
   @Override
   public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
		//Allocate statements variables with getParameter
		int ID = Integer.parseInt(req.getParameter("edit"));
		//Allocate connection and PStatement
		Connection conn = null;
		Statement stmt = null;
		
		res.setContentType("text/html;charset=UTF-8");
      // Allocate a output writer to write the response message into the network socket
      PrintWriter out = res.getWriter();
	
	
	try {
		//Connect to MySQL	
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music?useSSL=false", "root", "pass");
		//Form Statement 
		stmt = conn.createStatement();
		String sqlStr="SELECT * FROM ARTISTS WHERE artistID="+ID;
		ResultSet rset = stmt.executeQuery(sqlStr);
		rset.next();
		int oldYear = rset.getInt("yearformed");
		String oldName = rset.getString("name");
		String oldCountry = rset.getString("country");
		
		out.println("<html><head><link rel='stylesheet' type='text/css' href='main.css'></head><body>"+
						"<h1>Edit Artist</h1>"+
						"<div class='formBox'>"+
							"<form method='post' action='EditArtist2'>"+
								"Artist Name<br> <input type='text' name='name' value="+ "'" +oldName+ "'" + "required><br><br>"+
									"Country<br> <input type='text' name='country' value="+ "'" + oldCountry+"'"+" required><br><br>"+
									"Year Formed<br> <input type='number' name='year' min='1900' max='3000' value="+oldYear+" required><br><br>"+
									"<input type='hidden' name='ID' value="+ID+">"+
									"<input class='submitButton' type='submit' name='submit'><br> <a href='index.jsp'>Cancel</a></form></div></body></html>"
			);
		
		

	//close resources
	stmt.close();
	conn.close();
 } catch(SQLException ex) {
	 out.println("oh no... Something went wrong. <br> <a href='index.jsp'>Try something else</a>");
         ex.printStackTrace();
   }
}
}