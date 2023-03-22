package Logic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class GetNotification
 */
@WebServlet("/GetNotification")
public class GetNotification extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject js = new JSONObject();
		if(Message.aa.containsKey((Integer)request.getAttribute("id"))){
	 		JSONArray arr = new JSONArray();
			Message.aa.get(request.getAttribute("id")).forEach(n->arr.add(n.getJSON()));
			System.out.println(arr.toString());
			js.put("statuscode",200);
			js.put("arr", arr);
			response.getWriter().append(js.toString());
		}
		else {
			js.put("statuscode",300);
		}
		
	}

}
