package com.service.device.controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.service.client.table.ClientTable;
import com.service.device.fillteringdb.DeviceFillteringDB;
import com.service.setting.database.DataBaseConnect;
import com.service.setting.showinfo.ShowInfo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class DeviceNewController extends ClientTable implements Initializable {
	@FXML
	private TextField deviceNumber1, deviceNumber, deviceSerialNumber, deviceManufacturer, devicePassword,
			deviceReferences;
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
	private CheckBox deviceNewHouse;
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
		try {
			Connection con = DataBaseConnect.getConnection();
			PreparedStatement device = con.prepareStatement(
					"INSERT INTO gepadatok(ugyfel_adatok_id_ugyfel, eszkoz_fajtaja, eszkoz_gyarto) values(?,?,?) ");

			device.setString(1, deviceNumber1.getText());
			device.setString(2, deviceSerialNumber.getText());

			device.setBoolean(3, deviceNewHouse.isSelected());
			device.executeUpdate();
			System.out.println(deviceNumber1.getText());
			System.out.println(deviceSerialNumber.getText());
			System.out.println(deviceNewHouse.isSelected());
		} catch (SQLException ex) {

			ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
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
