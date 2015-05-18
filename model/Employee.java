package model;

import helper.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Project.PJ_STATUS;

public class Employee {

	private int id;
	private String name;
	private String password;
	private String tel;
	private String email;

	public Employee(int id, String name, String password, String tel, String email) {
		this.setId(id);
		this.setName(name);
		this.setPassword(password);
		this.setTel(tel);
		this.setEmail(email);
	}

	public ArrayList<Project> getFinishedProjects() {
		ArrayList<Project> arrayList = new ArrayList<Project>();

		String query = "SELECT Project.id, Project.name, Project.description, Project.status, Project.openday, Project.closeday, Project.estimated_day, Project.manager_id"
				+ "FROM Project, Employee_PJ"
				+ "WHERE Employee_PJ.E_id = "
				+ this.id
				+ "AND Employee_PJ.PJ_id = Project.id AND Project.status = 1";

		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");
				PJ_STATUS status = resultSet.getInt("status") == 0 ? PJ_STATUS.ONGOING : PJ_STATUS.FINISHED;
				String openday = resultSet.getString("openday");
				String closeday = resultSet.getString("closeday");
				String estimatedDay = resultSet.getString("estimated_day");

				// Get current manager for this project
				int managerId = resultSet.getInt("manager_id");
				String sql = "SELECT * FROM Employee WHERE id = " + managerId;
				ResultSet resultSetManager = statement.executeQuery(sql);
				resultSetManager.next();
				Manager manager = new Manager(resultSetManager.getInt("id"), resultSetManager.getString("name"), resultSetManager.getString("password"), resultSetManager.getString("tel"), resultSetManager.getString("email"));

				Project project = new Project(id, name, description, openday, closeday, estimatedDay, status, manager);
				arrayList.add(project);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arrayList;
	}

	public ArrayList<Project> getOngoingProjects() {
		ArrayList<Project> arrayList = new ArrayList<Project>();

		String query = "SELECT Project.id, Project.name, Project.description, Project.status, Project.openday, Project.closeday, Project.estimated_day, Project.manager_id"
				+ "FROM Project, Employee_PJ"
				+ "WHERE Employee_PJ.E_id = "
				+ this.id
				+ "AND Employee_PJ.PJ_id = Project.id AND Project.status = 0";

		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String description = resultSet.getString("description");
				PJ_STATUS status = resultSet.getInt("status") == 0 ? PJ_STATUS.ONGOING : PJ_STATUS.FINISHED;
				String openday = resultSet.getString("openday");
				String closeday = resultSet.getString("closeday");
				String estimatedDay = resultSet.getString("estimated_day");

				// Get current manager for this project
				int managerId = resultSet.getInt("manager_id");
				String sql = "SELECT * FROM Employee WHERE id = " + managerId;
				ResultSet resultSetManager = statement.executeQuery(sql);
				resultSetManager.next();
				Manager manager = new Manager(resultSetManager.getInt("id"), resultSetManager.getString("name"), resultSetManager.getString("password"), resultSetManager.getString("tel"), resultSetManager.getString("email"));

				Project project = new Project(id, name, description, openday, closeday, estimatedDay, status, manager);
				arrayList.add(project);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arrayList;
	}

	public ArrayList<Issue> getAssignedIssue() {
		ArrayList<Issue> arrayList = new ArrayList<Issue>();

		// TODO: Implement this

		return arrayList;
	}

	public ArrayList<Issue> getNewAssignedIssueAsNotification() {
		ArrayList<Issue> arrayList = new ArrayList<Issue>();

		// TODO: Implement this

		return arrayList;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
