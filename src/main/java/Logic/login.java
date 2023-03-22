package Logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.employee;

public class login extends HttpServlet {	
	static int sickLeaves = 12;
    static int CasualLeaves = 6;
	static int EarnedLeaves = 4;
	private static final long serialVersionUID = 1L;
    public login() {
        super();
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String emailId = request.getParameter("emailId");
		System.out.println(emailId);
		String password = request.getParameter("userPass");
		System.out.println(password);
		PrintWriter out = response.getWriter();
		employee emp = null;
		try {
			PreparedStatement st = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where email_id = ?");
			st.setString(1,emailId);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				emp = new employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
				System.out.println( emp.getEmail_id()); 
				System.out.println( emp.getContact_no());
				System.out.println(emp.getRole());
				if(emp.getEmail_id().equals(emailId)) {
					System.out.println(emp.getRole());
					if((emp.getName()+"007@@").equals(password) ) {
						UUID uuid=UUID.randomUUID(); 					
						Cookie sessionId=new Cookie("SESSIONID",""+uuid);
		 				response.addCookie(sessionId);
						try {
							st = dbconnection.dbConnection.prepareStatement("insert into cookie values(?,?)");
							st.setString(1, uuid.toString());
							st.setString(2, emp.getId());
							st.executeUpdate();
						}
						catch(SQLException ex) {
							System.out.println(ex.getMessage());
						}
						System.out.println(emp.getRole());
						if(emp.getRole().equals("HR")) {
							out.println("HR");
						}
						else if(emp.getRole().equals("Employee")) {
							out.println("emp");
						}
						else if(emp.getRole().equals("Mentor")) {
							System.out.println("dd");
							out.println("Mentor");
						}
						else if(emp.getRole().equals("Manager")) {
							System.out.println("Manager");
							out.println("Manager");
						}
						
					}
					else {
						out.println("Incorrect Password");
					}
				}
				
			}
			else {
				out.println("Employee Not Found");
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());;
		}
		}
}