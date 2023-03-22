package Logic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Model.employee;

public class DBservice {
	public JSONArray viewEmployeeProfile(String EmpID) {
		employee emp = null;
		JSONArray json = new JSONArray();
		System.out.println(EmpID);
		try {
		PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where id = ?");
		stmt.setString(1, EmpID);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			 emp = new employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
			 json.add(emp.getJSON());
		}
		stmt = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where id = ?");
		stmt.setString(1, emp.getReportingToList());
		 rs = stmt.executeQuery();
		while(rs.next()) {
			 emp = new employee(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
			 json.add(emp.getJSON());
		}
		
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(json.toString());
		
		return json;
	}
}
