import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionDb {
	
	public static final String url="jdbc:mysql://localhost:3306/employeemanage";
	public static final String user="root";
	public static final String password="meraubuntu";
	
	public static Connection getConnection(){
		
		// Loading the Driver 
		System.out.println("Attempting to load the MySql jdbc Driver");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySql JDBC Driver Loaded successfully");
		}catch(ClassNotFoundException e) {
			System.out.println("MySql JDBC driver Failed to load");
		}	
		
		// Establishing the Connection with Database
		Connection conn=null;
		
		try {
			conn=DriverManager.getConnection(url,user,password);
			System.out.println("Connection with database established successfully");
		}catch(SQLException e) {
			System.out.println("Failed to connect to the database");
		}
			
	return conn;
}
}