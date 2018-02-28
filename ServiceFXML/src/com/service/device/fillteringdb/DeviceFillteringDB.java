package com.service.device.fillteringdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.service.device.Device;
import com.service.setting.database.DataBaseConnect;
import com.service.setting.showinfo.ShowInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeviceFillteringDB {
	public ObservableList<String> administratorList = FXCollections.observableArrayList();
	private String SQLaDMINISTRATOR = " select * from ugyintezo ";

	public DeviceFillteringDB() {
		setGetAllAdministrator();
	}

	public static ArrayList<Device> getAllDevice() {
		Connection con = DataBaseConnect.getConnection();
		String sql = "SELECT * FROM `gepadatok`";
		ArrayList<Device> device = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			device = new ArrayList<>();
			while (rs.next()) {
				Device actualDevice = new Device(rs.getString("id_gepadatok"), rs.getString("ugyintezo"),
						rs.getBoolean("laptop"));
				device.add(actualDevice);
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
		return device;
	}

	public void setGetAllAdministrator() {
		Connection con = DataBaseConnect.getConnection();
		PreparedStatement pstStn = null;
		ResultSet stnRS = null;
		try {
			pstStn = con.prepareStatement(SQLaDMINISTRATOR);
			stnRS = pstStn.executeQuery(SQLaDMINISTRATOR);
			while (stnRS.next()) {
				administratorList.add(stnRS.getString("ugyintezo_neve"));
			}
		} catch (SQLException ex) {
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
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
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}

	public void updateDevice(Device client) {
		Connection conn = DataBaseConnect.getConnection();
		try {
			String sqlClient = "UPDATE `gepadatok` set ugyintezo = ?, laptop = ?" + " WHERE id_gepadatok = ?";
			PreparedStatement pr = conn.prepareStatement(sqlClient);

			pr.setString(1, client.getDeviceAdministrator());
			pr.setBoolean(2, client.getDeviceLaptop());
			pr.setString(3, client.getDeviceID());

			pr.execute();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
