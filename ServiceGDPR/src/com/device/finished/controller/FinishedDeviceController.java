package com.device.finished.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.device.finished.database.DeviceFinishedDB;
import com.device.pojo.Device;
import com.device.table.controller.DeviceTableController;
import com.log.filewriter.FileWriterLog;
import com.login.database.LoginDataBase;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class FinishedDeviceController extends DeviceTableController implements Initializable {
	@FXML
	protected TextField deviceClientNameFilteringTxt;
	private final ObservableList<Device> dataFinishedDevice = FXCollections.observableArrayList();
	private DeviceFinishedDB db = new DeviceFinishedDB();

	@Override
	protected void setDeviceTableData() {
		this.setDataTable();
		super.setDeviceTableData();
	}

	@Override
	protected void setDataTable() {
		deviceTable.getItems().clear();
		dataFinishedDevice.clear();
		dataFinishedDevice.addAll(db.getFinishedDevice());
		deviceTable.setItems(dataFinishedDevice);
		super.setDataTable();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.setDeviceTableData();
		deviceClientNameFilteringTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode().equals(KeyCode.ENTER)) {
					checkClient();
				}
			}
		});

	}

	private void checkClient() {
		new FileWriterLog(LoginDataBase.name + " Eszköz keresés a befejezettbe: " + deviceClientNameFilteringTxt.getText());
		deviceTable.getItems().clear();
		dataFinishedDevice.clear();
		dataFinishedDevice.addAll(db.getClientNameFilltering(deviceClientNameFilteringTxt.getText()));
		tray = new TrayNotification("Keresés", "Sikeres Frissítése", NotificationType.SUCCESS);
		tray.setAnimationType(AnimationType.POPUP);
		tray.showAndDismiss(Duration.seconds(2));
	}

}
