package Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import Enum.status;


@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	static int sickLeaves = 12;
    static int CasualLeaves = 6;
	static int EarnedLeaves = 4;
	ArrayList<String>name;
	ArrayList<String>id ;
	private static final long serialVersionUID = 1L;
	
    public AddEmployee() {
        super();
     
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		name = new ArrayList<String>();
		id = new ArrayList<String>();
    	String Team = request.getParameter("id");
    	PrintWriter out = response.getWriter();
    	try {
			PreparedStatement st = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where role = 'Mentor' AND Team = ?");
			st.setString(1, Team);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				String a = (rs.getString(3));
				String b = (rs.getString(2));
				name.add(a);
				id.add(b);
			}
			System.out.println(name);
			out.println(name.toString());
    }
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
  	}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("aa");
		boolean check = true;
		PrintWriter out = response.getWriter();
		 JSONObject obj = (JSONObject) request.getAttribute("json");
			String DOB=  (String)obj.get("DOB");
			String EmployeeName =(String) obj.get("empName");
			double EmployeeSalary =Double.parseDouble((String) obj.get("salary"));
			String contact_no = (String) obj.get("ph_no");
			String Email_id = (String) obj.get("email_id");
			String AssignTeam = (String) obj.get("Team");
			String Mentor = null;
			System.out.println(AssignTeam);
			for(int i=0;i<name.size();i++){
				if((name.get(i)).equals ((String)obj.get("Mentor"))) {
					Mentor = id.get(i);
				}
			}
		try {
			PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("insert into employeeDetail (role,name,DOB,Team,salary,email_id,contact_no,status,ReportingTo) values(?,?,?,?,?,?,?,?,?)");
			stmt.setString(1, "Employee");
			stmt.setString(2, EmployeeName);
			stmt.setString(3, DOB);
			stmt.setString(4, AssignTeam);
			stmt.setDouble(5, EmployeeSalary);
			stmt.setString(6,Email_id);
			stmt.setString(7, contact_no);
			stmt.setString(8, status.ACTIVE.toString());
			stmt.setString(9, Mentor);

			stmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("lsl"+ex.getMessage());
		}
		try {
			PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("insert into values(?,?,?,?)");
			stmt.setString(1,Email_id);
			stmt.setInt(2,sickLeaves);
			stmt.setInt(3,CasualLeaves);
			stmt.setInt(4,EarnedLeaves);
			stmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		out.println("Sucessfully Updated");
			}
	}
		


