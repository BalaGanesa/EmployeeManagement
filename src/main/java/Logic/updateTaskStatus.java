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

import Enum.TaskStatus;
import Model.Task;

/**
 * Servlet implementation class updateTaskStatus
 */
@WebServlet("/updateTaskStatus")
public class updateTaskStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateTaskStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("var");
		String status = null;
		String divname = request.getParameter("divname");
		System.out.println(divname);
		String taskId = request.getParameter("taskId");
		System.out.println(taskId);
		if(divname.equals("div1")) {
			System.out.println("div11");
			status = TaskStatus.PENDING.toString();
		}
		else if(divname.equals("div2")) {
			System.out.println("div21");
			status = TaskStatus.INPROGRESS.toString();
		}
		else if(divname.equals("div3")) {
			System.out.println("div31");
			status = TaskStatus.FINISHED.toString();
		}
		try {
			System.out.println(status);
			System.out.println(taskId);
			PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("update Task set TaskStatus = ? where TaskId = ?");
			stmt.setString(1, status);
			stmt.setString(2, taskId);
			System.out.println(stmt);
			stmt.executeUpdate();
			response.getWriter().append("sucessfully Updated !");
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray json = new JSONArray();
		Task task = null;
		String taskId = request.getParameter("taskId");
		System.out.println(taskId);
		try {
			PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("select * from Task where TaskId = ?");
			stmt.setString(1, taskId);
			ResultSet rs = stmt.executeQuery();
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

}


