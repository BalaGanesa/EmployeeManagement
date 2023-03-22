package Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Enum.Request;

/**
 * Servlet implementation class Requirement
 */
@WebServlet("/Requirement")
public class Requirement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Requirement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Request[] arr = Request.values();
		JSONArray json = new JSONArray();
		for(Request req:arr) {
	 		json.add(req.toString());
		}
		response.getWriter().write(json.toString()); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject obj = null;
		String a = "";
		String b = "";
		BufferedReader reader = request.getReader();
		while((b = reader.readLine())!=null) {
			a+=b;
		}
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject) parser.parse(a);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String empId =  (String)obj.get("empId");
		String EmployeeName =(String) obj.get("empName");
		Date date = new Date();
		String require = (String)obj.get("require");
		String reason  = (String)obj.get("reason");
		SimpleDateFormat fd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("insert into Request values(?,?,?,?,?,?)");
			stmt.setString(1, empId);
			stmt.setString(2, EmployeeName);
			stmt.setString(3, fd.format(date));
			stmt.setString(4, require);
			stmt.setString(5, reason);
			stmt.setString(6,"PENDING");
			stmt.executeUpdate();
		}
        catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
