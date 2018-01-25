package com.service.stock.newcontroller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.service.setting.database.DataBaseConnect;
import com.service.setting.showinfo.ShowInfo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class NewStock implements Initializable {
	@FXML
	private TextField stockDeviceName, stockDeviceQuantity, stockDeviceDescription;
	@FXML
	private DatePicker stockDeviceDate, stockDeviceSalesDate;

	private boolean setStockCheckTxt() {
		if (stockDeviceName.getText().trim().isEmpty()) {
			stockDeviceName.setStyle("-fx-prompt-text-fill: red");
		}
		if (stockDeviceQuantity.getText().trim().isEmpty()) {
			stockDeviceQuantity.setStyle("-fx-prompt-text-fill: red");
		}
		if (stockDeviceDescription.getText().trim().isEmpty()) {
			stockDeviceDescription.setStyle("-fx-prompt-text-fill: red");
		}
		if (stockDeviceDate.getValue()==null) {
			stockDeviceDate.setPromptText("!!!!");
		}
		
		if (stockDeviceName.getText().trim().isEmpty() || stockDeviceQuantity.getText().trim().isEmpty()
				|| stockDeviceDescription.getText().trim().isEmpty()|| stockDeviceDate.getValue()==null) {
			return false;
		} else {
			return true;
		}
	}

	@FXML
	private void newDev(ActionEvent event) {
		
		if (setStockCheckTxt()) {
			try {
				Connection con = DataBaseConnect.getConnection();
				PreparedStatement insertStock = con
						.prepareStatement("INSERT INTO raktar(eszkoznev, kelte, eladas_kelte,"
								+ "mennyiseg, leiras)"
								+ "values(?,?,?,?,?) ");
				insertStock.setString(1, stockDeviceName.getText());
				insertStock.setString(2, ((TextField)stockDeviceDate.getEditor()).getText());
				insertStock.setString(3, ((TextField)stockDeviceSalesDate.getEditor()).getText());
				insertStock.setString(4, stockDeviceQuantity.getText());
				insertStock.setString(5, stockDeviceDescription.getText());
				insertStock.executeUpdate();
			} catch (SQLException e) {
				ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", e.getMessage());
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stockDeviceDate.setStyle("-fx-font-size: 15px");
		stockDeviceSalesDate.setStyle("-fx-font-size: 15px");
	}

}
