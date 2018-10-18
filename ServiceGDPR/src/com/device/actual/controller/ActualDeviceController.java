package com.device.actual.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.device.actual.database.DeviceActualDB;
import com.device.pojo.Device;
import com.device.table.controller.DeviceTableController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

public class ActualDeviceController extends DeviceTableController implements Initializable{
	private final ObservableList<Device> dataActualDevice = FXCollections.observableArrayList();
	private DeviceActualDB db = new DeviceActualDB();

	@Override
	protected void setDeviceTableData() {
		this.setDataTable();
		super.setDeviceTableData();
	}
	
	@Override
	protected void setDataTable() {
		dataActualDevice.addAll(db.getActualDevice());
		deviceTable.setItems(dataActualDevice);
		super.setDataTable();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.setDeviceTableData();
	}
	
}
