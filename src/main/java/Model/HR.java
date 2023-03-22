package Model;

public class HR extends employee
{

	public HR(String role, String id, String name,String Dob, String team, double salary, String contact_no, String email_id,
			String status,String reportingToList) {
	 	super(role, id, name, team,Dob, salary, contact_no, email_id, reportingToList, status);
		
	}

}
