package Logic;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Enum.status;
import Model.employee;

/**
 * Servlet implementation class RemoveEmployee
 */
@WebServlet("/RemoveEmployee")
public class RemoveEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		employee emp = null;
		String empID = request.getParameter("empId");
		System.out.println(empID);
		try {
			PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where id = ?");
			stmt.setString(1, empID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				emp = new employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
			}
			if(emp!=null) {
				response.getWriter().append("Are you confirm to Remove Employee");
			}
			else {
				response.getWriter().append("Please enter Valid empId");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String empID = request.getParameter("empId");
		System.out.println("32432"+empID);
		try {
			PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("update employeeDetail set status = ? where id = ?");
			stmt.setString(1, status.ARCHIVED.toString());
			stmt.setString(2, empID);
			stmt.executeUpdate();
			response.getWriter().append("Sucessfully Updated !");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
