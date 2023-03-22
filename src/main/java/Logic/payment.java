package Logic;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.jar.JarException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class payment {
	double PF;
	double Tax;
	public void taxcalcluate(JSONArray jarr) {
		System.out.println("BALA");
		   try {
			   System.out.println("Akash");
			   System.out.println(jarr);
	            for (int i = 0; i <  jarr.size(); i++) {
	                JSONObject jsonObject =   (JSONObject) jarr.get(i);
	                String empID = (String) jsonObject.get("id");
	                double salary = (double) jsonObject.get("salary");
	                System.out.println(salary);
	                System.out.println(empID);
	                PF = (salary*15)/100;
	                Tax =  (salary*8)/100;
	                salary = salary-(PF+Tax);
	                SimpleDateFormat ds = new SimpleDateFormat("yyyy-MM-dd");
	                
	            
	                	PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("insert into TAX values(?,?,?)");
	        			stmt.setDouble(1, PF);
	        			stmt.setDouble(2, Tax);
	        			stmt.setString(3, empID);
	        			stmt.executeUpdate();
                		stmt = dbconnection.dbConnection.prepareStatement("insert into payment values(?,?,?)");
 	        			stmt.setDouble(1, salary);
 	        			stmt.setString(2, ds.format(new Date()));
 	        			stmt.setString(3, empID);
 	        			stmt.executeUpdate();
                		stmt = dbconnection.dbConnection.prepareStatement("insert into Benefits values(?,?,?,?)");
 	        			stmt.setDouble(1, 4000);
 	        			stmt.setDouble(2, 2000);
 	        			stmt.setDouble(3, 1000);
 	        			stmt.setString(4, empID);
 	        			stmt.executeUpdate();
 	                
              
	                
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	}
}
