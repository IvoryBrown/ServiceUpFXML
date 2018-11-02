package com.printer.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.printer.pojo.Printer;
import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

public class PrinterDataBase {

	public static ArrayList<Printer> getAllDeviceClient(String name) {
		Connection con = DataBaseConnect.getConnection();
		String sql = "SELECT * FROM `ugyfel_adatok` JOIN `gepadatok1` ON id_ugyfel = ugyfel_adatok_id_ugyfel WHERE "
				 + "ugyfel_adatok_id_ugyfel = '" + name + "'";

		ArrayList<Printer> printer = null;
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			printer = new ArrayList<>();
			while (rs.next()) {
				Printer printers = new Printer(rs.getString("ugyfel_nev"),rs.getString("ugyfel_azonosito"), rs.getString("telepules"),
						rs.getString("iranyitoszam"), rs.getString("cim"), rs.getString("ugyfel_telefon"),
						rs.getString("eszkoz_azonosito"), rs.getString("vasarlasi_datuma"),
						rs.getString("bejelentes_datuma"), rs.getString("hatarido_datuma"), rs.getString("eszkoz"),
						rs.getString("eszkoz_gyarto"),

						rs.getString("jelszo"), rs.getString("tartozekok"), rs.getString("serules"),
						rs.getString("hiba_leirasa"),

						rs.getString("adatmentes"));
				printer.add(printers);
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
		return printer;
	}

}
