package com.device.actual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.device.actual.database.DeviceActualDB;
import com.device.pojo.Device;
import com.device.table.controller.DeviceTableController;

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

public class ActualDeviceController extends DeviceTableController implements Initializable {
	@FXML
	protected TextField deviceClientNameFilteringTxt;
	private final ObservableList<Device> dataActualDevice = FXCollections.observableArrayList();
	private DeviceActualDB db = new DeviceActualDB();

	@Override
	protected void setDeviceTableData() {
		this.setDataTable();
		super.setDeviceTableData();
	}

	@Override
	protected void setDataTable() {
		deviceTable.getItems().clear();
		dataActualDevice.clear();
		dataActualDevice.addAll(db.getActualDevice());
		deviceTable.setItems(dataActualDevice);
		super.setDataTable();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
		deviceTable.getItems().clear();
		dataActualDevice.clear();
		dataActualDevice.addAll(db.getClientNameFilltering(deviceClientNameFilteringTxt.getText()));
		tray = new TrayNotification("Keresés", "Sikeres Frissítése", NotificationType.SUCCESS);
		tray.setAnimationType(AnimationType.POPUP);
		tray.showAndDismiss(Duration.seconds(2));
	}

}
