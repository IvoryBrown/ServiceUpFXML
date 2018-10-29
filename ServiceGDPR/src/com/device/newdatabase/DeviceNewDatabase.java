package com.device.newdatabase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.device.pojo.Device;
import com.setting.database.DataBaseConnect;

public class DeviceNewDatabase {

	public void addContact(Device device){
		String SQL = null;
		SQL = "INSERT INTO gepadatok1(ugyfel_adatok_id_ugyfel, eszkoz_azonosito, ceg_nev_gep, ugyfél_nev_gep,"
				+ " eszkoz, eszkoz_gyarto, eszkoz_gyari_szama, javitas_helye, allapot, uj_gep, ugyintezo, prioritas,"
				+ "jelszo, hivatkozasi_szam, tartozekok, serules, hiba_leirasa, eszkoz_megjegyzes, vasarlasi_datuma,"
				+ " bejelentes_datuma, hatarido_datuma, kiszallas_datuma, adatmentes, softver, operacios_rendszer, "
				+ "softver_megjegyzés, laptop, haz, tapegyseg, processzor, alaplap, memoria, videokartya, ssd, meghajto,"
				+ " hutoventilator, optikai_meghajto, bovitokartya)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		try {
		Connection con = DataBaseConnect.getConnection();
			PreparedStatement insertDevice = con.prepareStatement(SQL);
			insertDevice.setString(1, device.getDeviceClientID());
			insertDevice.setString(2, device.getDeviceNumber());
			insertDevice.setString(3, device.getDeviceCompanyName());
			insertDevice.setString(4, device.getDeviceClientName());
			insertDevice.setString(5, device.getDeviceName());
			insertDevice.setString(6, device.getDeviceManufacturer());
			insertDevice.setString(7, device.getDeviceSerialNumber());
			insertDevice.setString(8, device.getDeviceRepairLocation());
			insertDevice.setString(9, device.getDeviceStatus());
			insertDevice.setString(10, device.getDeviceNewMachine());
			insertDevice.setString(11, device.getDeviceAdministrator());
			insertDevice.setString(12, device.getDevicePriorit());
			insertDevice.setString(13, device.getDevicePassword());
			insertDevice.setString(14, device.getDeviceReferences());
			insertDevice.setString(15, device.getDeviceAccesssory());
			insertDevice.setString(16, device.getDeviceInjury());
			insertDevice.setString(17, device.getDeviceErrorDescription());
			insertDevice.setString(18, device.getDeviceComment());
			if (device.getDeviceSalesBuying() != null) {
				insertDevice.setString(19,  device.getDeviceSalesBuyingConverter());
			} else {
				insertDevice.setDate(19, null);
			}
			insertDevice.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Automatikusan előállított elfogási blokk
			e.printStackTrace();
		}
	}
}
