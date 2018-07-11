package com.administrator.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.administrator.pojo.Administrator;
import com.setting.database.DataBaseConnect;

public class AdministratorDataBase {

	public static ArrayList<Administrator> getAllAdministratorDataBase() {
		ArrayList<Administrator> administrator = null;
		Statement createStatement = null;
		ResultSet rs = null;
		Connection con = DataBaseConnect.getConnection();
		String sql = " SELECT * FROM dolgozok";
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			administrator = new ArrayList<>();
			while (rs.next()) {
				Administrator actualDevice = new Administrator(rs.getInt("idugyintezo"), rs.getString("ugyintezo_neve"),
						rs.getString("ugyintezo_email"), rs.getString("beosztas"));
				administrator.add(actualDevice);
			}
		} catch (SQLException e) {

		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (createStatement != null) {
					createStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {

			}
		}
		return administrator;
	}
}
