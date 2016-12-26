package com.gom.de.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static Connection connection = null;

	public synchronized static Connection getConnection() {
		if (connection == null) {
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://155.230.90.250:3306/gnoexp?autoReconnect=true&useSSL=false";
			try {
				Class.forName(myDriver);
				connection = DriverManager.getConnection(myUrl, "bchoi000", "Qwert!23");

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

}
