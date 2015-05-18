package model;

import java.util.ArrayList;

public class Project {

	public enum PJ_STATUS {
		ONGOING, FINISHED
	};

	private int id;
	private String name;
	private String description;
	private PJ_STATUS status;
	private String openDay;
	private String closeDay;
	private String estimateDay;
	private Manager currentManager;

	public Project(int id, String name, String description, String openDay, String estimateDay, Manager currentManager) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setStatus(PJ_STATUS.ONGOING);
		this.setOpenDay(openDay);
		this.setEstimateDay(estimateDay);
		this.setCurrentManager(currentManager);
	}

	public ArrayList<Employee> getCurrentEmployees() {
		ArrayList<Employee> arrayList = new ArrayList<Employee>();

		// TODO: Implement this

		return arrayList;
	}

	public ArrayList<Issue> getTotalIssue() {
		ArrayList<Issue> arrayList = new ArrayList<Issue>();

		// TODO: Implement this

		return arrayList;
	}

	public boolean isUnreadIssueAvailable() {
		// TODO: Implement this
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PJ_STATUS getStatus() {
		return status;
	}

	public void setStatus(PJ_STATUS status) {
		this.status = status;
	}

	public String getOpenDay() {
		return openDay;
	}

	public void setOpenDay(String openDay) {
		this.openDay = openDay;
	}

	public String getCloseDay() {
		return closeDay;
	}

	public void setCloseDay(String closeDay) {
		this.closeDay = closeDay;
	}

	public String getEstimateDay() {
		return estimateDay;
	}

	public void setEstimateDay(String estimateDay) {
		this.estimateDay = estimateDay;
	}

	public Manager getCurrentManager() {
		return currentManager;
	}

	public void setCurrentManager(Manager currentManager) {
		this.currentManager = currentManager;
	}
}
