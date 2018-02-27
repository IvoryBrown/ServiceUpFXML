package com.service.device.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.client.table.ClientTable;
import com.service.device.fillteringdb.DeviceFillteringDB;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DeviceNewController extends ClientTable implements Initializable {
	@FXML
	private TextField deviceNumber1, deviceNumber, deviceSerialNumber, deviceManufacturer, devicePassword,
			deviceReferences, deviceClientName, deviceCompanyName, deviceClientId;
	@FXML
	private TextArea deviceAccesssory, deviceInjury, deviceErrorDescription, deviceComment, deviceSoftverComment;
	@FXML
	private ComboBox<String> deviceName, deviceRepairLocation, deviceStatus, deviceNewMachine, devicePriorit,
			deviceAdministrator, deviceSoftver, deviceOperatingSystem, deviceDataRecovery;
	@FXML
	private Button deviceBtnDate, deviceBtnSoftver, deviceBtnHardver;
	@FXML
	private AnchorPane deviceAnchorPDate, deviceAncorPSoftver, deviceAncorPHardver;
	@FXML
	private DatePicker deviceSalesBuying, deviceAddDate, deviceEndDate, deviceDeliveryDate;
	@FXML
	private CheckBox deviceNewHouse, devicePowerSupply, deviceProcessor, deviceBaseBoard, deviceMemory, deviceVideoCard,
			deviceSSDDrive, deviceHardDrive, deviceCoolingFan, deviceOpticalDrive, deviceExpansionCard, deviceLaptop;
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
		deviceDataRecovery.getItems().addAll(CMBDEVICNEWMACHINE);
	}

	@FXML
	private void setButtonPaneDate() {
		deviceAnchorPDate.setVisible(true);
		deviceAncorPSoftver.setVisible(false);
		deviceAncorPHardver.setVisible(false);
	}

	@FXML
	private void setButtonPaneSoftver() {
		deviceAnchorPDate.setVisible(false);
		deviceAncorPSoftver.setVisible(true);
		deviceAncorPHardver.setVisible(false);
	}

	@FXML
	private void setButtonPaneHardver() {
		deviceAnchorPDate.setVisible(false);
		deviceAncorPSoftver.setVisible(false);
		deviceAncorPHardver.setVisible(true);
	}

	@FXML
	private void setButtonNewDevie() {
		if (setDeviceClientCheck()) {
			if (setDeviceCheck()) {
				if (setDeviceSoftverCheck()) {

				} else {
					tray = new TrayNotification("HIBA", "Nincs Softver kiválasztva", NotificationType.ERROR);
					tray.showAndDismiss(Duration.seconds(2));
				}
			} else {
				tray = new TrayNotification("HIBA", "Nincs minden mező kitöltve", NotificationType.ERROR);
				tray.showAndDismiss(Duration.seconds(2));
			}
		} else {
			tray = new TrayNotification("HIBA", "Nincs ügyfél kiválasztva", NotificationType.ERROR);
			tray.showAndDismiss(Duration.seconds(2));
		}
	}

	private boolean setDeviceClientCheck() {
		if (deviceClientName.getText().trim().isEmpty() || deviceCompanyName.getText().trim().isEmpty()
				|| deviceClientId.getText().trim().isEmpty()) {
			deviceCompanyName.setPromptText("Kérlek válasz a táblából!");
			deviceClientName.setPromptText("Kérlek válasz a táblából!");
			return false;
		} else {
			deviceCompanyName.setStyle(null);
			deviceClientName.setStyle(null);
			return true;
		}
	}

	private boolean setDeviceCheck() {
		if (deviceName.getValue() == null || deviceManufacturer.getText().trim().isEmpty()
				|| deviceSerialNumber.getText().trim().isEmpty() || deviceRepairLocation.getValue() == null
				|| deviceStatus.getValue() == null || deviceNewMachine.getValue() == null
				|| deviceAdministrator.getValue() == null || devicePriorit.getValue() == null
				|| deviceAccesssory.getText().trim().isEmpty() || deviceInjury.getText().trim().isEmpty()
				|| deviceErrorDescription.getText().trim().isEmpty() || deviceAddDate.getValue() == null
				|| deviceEndDate.getValue() == null || deviceDataRecovery.getValue() == null) {
			deviceName.setPromptText("Kérlek válasz!");
			deviceManufacturer.setPromptText("Kérlek válasz!");
			deviceSerialNumber.setPromptText("Kérlek válasz!");
			deviceRepairLocation.setPromptText("Kérlek válasz!");
			deviceStatus.setPromptText("Kérlek válasz!");
			deviceNewMachine.setPromptText("Kérlek válasz!");
			deviceAdministrator.setPromptText("Kérlek válasz!");
			devicePriorit.setPromptText("Kérlek válasz!");
			deviceAccesssory.setPromptText("Kérlek válasz!");
			deviceInjury.setPromptText("Kérlek válasz!");
			deviceErrorDescription.setPromptText("Kérlek válasz!");
			deviceAddDate.setPromptText("Kérlek válasz!");
			deviceEndDate.setPromptText("Kérlek válasz!");
			deviceDataRecovery.setPromptText("Kérlek válasz!");
			return false;
		} else {
			deviceName.setPromptText(null);
			deviceManufacturer.setPromptText(null);
			deviceSerialNumber.setPromptText(null);
			deviceRepairLocation.setPromptText(null);
			deviceStatus.setPromptText(null);
			deviceNewMachine.setPromptText(null);
			deviceAdministrator.setPromptText(null);
			devicePriorit.setPromptText(null);
			deviceAccesssory.setPromptText(null);
			deviceInjury.setPromptText(null);
			deviceErrorDescription.setPromptText(null);
			deviceAddDate.setPromptText(null);
			deviceEndDate.setPromptText(null);
			deviceDataRecovery.setPromptText(null);
			return true;
		}

	}

	private boolean setDeviceSoftverCheck() {
		if (deviceSoftver.getSelectionModel().getSelectedIndex() == 0) {
			if (deviceOperatingSystem.getValue() == null) {
				deviceOperatingSystem.setPromptText("Kérlek válasz!");
				return false;
			} else {
				deviceOperatingSystem.setPromptText(null);
				return true;
			}
		} else {
			deviceOperatingSystem.setPromptText(null);
			return true;
		}
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
