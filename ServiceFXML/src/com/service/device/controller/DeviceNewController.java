package com.service.device.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import com.service.client.table.ClientTable;
import com.service.device.fillteringdb.DeviceFillteringDB;
import com.service.main.LoginController;
import com.service.setting.combobox.Combobox;
import com.service.setting.database.DataBaseConnect;
import com.service.setting.email.EmailSending;
import com.service.setting.email.HTMLDataSource;
import com.service.setting.identification.DeviceIdentificationGenereator;
import com.service.setting.showinfo.ShowInfo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DeviceNewController extends ClientTable {
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
			deviceSSDDrive, deviceHardDrive, deviceCoolingFan, deviceOpticalDrive, deviceExpansionCard, deviceLaptop,
			emailCeckBox;

	private DeviceFillteringDB db = new DeviceFillteringDB();
	private Callback<DatePicker, DateCell> dayCellFactory;

	protected void setComboxAll() {
		deviceName.getItems().addAll(Combobox.setDeviceNameCombobox());
		deviceRepairLocation.getItems().addAll(Combobox.setLocationCombobox());
		deviceStatus.getItems().addAll(Combobox.setStatusCombobox());
		deviceNewMachine.getItems().addAll(Combobox.setYesNoCombobox());
		deviceAdministrator.setItems(db.administratorList);
		devicePriorit.getItems().addAll(Combobox.setNewPrioritCombobox());
		deviceSoftver.getItems().addAll(Combobox.setYesNoCombobox());
		deviceOperatingSystem.getItems().addAll(Combobox.setOperatingSystemCombobox());
		deviceDataRecovery.getItems().addAll(Combobox.setYesNoCombobox());
		setDate();
	}

	private void setDate() {
		deviceAddDate.setValue(LocalDate.now());
		dayCellFactory = new Callback<DatePicker, DateCell>() {
			@Override
			public DateCell call(final DatePicker datePicker) {
				return new DateCell() {
					@Override
					public void updateItem(LocalDate item, boolean empty) {
						super.updateItem(item, empty);

						if (item.isBefore(deviceAddDate.getValue().plusDays(5))) {
							setDisable(true);
							setStyle("-fx-background-color: linear-gradient(#61a2b1, #2A5058);");
						}
					}
				};
			}
		};
		deviceEndDate.setDayCellFactory(dayCellFactory);
		deviceEndDate.setValue(deviceAddDate.getValue().plusDays(5));
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

	private void setHtmlEmail() {
		if (emailCeckBox.isSelected()) {
		HTMLDataSource html = new HTMLDataSource();
		html.setHTMLCompom(deviceNumber.getText(), deviceName.getSelectionModel().getSelectedItem(),
				deviceManufacturer.getText(), deviceRepairLocation.getSelectionModel().getSelectedItem(),
				deviceStatus.getSelectionModel().getSelectedItem(),
				deviceNewMachine.getSelectionModel().getSelectedItem(),
				deviceAdministrator.getSelectionModel().getSelectedItem(), " ",
				devicePriorit.getSelectionModel().getSelectedItem(), deviceReferences.getText(),
				deviceErrorDescription.getText(), " ", deviceDeliveryDate.getEditor().getText(),
				deviceAddDate.getEditor().getText(), deviceEndDate.getEditor().getText(), " ",
				deviceSoftver.getSelectionModel().getSelectedItem());
		new EmailSending();
		}
	}

	@FXML
	private void setButtonNewDevie() {
		if (setDeviceClientCheck()) {
			if (setDeviceCheck()) {
				if (setDeviceSoftverCheck()) {
					String SQL = null;
					if (LoginController.setLogin.equals("Irisz")) {
						SQL = "INSERT INTO gepadatok1(ugyfel_adatok_id_ugyfel, eszkoz_azonosito, ceg_nev_gep, ugyfél_nev_gep,"
								+ " eszkoz, eszkoz_gyarto, eszkoz_gyari_szama, javitas_helye, allapot, uj_gep, ugyintezo, prioritas,"
								+ "jelszo, hivatkozasi_szam, tartozekok, serules, hiba_leirasa, eszkoz_megjegyzes, vasarlasi_datuma,"
								+ " bejelentes_datuma, hatarido_datuma, kiszallas_datuma, adatmentes, softver, operacios_rendszer, "
								+ "softver_megjegyzés, laptop, haz, tapegyseg, processzor, alaplap, memoria, videokartya, ssd, meghajto,"
								+ " hutoventilator, optikai_meghajto, bovitokartya)"
								+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
					}
					if (LoginController.setLogin.equals("Exicom")) {
						SQL = "INSERT INTO gepadatok1_exi(ugyfel_adatok_id_ugyfel_exi, eszkoz_azonosito, ceg_nev_gep, ugyfél_nev_gep,"
								+ " eszkoz, eszkoz_gyarto, eszkoz_gyari_szama, javitas_helye, allapot, uj_gep, ugyintezo, prioritas,"
								+ "jelszo, hivatkozasi_szam, tartozekok, serules, hiba_leirasa, eszkoz_megjegyzes, vasarlasi_datuma,"
								+ " bejelentes_datuma, hatarido_datuma, kiszallas_datuma, adatmentes, softver, operacios_rendszer, "
								+ "softver_megjegyzés, laptop, haz, tapegyseg, processzor, alaplap, memoria, videokartya, ssd, meghajto,"
								+ " hutoventilator, optikai_meghajto, bovitokartya)"
								+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
					}
					try {
						Connection con = DataBaseConnect.getConnection();
						PreparedStatement insertDevice = con.prepareStatement(SQL);
						deviceNumber.setText(DeviceIdentificationGenereator.random());
						insertDevice.setString(1, deviceClientId.getText());
						insertDevice.setString(2, deviceNumber.getText());
						insertDevice.setString(3, deviceCompanyName.getText());
						insertDevice.setString(4, deviceClientName.getText());
						insertDevice.setString(5, deviceName.getSelectionModel().getSelectedItem());
						insertDevice.setString(6, deviceManufacturer.getText());
						insertDevice.setString(7, deviceSerialNumber.getText());
						insertDevice.setString(8, deviceRepairLocation.getSelectionModel().getSelectedItem());
						insertDevice.setString(9, deviceStatus.getSelectionModel().getSelectedItem());
						insertDevice.setString(10, deviceNewMachine.getSelectionModel().getSelectedItem());
						insertDevice.setString(11, deviceAdministrator.getSelectionModel().getSelectedItem());
						insertDevice.setString(12, devicePriorit.getSelectionModel().getSelectedItem());
						insertDevice.setString(13, devicePassword.getText());
						insertDevice.setString(14, deviceReferences.getText());
						insertDevice.setString(15, deviceAccesssory.getText());
						insertDevice.setString(16, deviceInjury.getText());
						insertDevice.setString(17, deviceErrorDescription.getText());
						insertDevice.setString(18, deviceComment.getText());
						if (deviceSalesBuying.getValue() != null) {
							insertDevice.setString(19, ((TextField) deviceSalesBuying.getEditor()).getText());
						} else {
							insertDevice.setDate(19, null);
						}
						insertDevice.setString(20, ((TextField) deviceAddDate.getEditor()).getText());
						insertDevice.setString(21, ((TextField) deviceEndDate.getEditor()).getText());
						if (deviceDeliveryDate.getValue() != null) {
							insertDevice.setString(22, ((TextField) deviceDeliveryDate.getEditor()).getText());
						} else {
							insertDevice.setDate(22, null);
						}
						insertDevice.setString(23, deviceDataRecovery.getSelectionModel().getSelectedItem());
						insertDevice.setString(24, deviceSoftver.getSelectionModel().getSelectedItem());
						insertDevice.setString(25, deviceOperatingSystem.getSelectionModel().getSelectedItem());
						insertDevice.setString(26, deviceSoftverComment.getText());
						insertDevice.setBoolean(27, deviceLaptop.isSelected());
						insertDevice.setBoolean(28, deviceNewHouse.isSelected());
						insertDevice.setBoolean(29, devicePowerSupply.isSelected());
						insertDevice.setBoolean(30, deviceProcessor.isSelected());
						insertDevice.setBoolean(31, deviceBaseBoard.isSelected());
						insertDevice.setBoolean(32, deviceMemory.isSelected());
						insertDevice.setBoolean(33, deviceVideoCard.isSelected());
						insertDevice.setBoolean(34, deviceSSDDrive.isSelected());
						insertDevice.setBoolean(35, deviceHardDrive.isSelected());
						insertDevice.setBoolean(36, deviceCoolingFan.isSelected());
						insertDevice.setBoolean(37, deviceOpticalDrive.isSelected());
						insertDevice.setBoolean(38, deviceExpansionCard.isSelected());
						insertDevice.executeUpdate();
						setHtmlEmail();
						tray = new TrayNotification("Remek!", "Sikeres Felvétel", NotificationType.SUCCESS);
						tray.showAndDismiss(Duration.seconds(1));
						setClearAllText();
					} catch (SQLException ex) {
						System.out.println(ex);
						ShowInfo.errorInfoMessengeException("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
					}
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
		if (deviceClientName.getText().trim().isEmpty() || deviceClientId.getText().trim().isEmpty()) {
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
			deviceName.setStyle(null);
			deviceManufacturer.setStyle(null);
			deviceSerialNumber.setStyle(null);
			deviceRepairLocation.setStyle(null);
			deviceStatus.setStyle(null);
			deviceNewMachine.setStyle(null);
			deviceAdministrator.setStyle(null);
			devicePriorit.setStyle(null);
			deviceAccesssory.setStyle(null);
			deviceInjury.setStyle(null);
			deviceErrorDescription.setStyle(null);
			deviceAddDate.setStyle(null);
			deviceEndDate.setStyle(null);
			deviceDataRecovery.setStyle(null);
			return true;
		}

	}

	private boolean setDeviceSoftverCheck() {
		if (deviceSoftver.getSelectionModel().getSelectedIndex() == 0) {
			if (deviceOperatingSystem.getValue() == null) {
				deviceOperatingSystem.setPromptText("Kérlek válasz!");
				return false;
			} else {
				deviceOperatingSystem.setStyle(null);
				return true;
			}
		} else {
			deviceOperatingSystem.setStyle(null);
			return true;
		}
	}

	@FXML
	private void setClearAllText() {
		deviceClientId.clear();
		deviceNumber.clear();
		deviceCompanyName.clear();
		deviceClientName.clear();
		deviceName.setValue(null);
		deviceManufacturer.clear();
		deviceSerialNumber.clear();
		deviceRepairLocation.setValue(null);
		deviceStatus.setValue(null);
		deviceNewMachine.setValue(null);
		deviceAdministrator.setValue(null);
		devicePriorit.setValue(null);
		devicePassword.setText("nincs");
		deviceReferences.clear();
		deviceAccesssory.clear();
		deviceInjury.clear();
		deviceErrorDescription.clear();
		deviceComment.clear();
		deviceSalesBuying.setValue(null);
		deviceAddDate.setValue(null);
		deviceEndDate.setValue(null);
		deviceDeliveryDate.setValue(null);
		deviceDataRecovery.setValue(null);
		deviceSoftver.setValue(null);
		deviceOperatingSystem.setValue(null);
		deviceSoftverComment.clear();
		deviceLaptop.setSelected(false);
		deviceNewHouse.setSelected(false);
		devicePowerSupply.setSelected(false);
		deviceProcessor.setSelected(false);
		deviceBaseBoard.setSelected(false);
		deviceMemory.setSelected(false);
		deviceVideoCard.setSelected(false);
		deviceSSDDrive.setSelected(false);
		deviceHardDrive.setSelected(false);
		deviceCoolingFan.setSelected(false);
		deviceOpticalDrive.setSelected(false);
		deviceExpansionCard.setSelected(false);
		emailCeckBox.setSelected(false);
		setDate();
	}

}
