package com.service.device.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.client.table.ClientTable;
import com.service.device.fillteringdb.DeviceFillteringDB;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DeviceNewController extends ClientTable implements Initializable {
	@FXML
	private TextField deviceSerialNumber, deviceManufacturer;
	@FXML
	private ComboBox<String> deviceName, deviceRepairLocation, deviceStatus, deviceNewMachine;
	@FXML
	private ComboBox<String> deviceAdministrator;
	private final String CMBDEVICENAME[] = { "Asztali PC", "Notebook", "Nyomtató", "Monitor", "Projektor", "Pendrive",
			"Szünetmentes tápegység", "Egyéb" };
	private final String CMBDEVICEREPAIRLOCATION[] = { "Szervíz", "Helyszíni" };
	private final String CMBDEVICESTATUS[] = { "Bevételezve" };
	private final String CMBDEVICNEWMACHINE[] = { "Igen" };
	DeviceFillteringDB db = new DeviceFillteringDB();

	protected void setComboxAll() {
		deviceName.getItems().addAll(CMBDEVICENAME);
		deviceRepairLocation.getItems().addAll(CMBDEVICEREPAIRLOCATION);
		deviceStatus.getItems().addAll(CMBDEVICESTATUS);
		deviceNewMachine.getItems().addAll(CMBDEVICNEWMACHINE);
		deviceAdministrator.setItems(db.stationsList);
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		setComboxAll();
		setClientTableData();
		setStockTableData();
		setComponentAll();
		setMenuData();

	}

}
