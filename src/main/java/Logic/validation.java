package Logic;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

/**
 * Servlet Filter implementation class validation
 */

public class validation extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public validation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
	 	JSONObject obj = new JSONObject();
		Cookie[] cook = req.getCookies();
		obj.put("status", false);
		if(cook!=null) {
		for(int i=0;i<cook.length;i++) {
			if(cook[i].getName().equals("SESSIONID")){
				try {
					PreparedStatement pre = dbconnection.dbConnection.prepareStatement("select * from cookie where session_id = ?");
					pre.setString(1,cook[i].getValue());
					ResultSet rs = pre.executeQuery();
					obj.put("status", rs.next());
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		}
		response.getWriter().append(obj.toString());
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
