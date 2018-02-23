package com.service.client.fillteringdb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.service.client.Client;
import com.service.setting.database.DataBaseConnect;
import com.service.setting.showinfo.ShowInfo;

public class ClientFillteringDB {
	public static ArrayList<Client> getAllClient() {
		Connection con = DataBaseConnect.getConnection();
		String sql = "SELECT * FROM `ugyfel_adatok`";
		ArrayList<Client> client = null;

		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			client = new ArrayList<>();
			while (rs.next()) {
				Client actualCLient = new Client(rs.getInt("id_ugyfel"), rs.getString("ugyfel_azonosito"),
						rs.getString("cegnev"), rs.getString("ugyfel_nev"), rs.getString("megye"),
						rs.getString("telepules"), rs.getString("iranyitoszam"), rs.getString("cim"),
						rs.getString("ceg_telefon"), rs.getString("ceg_email"), rs.getString("ugyfel_telefon"),
						rs.getString("ugyfel_email"), rs.getString("csomag_tipus"),
						rs.getString("ugyfel_megjegyzes"));
				client.add(actualCLient);
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
		return client;
	}

	public void updateClient(Client client) {
		// TODO Automatikusan előállított metóduscsonk
		
	}
}
