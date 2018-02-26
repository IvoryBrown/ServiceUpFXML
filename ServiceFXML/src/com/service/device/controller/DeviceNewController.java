package com.service.device.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.client.table.ClientTable;
import com.service.device.fillteringdb.DeviceFillteringDB;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DeviceNewController extends ClientTable implements Initializable {
	@FXML
	private TextField deviceSerialNumber, deviceManufacturer, devicePassword, deviceReferences;
	@FXML
	private TextArea deviceAccesssory, deviceInjury, deviceErrorDescription, deviceComment;
	@FXML
	private ComboBox<String> deviceName, deviceRepairLocation, deviceStatus, deviceNewMachine, devicePriorit,
			deviceAdministrator, deviceSoftver, deviceOperatingSystem;
	@FXML
	private Button deviceBtnDate, deviceBtnSoftver;
	@FXML
	private AnchorPane deviceAnchorPDate, deviceAncorPSoftver;
	@FXML
	private DatePicker deviceSalesBuying, deviceAddDate, deviceEndDate, deviceDeliveryDate;
	private final String CMBDEVICENAME[] = { "Asztali PC", "Notebook", "Nyomtató", "Monitor", "Projektor", "Pendrive",
			"Szünetmentes tápegység", "Egyéb" };
	private final String CMBDEVICEREPAIRLOCATION[] = { "Szervíz", "Helyszíni" };
	private final String CMBDEVICESTATUS[] = { "Bevételezve" };
	private final String CMBDEVICNEWMACHINE[] = { "Igen", "Nem" };
	private final String CMDDEVICEPRIORIT[] = { "Alap", "Szerződött", "Sűrgős" };
	private final String CMDDEVICEOPERATINGSYSTEM[] = { "Win Pro 7 32Bit Hungarian", "Win Pro 7 64Bit Hungarian",
			"Win 8.1 32Bit Hungarian", "Win 8.1 64Bit Hungarian", "Win 10 32Bit Hungarian", "Win 10 64Bit Hungarian" };
	DeviceFillteringDB db = new DeviceFillteringDB();

	protected void setComboxAll() {
		deviceName.getItems().addAll(CMBDEVICENAME);
		deviceRepairLocation.getItems().addAll(CMBDEVICEREPAIRLOCATION);
		deviceStatus.getItems().addAll(CMBDEVICESTATUS);
		deviceNewMachine.getItems().addAll(CMBDEVICNEWMACHINE);
		deviceAdministrator.setItems(db.stationsList);
		devicePriorit.getItems().addAll(CMDDEVICEPRIORIT);
		deviceSoftver.getItems().addAll(CMBDEVICNEWMACHINE);
		deviceOperatingSystem.getItems().addAll(CMDDEVICEOPERATINGSYSTEM);
	}

	@FXML
	private void setButtonPaneDate() {
		deviceAnchorPDate.setVisible(true);
		deviceAncorPSoftver.setVisible(false);

	}

	@FXML
	private void setButtonPaneSoftver() {
		deviceAnchorPDate.setVisible(false);
		deviceAncorPSoftver.setVisible(true);

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
