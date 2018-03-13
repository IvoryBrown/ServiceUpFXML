package com.service.setting.error.fillteringdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.service.setting.error.Error;
import com.service.setting.database.DataBaseConnect;
import com.service.setting.showinfo.ShowInfo;

public class ErrorFillteringDB {
	public static ArrayList<Error> getAllError() {
		Connection con = DataBaseConnect.getConnection();
		String sql = "SELECT * FROM `error`";
		ArrayList<Error> error = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			error = new ArrayList<>();
			while (rs.next()) {
				Error actualError = new Error(rs.getString("id_error"), rs.getString("error_name"),
						rs.getString("error_comment"), rs.getString("error_status"));
				error.add(actualError);
			}
		} catch (SQLException e) {
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
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
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
		return error;
	}

	public void updateError(Error error) {
		Connection conn = DataBaseConnect.getConnection();
		PreparedStatement pr = null;
		try {
			String sqlError = "UPDATE `error` set error_status = ? " + " WHERE id_error = ?";
			pr = conn.prepareStatement(sqlError);
			pr.setString(1, error.getErrorStatus());
			pr.setString(2, error.getErrorId());
			pr.execute();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} finally {
			try {
				if (pr != null) {
					pr.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}
}
