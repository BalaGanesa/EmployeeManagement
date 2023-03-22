package Logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Model.Notification;

public class Message {
	public static Map<Integer,List<Notification>> aa = new HashMap<>();
	public static void addnotification(Notification notification) {
		if(aa.containsKey(notification.getReceiverId())) {
			aa.get(notification.getReceiverId()).add(notification);
		}
		else {
			List<Notification> notify = new ArrayList();
			notify.add(notification);
			aa.put(Integer.parseInt(notification.getReceiverId()), notify);
		 }
	}
	public static void main(String[] args) {
		Map<Integer,Integer> map = new HashMap<>();
		map.put(1, 3);
		System.out.println(map.containsKey(1));
	}
}
