package model;

import helper.DatabaseHelper;

import java.sql.ResultSet;
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

	public Project() {
		this.id = 0;
		this.name = "";
		this.description = "";
		this.status = PJ_STATUS.ONGOING;
		this.openDay = "";
		this.closeDay = "";
		this.estimateDay = "";
		this.currentManager = null;
	}

	public Project(int id, String name, String description, String openDay, String closeDay, String estimateDay, PJ_STATUS status, Manager currentManager) {
		this.setId(id);
		this.setName(name);
		this.setDescription(description);
		this.setStatus(status);
		this.setOpenDay(openDay);
		this.setCloseDay(closeDay);
		this.setEstimateDay(estimateDay);
		this.setCurrentManager(currentManager);
	}

	public ArrayList<Employee> getCurrentEmployees() {
		ArrayList<Employee> arrayList = new ArrayList<Employee>();

		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			String query = "SELECT * FROM Employee_PJ WHERE PJ_id = " + this.id;
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("E_id");
				Employee employee = DatabaseHelper.getEmployeeFromId(id);
				arrayList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public ArrayList<Employee> getCurrentManagers() {
		ArrayList<Employee> arrayList = new ArrayList<Employee>();

		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			String query = "SELECT Employee.id FROM Employee,Employee_PJ WHERE Employee.id=Employee_PJ.E_id AND Employee.type=0 AND Employee_PJ.PJ_id = "
					+ this.id;
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Employee employee = DatabaseHelper.getEmployeeFromId(id);
				arrayList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public ArrayList<Employee> getCurrentDevs() {
		ArrayList<Employee> arrayList = new ArrayList<Employee>();

		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			String query = "SELECT Employee.id FROM Employee,Employee_PJ WHERE Employee.id=Employee_PJ.E_id AND Employee.type=1 AND Employee_PJ.PJ_id = "
					+ this.id;
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Employee employee = DatabaseHelper.getEmployeeFromId(id);
				arrayList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public ArrayList<Employee> getCurrentTesters() {
		ArrayList<Employee> arrayList = new ArrayList<Employee>();

		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			String query = "SELECT Employee.id FROM Employee,Employee_PJ WHERE Employee.id=Employee_PJ.E_id AND Employee.type=2 AND Employee_PJ.PJ_id = "
					+ this.id;
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Employee employee = DatabaseHelper.getEmployeeFromId(id);
				arrayList.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public ArrayList<Issue> getTotalIssue() {
		ArrayList<Issue> arrayList = new ArrayList<Issue>();

		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			String query = "SELECT Issue.id FROM Issue, Project WHERE Issue.PJ_id = "
					+ this.id;
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Issue issue = DatabaseHelper.getIssueFromId(id);
				arrayList.add(issue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public ArrayList<Issue> getTotalUnreadIssue() {
		ArrayList<Issue> arrayList = new ArrayList<Issue>();

		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			String query = "SELECT Issue.id FROM Issue, Project WHERE Issue.PJ_id = "
					+ this.id + " AND is_unread = " + 1;
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Issue issue = DatabaseHelper.getIssueFromId(id);
				arrayList.add(issue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public boolean isUnreadIssueAvailable() {
		return getTotalUnreadIssue().size() != 0;
	}

	public int getProjectProgress() {
		int totalPriorityDoneIssue = 0;
		int totalPriorityAllIssue = 0;

		if (getTotalIssue().size() == 0) {
			return 0;
		}

		for (Issue issue : getTotalIssue()) {
			if (issue.getStatus() == ISSUE_STATUS.CLOSED) {
				totalPriorityDoneIssue += issue.getPriority();
			}

			totalPriorityAllIssue += issue.getPriority();
		}

		return (int) Math.floor(totalPriorityDoneIssue / totalPriorityAllIssue
				* 100);
	}

	public void addEmployeeToProject(ArrayList<Employee> employees) throws Exception {
		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			for (Employee employee : employees) {
				String query = "INSERT INTO Employee_PJ (E_id,PJ_id) VALUES ("
						+ employee.getId() + "," + this.getId() + ")";
				statement.executeUpdate(query);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new Exception("This employee is already in the project");
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

	public void createIssue(Issue newIssue) {
		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			int statusInt;
			if (newIssue.getStatus() == ISSUE_STATUS.NEW) {
				statusInt = 0;
			} else if (newIssue.getStatus() == ISSUE_STATUS.CHECKING) {
				statusInt = 1;
			} else {
				statusInt = 2;
			}
			String query = "INSERT INTO Issue (PJ_id,name,description,reporter_id,assigned_id,status,priority,is_unread)"
					+ "VALUES ("
					+ this.getId()
					+ ",'"
					+ newIssue.getName()
					+ "','"
					+ newIssue.getDescription()
					+ "',"
					+ newIssue.getReporter().getId()
					+ ","
					+ newIssue.getAssignee().getId()
					+ ","
					+ statusInt
					+ ","
					+ newIssue.getPriority() + ",1)";
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editIssue(Issue editedIssue) {
		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			int statusInt;
			if (editedIssue.getStatus() == ISSUE_STATUS.NEW) {
				statusInt = 0;
			} else if (editedIssue.getStatus() == ISSUE_STATUS.CHECKING) {
				statusInt = 1;
			} else {
				statusInt = 2;
			}
			int isUnreadInt = editedIssue.isUnread() ? 1 : 0;

			String query = "UPDATE Issue SET name='" + editedIssue.getName()
					+ "',description='" + editedIssue.getDescription()
					+ "',reporter_id=" + editedIssue.getReporter().getId()
					+ ",assigned_id=" + editedIssue.getAssignee().getId()
					+ ",status=" + statusInt + ",priority="
					+ editedIssue.getPriority() + ",is_unread=" + isUnreadInt
					+ " WHERE id=" + editedIssue.getId();
			statement.executeUpdate(query);
		} catch (SQLException e) {
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
