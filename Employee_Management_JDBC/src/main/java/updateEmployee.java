

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
		String newName=request.getParameter("newName");
		String newEmail=request.getParameter("newEmail");
		String phoneStr=request.getParameter("newPhone");
			long newPhone=0L; // initialized long to 0
			newPhone=Long.parseLong(phoneStr);//converted the string phone number in long datatype

		String newAddr=request.getParameter("newAddr");
		String newDesig=request.getParameter("newDesig");
		int newSal=Integer.parseInt(request.getParameter("newSal"));
		
		System.out.println("Entered details for update are :");
		System.out.println("Id: "+eid);
		System.out.println("newName: "+newName);
		System.out.println("newEmail: "+newEmail);
		System.out.println("newPhone: "+newPhone);
		System.out.println("newAddr: "+newAddr);
		System.out.println("newDesig: "+newDesig);
		System.out.println("newSal: "+newSal);
		
		
		
		// establishing connection
		Connection conn=connectionDb.getConnection();
		if(conn!=null) {
			
			// Creating Statement
			try {
				
				PreparedStatement pstmt=conn.prepareStatement("update employee set ename=?,email=?,phone=?,designation=?,salary=?,address=? where eid=?");
				pstmt.setString(1,newName);
				pstmt.setString(2,newEmail);
				pstmt.setLong(3,newPhone);
				pstmt.setString(4,newDesig);
				pstmt.setInt(5,newSal);
				pstmt.setString(6,newAddr);
				
				pstmt.setInt(7, eid);
				
				int n=pstmt.executeUpdate();	
				if(n>0) {
					System.out.println("Employee Details updated successfully");
					out.println("Employee Details updated successfully");
					
					out.print("<head>\r\n"
							+ "<meta charset=\"ISO-8859-1\">\r\n"
							+ "<title>Retrieve Employee</title>\r\n"
							+ "	<link rel=\"stylesheet\" href=\"style2.css\"></link>\r\n"
							+ "</head>\r\n"
							+ "<body>\r\n"
							+ "<p id=\"welcome\">Welcome to Employee Management MiniProject using JDBC and MySql</p>\r\n"
							+ "\r\n");
					
					
					out.println("<center><h2>Employee Updated Successfully");
					out.println("<br><br> <a href=updateEmployee.html>Back</a>");
					out.println("</h2></center>");
					
				}
				else {
					System.out.println("Employee Details Not updated!");
					out.println("Employee Details Not updated!");
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
