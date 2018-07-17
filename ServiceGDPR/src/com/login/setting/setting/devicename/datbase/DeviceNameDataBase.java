package com.login.setting.setting.devicename.datbase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.login.setting.setting.devicename.pojo.DeviceName;
import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

public class DeviceNameDataBase {

	public static ArrayList<DeviceName> getAllAdministratorDataBase() {
		ArrayList<DeviceName> deviceName = null;
		Statement createStatement = null;
		ResultSet rs = null;
		Connection con = DataBaseConnect.getConnection();
		String sql = " SELECT * FROM dolgozok";
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			deviceName = new ArrayList<>();
			while (rs.next()) {
				DeviceName actualDevice = new DeviceName(rs.getInt("idugyintezo"), rs.getString("ugyintezo_neve"));
				deviceName.add(actualDevice);
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
}
