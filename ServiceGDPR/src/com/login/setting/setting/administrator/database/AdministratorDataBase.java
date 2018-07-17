package com.login.setting.setting.administrator.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.login.setting.setting.administrator.pojo.Administrator;
import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;

public class AdministratorDataBase {

	public static ArrayList<Administrator> getAllAdministratorDataBase() {
		ArrayList<Administrator> administrator = null;
		Statement createStatement = null;
		ResultSet rs = null;
		Connection con = DataBaseConnect.getConnection();
		String sql = " SELECT * FROM dolgozok";
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			administrator = new ArrayList<>();
			while (rs.next()) {
				Administrator actualDevice = new Administrator(rs.getInt("idugyintezo"), rs.getString("ugyintezo_neve"),
						rs.getString("ugyintezo_email"), rs.getString("beosztas"), rs.getString("jelszo"), rs.getString("jogkor"));
				administrator.add(actualDevice);
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
		return administrator;
	}

	public static void addContact(Administrator administrator) {
		Connection con = DataBaseConnect.getConnection();
		try {
			String sql = "INSERT INTO dolgozok (ugyintezo_neve, ugyintezo_email, beosztas, jelszo, jogkor) values (?,?,?,?,?)";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, administrator.getAdministratorName());
			preparedStatement.setString(2, administrator.getAdministratorEmail());
			preparedStatement.setString(3, administrator.getAdministratorPost());
			preparedStatement.setString(4, administrator.getAdministratorPassword());
			preparedStatement.setString(5, administrator.getAdministratorAuthority());
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

	public static void updateAdministrator(Administrator administrator) {
		Connection con = DataBaseConnect.getConnection();
		try {
			String sql = "UPDATE dolgozok SET ugyintezo_neve = ?, ugyintezo_email = ? , beosztas = ?, jelszo = ?, jogkor = ? where idugyintezo = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, administrator.getAdministratorName());
			preparedStatement.setString(2, administrator.getAdministratorEmail());
			preparedStatement.setString(3, administrator.getAdministratorPost());
			preparedStatement.setString(4, administrator.getAdministratorPassword());
			preparedStatement.setString(5, administrator.getAdministratorAuthority());
			preparedStatement.setInt(6, Integer.parseInt(administrator.getAdministratorId()));
			preparedStatement.execute();
		} catch (SQLException ex) {
			System.out.println("Valami baj van a contact hozzáadásakor");
			new ShowInfo("Adatbázis Hiba", "", ex.getMessage());
		}
	}

	public static void removeAdministrator(Administrator administrator) {
		Connection con = DataBaseConnect.getConnection();
		try {
			String sql = "DELETE FROM dolgozok where idugyintezo = ?";
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setInt(1, Integer.parseInt(administrator.getAdministratorId()));
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
