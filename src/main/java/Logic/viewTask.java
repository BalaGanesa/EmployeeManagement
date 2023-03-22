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
import org.json.simple.JSONObject;

import Model.Task;
import Model.employee;

/**
 * Servlet implementation class viewTask
 */
@WebServlet("/viewTask")
public class viewTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray json = new JSONArray();
		Task task = null;
		String userId = ""+request.getAttribute("id");
		System.out.println("hischui"+userId);
		try {
			PreparedStatement st = dbconnection.dbConnection.prepareStatement("select * from Task where AssignedTo = ?");
			st.setString(1, userId);
			ResultSet rs = st.executeQuery();
			JSONObject jObj = new JSONObject();
			jObj.put("TaskId","TaskId");
			jObj.put("TaskName","TaskName");
			jObj.put("TaskDescription","TaskDescription");
			jObj.put("AssignedTo","AssignedTo");
			jObj.put("AssignedBy","AssignedBy");
			jObj.put("Assigneddate","Assigneddate");
			jObj.put("submissionDate","submissionDate");
			jObj.put("Priority","Priority");
			jObj.put("TaskStatus","TaskStatus");
			json.add(jObj);
			while(rs.next()) {
				task = new Task(rs.getString(1),rs.getString(2),rs.getInt(4),rs.getInt(5),rs.getString(3),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
				json.add(task.getJSON());
			}
			System.out.println(json.toString());
			response.getWriter().append(json.toString());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<employee> aa = new ArrayList<>();
		employee emp = null;
		JSONArray json = new JSONArray();
		Task task = null;
		String userId = ""+request.getAttribute("id");
			JSONObject jObj = new JSONObject();
			jObj.put("TaskId","TaskId");
			jObj.put("TaskName","TaskName");
			jObj.put("TaskDescription","TaskDescription");
			jObj.put("AssignedTo","AssignedTo");
			jObj.put("AssignedBy","AssignedBy");
			jObj.put("Assigneddate","Assigneddate");
			jObj.put("submissionDate","submissionDate");
			jObj.put("Priority","Priority");
			jObj.put("TaskStatus","TaskStatus");
			json.add(jObj);
			try {
				System.out.println(userId);
				PreparedStatement st = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where ReportingTo = ?");
			 st.setString(1, userId);
			 ResultSet rs = st.executeQuery();
			 while(rs.next()) {
				 emp = new employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
				 aa.add(emp);
			 }
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.println("ArrayListSize"+aa.size());
			try {
				for(int i=0;i<aa.size();i++) {
					PreparedStatement   st = dbconnection.dbConnection.prepareStatement("select * from Task where AssignedBy = ?");
				    st.setString(1, aa.get(i).getId());
				    ResultSet  rs = st.executeQuery();	
				    while(rs.next()) {
						task = new Task(rs.getString(1),rs.getString(2),rs.getInt(4),rs.getInt(5),rs.getString(3),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9));
						json.add(task.getJSON());
					}
				}
			}
			catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
			System.out.println(json.toString());
			response.getWriter().append(json.toString());
		}
		
		
	


}
