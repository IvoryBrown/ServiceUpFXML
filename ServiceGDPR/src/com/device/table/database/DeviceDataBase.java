package com.device.table.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.device.pojo.Device;
import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

public class DeviceDataBase {

	public void updateDevice(Device device) {
		Connection conn = DataBaseConnect.getConnection();
		PreparedStatement pr = null;
		try {
			String sqlDevice = null;

			sqlDevice = "UPDATE `gepadatok1` SET eszkoz_azonosito = ?, ceg_nev_gep = ?, ugyfél_nev_gep = ?, eszkoz = ?, eszkoz_gyarto = ?, 	eszkoz_gyari_szama = ?, "
					+ "javitas_helye = ?, allapot = ?, tartozekok = ?,"
					+ "hiba_leirasa = ?, eszkoz_megjegyzes = ?, bejelentes_datuma = ?, hatarido_datuma = ?, kiszallas_datuma = ?, softver_megjegyzés = ?,"
					+ " elkeszult_datuma = ?, hibajavitas_leirasa = ?, technikus = ?, statusz = ?"
					+ " WHERE id_gepadatok = ?";

			pr = conn.prepareStatement(sqlDevice);
			pr.setString(1, device.getDeviceNumber());
			pr.setString(2, device.getDeviceCompanyName());
			pr.setString(3, device.getDeviceClientName());
			pr.setString(4, device.getDeviceName());
			pr.setString(5, device.getDeviceManufacturer());
			pr.setString(6, device.getDeviceSerialNumber());
			pr.setString(7, device.getDeviceRepairLocation());
			pr.setString(8, device.getDeviceStatus());
			pr.setString(9, device.getDeviceAccesssory());
			pr.setString(10, device.getDeviceErrorDescription());
			pr.setString(11, device.getDeviceComment());
			pr.setObject(12, device.getDeviceAddDate());
			pr.setObject(13, device.getDeviceEndDate());
			pr.setObject(14, device.getDeviceDeliveryDate());
			pr.setString(15, device.getDeviceSoftverComment());
			pr.setObject(16, device.getDeviceCompletedDate());
			pr.setString(17, device.getDeviceErrorCorrection());
			pr.setString(18, device.getDeviceTechnicalPerson());
			pr.setString(19, device.getDeviceStatusz());
			pr.setString(20, device.getDeviceID());

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
				new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}

	public void removeContact(Device device) {
		Connection conn = DataBaseConnect.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			String sql = null;
			sql = "DELETE FROM `gepadatok1` WHERE id_gepadatok = ?";

			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(device.getDeviceID()));
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a eszköz törlésekor");
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
}
