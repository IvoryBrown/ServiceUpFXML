package com.service.client.fillteringdb;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
						rs.getString("ugyfel_email"), rs.getString("csomag_tipus"), rs.getString("ugyfel_megjegyzes"));
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

	public static ArrayList<Client> getClientNameFilltering(String clientName) {
		Connection con = DataBaseConnect.getConnection();
		String sql = "SELECT * FROM `ugyfel_adatok` WHERE CONCAT (`" + "ugyfel_nev" + "`) LIKE '%" + clientName + "%'";
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
						rs.getString("ugyfel_email"), rs.getString("csomag_tipus"), rs.getString("ugyfel_megjegyzes"));
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

	public static ArrayList<Client> getCompanyNameFilltering(String companyName) {
		Connection con = DataBaseConnect.getConnection();
		String sql = "SELECT * FROM `ugyfel_adatok` WHERE CONCAT (`" + "cegnev" + "`) LIKE '%" + companyName + "%'";
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
						rs.getString("ugyfel_email"), rs.getString("csomag_tipus"), rs.getString("ugyfel_megjegyzes"));
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
		Connection conn = DataBaseConnect.getConnection();
		try {
			String sqlClient = "UPDATE `ugyfel_adatok` set cegnev = ?, ugyfel_nev = ?, megye = ?,"
					+ "telepules = ?, iranyitoszam = ?, cim = ?, ceg_telefon = ?, ceg_email = ?, ugyfel_telefon = ?,"
					+ " ugyfel_email = ?, csomag_tipus = ?, ugyfel_megjegyzes = ?" + " WHERE id_ugyfel = ?";
			PreparedStatement pr = conn.prepareStatement(sqlClient);
			pr.setString(1, client.getClientCompanyName());
			pr.setString(2, client.getClientName());
			pr.setString(3, client.getClientCounty());
			pr.setString(4, client.getClientSettlement());
			pr.setString(5, client.getClientZipCode());
			pr.setString(6, client.getClientAddress());
			pr.setString(7, client.getClientCompanyPhone());
			pr.setString(8, client.getClientCompanyEmail());
			pr.setString(9, client.getClientPhone());
			pr.setString(10, client.getClientEmail());
			pr.setString(11, client.getClientPackage());
			pr.setString(12, client.getClientComment());
			pr.setInt(13, Integer.parseInt(client.getClientId()));
			pr.execute();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
