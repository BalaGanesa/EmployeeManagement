package Logic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket")
public class WebSocket {
	private static List<Session> sessions = new ArrayList<>();
	@OnMessage
	public void bala(String a,Session bala) throws IOException {
		sessions.forEach(n->{
			try {
				n.getBasicRemote().sendText(a);
			} catch (IOException e) {
				
			}
		});
		System.out.println(sessions.size());
		
	}
	
	@OnOpen
	public void balala(Session session) {
		sessions.add(session);
	}
	@OnClose
	public void balalalala(Session session) {
		sessions.remove(session);
	}
	@OnError
	public void balalala(Throwable t) throws Throwable{
		throw t;
	}
}
