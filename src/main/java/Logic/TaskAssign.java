package Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Enum.TaskStatus;
import Model.employee;

/**
 * Servlet implementation class TaskAssign
 */
@WebServlet("/TaskAssign")
public class TaskAssign extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<employee> emp1;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskAssign() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 emp1 = new ArrayList<employee>();
		 JSONArray a = new JSONArray();
		employee emp = null;
		long TaskId = System.currentTimeMillis() + new Random().nextInt(1000);
		String userId = ""+ request.getAttribute("id");
		System.out.println(userId);
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
		res.put("taskId", TaskId);
		res.put("employees", a);
		response.getWriter().append(res.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = ""+request.getAttribute("id");
		LocalDate date = java.time.LocalDate.now();
		String taskStatus = TaskStatus.PENDING.toString();
		JSONObject obj = null;
		String a = "";
		String b = "";
		BufferedReader reader = (request).getReader();
		while((b = reader.readLine())!=null) {
			a+=b;
		}
		JSONParser parser = new JSONParser();
		try {
			obj = (JSONObject) parser.parse(a);
			
		} catch (ParseException e) {
			System.out.println(e.getMessage());;
		}
		String empId = (String)obj.get("empId");
		String TaskId = (String)obj.get("TaskId");
		String TaskName = (String)obj.get("TaskName");
		String TaskDescription = (String)obj.get("TaskDescription");
		String SubmissionDate = (String)obj.get("submissionDate");
		String TaskPriority = (String)obj.get("TaskPriority");
		
		try {
			PreparedStatement st = dbconnection.dbConnection.prepareStatement("insert into Task values(?,?,?,?,?,?,?,?,?)");
			st.setString(1, TaskId);
			st.setString(2, TaskName);
			st.setString(3, TaskDescription);
			st.setString(4, empId);
			st.setString(5, userId);
			st.setString(6, date.toString());
			st.setString(7, SubmissionDate);
			st.setString(8, TaskPriority);
			st.setString(9, taskStatus);
			st.executeUpdate();
			response.getWriter().append("Task sucessfully Assigned to the Employee !");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
