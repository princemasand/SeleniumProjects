package CommonLibs.Implementation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCConnection extends getConfigProperty{
	private static Connection connection = null;
	private static Statement statement;
	private static ResultSet resultSet;

	public static void connection() {
		try {
			System.out.println("Connecting JDBC Connection...");

			Class.forName("oracle.jdbc.driver.OracleDriver");

			connection = DriverManager.getConnection(getProperty("jdbcuaturl"),
					getProperty("jdbcuser"), getProperty("jdbcpwd"));

			System.out.println("JDBC Connection created successfully");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ResultSet prepareStatement(String sqlQuery) {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlQuery);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	public static void close() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("JDBC Connection closed successfully.");

	}

}
