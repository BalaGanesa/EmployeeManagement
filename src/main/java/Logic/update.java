package Logic;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Enum.status;

/**
 * Servlet implementation class update
 */
@WebServlet("/update")
public class update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public update() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 JSONObject obj = null;
		try {
			obj = (JSONObject) new JSONParser().parse((String) request.getParameter("json"));
			System.out.println(obj);
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
				PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("update employeeDetail set name = ?,DOB = ?,Email_id = ?,contact_no = ? where id = ?");
				stmt.setString(1, (String)obj.get("empName"));
				stmt.setString(2, (String)obj.get("DOB"));
				stmt.setString(3, (String)obj.get("email_id"));
				stmt.setString(4, (String)obj.get("ph_no"));
				stmt.setString(5, (String)obj.get("empId"));
				stmt.executeUpdate();
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json = new JSONObject();
	 	String id = null;
		String currentlyLoged = request.getParameter("currentlyLoged");
		PreparedStatement stmt;
		try {
			stmt = dbconnection.dbConnection.prepareStatement("select * from cookie where SESSIONID = ?");
			stmt.setString(1, currentlyLoged);
		    stmt.executeQuery();
		   ResultSet re = stmt.executeQuery();
		   if(re.next()) {
			   id = re.getString(2);
		   }
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
	
		try {
			stmt = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where id = ?");
			stmt.setString(1, id);
			 ResultSet re = stmt.executeQuery();
			if(re.next()) {
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				json.put("empID", re.getString(2));
				json.put("empName", re.getString(3));
				json.put("DOB", sdf.format(df.parse(re.getString(4))));
				json.put("team", re.getString(5));
				json.put("Emailid", re.getString(7));
				json.put("contactno", re.getString(8));
				try {
					stmt = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where id = ?");
					stmt.setString(1,re.getString(10));
					 re = stmt.executeQuery();
					if(re.next()) {
						json.put("ReportingTO",re.getString(3));
					}
					}
				catch(SQLException e) {
					System.out.println(e.getMessage());
				}
				}
			}
			
			
	
		catch(SQLException | ParseException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(id);
		response.getWriter().append(json.toString());
	}

}
