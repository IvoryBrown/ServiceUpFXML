package com.login.setting.setting.location.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.login.setting.setting.location.pojo.Location;
import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LocationDataBase {
	public static ObservableList<String> locationListComboBox = FXCollections.observableArrayList();

	public static ArrayList<Location> getAllLocationDataBase() {
		ArrayList<Location> location = null;
		Statement createStatement = null;
		ResultSet rs = null;
		Connection con = DataBaseConnect.getConnection();
		String sql = " SELECT * FROM helyszin";
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			location = new ArrayList<>();
			while (rs.next()) {
				Location actualLocation = new Location(rs.getInt("id_helyszin"), rs.getString("helyszin"));
				location.add(actualLocation);
				locationListComboBox.add(rs.getString("helyszin"));
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
		return location;
	}

	public static void addLocation(Location location) {
		Connection con = DataBaseConnect.getConnection();
		try {
			String sql = "INSERT INTO helyszin (helyszin) VALUES (?)";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, location.getLocation());
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

	public static void updateLocation(Location location) {
		Connection con = DataBaseConnect.getConnection();
		try {
			String sql = "UPDATE helyszin SET helyszin = ? WHERE id_helyszin = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, location.getLocation());
			preparedStatement.setInt(2, Integer.parseInt(location.getLocationId()));
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a contact hozzáadásakor");
			new ShowInfo("Adatbázis Hiba", "", ex.getMessage());
		}
	}

	public static void removeLocation(Location location) {
		Connection con = DataBaseConnect.getConnection();
		try {
			String sql = "DELETE FROM helyszin where id_helyszin = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(location.getLocationId()));
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
