

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
 * Servlet implementation class updateEmployee
 */
@WebServlet("/updateEmployee")
public class updateEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateEmployee() {
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
		
		// getting Inputs from updateEmployee.html
		int eid=Integer.parseInt(request.getParameter("eId"));
		int option=Integer.parseInt(request.getParameter("option"));
		
		System.out.println("Entered Eid: "+eid+"\noption: "+option);
		

		// establishing connection
		Connection conn=connectionDb.getConnection();
		if(conn!=null) {
			
			// Creating Statement
			try {
				switch(option) {
				
				case 1:
					out.println("<html>\r\n"
							+ "<head>\r\n"
							+ "<meta charset=\"ISO-8859-1\">\r\n"
							+ "<title>Retrieve Employee</title>\r\n"
							+ "	<link rel=\"stylesheet\" href=\"style2.css\"></link>\r\n"
							+ "</head>\r\n"
							+ "<body>\r\n"
							+ "<p id=\"welcome\">Welcome to Employee Management MiniProject using JDBC and MySql</p>\r\n"
							+ "\r\n"
							+ "    <div id=\"nav\">\r\n"
							+ "        <ul>\r\n"
							+ "            <li><a href=\"employeeHome.html\">Home</a></li>\r\n"
							+ "            <li><a href=\"addEmployee.html\">Add New Employee</a></li>\r\n"
							+ "            <li><a href=\"retrieveEmployee.html\">Retrieve Employee</a></li>\r\n"
							+ "            <li><a href=\"updateEmployee.html\">Update Employee Details</a></li>\r\n"
							+ "            <li><a href=\"deleteEmployee.html\">Delete an Employee</a></li>\r\n"
							+ "            <li><a href=\"#\">Show all Employees</a></li>\r\n"
							+ "            \r\n"
							+ "        </ul>\r\n"
							+ "    </div>  \r\n"
							+ "    <form method=\"post\" action=\"updateEmployee\">\r\n"
							+ "    	<div id=\"centerIt\">\r\n"
							+ "            <br>\r\n"
							+ "    		<h2>Enter New Name for the Employee</h2>\r\n"
							+ "    		<br>\r\n"
							+ "    		<input type=\"text\" name=\"newName\" placeholder=\"New_Name\">\r\n"
							+ "    		<br>\r\n"
							+ "            <br>\r\n"
							+ "    		<input type=\"submit\" value=\"Update\">\r\n"
							+ "		</div>\r\n"
							+ "	</form>\r\n"
							+ "</body>\r\n"
							+ "</html>");
					
					String newName=request.getParameter("newName");
					PreparedStatement pstmt=conn.prepareStatement("update employee set ename=? where eid=?");
						pstmt.setString(1,newName);
						pstmt.setInt(2,option);
						
					break;
					
					default:System.out.println("ImProper input");
				}
				
				
				
				
				
				
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("there occured an Exception");
			}
			}else {
			System.out.println("Failed to connect with database");
			}
			
	}

}
