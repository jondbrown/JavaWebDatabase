import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class EditArtistServlet2 extends HttpServlet {
   @Override
   public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
		//Allocate statements variables with getParameter
		String name = req.getParameter("name");
		String country = req.getParameter("country");
		int year = Integer.parseInt(req.getParameter("year"));
		int ID = Integer.parseInt(req.getParameter("ID"));
		//Allocate connection and PStatement
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		res.setContentType("text/html;charset=UTF-8");
      // Allocate a output writer to write the response message into the network socket
      PrintWriter out = res.getWriter();
	
	try {
		//Connect to MySQL	
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music", "root", "pass");
		//Form Statement 
		pstmt = conn.prepareStatement("UPDATE artists SET name=?, yearformed=?, country=? WHERE artistID=? ");
		pstmt.setString(1, name);
		pstmt.setInt(2, year);
		pstmt.setString(3, country);
		pstmt.setInt(4, ID);
        pstmt.executeUpdate();
         
      
		//Print success message
		out.println("Record is has been Updated in Artist table!");
		out.println("<br>" +name+ " formed in " +year+ " in " +country+ ". <br> <a href='index.jsp'>Home</a>");

	//close resources
	pstmt.close();
	conn.close();
 } catch(SQLException ex) {
	 out.println("oh no... Something went wrong. <br> <a href='index.jsp'>Try something else</a>");
         ex.printStackTrace();
   }
}
}