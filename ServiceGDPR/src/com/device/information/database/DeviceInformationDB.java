package com.device.information.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.device.information.pojo.DeviceInfo;
import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeviceInformationDB {
	
	public DeviceInformationDB() {
		setCheckDeviceNumber();
	}
	
	public ObservableList<String> checkDeviceNumber = FXCollections.observableArrayList();

	public static ArrayList<DeviceInfo> getAllDeviceInfo() {
		Connection con = DataBaseConnect.getConnection();
		String sql = null;
		sql = "SELECT * FROM `gepadatok_informacio`";

		ArrayList<DeviceInfo> deviceInfo = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			deviceInfo = new ArrayList<>();
			while (rs.next()) {
				DeviceInfo actualDevice = new DeviceInfo(rs.getString("int"), rs.getString("eszkoz_az"),
						rs.getBlob("gep_info"));
				deviceInfo.add(actualDevice);
			}
		} catch (SQLException e) {
			new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
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
				new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
		return deviceInfo;
	}

	public static ArrayList<DeviceInfo> getDeviceInfoFilltering(String deviceNumber) {
		Connection con = DataBaseConnect.getConnection();
		String sql = null;
		sql = "SELECT * FROM `gepadatok_informacio` WHERE CONCAT (`" + "eszkoz_az" + "`) LIKE '%" + deviceNumber + "%'";

		ArrayList<DeviceInfo> deviceInfo = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			deviceInfo = new ArrayList<>();
			while (rs.next()) {
				DeviceInfo actualDevice = new DeviceInfo(rs.getString("int"), rs.getString("eszkoz_az"),
						rs.getBlob("gep_info"));
				deviceInfo.add(actualDevice);
			}
		} catch (SQLException e) {
			new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
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
				new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
		return deviceInfo;
	}

	public void removeContact(DeviceInfo deviceInfo) {
		Connection conn = DataBaseConnect.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = null;
			sql = "DELETE FROM `gepadatok_informacio` WHERE `int` = ?";

			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, deviceInfo.getDeviceInfoID());
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a contact törlésekor");
			System.out.println("" + ex);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}

	public void setCheckDeviceNumber() {
		String SQLaDMINISTRATOR = null;
		SQLaDMINISTRATOR = " SELECT `eszkoz_azonosito` FROM gepadatok1 ";

		Connection con = DataBaseConnect.getConnection();
		PreparedStatement pstStn = null;
		ResultSet stnRS = null;
		try {
			pstStn = con.prepareStatement(SQLaDMINISTRATOR);
			stnRS = pstStn.executeQuery(SQLaDMINISTRATOR);
			while (stnRS.next()) {
				checkDeviceNumber.add(stnRS.getString("eszkoz_azonosito"));
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
	}
}
