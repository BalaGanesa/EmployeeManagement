package Model;

public class Availableleaves {
	private employee employee;
	private int availableSickLeave;
	private int availableCasualLeave;
	private int availableEarnedLeave;
	
	public Availableleaves(Model.employee emp, int availableSickLeaves, int availableCasualLeaves, int availableEarnedLeaves) {
		this.employee = emp;
		this.availableCasualLeave = availableCasualLeaves;
		this.availableSickLeave = availableSickLeaves;
		this.availableEarnedLeave = availableEarnedLeaves;
	}
	public employee getEmployee() {
		return employee;
	}
	public void setEmployee(employee employee) {
		this.employee = employee;
	}
	public int getAvailableSickLeave() {
		return availableSickLeave;
	}
	public void setAvailableSickLeave(int availableSickLeave) {
		this.availableSickLeave = availableSickLeave;
	}
	public int getAvailableCasualLeave() {
		return availableCasualLeave;
	}
	public void setAvailableCasualLeave(int availableCasualLeave) {
		this.availableCasualLeave = availableCasualLeave;
	}
	public  int getAvailableEarnedLeave() {
		return availableEarnedLeave;
	}
	public void setAvailableEarnedLeave(int availableEarnedLeave) {
		this.availableEarnedLeave = availableEarnedLeave;
	}
	
}
