package LTWebST2.Models;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private final String serverName = "LAPTOP-DLNV3RL7\\VANTHUONG";
	private final String dbName = "WEBST2";
	private final String portNumber = "1433";
	private final String instance = ""; // empty if there is only one instance of SQL Server
	private final String userID = "sa";
	private final String password = "123";

	public Connection getConnection() throws Exception {

		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;

		if (instance == null || instance.trim().isEmpty())

			url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		return DriverManager.getConnection(url, userID, password);

	}

	// test run: right click -> Run as -> Java application
	public static void main(String[] args) {
		try {
			Connection conn = new DBConnection().getConnection();
			if (conn != null) {
				System.out.println("Connection established successfully!");
			} else {
				System.out.println("Failed to establish connection.");
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}