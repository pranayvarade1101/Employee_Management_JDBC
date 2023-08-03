

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
 * Servlet implementation class addEmployee
 */
@WebServlet("/addEmployee")
public class addEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addEmployee() {
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
		
		// inputs from addEmployee.html
		String ename=request.getParameter("eName");
		String email=request.getParameter("email");
			String phoneStr=request.getParameter("phone");
			long phone=0L; // initialized long to 0
		phone=Long.parseLong(phoneStr);//converted the string phone number in long datatype
		String addr=request.getParameter("addr");
		int salary=Integer.parseInt(request.getParameter("sal"));
		String desig=request.getParameter("desig");
		
		 
		// Establishing connection with database
		Connection conn=connectionDb.getConnection();
		
		if(conn!=null) {
		// Creating Statement
			try {
				PreparedStatement pstmt=conn.prepareStatement("insert into employee(ename,address,salary,designation,phone,email) values(?,?,?,?,?,?)");
				pstmt.setString(1,ename);
				pstmt.setString(2, addr);
				pstmt.setInt(3,salary);
				pstmt.setString(4,desig);
				pstmt.setLong(5,phone);
				pstmt.setString(6, email);
				
				
				// Executing the Query
				int n=pstmt.executeUpdate();// executeUpdate() returns integer which denotes the no. of rows affected in the table
				if(n>0) {
					System.out.println("Values Inserted Successfully (New Employee added)");
					
					
					out.print("<head>\r\n"
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
							+ "            <li><a href=\"#\">Update Employee Details</a></li>\r\n"
							+ "            <li><a href=\"#\">Delete an Employee</a></li>\r\n"
							+ "            <li><a href=\"#\">Show all Employees</a></li>\r\n"
							+ "            \r\n"
							+ "        </ul>\r\n"
							+ "    </div>");
					
					
					out.println("<center><h2> New Employee Added Successfully");
					out.println("<br><br> <a href=addEmployee.html>Back</a>");
					out.println("</h2></center>");
				}
				else {
					System.out.println("Values Not Inserted Successfully");
					out.println("<h2> New Employee NOT Added");
					out.println("</h2>");
					
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Values not Inserted (New Employee not added) ");
			}
		}
		else {
			System.out.println("Failed to connect with database");
		}
	}
}
