package filters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


@WebFilter(urlPatterns = {"/AddEmployee"})
public class Validation extends HttpFilter implements Filter {
       

	private static final long serialVersionUID = 1L;
	/**
     * @see HttpFilter#HttpFilter()
     */
    public Validation() {
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
        HttpServletResponse res = (HttpServletResponse) response; 
        if(request.getParameter("method") != null && ((String)request.getParameter("method")).equals("GET")){
		    chain.doFilter(request, response);
	 	}
        else {
        	JSONObject obj = new JSONObject();
    		String a = "";
    		String b = "";
    		BufferedReader reader = request.getReader();
    		while((b = reader.readLine())!=null) {
    			a+=b;
    		}
    		JSONParser parser = new JSONParser();
    		try {
    			obj = (JSONObject) parser.parse(a);
    			System.out.println("fghjk"+obj);
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
            PrintWriter out  = res.getWriter();
            HttpServletRequest httpRequest = null;
            boolean check = true;
//            try {
//            int empId = Integer.parseInt((String) obj.get("EmployeeId") != null)
//            }
//            catch(Exception e) {
//            System.out.println("sss")
//            }
           
//            try {
//            int empId = Integer.parseInt((String) obj.get("EmployeeId") != null)
//            }
//            catch(Exception e) {
//            System.out.println("sss")
//            }
            System.out.println("empname"+(String) obj.get("empName") );
            System.out.println("salary"+(String) obj.get("salary") );
            System.out.println("ph_no"+(String) obj.get("ph_no"));
            System.out.println("email_id"+(String) obj.get("email_id"));
            System.out.println("Team"+(String) obj.get("Team"));
            System.out.println("Mentor"+(String) obj.get("Mentor"));
            System.out.println("emailchack"+isEmailValid((String) obj.get("email_id")));
            System.out.println("ph"+isPhoneNumberValid((String) obj.get("ph_no")));
            System.out.println((String) obj.get("ph_no"));
            System.out.println((String) obj.get("email_id"));
//         
    		if ((String) obj.get("empName") != null && (String) obj.get("salary") != null  && (String) obj.get("ph_no") !=null && (String) obj.get("email_id") != null && (String) obj.get("Team") != null  && (String) obj.get("Mentor") !=null && isEmailValid((String) obj.get("email_id")) && isPhoneNumberValid((String) obj.get("ph_no")) )  {
    				request.setAttribute("json", obj);
    		chain.doFilter(request, response);
    	}
    		else {
//    			out.println("Please enter the valid input");
    		} System.out.println("Mentor"+(String) obj.get("Mentor"));
            System.out.println("emailchack"+isEmailValid((String) obj.get("email_id")));
            System.out.println("ph"+isPhoneNumberValid((String) obj.get("ph_no")));
            System.out.println((String) obj.get("ph_no"));
            System.out.println((String) obj.get("email_id"));
//         
    		if ((String) obj.get("empName") != null && (String) obj.get("salary") != null  && (String) obj.get("ph_no") !=null && (String) obj.get("email_id") != null && (String) obj.get("Team") != null  && (String) obj.get("Mentor") !=null && isEmailValid((String) obj.get("email_id")) && isPhoneNumberValid((String) obj.get("ph_no")) )  {
    				request.setAttribute("json", obj);
    		chain.doFilter(request, response);
    	}
    		else {
//    			out.println("Please enter the valid input");
    		}
        }
    }
	private boolean isEmailValid(String email) {
    if (email == null || email.isEmpty()) {
        return false;
    }
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    return email.matches(emailRegex);
	}
	private boolean isPhoneNumberValid(String phoneNumber) {
    if (phoneNumber != null  && (phoneNumber.length() == 10)) {
        return true;
    }
        return false;
    
	}
}