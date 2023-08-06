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
        int eid = Integer.parseInt(request.getParameter("eId"));
        System.out.println("The ID entered is: " + eid);

        // Establishing connection
        Connection conn = connectionDb.getConnection();

        try {
            if (conn != null) {
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM employee WHERE eid=?");
                pstmt.setInt(1, eid);

                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    int eid1 = rs.getInt("eid");
                    String ename=rs.getString("ename");
                    String email = rs.getString("email");
                    long phone = rs.getLong("phone");
                    String designation = rs.getString("designation");
                    float sal = rs.getFloat("salary");
                    String addr = rs.getString("address");

                    // Print the results in a table format
                    
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
            
                
                    out.println("<tr style=\"font-size: 20px;\"><td>" + eid1 + " </td><td>" + ename + " </td><td>" +email + " </td><td>" + phone
                            + " </td><td>" + designation + " </td><td>" + sal + " </td><td>" + addr+"</tr>");
                    
                    out.print("<br><br>");
                    
                    out.print("<br><h2><a href=\"retrieveEmployee.html\">Back</a></h2>");
                    out.print("</br></center>");
                  
                } else {
                    out.println("<center><p>Record not found for " + eid + "</p></center>");
                }
            } else {
                out.println("<center><p>Failed to connect with the database!</p></center>");
            }
        } catch (SQLException e) {
            out.println("<center><p>Error occurred while processing the request.</p></center>");
            System.out.println("Not retrieved employee due to an exception!");
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
