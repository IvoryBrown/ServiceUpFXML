package com.login.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoginDataBase {
	public static ObservableList<String> user = FXCollections.observableArrayList();
	public static ObservableList<String> password = FXCollections.observableArrayList();
	public static String name;
	public static String authority;

	public static ObservableList<String> setGetDateLin() {
		ObservableList<String> dateLincList = FXCollections.observableArrayList();
		String SQLaDMINISTRATOR = " SELECT * FROM date";
		Connection con = DataBaseConnect.getConnection();
		Statement pstStn = null;
		ResultSet stnRS = null;
		try {
			pstStn = con.prepareStatement(SQLaDMINISTRATOR);
			stnRS = pstStn.executeQuery(SQLaDMINISTRATOR);
			while (stnRS.next()) {
				dateLincList.add(stnRS.getString("date"));
			}
		} catch (SQLException ex) {
			new ShowInfo("Adatbázis Hiba", "", ex.getMessage());
		} finally {
			try {
				if (pstStn != null) {
					pstStn.close();
				}
				if (stnRS != null) {
					stnRS.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				new ShowInfo("Adatbázis Hiba", "", ex.getMessage());
			}
		}
		return dateLincList;
	}

	public static void getGetLogin(String adminsitrator) {
		Connection con = DataBaseConnect.getConnection();
		String sql = "SELECT username, jelszo, ugyintezo_neve, jogkor FROM `dolgozok`  WHERE CONCAT" + "(`" + "username"
				+ "`) LIKE '%" + adminsitrator + "%'";

		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);

			while (rs.next()) {
				user.add(rs.getString("username"));
				password.add(rs.getString("jelszo"));
				name = rs.getString("ugyintezo_neve");
				authority = rs.getString("jogkor");
			}
		} catch (SQLException ex) {
			new ShowInfo("Adatbázis Hiba", "", ex.getMessage());
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
			} catch (SQLException ex) {
				new ShowInfo("Adatbázis Hiba", "", ex.getMessage());
			}
		}
	}

}
