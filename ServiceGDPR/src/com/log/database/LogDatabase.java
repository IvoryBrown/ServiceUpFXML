package com.log.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.log.pojo.Log;
import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

public class LogDatabase {

	public ArrayList<Log> getAllLog() {
		Connection con = DataBaseConnect.getConnection();
		String sql = null;
		sql = "SELECT * FROM `ugyfel_adatok`";
		ArrayList<Log> log = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			log = new ArrayList<>();
			while (rs.next()) {
				Log logActual = new Log(rs.getInt("logger_id"), rs.getString("belepo_nev"), rs.getDate("belepet_datum"),
						rs.getString("log_leiras"));
				log.add(logActual);
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
		return log;
	}

}
