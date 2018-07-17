package com.setting.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.login.setting.setting.database.filewrite.SettingDBFile;
import com.setting.showinfo.ShowInfo;

public class DataBaseConnect {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// static final String DB_URL =
	// "jdbc:mysql://localhost/iriszholding_hu_service?useUnicode=true&characterEncoding=UTF-8";
	// static final String USER = "root";
	// static final String PASS = "";

	static Connection conn = null;
	static Statement createStatement = null;

	public static Connection getConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection("jdbc:mysql://"+SettingDBFile.getDBOutput()+"?useUnicode=true&characterEncoding=UTF-8", SettingDBFile.getNameOutput(),
					SettingDBFile.getPasswordOutput());
		} catch (SQLException ex) {
			new ShowInfo("Adatbázis Hiba", "", ex.getMessage());
			return null;
		} catch (ClassNotFoundException e) {
			new ShowInfo("Adatbázis Hiba", "", e.getMessage());
			return null;
		}
		if (conn != null) {
			try {
				createStatement = conn.createStatement();
				return conn;
			} catch (SQLException ex) {
				new ShowInfo("Adatbázis Hiba", "", ex.getMessage());
				return null;
			}
		}
		return null;
	}
}
