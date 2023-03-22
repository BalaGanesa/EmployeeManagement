package Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import Enum.leaveStatus;
import Enum.leaveType;
import Model.Availableleaves;
import Model.employee;

public class appliedLeave {
   public static String applied(HttpServletRequest request) throws IOException, java.text.ParseException {
	   System.out.println("aaa");
		employee emp = null;
		int availableLeaves = 0;
		Availableleaves Ava = null;
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
		SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
		
		if(ds.parse((String)obj.get("fromDate")).compareTo(ds.parse((String)obj.get("toDate"))) > 0 ) {
		return ("Please enter valid date");
		}
		else {
		try {
			PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where id = ?");
			stmt.setInt(1, Integer.parseInt((String) obj.get("empId")));
			 ResultSet rs = stmt.executeQuery();
			 while(rs.next()) {
				 emp = new employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
			 }
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			long timeDiff = (ds.parse((String)obj.get("toDate"))).getTime() - ds.parse((String)obj.get("fromDate")).getTime();
			long numberOfDays = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
			System.out.println(timeDiff);
			System.out.println(numberOfDays);
			String LeaveType = (String)obj.get("leavetype");
			try {
				PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("select * from AvailableLeaves where emailId = ?");
				stmt.setString(1,emp.getEmail_id());
				ResultSet rs = stmt.executeQuery();
				 while(rs.next()) {
					 Ava = new Availableleaves(emp,rs.getInt(2),rs.getInt(3),rs.getInt(4));
				 }
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			 if(LeaveType.equals(leaveType.CASUAL_LEAVE.toString())) {
				availableLeaves = Ava.getAvailableCasualLeave();
			}else if(LeaveType.equals(leaveType.SICK_LEAVE.toString())) {
				availableLeaves = Ava.getAvailableSickLeave();
			}else if(LeaveType.equals(leaveType.EARNED_LEAVE.toString())) {
				availableLeaves = Ava.getAvailableEarnedLeave();
			}
			System.out.println(availableLeaves);
			if(availableLeaves > numberOfDays) {
				availableLeaves = (int) (availableLeaves - numberOfDays);
				
				try {
					if(LeaveType.equals(leaveType.CASUAL_LEAVE.toString())) {
						PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("update table AppliedLeaves set CASUAL_LEAVE = ? where emailId = ?");	
						stmt.setInt(1, availableLeaves);
						stmt.setString(2, emp.getEmail_id());
						stmt.executeUpdate();
					}
					else if(LeaveType.equals(leaveType.SICK_LEAVE.toString())) {
						PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("update table AppliedLeaves set SICK_LEAVE = ? where emailId = ?");	
						stmt.setInt(1, availableLeaves);
						stmt.setString(2, emp.getEmail_id());
						stmt.executeUpdate();
					}
					else if(LeaveType.equals(leaveType.EARNED_LEAVE.toString())) {
						PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("update table AppliedLeaves set EARNED_LEAVE = ? where emailId = ?");	
						stmt.setInt(1, availableLeaves);
						stmt.setString(2, emp.getEmail_id());
						stmt.executeUpdate();
					}
				PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("insert into AppliedLeaves values(?,?,?,?,?,?,?,?)");
				stmt.setString(1, emp.getId());
				stmt.setString(2, emp.getName());
				stmt.setString(3, (String)obj.get("fromDate"));
				stmt.setString(4, (String)obj.get("toDate"));
				stmt.setString(5, (String)obj.get("reason"));
				stmt.setString(6, (String)obj.get("leavetype"));
				stmt.setString(7, leaveStatus.APPORVED.toString());
				stmt.setString(8, emp.getReportingToList());
				stmt.executeUpdate();
				}
				catch(Exception e){
					System.out.println(e.getMessage());
				}
				return "your Leave has been Apporved"; 
			}
			else {
				try {
				PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("insert into AppliedLeaves values(?,?,?,?,?,?,?,?)");
				stmt.setString(1, emp.getId());
				stmt.setString(2, emp.getName());
				stmt.setString(3, (String)obj.get("fromDate"));
				stmt.setString(4, (String)obj.get("toDate"));
				stmt.setString(5, (String)obj.get("reason"));
				stmt.setString(6, (String)obj.get("leavetype"));
				stmt.setString(7, leaveStatus.SENTFORAPPORVAL.toString());
				stmt.setString(8, emp.getReportingToList());
				stmt.executeUpdate();
			}
			
			catch(Exception e){
				System.out.println(e.getMessage());
			}
				return "your Leave has been SentForApproval"; 
		}
   }
   }
}

