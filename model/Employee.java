package model;

import helper.DatabaseHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Employee {

	private int id;
	private String name;
	private String password;
	private String tel;
	private String email;

	public Employee() {
		this.id = 0;
		this.name = "";
		this.password = "";
		this.tel = "";
		this.email = "";
	}

	public Employee(int id, String name, String password, String tel, String email) {
		this.setId(id);
		this.setName(name);
		this.setPassword(password);
		this.setTel(tel);
		this.setEmail(email);
	}

	public ArrayList<Project> getFinishedProjects() {
		ArrayList<Project> arrayList = new ArrayList<Project>();

		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			String query = "SELECT Project.id "
					+ "FROM Project, Employee_PJ "
					+ "WHERE Employee_PJ.E_id = "
					+ this.id
					+ " AND Employee_PJ.PJ_id = Project.id AND Project.status = 1";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Project project = DatabaseHelper.getProjectFromPjId(id);
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

		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			String query = "SELECT Project.id "
					+ "FROM Project, Employee_PJ "
					+ "WHERE Employee_PJ.E_id = "
					+ this.getId()
					+ " AND Employee_PJ.PJ_id = Project.id AND Project.status = 0";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Project project = DatabaseHelper.getProjectFromPjId(id);
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

		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			String query = "SELECT Issue.id"
					+ "FROM Issue, Employee WHERE Issue.assigned_id = "
					+ this.getId()
					+ " AND (Issue.status = 0 or Issue.status = 1)";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Issue issue = DatabaseHelper.getIssueFromId(id);
				arrayList.add(issue);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arrayList;
	}

	public ArrayList<Issue> getNewAssignedIssueAsNotification() {
		ArrayList<Issue> arrayList = new ArrayList<Issue>();

		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			String query = "SELECT `Issue`.`id`"
					+ "FROM `Issue`, `Employee` WHERE `Issue`.`assigned_id` = "
					+ this.getId() + " AND `Issue`.`is_unread` = 1";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				Issue issue = DatabaseHelper.getIssueFromId(id);
				arrayList.add(issue);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
