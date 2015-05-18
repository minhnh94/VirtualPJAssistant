package model;

import helper.DatabaseHelper;

import java.sql.Statement;
import java.util.ArrayList;

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

		// TODO: Implement method
		String query = "SELECT Project.id, Project.name, Project.description, Project.status, Project.openday, Project.closeday, Project.estimated_day, Project.manager_id"
				+ "FROM Project, Employee_PJ"
				+ "WHERE Employee_PJ.E_id = "
				+ this.id + "AND Employee_PJ.PJ_id = Project.id";

		return arrayList;
	}

	public ArrayList<Project> getOngoingProjects() {
		ArrayList<Project> arrayList = new ArrayList<Project>();

		// TODO: Implement method

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
