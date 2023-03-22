package Logic;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Enum.notify;
import Model.Notification;

/**
 * Servlet implementation class LeaveApporve
 */
@WebServlet("/leave")
public class LeaveApporve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveApporve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = request.getParameter("id");
		System.out.println("id"+id);
		String status = request.getParameter("status");
		String userId = null;
		Cookie[] cookies =	request.getCookies();
		if(cookies != null)
			for(Cookie cookie:cookies)
				if(cookie.getName().equals("SESSIONID")) 
					try {
						PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("select * from cookie where SESSIONID = ?");
						stmt.setString(1, cookie.getValue());
				 		ResultSet set = stmt.executeQuery();
						if(set.next()) {
							userId = set.getString(2);	
						}
					}
					catch(Exception e) {
						System.out.println(e.getMessage());
					}
		if(status.equals("ACCEPT")) {
			Notification a = new Notification(new Date(),userId,id,"Your Request is Accepted and You get it in Two Days ",notify.READ);
			Message.addnotification(a);
		}
		else if(status.equals("REJECT")) {
			Notification a = new Notification(new Date(),userId,id,"Your Request is Rejected ",notify.READ);
			Message.addnotification(a);
		}
		System.out.println(Message.aa);
		try {
			System.out.println("ab");
			PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("update AppliedLeaves set statuc = ? where empId = ?");
			stmt.setString(1, status);
			stmt.setString(2,id);
			stmt.executeUpdate();
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("hi");
		
		JSONArray arr = new JSONArray();
		
		try {
			PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("select * from AppliedLeaves where statuc = ? AND reportingTo = ?");
			stmt.setString(1,"SENTFORAPPORVAL");
			stmt.setString(2, ""+request.getAttribute("id"));
			 ResultSet rset = stmt.executeQuery();
			 JSONObject obj1 = new JSONObject();
			 obj1.put("EmpId","Employee ID");
			 obj1.put("EmpName","Employee Name");
			 obj1.put("FromDate","FromDate");
			 obj1.put("ToDate", "ToDate");
			 obj1.put("Reason", "Reason");
			 obj1.put("LeaveType","LeaveType");
			 obj1.put("Approve","Accept");
			 obj1.put("Reject","Reject");
			 arr.add(obj1);
			 
			 while(rset.next()) {
				 JSONObject obj = new JSONObject();
				 obj.put("EmpId", rset.getString(1));
				 obj.put("EmpName", rset.getString(2));
				 obj.put("FromDate", rset.getString(3));
				 obj.put("ToDate", rset.getString(4));
				 obj.put("Reason", rset.getString(5));
				 obj.put("LeaveType", rset.getString(6));
				 obj.put("Status", rset.getString(7));
				 obj.put("Approve", "Approve");
				 obj.put("Reject", "reject");
				 arr.add(obj);
				 
				 System.out.println(rset.getString(1));
			 }
			 
			 response.getWriter().append(arr.toString());
	}
		catch(Exception e) {
			System.out.println("2"+e.getMessage());
		}
		

}
}
