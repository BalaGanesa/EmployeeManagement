package Model;

import java.util.Date;

import org.json.simple.JSONObject;

public class Task {
		private String TaskId;
		private String TaskName;
		private int empId;
		private int AssignedBy;
		private String TaskDescription;
		private String AssignDate;
		private String priority;
		private String endDate;
		private String TaskStatus;
		
		
		public int getAssignedBy() {
			return AssignedBy;
		}
		public void setAssignedBy(int assignedBy) {
			AssignedBy = assignedBy;
		}
		public String getTaskStatus() {
			return TaskStatus;
		}
		public void setTaskStatus(String taskStatus) {
			TaskStatus = taskStatus;
		}
		public String getTaskId() {
			return TaskId;
		}
		public void setTaskId(String taskId) {
			TaskId = taskId;
		}
		public String getTaskName() {
			return TaskName;
		}
		public void setTaskName(String taskName) {
			TaskName = taskName;
		}
		public int getEmpId() {
			return empId;
		}
		public void setEmpId(int empId) {
			this.empId = empId;
		}
		public String getTaskDescription() {
			return TaskDescription;
		}
		public void setTaskDescription(String taskDescription) {
			TaskDescription = taskDescription;
		}
		public String getAssignDate() {
			return AssignDate;
		}
		public void setAssignDate(String assignDate) {
			AssignDate = assignDate;
		}
		public String getPriority() {
			return priority;
		}
		public void setPriority(String priority) {
			this.priority = priority;
		}
		public String getEndDate() {
			return endDate;
		}
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		public JSONObject getJSON() {
			JSONObject json = new JSONObject();
			json.put("TaskId",getTaskId());
			json.put("TaskName",getTaskName());
			json.put("TaskDescription",getTaskDescription());
			json.put("AssignedTo",getEmpId());
			json.put("AssignedBy",getAssignedBy());
			json.put("Assigneddate",getAssignDate());
			json.put("submissionDate",getEndDate());
			json.put("Priority",getPriority());
			json.put("TaskStatus",getTaskStatus());
		
			return json;
		}
		public Task(String taskId, String taskName, int empId, int assignedBy, String taskDescription,
				String assignDate,String endDate, String priority,String taskStatus) {
			super();
			TaskId = taskId;
			TaskName = taskName;
			this.empId = empId;
			AssignedBy = assignedBy;
			TaskDescription = taskDescription;
			AssignDate = assignDate;
			this.endDate = endDate;
			this.priority = priority;
			TaskStatus = taskStatus;
		}
	
		
}
