package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import model.Manager;
import model.Project.PJ_STATUS;

public class DatabaseHelper {
	private static DatabaseHelper sharedInstance = new DatabaseHelper();
	private Connection connection = null;
	private Statement statement = null;

	private DatabaseHelper() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:helper/database/vpa_db.sqlite");
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static DatabaseHelper getInstance() {
		return sharedInstance;
	}

	public void addEmployee(String name, String password, String tel, String email, int type) {
		String query = String.format("INSERT INTO Employee (name, password, tel, email, type) " + "VALUES ('%s','%s','%s','%s',%d)", name, password, tel, email, type);
		System.out.println(query);
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editEmployee(String name, String password, String tel, String email, int type) {
		String query = String.format("UPDATE Employee SET password='%s', tel='%s', email='%s', type=%d WHERE name='%s'", password, tel, email, type, name);
		System.out.println(query);
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addProject(String name, String description, PJ_STATUS status, String openday, String estimateDay, Manager currentManager) {
		String query = String.format("INSERT INTO Project (name, description, status, openday, closeday, estimated_day, manager_id) " + "VALUES ('%s','%s',%d,'%s','','%s',%d)", name, description, status == PJ_STATUS.ONGOING ? 0 : 1, openday, estimateDay, currentManager.getId());
		System.out.println(query);
		 try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
