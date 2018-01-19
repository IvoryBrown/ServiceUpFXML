package com.service.stock.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.service.stock.Stock;

public class StockDataBase {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/szerviz_up";
	static final String USER = "root";
	static final String PASS = "12345";

	Connection conn = null;
	Statement createStatement = null;

	public StockDataBase() {
		try {
			conn = (Connection) DriverManager.getConnection(DB_URL);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		if (conn != null) {
			try {
				createStatement = (Statement) conn.createStatement();
			} catch (SQLException e) {
				// TODO: handle exception
			}
		}
	}

	protected ArrayList<Stock> getAllStock() {
		String sql = "select * from raktar";
		ArrayList<Stock> device = null;
		try {
			ResultSet rs = createStatement.executeQuery(sql);
			device = new ArrayList<>();
			while (rs.next()) {
				Stock actualStock = new Stock(rs.getInt("category_id"), rs.getString("eszkoznev"),
						rs.getString("kelte"), rs.getString("eladas_kelte"), rs.getInt("mennyiseg"),
						rs.getString("leiras"));
				device.add(actualStock);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return device;

	}
}
