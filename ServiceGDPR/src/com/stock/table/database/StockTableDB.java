package com.stock.table.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.setting.database.DataBaseConnect;
import com.setting.showinfo.ShowInfo;
import com.stock.pojo.Stock;

public class StockTableDB {

	public static ArrayList<Stock> getAllStock(String stockDeviceNameFilteringTxt) {

		String sql = "SELECT * FROM `raktar` WHERE CONCAT (`" + "eszkoznev" + "`) LIKE '%" + stockDeviceNameFilteringTxt
				+ "%'";
		ArrayList<Stock> device = null;

		Statement createStatement = null;
		ResultSet rs = null;
		Connection con = DataBaseConnect.getConnection();
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			device = new ArrayList<>();
			while (rs.next()) {
				Stock actualStock = new Stock(rs.getInt("category_id"), rs.getString("eszkoznev"),
						rs.getString("kelte"), rs.getDate("eladas_kelte"), rs.getInt("mennyiseg"),
						rs.getString("raktaron"), rs.getString("leiras"), rs.getString("szamla_azonosito"));
				device.add(actualStock);
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
		return device;
	}

	public void updateStock(Stock stock) {
		Connection conn = DataBaseConnect.getConnection();
		PreparedStatement pr = null;
		try {
			String sqlStock = "UPDATE `raktar` set eszkoznev = ?, kelte = ?, eladas_kelte = ?, mennyiseg = ?,"
					+ "raktaron = ?, leiras = ?, szamla_azonosito = ? WHERE category_id = ?";
			pr = conn.prepareStatement(sqlStock);
			pr.setString(1, stock.getStockDeviceName());
			pr.setString(2, stock.getStockDeviceDate());
			pr.setObject(3, stock.getStockDeviceSalesDate());
			pr.setInt(4, stock.getStockDeviceQuantity());
			pr.setInt(5, Integer.parseInt(stock.getStockDeviceInStock()));
			pr.setString(6, stock.getStockDeviceDescription());
			pr.setString(7, stock.getStockDeviceAccountIdentity());
			pr.setInt(8, stock.getStockDeviceId());
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
