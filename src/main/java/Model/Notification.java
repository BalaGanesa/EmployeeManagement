package Model;

import java.util.Date;

import org.json.simple.JSONObject;

import Enum.notify;

public class Notification {
	Date date;
	String senderId;
	String receiverId;
	String Message;
	notify notify;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}

	
	public Notification(Date date, String senderId, String receiverId, String message, notify notify) {
		super();
		this.date = date;
		this.senderId = senderId;
		this.receiverId = receiverId;
		Message = message;
		this.notify = notify;
	}
	public notify getNotify() {
		return notify;
	}
	public void setNotify(notify notify) {
		this.notify = notify;
	}
	public JSONObject getJSON() {
		JSONObject json = new JSONObject();
		json.put("date",getDate());
		json.put("senderId",getSenderId());
		json.put("receiverId",getReceiverId());
		json.put("message",getMessage());
		json.put("date",getNotify());
		return json;
	}
	
}
