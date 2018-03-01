package com.service.home.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.service.device.table.DeviceTable;
import com.service.setting.database.DataBaseConnect;
import com.service.setting.showinfo.ShowInfo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;

public class HomeController extends DeviceTable implements Initializable {

	@FXML
	private BarChart<String, Integer> bar;
	@FXML
	private DatePicker fejezd, kezd;

	@FXML
	private void setStaticcc() {
		String sql = "SELECT hatarido_datuma,bovitokartya FROM `gepadatok` ORDER BY hatarido_datuma asc";
		XYChart.Series<String, Integer> serial = new XYChart.Series<>();
		Connection con = DataBaseConnect.getConnection();
		Statement createStatement = null;
		ResultSet rs = null;
		try {
			createStatement = con.createStatement();
			rs = createStatement.executeQuery(sql);
			while (rs.next()) {
				serial.getData().add(new XYChart.Data<>(rs.getString(1), rs.getInt(2)));
			}
			bar.getData().add(serial);
		} catch (SQLException e) {
			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setStaticcc();
		setDeviceTableData();
		setComboxAll();
		setClientTableData();
		setStockTableData();
		setMenuData();

	}

}
