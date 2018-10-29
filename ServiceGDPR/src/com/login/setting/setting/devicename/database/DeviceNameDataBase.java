package com.login.setting.setting.devicename.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.login.setting.setting.devicename.pojo.DeviceName;
import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeviceNameDataBase {
	public static ObservableList<String> deviceNameList = FXCollections.observableArrayList();

	public static ArrayList<DeviceName> getAllDeviceNameDataBase() {
		ArrayList<DeviceName> deviceName = null;
		Statement createStatement = null;
		ResultSet rs = null;
		Connection con = DataBaseConnect.getConnection();
		String sql = " SELECT * FROM eszkoz_nevek";
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			deviceName = new ArrayList<>();
			while (rs.next()) {
				DeviceName actualDevice = new DeviceName(rs.getInt("id_eszkoz_nevek"), rs.getString("eszkoz_nev"));
				deviceName.add(actualDevice);
				deviceNameList.add(rs.getString("eszkoz_nev"));
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
		return deviceName;
	}

	public static void addDeviceName(DeviceName deviceName) {
		Connection con = DataBaseConnect.getConnection();
		try {
			String sql = "INSERT INTO eszkoz_nevek (eszkoz_nev) VALUES (?)";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, deviceName.getDeviceName());
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a contact hozzáadásakor");
			new ShowInfo("Adatbázis Hiba", "", ex.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				new ShowInfo("Adatbázis Hiba", "", ex.getMessage());
			}
		}
	}

	public static void updateDeviceName(DeviceName deviceName) {
		Connection con = DataBaseConnect.getConnection();
		try {
			String sql = "UPDATE eszkoz_nevek SET eszkoz_nev = ? WHERE id_eszkoz_nevek = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, deviceName.getDeviceName());
			preparedStatement.setInt(2, Integer.parseInt(deviceName.getDeviceNameId()));
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a contact hozzáadásakor");
			new ShowInfo("Adatbázis Hiba", "", ex.getMessage());
		}
	}

	public static void removeDeviceName(DeviceName deviceName) {
		Connection con = DataBaseConnect.getConnection();
		try {
			String sql = "DELETE FROM eszkoz_nevek where id_eszkoz_nevek = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(deviceName.getDeviceNameId()));
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a contact törlésekor");
			new ShowInfo("Adatbázis Hiba", "", ex.getMessage());
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				new ShowInfo("Adatbázis Hiba", "", ex.getMessage());
			}
		}
	}
}
