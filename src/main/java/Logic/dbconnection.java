package Logic;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.http.HttpServlet;

public class dbconnection extends HttpServlet{
	
	private static final long serialVersionUID = 6297371707784893479L;
	public static Connection dbConnection = null;
	
	 public void init() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				dbconnection.dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeManagement", "bala", "bala");
 			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			
	   }
}
