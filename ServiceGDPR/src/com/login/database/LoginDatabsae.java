package com.login.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LoginDatabsae {

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
}
