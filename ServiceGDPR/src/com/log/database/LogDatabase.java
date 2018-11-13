package com.log.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.log.pojo.Log;
import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

public class LogDatabase {
	public static ArrayList<String> logDate = new ArrayList<String>();

	public ArrayList<String> setGetAllAdministrator() {
		String sql = " SELECT ugyintezo_neve FROM dolgozok";
		ArrayList<String> log = null;
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement pstStn = null;
		ResultSet stnRS = null;
		try {
			pstStn = con.prepareStatement(sql);
			stnRS = pstStn.executeQuery(sql);
			log = new ArrayList<>();
			while (stnRS.next()) {
				log.add(stnRS.getString("ugyintezo_neve"));
			}
		} catch (SQLException ex) {
			new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
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
			} catch (SQLException e) {
				new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
		return log;
	}

	public String getLogTextArea(String id) {
		String sql = "SELECT * FROM `logger` WHERE CONCAT" + "(`" + "logger_id" + "`) LIKE '%" + id + "%'";
		String log = null;
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement pstStn = null;
		ResultSet stnRS = null;
		try {
			pstStn = con.prepareStatement(sql);
			stnRS = pstStn.executeQuery(sql);

			while (stnRS.next()) {
				log = stnRS.getString("log_leiras");

			}
		} catch (SQLException ex) {
			new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
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
			} catch (SQLException e) {
				new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
		return log;
	}

	public ArrayList<Log> getAllLog() {
		Connection con = DataBaseConnect.getConnection();
		String sql = null;
		sql = "SELECT * FROM `logger`";
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

	public ArrayList<Log> getAllLogFillter(String name) {
		Connection con = DataBaseConnect.getConnection();
		String sql = null;
		sql = "SELECT * FROM `logger` WHERE belepo_nev = '" + name + "'";
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
