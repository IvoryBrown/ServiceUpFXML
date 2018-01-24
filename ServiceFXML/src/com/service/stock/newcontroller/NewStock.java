package com.service.stock.newcontroller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class NewStock implements Initializable {
	@FXML
	private TextField stockDeviceName, stockDeviceQuantity, stockDeviceDescription;
	@FXML
	private DatePicker stockDeviceDate, stockDeviceSalesDate;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
