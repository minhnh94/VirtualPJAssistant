package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import model.Manager;
import model.Project.PJ_STATUS;

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
}
