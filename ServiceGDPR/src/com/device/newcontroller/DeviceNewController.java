package com.device.newcontroller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.device.pojo.DeviceBackAllText;
import com.device.pojo.DeviceClient;
import com.login.database.LoginDataBase;
import com.login.setting.setting.devicename.database.DeviceNameDataBase;
import com.login.setting.setting.location.database.LocationDataBase;
import com.login.setting.setting.operatingsystem.database.OperatingSystemDataBase;
import com.setting.combobox.ComboBoxSet;
import com.setting.database.DataBaseConnect;
import com.setting.identification.DeviceIdentificationGenereator;
import com.setting.showinfo.ShowInfo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DeviceNewController implements Initializable {
	private String deviceClientId, deviceAdministratorLocation;;
	@FXML
	private DatePicker deviceSalesBuying, deviceAddDate, deviceEndDate, deviceDeliveryDate;
	@FXML
	private AnchorPane deviceAnchorPDate, deviceAncorPSoftver, deviceAncorPHardver;
	@FXML
	private TextField deviceNumber, deviceSerialNumber, deviceManufacturer, deviceAdministrator, devicePassword,
			deviceReferences, deviceClientName, deviceCompanyName, deviceStatus;
	@FXML
	private TextArea deviceAccesssory, deviceInjury, deviceErrorDescription, deviceComment, deviceSoftverComment;
	@FXML
	private ComboBox<String> deviceName, deviceRepairLocation, deviceNewMachine, devicePriorit, deviceSoftver,
			deviceOperatingSystem, deviceDataRecovery;
	@FXML
	private CheckBox deviceNewHouse, devicePowerSupply, deviceProcessor, deviceBaseBoard, deviceMemory, deviceVideoCard,
			deviceSSDDrive, deviceHardDrive, deviceCoolingFan, deviceOpticalDrive, deviceExpansionCard, deviceLaptop;
	private Callback<DatePicker, DateCell> dayCellFactory;
	private TrayNotification tray = new TrayNotification();

	private void setDate() {
		deviceAddDate.setOnMouseClicked(e -> {
		     if(!deviceAddDate.isEditable())
		    	 deviceAddDate.hide();
		});
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

	private void setCombobox() {
		deviceAdministratorLocation = LoginDataBase.location;
		deviceAdministrator.setText(LoginDataBase.name);
		DeviceNameDataBase.getAllDeviceNameDataBase();
		deviceName.getItems().addAll(DeviceNameDataBase.deviceNameList);
		LocationDataBase.getAllLocationDataBase();
		deviceRepairLocation.getItems().addAll(LocationDataBase.locationListComboBox);
		deviceNewMachine.getItems().addAll(ComboBoxSet.setYesNoCombobox());
		devicePriorit.getItems().addAll(ComboBoxSet.setNewPrioritCombobox());
		deviceSoftver.getItems().addAll(ComboBoxSet.setYesNoCombobox());
		deviceDataRecovery.getItems().addAll(ComboBoxSet.setYesNoCombobox());
		OperatingSystemDataBase.getAllOperatingSystem();
		deviceOperatingSystem.getItems().addAll(OperatingSystemDataBase.operatingSystemListComboBox);
		DeviceNameDataBase.deviceNameList.clear();
		LocationDataBase.locationListComboBox.clear();
		OperatingSystemDataBase.operatingSystemListComboBox.clear();
	}

	@FXML
	private void newDevice() {
		if (setDeviceClientCheck()) {
			if (setDeviceCheck()) {
				if (setDeviceDataRecoveryCheck()) {
					if (setDeviceSoftverCheck()) {

						String SQL = "INSERT INTO gepadatok1(ugyfel_adatok_id_ugyfel, eszkoz_azonosito, ceg_nev_gep, ugyfél_nev_gep,"
								+ " eszkoz, eszkoz_gyarto, felvetel_helye,  eszkoz_gyari_szama, javitas_helye, allapot, uj_gep, ugyintezo, prioritas,"
								+ "jelszo, hivatkozasi_szam, tartozekok, serules, hiba_leirasa, eszkoz_megjegyzes, vasarlasi_datuma,"
								+ " bejelentes_datuma, hatarido_datuma, kiszallas_datuma, adatmentes, softver, operacios_rendszer, "
								+ "softver_megjegyzés, laptop, haz, tapegyseg, processzor, alaplap, memoria, videokartya, ssd, meghajto,"
								+ " hutoventilator, optikai_meghajto, bovitokartya)"
								+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

						try {
							Connection con = DataBaseConnect.getConnection();
							PreparedStatement insertDevice = con.prepareStatement(SQL);
							deviceNumber.setText(DeviceIdentificationGenereator.random());
							insertDevice.setString(1, deviceClientId);
							insertDevice.setString(2, deviceNumber.getText());
							insertDevice.setString(3, deviceCompanyName.getText());
							insertDevice.setString(4, deviceClientName.getText());
							insertDevice.setString(5, deviceName.getSelectionModel().getSelectedItem());
							insertDevice.setString(6, deviceManufacturer.getText());
							insertDevice.setString(7, deviceAdministratorLocation);
							insertDevice.setString(8, deviceSerialNumber.getText());
							insertDevice.setString(9, deviceRepairLocation.getSelectionModel().getSelectedItem());
							insertDevice.setString(10, deviceStatus.getText());
							insertDevice.setString(11, deviceNewMachine.getSelectionModel().getSelectedItem());
							insertDevice.setString(12, deviceAdministrator.getText());
							insertDevice.setString(13, devicePriorit.getSelectionModel().getSelectedItem());
							insertDevice.setString(14, devicePassword.getText());
							insertDevice.setString(15, deviceReferences.getText());
							insertDevice.setString(16, deviceAccesssory.getText());
							insertDevice.setString(17, deviceInjury.getText());
							insertDevice.setString(18, deviceErrorDescription.getText());
							insertDevice.setString(19, deviceComment.getText());
							if (deviceSalesBuying.getValue() != null) {
								insertDevice.setString(20, ((TextField) deviceSalesBuying.getEditor()).getText());
							} else {
								insertDevice.setDate(20, null);
							}
							insertDevice.setString(21, ((TextField) deviceAddDate.getEditor()).getText());
							insertDevice.setString(22, ((TextField) deviceEndDate.getEditor()).getText());
							if (deviceDeliveryDate.getValue() != null) {
								insertDevice.setString(23, ((TextField) deviceDeliveryDate.getEditor()).getText());
							} else {
								insertDevice.setDate(23, null);
							}
							insertDevice.setString(24, deviceDataRecovery.getSelectionModel().getSelectedItem());
							insertDevice.setString(25, deviceSoftver.getSelectionModel().getSelectedItem());
							insertDevice.setString(26, deviceOperatingSystem.getSelectionModel().getSelectedItem());
							insertDevice.setString(27, deviceSoftverComment.getText());
							insertDevice.setBoolean(28, deviceLaptop.isSelected());
							insertDevice.setBoolean(29, deviceNewHouse.isSelected());
							insertDevice.setBoolean(30, devicePowerSupply.isSelected());
							insertDevice.setBoolean(31, deviceProcessor.isSelected());
							insertDevice.setBoolean(32, deviceBaseBoard.isSelected());
							insertDevice.setBoolean(33, deviceMemory.isSelected());
							insertDevice.setBoolean(34, deviceVideoCard.isSelected());
							insertDevice.setBoolean(35, deviceSSDDrive.isSelected());
							insertDevice.setBoolean(36, deviceHardDrive.isSelected());
							insertDevice.setBoolean(37, deviceCoolingFan.isSelected());
							insertDevice.setBoolean(38, deviceOpticalDrive.isSelected());
							insertDevice.setBoolean(39, deviceExpansionCard.isSelected());
							insertDevice.executeUpdate();
							setBackAllText();
							setClearAllText();
							tray = new TrayNotification("Remek!", "Sikeres Felvétel", NotificationType.SUCCESS);
							tray.showAndDismiss(Duration.seconds(1));
						} catch (SQLException ex) {
							System.out.println(ex);
							new ShowInfo("Adatbázis Hiba", "Szerver válasza: ", ex.getMessage());
						}
					} else {
						tray = new TrayNotification("HIBA", "Nincs Softver kiválasztva", NotificationType.ERROR);
						tray.setAnimationType(AnimationType.POPUP);
						tray.showAndDismiss(Duration.seconds(3));
					}
				} else {
					tray = new TrayNotification("HIBA", "Miről kell a mentés???", NotificationType.ERROR);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(3));
				}
			} else {
				tray = new TrayNotification("HIBA", "Nincs minden mező kitöltve", NotificationType.ERROR);
				tray.setAnimationType(AnimationType.POPUP);
				tray.showAndDismiss(Duration.seconds(3));
			}
		} else {
			tray = new TrayNotification("HIBA", "Nincs ügyfél kiválasztva", NotificationType.ERROR);
			tray.setAnimationType(AnimationType.POPUP);
			tray.showAndDismiss(Duration.seconds(3));
		}
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDate();
		setCombobox();
		deviceClientId = DeviceClient.getDeviceClientID();
		deviceClientName.setText(DeviceClient.getDeviceClientName());
		deviceCompanyName.setText(DeviceClient.getDeviceCompanyName());
	}

	private boolean setDeviceCheck() {
		if (deviceName.getValue() == null || deviceManufacturer.getText().trim().isEmpty()
				|| deviceSerialNumber.getText().trim().isEmpty() || deviceRepairLocation.getValue() == null
				|| deviceStatus.getText().trim().isEmpty() || deviceNewMachine.getValue() == null
				|| deviceAdministrator.getText().trim().isEmpty() || devicePriorit.getValue() == null
				|| deviceAccesssory.getText().trim().isEmpty() || devicePassword.getText().trim().isEmpty()
				|| deviceInjury.getText().trim().isEmpty() || deviceErrorDescription.getText().trim().isEmpty()
				|| deviceAddDate.getValue() == null || deviceEndDate.getValue() == null
				|| deviceDataRecovery.getValue() == null) {
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
			devicePassword.setPromptText("Kérlek válasz!");
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
			devicePassword.setStyle(null);
			return true;
		}

	}

	private boolean setDeviceClientCheck() {
		if (deviceClientName.getText().trim().isEmpty()) {
			deviceCompanyName.setPromptText("Kérlek válasz a táblából!");
			deviceClientName.setPromptText("Kérlek válasz a táblából!");
			return false;
		} else {
			deviceCompanyName.setStyle(null);
			deviceClientName.setStyle(null);
			return true;
		}
	}

	private boolean setDeviceSoftverCheck() {
		if (deviceSoftver.getSelectionModel().getSelectedIndex() == 0) {
			if (deviceOperatingSystem.getValue() == null) {
				deviceOperatingSystem.setPromptText("Kérlek válasz!");
				return false;
			} else {
				deviceOperatingSystem.setPromptText("Softver megjegyzés!");
				deviceOperatingSystem.setStyle(null);
				return true;
			}
		} else {
			deviceSoftver.setPromptText("Softver!");
			return true;
		}
	}

	private boolean setDeviceDataRecoveryCheck() {
		if (deviceDataRecovery.getSelectionModel().getSelectedIndex() == 0) {
			if (deviceSoftverComment.getText().trim().isEmpty()) {
				deviceSoftverComment.setPromptText("Miről????????");
				return false;
			} else {
				deviceSoftverComment.setStyle(null);
				return true;
			}
		} else {
			deviceSoftverComment.setStyle(null);
			return true;
		}
	}

	private void setBackAllText() {
		DeviceBackAllText.setDeviceClientId(deviceClientId);
		DeviceBackAllText.setDeviceCompanyName(deviceCompanyName.getText());
		DeviceBackAllText.setDeviceClientName(deviceClientName.getText());
		DeviceBackAllText.setDeviceName(deviceName.getValue());
		DeviceBackAllText.setDeviceManufacturer(deviceManufacturer.getText());
		DeviceBackAllText.setDeviceSerialNumber(deviceSerialNumber.getText());
		DeviceBackAllText.setDeviceRepairLocation(deviceRepairLocation.getValue());
		DeviceBackAllText.setDeviceNewMachine(deviceNewMachine.getValue());
		DeviceBackAllText.setDevicePriorit(devicePriorit.getValue());
		DeviceBackAllText.setDevicePassword(devicePassword.getText());
		DeviceBackAllText.setDeviceReferences(deviceReferences.getText());
		DeviceBackAllText.setDeviceAccesssory(deviceAccesssory.getText());
		DeviceBackAllText.setDeviceInjury(deviceInjury.getText());
		DeviceBackAllText.setDeviceErrorDescription(deviceErrorDescription.getText());
		DeviceBackAllText.setDeviceComment(deviceComment.getText());
		DeviceBackAllText.setDeviceSalesBuying(deviceSalesBuying.getValue());
		DeviceBackAllText.setDeviceAddDate(deviceAddDate.getValue());
		DeviceBackAllText.setDeviceEndDate(deviceEndDate.getValue());
		DeviceBackAllText.setDeviceDeliveryDate(deviceDeliveryDate.getValue());
		DeviceBackAllText.setDeviceDataRecovery(deviceDataRecovery.getValue());
		DeviceBackAllText.setDeviceSoftver(deviceSoftver.getValue());
		DeviceBackAllText.setDeviceOperatingSystem(deviceOperatingSystem.getValue());
		DeviceBackAllText.setDeviceSoftverComment(deviceSoftverComment.getText());
		DeviceBackAllText.setDeviceLaptop(deviceLaptop.isSelected());
		DeviceBackAllText.setDeviceNewHouse(deviceNewHouse.isSelected());
		DeviceBackAllText.setDevicePowerSupply(devicePowerSupply.isSelected());
		DeviceBackAllText.setDeviceProcessor(deviceProcessor.isSelected());
		DeviceBackAllText.setDeviceBaseBoard(deviceBaseBoard.isSelected());
		DeviceBackAllText.setDeviceMemory(deviceMemory.isSelected());
		DeviceBackAllText.setDeviceVideoCard(deviceVideoCard.isSelected());
		DeviceBackAllText.setDeviceSSDDrive(deviceSSDDrive.isSelected());
		DeviceBackAllText.setDeviceHardDrive(deviceHardDrive.isSelected());
		DeviceBackAllText.setDeviceCoolingFan(deviceCoolingFan.isSelected());
		DeviceBackAllText.setDeviceOpticalDrive(deviceOpticalDrive.isSelected());
		DeviceBackAllText.setDeviceExpansionCard(deviceExpansionCard.isSelected());
	}

	@FXML
	private void getBackAllText() {
		deviceClientId = DeviceBackAllText.getDeviceClientId();
		deviceCompanyName.setText(DeviceBackAllText.getDeviceCompanyName());
		deviceClientName.setText(DeviceBackAllText.getDeviceClientName());
		deviceName.setValue(DeviceBackAllText.getDeviceName());
		deviceManufacturer.setText(DeviceBackAllText.getDeviceManufacturer());
		deviceSerialNumber.setText(DeviceBackAllText.getDeviceSerialNumber());
		deviceRepairLocation.setValue(DeviceBackAllText.getDeviceRepairLocation());
		deviceNewMachine.setValue(DeviceBackAllText.getDeviceNewMachine());
		devicePriorit.setValue(DeviceBackAllText.getDevicePriorit());
		devicePassword.setText(DeviceBackAllText.getDevicePassword());
		deviceReferences.setText(DeviceBackAllText.getDeviceReferences());
		deviceAccesssory.setText(DeviceBackAllText.getDeviceAccesssory());
		deviceInjury.setText(DeviceBackAllText.getDeviceInjury());
		deviceErrorDescription.setText(DeviceBackAllText.getDeviceErrorDescription());
		deviceComment.setText(DeviceBackAllText.getDeviceComment());
		deviceSalesBuying.setValue(DeviceBackAllText.getDeviceSalesBuying());
		deviceAddDate.setValue(DeviceBackAllText.getDeviceAddDate());
		deviceEndDate.setValue(DeviceBackAllText.getDeviceEndDate());
		deviceDeliveryDate.setValue(DeviceBackAllText.getDeviceDeliveryDate());
		deviceDataRecovery.setValue(DeviceBackAllText.getDeviceDataRecovery());
		deviceSoftver.setValue(DeviceBackAllText.getDeviceSoftver());
		deviceOperatingSystem.setValue(DeviceBackAllText.getDeviceOperatingSystem());
		deviceSoftverComment.setText(DeviceBackAllText.getDeviceSoftverComment());
		deviceLaptop.setSelected(DeviceBackAllText.isDeviceLaptop());
		deviceNewHouse.setSelected(DeviceBackAllText.isDeviceNewHouse());
		devicePowerSupply.setSelected(DeviceBackAllText.isDevicePowerSupply());
		deviceProcessor.setSelected(DeviceBackAllText.isDeviceProcessor());
		deviceBaseBoard.setSelected(DeviceBackAllText.isDeviceBaseBoard());
		deviceMemory.setSelected(DeviceBackAllText.isDeviceMemory());
		deviceVideoCard.setSelected(DeviceBackAllText.isDeviceVideoCard());
		deviceSSDDrive.setSelected(DeviceBackAllText.isDeviceSSDDrive());
		deviceHardDrive.setSelected(DeviceBackAllText.isDeviceHardDrive());
		deviceCoolingFan.setSelected(DeviceBackAllText.isDeviceCoolingFan());
		deviceOpticalDrive.setSelected(DeviceBackAllText.isDeviceOpticalDrive());
		deviceExpansionCard.setSelected(DeviceBackAllText.isDeviceExpansionCard());

	}

	@FXML
	private void setClearAllText() {
		deviceClientId = null;
		deviceNumber.clear();
		deviceCompanyName.clear();
		deviceClientName.clear();
		deviceName.setValue(null);
		deviceManufacturer.clear();
		deviceSerialNumber.clear();
		deviceRepairLocation.setValue(null);
		deviceNewMachine.setValue(null);
		devicePriorit.setValue(null);
		devicePassword.clear();
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
		setDate();
	}
}
