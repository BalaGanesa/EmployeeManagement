package Model;

import java.util.Date;

import org.json.simple.JSONObject;

import Enum.status;
public class employee{
	String role;
	String id;
	String name;
	String team;
	String Dob;
	double salary;
	String email_id;
	String contact_no;
	String status;
	String ReportingToList;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
 		this.role = role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setDob(String Dob) {
		this.Dob = Dob;
	}
	public String getDob() {
		return Dob;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	public String getReportingToList() {
		return ReportingToList;
	}
	public void setReportingToList(String reportingToList) {
		ReportingToList = reportingToList;
	}
	public employee(String role, String id, String name, String Dob,String team, double salary,  String email_id,String contact_no,
			String status,String reportingToList) {
		super();
		this.role = role;
		this.id = id;
		this.name = name;
		this.Dob = Dob;
		this.team = team;
		this.salary = salary;
		this.email_id = email_id;
		this.contact_no = contact_no;
		this.status = status;
		ReportingToList = reportingToList;
	}
	public JSONObject getJSON() {
		JSONObject json = new JSONObject();
		json.put("role",getRole());
		json.put("id",getId());
		json.put("name",getName());
		json.put("DOB",getDob());
		json.put("team",getTeam());
		json.put("salary",getSalary());
		json.put("email_id",getEmail_id());
		json.put("contact_no",getContact_no());
		json.put("ReporTingTo",getReportingToList());
	
		return json;
	}
}