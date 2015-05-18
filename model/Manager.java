package model;

import helper.DatabaseHelper;

import java.sql.SQLException;
import java.sql.Statement;

import model.Project.PJ_STATUS;

public class Manager extends Employee {

	public Manager(int id, String name, String password, String tel, String email) {
		super(id, name, password, tel, email);
		// TODO Auto-generated constructor stub
	}

	public void addNewProject(Project addedProject) {
		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			int statusInt = addedProject.getStatus() == PJ_STATUS.ONGOING ? 0 : 1;
			String query = "INSERT INTO Project ('name','description','status','openday','closeday','estimated_day','manager_id')"
					+ "VALUES ('"
					+ addedProject.getName()
					+ "','"
					+ addedProject.getDescription()
					+ "',"
					+ statusInt
					+ ",'"
					+ addedProject.getOpenDay()
					+ "','"
					+ addedProject.getCloseDay()
					+ "','"
					+ addedProject.getEstimateDay()
					+ "',"
					+ addedProject.getCurrentManager().getId() + ")";
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeProject(Project projectToBeClosed) {
		try {
			Statement statement = DatabaseHelper.getInstance().createStatement();
			String query = "UPDATE Project SET status = 1 WHERE id = "
					+ projectToBeClosed.getId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
