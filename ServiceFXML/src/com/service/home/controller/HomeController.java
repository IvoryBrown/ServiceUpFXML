package com.service.home.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.device.table.DeviceTable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeController extends DeviceTable implements Initializable {

	@FXML
	private void newStatisticsBtn(ActionEvent event) {
		try {
			Parent root = FXMLLoader
					.load(getClass().getResource("/com/service/setting/fxmlstatistics/FxmlStatistics.fxml"));
			Stage stage = new Stage();
//			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle("Statisztika");
			stage.setScene(new Scene(root, 1350, 650));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		setDeviceTableData();
		setComboxAll();
		setClientTableData();
		setStockTableData();
		setMenuData();
	}

}
