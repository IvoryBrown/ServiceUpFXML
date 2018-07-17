package com.login.setting.setting.devicename.controller;

import com.login.setting.setting.devicename.pojo.DeviceName;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class SettingDeviceNameController {

	@FXML
	private TableView<DeviceName> tableDeviceSetting;
	private TableColumn<DeviceName, Integer> deviceNameId;
	private TableColumn<DeviceName, String> deviceName;
	@FXML
	private TextField deviceNameSettingTxt;
	@FXML
	private ComboBox<String> deviceNameSettingCmb;
	private final ObservableList<DeviceName> dataDeviceName = FXCollections.observableArrayList();

	@FXML
	private void deviceNameSave() {

	}

	@SuppressWarnings("unchecked")
	protected void setDeviceNameTableData() {
		tableDeviceSetting.getColumns().clear();
		dataDeviceName.clear();
		deviceNameId = new TableColumn<>("ID");
		deviceNameId.setMinWidth(70);
		deviceNameId.setCellValueFactory(new PropertyValueFactory<DeviceName, Integer>("deviceNameId"));

		deviceName = new TableColumn<>("Név");
		deviceName.setMinWidth(350);
		deviceName.setCellValueFactory(new PropertyValueFactory<DeviceName, String>("deviceName"));

		tableDeviceSetting.setItems(dataDeviceName);
		tableDeviceSetting.getColumns().addAll(deviceNameId, deviceName);
	}
}
