package model;

import helper.DatabaseHelper;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Issue.ISSUE_STATUS;

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

	public Project(int id, String name, String description, String openDay, String closeDay, String estimateDay, PJ_STATUS status, Manager currentManager) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setStatus(status);
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

	public int getProjectProgress() {
		int totalPriorityDoneIssue = 0;
		int totalPriorityAllIssue = 0;

		for (Issue issue : getTotalIssue()) {
			if (issue.getStatus() == ISSUE_STATUS.CLOSED) {
				totalPriorityDoneIssue += issue.getPriority();
			}

			totalPriorityAllIssue += issue.getPriority();
		}

		return (int) Math.floor(totalPriorityDoneIssue / totalPriorityAllIssue
				* 100);
	}

	public void addEmployeeToProject(ArrayList<Employee> employees) {
		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			for (Employee employee : employees) {
				String query = "INSERT INTO Employee_PJ (E_id,PJ_id) VALUES ("
						+ employee.getId() + "," + this.getId() + ")";
				statement.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void removeEmployeeFromProject(ArrayList<Employee> employees) {
		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			for (Employee employee : employees) {
				String query = "DELETE FROM Employee_PJ WHERE E_id = "
						+ employee.getId();
				statement.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
