package com.login.setting.setting.operatingsystem.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.login.setting.setting.operatingsystem.pojo.OperatingSystem;
import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class OperatingSystemDataBase {
	public static ObservableList<String> operatingSystemListComboBox = FXCollections.observableArrayList();

	public static ArrayList<OperatingSystem> getAllOperatingSystem() {
		ArrayList<OperatingSystem> operatingSystem = null;
		Statement createStatement = null;
		ResultSet rs = null;
		Connection con = DataBaseConnect.getConnection();
		String sql = " SELECT * FROM operacios_rendszer";
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			operatingSystem = new ArrayList<>();
			while (rs.next()) {
				OperatingSystem actualOperatingSystem = new OperatingSystem(rs.getInt("id_operacios_rendszer"),
						rs.getString("operacios_rendszer"));
				operatingSystem.add(actualOperatingSystem);
				operatingSystemListComboBox.add(rs.getString("operacios_rendszer"));
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
		return operatingSystem;
	}

	public static void addOperatingSystem(OperatingSystem operatingSystem) {
		Connection con = DataBaseConnect.getConnection();
		try {
			String sql = "INSERT INTO operacios_rendszer (operacios_rendszer) VALUES (?)";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, operatingSystem.getOperatingSystem());
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

	public static void updateOperatingSystem(OperatingSystem operatingSystem) {
		Connection con = DataBaseConnect.getConnection();
		try {
			String sql = "UPDATE operacios_rendszer SET operacios_rendszer = ? WHERE id_operacios_rendszer = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, operatingSystem.getOperatingSystem());
			preparedStatement.setInt(2, Integer.parseInt(operatingSystem.getOperatingSystemId()));
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a contact hozzáadásakor");
			new ShowInfo("Adatbázis Hiba", "", ex.getMessage());
		}
	}

	public static void removeOperatingSystem(OperatingSystem operatingSystem) {
		Connection con = DataBaseConnect.getConnection();
		try {
			String sql = "DELETE FROM operacios_rendszer WHERE id_operacios_rendszer = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(operatingSystem.getOperatingSystemId()));
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
