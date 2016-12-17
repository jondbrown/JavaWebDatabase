import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class DeleteArtistServlet extends HttpServlet {
   @Override
   public void doPost(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
		//Allocate statements variables with getParameter
		int ID = Integer.parseInt(req.getParameter("delete"));
		//Allocate connection and PStatement
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		res.setContentType("text/html;charset=UTF-8");
      // Allocate a output writer to write the response message into the network socket
      PrintWriter out = res.getWriter();
	
	
	try {
		//Connect to MySQL	
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music?useSSL=false", "root", "pass");
		//Form Statement 
		pstmt = conn.prepareStatement("DELETE FROM ARTISTS"
		+ " WHERE artistID="+ID);

		pstmt.executeUpdate();
         
      
		//Print success message
		out.println("Record "+ID+" was deleted from Artist table!<br> <a href='index.jsp'>Home</a>");
		//out.println("<br>" +name+ " formed in " +year+ " in " +country+ "." );

	//close resources
	pstmt.close();
	conn.close();
 } catch(SQLException ex) {
	 out.println("oh no... Something went wrong. <br> <a href='index.jsp'>Try something else</a>");
         ex.printStackTrace();
   }
}
}