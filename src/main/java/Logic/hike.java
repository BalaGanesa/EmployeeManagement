package Logic;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Model.employee;

/**
 * Servlet implementation class hike
 */
@WebServlet("/hike")
public class hike extends HttpServlet {
	private static final long serialVersionUID = 1L;
	JSONArray a = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hike() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		employee emp = null;
		 a = new JSONArray();
		String userId = ""+ request.getAttribute("id");
		try {
			PreparedStatement st = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where ReportingTo = ?");
			st.setString(1,userId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				emp = new employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
				a.add(emp.getJSON());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		JSONObject res = new JSONObject();
		res.put("employees", a);
		response.getWriter().append(res.toString());
	}
	
	// TODO Auto-generated method stub


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ss");
		employee emp = null;
		String empID = request.getParameter("empId");
		String hike = request.getParameter("hike");
		String [] id = empID.split(" ");
		try {
		PreparedStatement st = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where id = ?");
		st.setString(1,empID);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			emp = new employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
		}
		double salary =emp.getSalary();
		salary += salary*(Double.parseDouble(hike))/100;
		st = dbconnection.dbConnection.prepareStatement("update employeeDetail set salary = ? where id = ?");
		st.setDouble(1,salary);
		st.setString(2, id[0]);
		st.executeUpdate();
		response.getWriter().append("Sucessfully Updated !");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
