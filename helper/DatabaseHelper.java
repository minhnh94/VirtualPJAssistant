package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Developer;
import model.Employee;
import model.Issue;
import model.Manager;
import model.Project;
import model.Issue.ISSUE_STATUS;
import model.Project.PJ_STATUS;
import model.Tester;

public class DatabaseHelper {
	private static Connection connection = getSQLConnection();

	private static Connection getSQLConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:helper/database/vpa_db.sqlite");
			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Connection getInstance() {
		return connection;
	}

	public static Project getProjectFromPjId(int pjId) {
		Project project = null;

		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Project WHERE id = " + pjId;
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.next();
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String description = resultSet.getString("description");
			PJ_STATUS status = resultSet.getInt("status") == 0 ? PJ_STATUS.ONGOING : PJ_STATUS.FINISHED;
			String openday = resultSet.getString("openday");
			String closeday = resultSet.getString("closeday");
			String estimatedDay = resultSet.getString("estimated_day");

			// Get current manager for this project
			int managerId = resultSet.getInt("manager_id");
			Manager manager = (Manager) getEmployeeFromId(managerId);

			project = new Project(id, name, description, openday, closeday, estimatedDay, status, manager);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return project;
	}

	public static Issue getIssueFromId(int issueId) {
		Issue issue = null;

		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Issue WHERE id=" + issueId;
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.next();

			int id = resultSet.getInt("id");

			int pjId = resultSet.getInt("PJ_id");
			Project project = getProjectFromPjId(pjId);

			String name = resultSet.getString("name");
			String desc = resultSet.getString("description");
			int reporterId = resultSet.getInt("reporter_id");
			int assignedId = resultSet.getInt("assigned_id");
			Employee reporter = getEmployeeFromId(reporterId);
			Employee assignee = getEmployeeFromId(assignedId);

			// Get status
			ISSUE_STATUS status;
			if (resultSet.getInt("status") == 0) {
				status = ISSUE_STATUS.NEW;
			} else
				status = ISSUE_STATUS.CHECKING;

			int priority = resultSet.getInt("priority");
			boolean isUnread = resultSet.getInt("is_unread") == 1 ? true : false;

			issue = new Issue(id, project, name, desc, reporter, assignee, status, priority, isUnread);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return issue;
	}

	public static Employee getEmployeeFromId(int employeeId) {
		Employee employee = null;

		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Employee WHERE id = " + employeeId;
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.next();

			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String password = resultSet.getString("password");
			String tel = resultSet.getString("tel");
			String email = resultSet.getString("email");
			if (resultSet.getInt("type") == 0) {
				employee = new Manager(id, name, password, tel, email);
			} else if (resultSet.getInt("type") == 1) {
				employee = new Developer(id, name, password, tel, email);
			} else {
				employee = new Tester(id, name, password, tel, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return employee;
	}

	public static ArrayList<Manager> getAllManagersFromDatabase() {
		ArrayList<Manager> arrayList = new ArrayList<Manager>();

		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Employee WHERE type = 0";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Manager manager = (Manager) getEmployeeFromId(resultSet.getInt("id"));
				arrayList.add(manager);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public static ArrayList<Developer> getAllDevelopersFromDatabase() {
		ArrayList<Developer> arrayList = new ArrayList<Developer>();

		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Employee WHERE type = 1";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Developer dev = (Developer) getEmployeeFromId(resultSet.getInt("id"));
				arrayList.add(dev);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}

	public static ArrayList<Tester> getAllTestersFromDatabase() {
		ArrayList<Tester> arrayList = new ArrayList<Tester>();

		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Employee WHERE type = 2";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Tester tester = (Tester) getEmployeeFromId(resultSet.getInt("id"));
				arrayList.add(tester);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return arrayList;
	}
}
