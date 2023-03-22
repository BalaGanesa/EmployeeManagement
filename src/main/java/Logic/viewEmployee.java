package Logic;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


@WebServlet("/view")
public class viewEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewEmployee() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	 
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	JSONArray Json = new JSONArray(); 
		try (PreparedStatement st = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where status = ?")) {
			st.setString(1,"ACTIVE");
			ResultSet set = st.executeQuery();
			while(set.next()) {
				JSONObject js = new JSONObject();
				js.put("name", set.getString(3)+" ("+set.getString(2)+")");
				js.put("id", set.getString(2));
				js.put("title", set.getString(1)+" ("+set.getString(5)+")");
				js.put("ReportTingTo", set.getString(10)==null?"NULL":set.getString(10));
				Json.add(js);
			}
			response.getWriter().append(Json.toString());

} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String EmpID = request.getParameter("empId");
		DBservice obj = new DBservice();
		response.getWriter().append(obj.viewEmployeeProfile(EmpID).toString());
	}
	}

