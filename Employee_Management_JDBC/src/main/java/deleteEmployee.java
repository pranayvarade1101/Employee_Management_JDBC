

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mysqlConnection.connectionDb;

/**
 * Servlet implementation class deleteEmployee
 */
@WebServlet("/deleteEmployee")
public class deleteEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		
		//accepting inputs from html
		int eid =Integer.parseInt(request.getParameter("eId"));
		
		// establishing connection
		Connection conn =connectionDb.getConnection();
		
		// Creating Statement
		if(conn!=null) {
			try {
				
				PreparedStatement pstmt=conn.prepareStatement(" delete from employee where eid=?");
				pstmt.setInt(1, eid);
				
				// executing the Query
				int n=pstmt.executeUpdate();
				
				if(n>0) {
					System.out.print("Employee with Id:"+eid+" deleted successfully");
					
					out.print("<head>\r\n"
							+ "<meta charset=\"ISO-8859-1\">\r\n"
							+ "<title>Retrieve Employee</title>\r\n"
							+ "	<link rel=\"stylesheet\" href=\"style2.css\"></link>\r\n"
							+ "</head>\r\n"
							+ "<body>\r\n"
							+ "<p id=\"welcome\">Welcome to Employee Management MiniProject using JDBC and MySql</p>\r\n"
							+ "\r\n");
					
					
					out.println("<center><h2> <h2>Employee with Id:"+eid+" deleted successfully");
					out.println("<br><br> <a href=deleteEmployee.html>Back</a>");
					out.println("</h2></center>");
					
					
					
					out.print("</h2>");
				}else {
					System.out.print("Employee with Id:"+eid+" NOT deleted!");
					out.print("<h2>Employee with Id:"+eid+" NOT deleted!</h2>");
					
				}
				
			}
			catch(SQLException e) {
				e.printStackTrace();
				System.out.println("Employee not deleted due to an exception");
			}
			
			
			
		}else {
			System.out.println("Failed to connect with the database!");
			out.println("Failed to connect with the database!");
		}
		
	}

}
