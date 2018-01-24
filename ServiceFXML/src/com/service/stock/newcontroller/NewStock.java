package com.service.stock.newcontroller;

import java.net.URL;
import java.util.ResourceBundle;

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
		if (stockDeviceDate.get) {
			stockDeviceDate.setStyle("-fx-prompt-text-fill: red");
		}
		if (stockDeviceName.getText().trim().isEmpty() || stockDeviceQuantity.getText().trim().isEmpty()
				|| stockDeviceDescription.getText().trim().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@FXML
	private void newDev(ActionEvent event) {
		if (setStockCheckTxt()) {

		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stockDeviceDate.setStyle("-fx-font-size: 15px");
		stockDeviceSalesDate.setStyle("-fx-font-size: 15px");
	}

}
