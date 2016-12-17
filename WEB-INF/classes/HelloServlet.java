import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
public class InsertServlet extends HttpServlet {
   @Override
   public void doGet(HttpServletRequest req, HttpServletResponse res)
        throws IOException, ServletException {
		String name = req.getParameter("name");
		String country = req.getParameter("country");
		int year = Integer.parseInt(req.getParameter("year"));
		Connection conn = null;
		PreparedStatement pstmt = null;
		res.setContentType("text/html;charset=UTF-8");
      // Allocate a output writer to write the response message into the network socket
      PrintWriter out = res.getWriter();
	try {
			
     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music", "root", "pass"); // MySQL
		pstmt = conn.prepareStatement("INSERT INTO ARTISTS"
		+ " values (?,?,?,?)");
		pstmt.setString(1, name);
		pstmt.setInt(2, 1994);
		pstmt.setString(3, country);
		pstmt.setInt(4, 0);
        pstmt.executeUpdate();
         
      

		out.println("Record is inserted into Artist table!");
		out.println("<br>" +name+ " formed in " +year+ " in " +country+ "." );


	pstmt.close();
	conn.close();
 } catch(SQLException ex) {
	 out.println("shit");
         ex.printStackTrace();
   }
}
}