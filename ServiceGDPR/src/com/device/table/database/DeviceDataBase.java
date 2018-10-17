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
		
				sqlDevice = "UPDATE `gepadatok1` set eszkoz = ?, eszkoz_gyarto = ?, javitas_helye = ?, allapot = ?, tartozekok = ?,"
						+ "hiba_leirasa = ?, eszkoz_megjegyzes = ?, bejelentes_datuma = ?, hatarido_datuma = ?, kiszallas_datuma = ?, softver_megjegyzés = ?,"
						+ " elkeszult_datuma = ?, hibajavitas_leirasa = ?, technikus = ?, statusz = ?"
						+ " WHERE id_gepadatok = ?";
			
			
			pr = conn.prepareStatement(sqlDevice);
			pr.setString(1, device.getDeviceName());
			pr.setString(2, device.getDeviceManufacturer());
			pr.setString(3, device.getDeviceRepairLocation());
			pr.setString(4, device.getDeviceStatus());
			pr.setString(5, device.getDeviceAccesssory());
			pr.setString(6, device.getDeviceErrorDescription());
			pr.setString(7, device.getDeviceComment());
			pr.setObject(8, device.getDeviceAddDate());
			pr.setObject(9, device.getDeviceEndDate());
			pr.setObject(10, device.getDeviceDeliveryDate());
			pr.setString(11, device.getDeviceSoftverComment());
			pr.setObject(12, device.getDeviceCompletedDate());
			pr.setString(13, device.getDeviceErrorCorrection());
			pr.setString(14, device.getDeviceTechnicalPerson());
			pr.setString(15, device.getDeviceStatusz());
			pr.setString(16, device.getDeviceID());

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
}
