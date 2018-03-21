package com.service.device.fillteringdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.service.device.DeviceInfo;
import com.service.setting.database.DataBaseConnect;
import com.service.setting.showinfo.ShowInfo;

public class DeviceInfoFillteringDB {
	public static ArrayList<DeviceInfo> getAllDeviceCompanyFiltering() {
		Connection con = DataBaseConnect.getConnection();
		String sql = "SELECT * FROM `gepadatok_informacio`";
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
		return deviceInfo;
	}
	
	public void removeContact(DeviceInfo deviceInfo) {
		Connection conn = DataBaseConnect.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = "delete from `gepadatok_informacio` where int = ?";
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(deviceInfo.getDeviceInfoID()));
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
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}
}
