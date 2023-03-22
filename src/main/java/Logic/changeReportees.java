package Logic;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import Model.employee;

/**
 * Servlet implementation class changeReportees
 */
@WebServlet("/changeReportees")
public class changeReportees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public changeReportees() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray json = new JSONArray();
		ArrayList<employee> a = new ArrayList<employee>();
		employee emp = null;
		String userId = ""+ request.getAttribute("id");
		try {
			PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where ReportingTo = ?");
			stmt.setString(1,userId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				emp = new employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
				a.add(emp);
		}
			for(int i = 0;i<a.size();i++) {
			 stmt = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where ReportingTo = ?");
			 stmt.setString(1, a.get(i).getId());
			 rs = stmt.executeQuery();
				while(rs.next()) {
					emp = new employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
					json.add(emp.getJSON());
			}
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		response.getWriter().append(json.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String empId= request.getParameter("empId");
		JSONArray json = new JSONArray();
		ArrayList<employee> a = new ArrayList<employee>();
		ArrayList<employee> b = new ArrayList<employee>();
		employee emp = null;
		String userId = ""+ request.getAttribute("id");
		try {
			PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where ReportingTo = ?");
			stmt.setString(1,userId);
			System.out.println(userId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				emp = new employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
				a.add(emp);
		}
			System.out.println(a.size());
			
			
			 stmt = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where id = ?");
			stmt.setString(1,empId);
			System.out.println();
			 rs = stmt.executeQuery();
			while(rs.next()) {
				emp = new employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
				b.add(emp);
		}	
			System.out.println(b.size());
			for(int i = 0;i<a.size();i++) {
				if(!(a.get(i).getId().equals(b.get(0).getReportingToList()))){
					json.add(a.get(i).getJSON());
				}
			}
			response.getWriter().append(json.toString());
	}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}

}
}
