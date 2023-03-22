package Logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class holiday
 */
@WebServlet("/holiday")
public class holiday extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public holiday() {
        super();
        // TODO Auto-generated constructor stub
    }
  

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject jsonResponse = new JSONObject();
		        try {
		            // Create URL object
		            URL url = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/bala");

		            // Create HTTP connection
		            HttpURLConnection con = (HttpURLConnection) url.openConnection();
		            con.setRequestMethod("GET");

		            // Read response from API
		            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		            String inputLine;
		            StringBuffer response1 = new StringBuffer();
		            while ((inputLine = in.readLine()) != null) {
		                response1.append(inputLine);
		            }
		            in.close();

		           System.out.println(response1.toString());
		            
		            jsonResponse.put("response1", response1);
		            response.getWriter().append(jsonResponse.toString());
		        } catch (Exception e) {
		            System.out.println("Error: " + e.getMessage());
		        }
		    }
		
	

}
