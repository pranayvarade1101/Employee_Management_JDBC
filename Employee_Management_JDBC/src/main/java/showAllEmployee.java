import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mysqlConnection.connectionDb;

@WebServlet("/showAllEmployee")
public class showAllEmployee extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET request if needed
        // For example, you can redirect to the doPost method
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Establishing connection
        try (Connection conn = connectionDb.getConnection()) {
            if (conn != null) {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM employee");

                out.print("<head>\r\n"
                		+ "<meta charset=\"ISO-8859-1\">\r\n"
                		+ "<title>Retrieve Employee</title>\r\n"
                		+ "	<link rel=\"stylesheet\" href=\"style2.css\"></link>\r\n"
                		+ "</head>\r\n"
                		+ "<body>\r\n"
                		+ "<p id=\"welcome\">Welcome to Employee Management MiniProject using JDBC and MySql</p>\r\n"
                		+ "\r\n");
                
                out.print("<br><br><br>");
                
                out.print("<center><table border=\"1\">\r\n"
                		+ "     <tr style=\"font-size: 25px;\">\r\n"
                		+ "            <th>Employee Id</th>\r\n"
                		+ "            <th>Name</th>\r\n"
                		+ "            <th>Email</th>\r\n"
                		+ "            <th>Phone</th>\r\n"
                		+ "            <th>Designation</th>\r\n"
                		+ "            <th>Salary</th>\r\n"
                		+ "            <th>Address</th>\r\n"
                		+ "     </tr>");
                while (rs.next()) {
                    int eid = rs.getInt("eid");
                    String ename = rs.getString("ename");
                    String email = rs.getString("email");
                    long phone = rs.getLong("phone");
                    String designation = rs.getString("designation");
                    float sal = rs.getFloat("salary");
                    String addr = rs.getString("address");

                    out.println("<tr><td>" + eid + "</td><td>" + ename + "</td><td>" + email + "</td><td>" + phone
                            + "</td><td>" + designation + "</td><td>" + sal + "</td><td>" + addr + "</td></tr>");
                }
                
                out.print("<br><br>");
                
                out.print("<br><h2><a href=\"employeeHome.html\">Back</a></h2>");
                out.print("</br></center>");

            } else {
                out.println("<p>Failed to connect to the database!</p>");
            }
        } catch (SQLException e) {
            out.println("<p>An error occurred while fetching data: " + e.getMessage() + "</p>");
        }
    }
}
