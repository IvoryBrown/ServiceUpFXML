package com.service.setting.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnect {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/szerviz_up";
	static final String USER = "root";
	static final String PASS = "12345";
	static Connection conn = null;
	static Statement createStatement = null;

	public static Connection getConnection() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("A h�d l�trej�tt");
		} catch (SQLException ex) {
			System.out.println("Valami baj van a connection (h�d) l�trehoz�sakor.");
			System.out.println("" + ex);
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Automatikusan el��ll�tott elfog�si blokk
			e.printStackTrace();
			return null;
		}

		// Ha �letre kelt, csin�lunk egy megpakolhat� teheraut�t
		if (conn != null) {
			try {
				createStatement = conn.createStatement();
				return conn;
			} catch (SQLException ex) {
				System.out.println("Valami baj van van a createStatament (teheraut�) l�trehoz�sakor.");
				System.out.println("" + ex);
				return null;
			}
		}
		return null;
	}
}
// Connection con = null;
// try {
// Class.forName(JDBC_DRIVER);
// con = DriverManager.getConnection(DB_URL, USER, PASS);
// return con;
// } catch (SQLException ex) {
// Logger.getLogger(DataBaseConnect.class.getName()).log(Level.SEVERE, null,
// ex);
// System.out.println("1"+ex);
// return null;
// } catch (ClassNotFoundException e) {
// e.printStackTrace();
// System.out.println("2"+e);
// return null;
// }
