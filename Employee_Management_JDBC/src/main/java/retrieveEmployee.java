import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysqlConnection.connectionDb;

@WebServlet("/retrieveEmployee")
public class retrieveEmployee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public retrieveEmployee() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET requests if needed
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Getting inputs from retrieveEmployee.html
        String ename = request.getParameter("eName");
        System.out.println("The name entered is: " + ename);

        // Establishing connection
        Connection conn = connectionDb.getConnection();

        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employee WHERE ename=?");
                pstmt.setString(1, ename);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int eno = rs.getInt("eno");
                    String address = rs.getString("address");
                    double salary = rs.getDouble("salary");
                    String designation = rs.getString("designation");
                    String phone = rs.getString("phone");
                    String email = rs.getString("email");

                    // Print the results in a table format
                    
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
                    		+ "    </div> ");
                    
                    out.print("<br><br><br>");
                    
                    out.print("<center><table border=\"1\">\r\n"
                    		+ "     <tr style=\"font-size: 25px;\">\r\n"
                    		+ "            <th>Employee Id</th>\r\n"
                    		+ "            <th>Name</th>\r\n"
                    		+ "            <th>Address</th>\r\n"
                    		+ "            <th>Salary</th>\r\n"
                    		+ "            <th>Designation</th>\r\n"
                    		+ "            <th>Phone</th>\r\n"
                    		+ "            <th>Email</th>\r\n"
                    		+ "     </tr>");
            
                
                    out.println("<tr style=\"font-size: 20px;\"><td>" + eno + " </td><td>" + ename + " </td><td>" + address + " </td><td>" + salary
                            + " </td><td>" + designation + " </td><td>" + phone + " </td><td>" + email+"</tr>");
                    
                    out.print("<br><br>");
                    
                    out.print("<br><h2><a href=\"retrieveEmployee.html\">Back</a></h2>");
                    out.print("</br></center>");
                  
                } else {
                    out.println("<center><p>Record not found for " + ename + "</p></center>");
                }
            } else {
                out.println("<center><p>Failed to connect with the database!</p></center>");
            }
        } catch (SQLException e) {
            out.println("<center><p>Error occurred while processing the request.</p></center>");
            e.printStackTrace();
        } finally {
            // Close resources in the reverse order of their creation
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
