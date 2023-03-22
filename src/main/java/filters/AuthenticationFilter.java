package filters;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import Logic.dbconnection;

@SuppressWarnings("serial")
@WebFilter(urlPatterns = {"/Home","/TaskAssign","/leave","/hike","/viewTask","/GetNotification","/changeReportees","/fetchTask"})
public class AuthenticationFilter extends HttpFilter implements Filter {
   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		boolean isAuthenticated = false;
		JSONObject json = new JSONObject();
		int userId = 0;
		HttpServletRequest httpReq = (HttpServletRequest) request;
		Cookie[] cookies = httpReq.getCookies();
		if(cookies != null)
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals("SESSIONID")) {
					try {
						PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("select * from cookie where sessionId = ?");
						stmt.setString(1, cookie.getValue());
						ResultSet set = stmt.executeQuery();
						if(set.next()) {
							userId = set.getInt(2);
							request.setAttribute("id", set.getInt(2));
							isAuthenticated = true;
						}
					}catch(SQLException e) {
						System.out.println(e.getMessage());
					}
				}
			}
					
					
		
				
		if(isAuthenticated) {
			String role = null;
			int id = 0;
			String name = null;
			try {
				PreparedStatement stmt = dbconnection.dbConnection.prepareStatement("select * from employeeDetail where id = ?");
				stmt.setInt(1, userId);
				ResultSet set = stmt.executeQuery();
				if(set.next()) {
					role = set.getString(1);
					id = set.getInt(2);
					name = set.getString(3);
				}
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			json.put("Statuscode", 200);
			json.put("currentlyLogedIn", userId);
			json.put("role", role);
			json.put("id", id);
			json.put("name", name);
			((ServletRequest) request).setAttribute("json",json);
			chain.doFilter(request, response);
		}
		else {
			json.put("Statuscode", 300);
			((ServletRequest) request).setAttribute("json",json);
			chain.doFilter(request, response);
		}
	}


}
