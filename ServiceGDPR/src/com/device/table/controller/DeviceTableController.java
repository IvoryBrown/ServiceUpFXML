package com.device.table.controller;

import java.util.Date;

import com.device.pojo.Device;
import com.device.table.DateEditingCellDevice;
import com.device.table.DeviceButtonCell;
import com.device.table.database.DeviceDataBase;
import com.login.database.LoginDataBase;
import com.login.setting.setting.devicename.database.DeviceNameDataBase;
import com.login.setting.setting.location.database.LocationDataBase;
import com.setting.combobox.ComboBoxSet;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DeviceTableController {
	@FXML
	protected TableView<Device> deviceTable;
	@FXML
	protected AnchorPane anc;
	private TableColumn<Device, Integer> deviceTableId, deviceTableClientID;
	private TableColumn<Device, Boolean> deviceTableNewHouse, deviceTablePowerSupply, deviceTableProcessor,
			deviceTableBaseBoard, deviceTableMemory, deviceTableVideoCard, deviceTableSSDDrive, deviceTableHardDrive,
			deviceTableCoolingFan, deviceTableOpticalDrive, deviceTableExpansionCard, deviceTableLaptop,
			colDeviceAction;
	private TableColumn<Device, String> deviceTableCompanyName, deviceTableClientName, deviceTableName,
			deviceTableNumber, deviceTabelManufacturer, deviceTabelSerialNumber, deviceTableRepairLocation,
			deviceTableStatus, deviceTableNewMachine, deviceTableAdministrator, deviceTablePriorit, deviceTablePassword,
			deviceTableReferences, deviceTableAccesssory, deviceTableInjury, deviceTableErrorDescription,
			deviceTableComment, deviceTableDataRecovery, deviceTableSoftver, deviceTableOperatingSystem,
			deviceTableSoftverComment, deviceTableErrorCorrection, deviceTableTechnicalPerson, deviceTableStatusz,
			removeCol;
	private TableColumn<Device, Date> deviceTableSalesBuying, deviceTableAddDate, deviceTableEndDate,
			deviceTableDeliveryDate, deviceTbaleCompletedDate;
	private TableColumn<Device, String> setDeviceTablePerson;
	private TableColumn<Device, Boolean> setDeviceTableNewDevice;
	private TableColumn<Device, Date> setDeviceAllDate;
	@FXML
	private Label blackDeviceNumberT, backDeviceNumber, backClientNameT, backClientName;
	private DeviceDataBase deviceDb = new DeviceDataBase();
	private TrayNotification tray = new TrayNotification();

	@SuppressWarnings("unchecked")
	protected void setDeviceTableData() {
		Callback<TableColumn<Device, Date>, TableCell<Device, Date>> dateCellFactory = (
				TableColumn<Device, Date> param) -> new DateEditingCellDevice();

		colDeviceAction = new TableColumn<>("+");
		colDeviceAction.setSortable(false);
		colDeviceAction.setPrefWidth(40);
		colDeviceAction.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Device, Boolean> p) {
						return new SimpleBooleanProperty(p.getValue() != null);
					}
				});
		colDeviceAction.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>() {
			@Override
			public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
				return new DeviceButtonCell(deviceTable);
			}
		});

		deviceTableId = new TableColumn<>("ID");
		deviceTableId.setMinWidth(50);
		deviceTableId.setCellValueFactory(new PropertyValueFactory<Device, Integer>("deviceID"));

		deviceTableClientID = new TableColumn<>("ID CLient");
		deviceTableClientID.setMinWidth(50);
		deviceTableClientID.setCellValueFactory(new PropertyValueFactory<Device, Integer>("deviceClientID"));
		deviceTableClientID.setVisible(false);
		if (LoginDataBase.authority.equals("Admin")) {
			deviceTableClientID.setVisible(true);
		}

		deviceTableNumber = new TableColumn<>("Azonosító");
		deviceTableNumber.setMinWidth(50);
		deviceTableNumber.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceNumber"));
		if (LoginDataBase.authority.equals("Admin")) {
			deviceTableNumber.setCellFactory(TextFieldTableCell.forTableColumn());
			deviceTableNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, String> d) {
					Device device = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					device.setDeviceNumber(d.getNewValue());
					deviceDb.updateDevice(device);
					tray = new TrayNotification("Azonosító!", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}

		deviceTableCompanyName = new TableColumn<>("Cég");
		deviceTableCompanyName.setMinWidth(200);
		deviceTableCompanyName.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceCompanyName"));
		if (LoginDataBase.authority.equals("Admin")) {
			deviceTableCompanyName.setCellFactory(TextFieldTableCell.forTableColumn());
			deviceTableCompanyName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, String> d) {
					Device device = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					device.setDeviceCompanyName(d.getNewValue());
					deviceDb.updateDevice(device);
					tray = new TrayNotification("Cég!", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}

		deviceTableClientName = new TableColumn<>("Ügyfél");
		deviceTableClientName.setMinWidth(150);
		deviceTableClientName.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceClientName"));
		if (LoginDataBase.authority.equals("Admin")) {
			deviceTableClientName.setCellFactory(TextFieldTableCell.forTableColumn());
			deviceTableClientName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, String> d) {
					Device device = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					device.setDeviceClientName(d.getNewValue());
					deviceDb.updateDevice(device);
					tray = new TrayNotification("Ügyfél!", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}

		deviceTableName = new TableColumn<>("Eszköz*");
		deviceTableName.setMinWidth(150);
		deviceTableName.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceName"));
		if (LoginDataBase.authority.equals("Admin")) {

			deviceTableName.setCellValueFactory(i -> {
				final String value = i.getValue().getDeviceName();
				return Bindings.createObjectBinding(() -> value);
			});
			deviceTableName
					.setCellFactory(ComboBoxTableCell.forTableColumn(DeviceNameDataBase.administratorListComboBox));
			deviceTableName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, String> d) {
					Device device = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					device.setDeviceName(d.getNewValue());
					deviceDb.updateDevice(device);
					tray = new TrayNotification("Eszköz!", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}

		deviceTabelManufacturer = new TableColumn<>("Gyártó*");
		deviceTabelManufacturer.setMinWidth(150);
		deviceTabelManufacturer.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceManufacturer"));
		if (LoginDataBase.authority.equals("Admin")) {
			deviceTabelManufacturer.setCellFactory(TextFieldTableCell.forTableColumn());
			deviceTabelManufacturer.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, String> d) {
					Device device = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					device.setDeviceManufacturer(d.getNewValue());
					deviceDb.updateDevice(device);
					tray = new TrayNotification("Gyártó!", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}

		deviceTabelSerialNumber = new TableColumn<>("Serial no.");
		deviceTabelSerialNumber.setMinWidth(120);
		deviceTabelSerialNumber.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceSerialNumber"));
		if (LoginDataBase.authority.equals("Admin")) {
			deviceTabelSerialNumber.setCellFactory(TextFieldTableCell.forTableColumn());
			deviceTabelSerialNumber.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, String> d) {
					Device device = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					device.setDeviceSerialNumber(d.getNewValue());
					deviceDb.updateDevice(device);
					tray = new TrayNotification("Serial no.!", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}

		deviceTableRepairLocation = new TableColumn<>("Javítás helye*");
		deviceTableRepairLocation.setMinWidth(130);
		deviceTableRepairLocation.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceRepairLocation"));
		if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")) {
			deviceTableRepairLocation.setCellValueFactory(i -> {
				final String value = i.getValue().getDeviceRepairLocation();
				return Bindings.createObjectBinding(() -> value);
			});
			deviceTableRepairLocation
					.setCellFactory(ComboBoxTableCell.forTableColumn(LocationDataBase.locationListComboBox));
			deviceTableRepairLocation.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, String> d) {
					Device device = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					device.setDeviceRepairLocation(d.getNewValue());
					deviceDb.updateDevice(device);
					tray = new TrayNotification("Javítás helye!", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}

		deviceTableStatus = new TableColumn<>("Állapot*");
		deviceTableStatus.setMinWidth(100);
		deviceTableStatus.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceStatus"));
		if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
				|| LoginDataBase.authority.equals("User")) {
			deviceTableStatus.setCellValueFactory(i -> {
				final String value = i.getValue().getDeviceStatus();
				return Bindings.createObjectBinding(() -> value);
			});
			deviceTableStatus.setCellFactory(ComboBoxTableCell.forTableColumn(ComboBoxSet.setDeviceStaCombobox()));
			deviceTableStatus.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, String> d) {
					Device device = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					String setTechnicalPerson;
					String setErrorCorrection;
					setTechnicalPerson = device.getDeviceTechnicalPerson();
					setErrorCorrection = device.getDeviceErrorCorrection();
					if (device.getDeviceStatusz().equals("Bevizsgálva") && setTechnicalPerson != null
							&& setErrorCorrection != null) {
						device.setDeviceStatus(d.getNewValue());
						deviceDb.updateDevice(device);
						tray = new TrayNotification("Állapot!", "Sikeres Frissítése", NotificationType.SUCCESS);
						setDeviceTableData();
						tray.setAnimationType(AnimationType.POPUP);
						tray.showAndDismiss(Duration.seconds(2));
					} else {
						setDeviceTableData();
						tray = new TrayNotification("HIBA", "Még nincs minden mező kitöltve", NotificationType.ERROR);
						tray.setAnimationType(AnimationType.POPUP);
						tray.showAndDismiss(Duration.seconds(2));
					}
				}
			});
		}
		deviceTableStatusz = new TableColumn<>("Státusz*");
		deviceTableStatusz.setMinWidth(140);
		deviceTableStatusz.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceStatusz"));
		if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")) {
			deviceTableStatusz.setCellValueFactory(i -> {
				final String value = i.getValue().getDeviceStatusz();
				return Bindings.createObjectBinding(() -> value);
			});
			deviceTableStatusz.setCellFactory(ComboBoxTableCell.forTableColumn(ComboBoxSet.setDeviceStatusCombobox()));
			deviceTableStatusz.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, String> d) {
					Device device = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					device.setDeviceStatusz(d.getNewValue());
					deviceDb.updateDevice(device);
					tray = new TrayNotification("Státusz", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}

		deviceTableNewMachine = new TableColumn<>("Új gép");
		deviceTableNewMachine.setMinWidth(30);
		deviceTableNewMachine.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceNewMachine"));

		deviceTableAdministrator = new TableColumn<>("Ügyintéző");
		deviceTableAdministrator.setMinWidth(70);
		deviceTableAdministrator.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceAdministrator"));

		deviceTableTechnicalPerson = new TableColumn<>("Technikus*");
		deviceTableTechnicalPerson.setMinWidth(100);
		deviceTableTechnicalPerson.setCellValueFactory(i -> {
			final String value = i.getValue().getDeviceTechnicalPerson();
			return Bindings.createObjectBinding(() -> value);
		});
		if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")) {
			deviceTableTechnicalPerson.setCellFactory(ComboBoxTableCell.forTableColumn(LoginDataBase.name));
			deviceTableTechnicalPerson.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, String> d) {
					Device device = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					device.setDeviceTechnicalPerson(d.getNewValue());
					deviceDb.updateDevice(device);
					tray = new TrayNotification("Technikus!", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}

		setDeviceTablePerson = new TableColumn<>("Dolgozok");
		setDeviceTablePerson.getColumns().addAll(deviceTableAdministrator, deviceTableTechnicalPerson);

		deviceTablePriorit = new TableColumn<>("Prioritás");
		deviceTablePriorit.setMinWidth(70);
		deviceTablePriorit.setCellValueFactory(new PropertyValueFactory<Device, String>("devicePriorit"));

		deviceTablePassword = new TableColumn<>("Jelszó");
		deviceTablePassword.setMinWidth(70);
		deviceTablePassword.setCellValueFactory(new PropertyValueFactory<Device, String>("devicePassword"));

		deviceTableReferences = new TableColumn<>("Hivatkozási szám");
		deviceTableReferences.setMinWidth(70);
		deviceTableReferences.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceReferences"));

		deviceTableAccesssory = new TableColumn<>("Tartozékok");
		deviceTableAccesssory.setMinWidth(170);
		deviceTableAccesssory.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceAccesssory"));

		deviceTableInjury = new TableColumn<>("Sérülés");
		deviceTableInjury.setMinWidth(170);
		deviceTableInjury.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceInjury"));

		deviceTableErrorDescription = new TableColumn<>("Hiba leírás*");
		deviceTableErrorDescription.setMinWidth(370);
		deviceTableErrorDescription
				.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceErrorDescription"));
		if (LoginDataBase.authority.equals("Admin")) {
			deviceTableErrorDescription.setCellFactory(TextFieldTableCell.forTableColumn());
			deviceTableErrorDescription.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, String> d) {
					Device actualDvice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					actualDvice.setDeviceErrorDescription(d.getNewValue());
					deviceDb.updateDevice(actualDvice);
					tray = new TrayNotification("Hiba leírás!", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}

		deviceTableErrorCorrection = new TableColumn<>("Valós hiba*");
		deviceTableErrorCorrection.setMinWidth(370);
		deviceTableErrorCorrection
				.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceErrorCorrection"));
		if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")) {
			deviceTableErrorCorrection.setCellFactory(TextFieldTableCell.forTableColumn());
			deviceTableErrorCorrection.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, String> d) {
					Device actualDvice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					actualDvice.setDeviceErrorCorrection(d.getNewValue());
					deviceDb.updateDevice(actualDvice);
					tray = new TrayNotification("Valós hiba!", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}

		deviceTableComment = new TableColumn<>("Eszközről megjegyzés*");
		deviceTableComment.setMinWidth(370);
		deviceTableComment.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceComment"));
		if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
				|| LoginDataBase.authority.equals("User")) {
			deviceTableComment.setCellFactory(TextFieldTableCell.forTableColumn());
			deviceTableComment.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, String> d) {
					Device actualDvice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					actualDvice.setDeviceComment(d.getNewValue());
					deviceDb.updateDevice(actualDvice);
					tray = new TrayNotification("Eszközről megjegyzés", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}
		deviceTableSalesBuying = new TableColumn<>("Vásárlási");
		deviceTableSalesBuying.setMinWidth(70);
		deviceTableSalesBuying.setCellValueFactory(new PropertyValueFactory<Device, Date>("deviceSalesBuying"));

		deviceTableAddDate = new TableColumn<>("Bejelentés");
		deviceTableAddDate.setMinWidth(70);
		deviceTableAddDate.setCellValueFactory(new PropertyValueFactory<Device, Date>("deviceAddDate"));

		deviceTableEndDate = new TableColumn<>("Határidő*");
		deviceTableEndDate.setMinWidth(140);
		deviceTableEndDate.setCellValueFactory(cellData -> cellData.getValue().getDeviceEndDateObject());
		if (LoginDataBase.authority.equals("Admin")) {
			deviceTableEndDate.setCellFactory(dateCellFactory);
			deviceTableEndDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, Date>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, Date> d) {
					Device actualDevice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					actualDevice.setDeviceEndDate(d.getNewValue());
					deviceDb.updateDevice(actualDevice);
					tray = new TrayNotification("Határidő", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}

		deviceTableDeliveryDate = new TableColumn<>("Kiszállás*");
		deviceTableDeliveryDate.setMinWidth(140);
		deviceTableDeliveryDate.setCellValueFactory(cellData -> cellData.getValue().getDeviceDeliveryDateObject());
		if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
				|| LoginDataBase.authority.equals("User")) {
			deviceTableDeliveryDate.setCellFactory(dateCellFactory);
			deviceTableDeliveryDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, Date>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, Date> d) {
					Device actualDevice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					actualDevice.setDeviceDeliveryDate(d.getNewValue());
					deviceDb.updateDevice(actualDevice);
					tray = new TrayNotification("Kiszállás", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}
		deviceTbaleCompletedDate = new TableColumn<>("Elkészült*");
		deviceTbaleCompletedDate.setMinWidth(140);
		deviceTbaleCompletedDate.setCellValueFactory(cellData -> cellData.getValue().getDeviceCompletedDateObject());
		if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")) {
			deviceTbaleCompletedDate.setCellFactory(dateCellFactory);
			deviceTbaleCompletedDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, Date>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, Date> d) {
					Device actualDevice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					actualDevice.setDeviceCompletedDate(d.getNewValue());
					deviceDb.updateDevice(actualDevice);
					tray = new TrayNotification("Elkészült", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}

		setDeviceAllDate = new TableColumn<>("Dátumok");
		setDeviceAllDate.getColumns().addAll(deviceTableSalesBuying, deviceTableAddDate, deviceTableEndDate,
				deviceTableDeliveryDate, deviceTbaleCompletedDate);

		deviceTableDataRecovery = new TableColumn<>("Adatmentés");
		deviceTableDataRecovery.setMinWidth(50);
		deviceTableDataRecovery.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceDataRecovery"));

		deviceTableSoftver = new TableColumn<>("Szoftver");
		deviceTableSoftver.setMinWidth(50);
		deviceTableSoftver.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceSoftver"));

		deviceTableOperatingSystem = new TableColumn<>("Operációs rendszer ");
		deviceTableOperatingSystem.setMinWidth(170);
		deviceTableOperatingSystem
				.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceOperatingSystem"));

		deviceTableSoftverComment = new TableColumn<>("Szoftver Megjegyzés*");
		deviceTableSoftverComment.setMinWidth(170);
		deviceTableSoftverComment.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceSoftverComment"));
		if (LoginDataBase.authority.equals("Admin") || LoginDataBase.authority.equals("SuperUser")
				|| LoginDataBase.authority.equals("User")) {
			deviceTableSoftverComment.setCellFactory(TextFieldTableCell.forTableColumn());
			deviceTableSoftverComment.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
				@Override
				public void handle(TableColumn.CellEditEvent<Device, String> d) {
					Device actualDvice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
					actualDvice.setDeviceSoftverComment(d.getNewValue());
					deviceDb.updateDevice(actualDvice);
					tray = new TrayNotification("Szoftver Megjegyzés", "Sikeres Frissítése", NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.POPUP);
					tray.showAndDismiss(Duration.seconds(2));
					setDeviceTableData();
				}
			});
		}

		deviceTableNewHouse = new TableColumn<>("Ház");
		deviceTableNewHouse.setEditable(false);
		deviceTableNewHouse.setMinWidth(48);
		deviceTableNewHouse.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>() {
			@Override
			public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
				CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<Device, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		deviceTableNewHouse
				.setCellValueFactory(new Callback<CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Device, Boolean> param) {
						Device device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceNewHouse());
						return booleanProp;
					}
				});

		deviceTablePowerSupply = new TableColumn<>("Tápegység");
		deviceTablePowerSupply.setEditable(false);
		deviceTablePowerSupply.setMinWidth(48);
		deviceTablePowerSupply.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>() {
			@Override
			public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
				CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<Device, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		deviceTablePowerSupply
				.setCellValueFactory(new Callback<CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Device, Boolean> param) {
						Device device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDevicePowerSupply());
						return booleanProp;
					}
				});

		deviceTableProcessor = new TableColumn<>("Processzor");
		deviceTableProcessor.setEditable(false);
		deviceTableProcessor.setMinWidth(48);
		deviceTableProcessor.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>() {
			@Override
			public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
				CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<Device, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		deviceTableProcessor
				.setCellValueFactory(new Callback<CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Device, Boolean> param) {
						Device device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceProcessor());
						return booleanProp;
					}
				});

		deviceTableBaseBoard = new TableColumn<>("Alaplap");
		deviceTableBaseBoard.setEditable(false);
		deviceTableBaseBoard.setMinWidth(48);
		deviceTableBaseBoard.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>() {
			@Override
			public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
				CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<Device, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		deviceTableBaseBoard
				.setCellValueFactory(new Callback<CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Device, Boolean> param) {
						Device device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceBaseBoard());
						return booleanProp;
					}
				});

		deviceTableMemory = new TableColumn<>("Memória");
		deviceTableMemory.setEditable(false);
		deviceTableMemory.setMinWidth(48);
		deviceTableMemory.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>() {
			@Override
			public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
				CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<Device, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		deviceTableMemory
				.setCellValueFactory(new Callback<CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Device, Boolean> param) {
						Device device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceMemory());
						return booleanProp;
					}
				});
		deviceTableVideoCard = new TableColumn<>("Videokártya");
		deviceTableVideoCard.setEditable(false);
		deviceTableVideoCard.setMinWidth(48);
		deviceTableVideoCard.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>() {
			@Override
			public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
				CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<Device, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		deviceTableVideoCard
				.setCellValueFactory(new Callback<CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Device, Boolean> param) {
						Device device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceVideoCard());
						return booleanProp;
					}
				});
		deviceTableSSDDrive = new TableColumn<>("SSD");
		deviceTableSSDDrive.setEditable(false);
		deviceTableSSDDrive.setMinWidth(48);
		deviceTableSSDDrive.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>() {
			@Override
			public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
				CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<Device, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		deviceTableSSDDrive
				.setCellValueFactory(new Callback<CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Device, Boolean> param) {
						Device device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceSSDDrive());
						return booleanProp;
					}
				});
		deviceTableHardDrive = new TableColumn<>("Merevlemez");
		deviceTableHardDrive.setEditable(false);
		deviceTableHardDrive.setMinWidth(48);
		deviceTableHardDrive.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>() {
			@Override
			public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
				CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<Device, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		deviceTableHardDrive
				.setCellValueFactory(new Callback<CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Device, Boolean> param) {
						Device device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceHardDrive());
						return booleanProp;
					}
				});
		deviceTableCoolingFan = new TableColumn<>("Hűtőventillátor");
		deviceTableCoolingFan.setEditable(false);
		deviceTableCoolingFan.setMinWidth(48);
		deviceTableCoolingFan.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>() {
			@Override
			public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
				CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<Device, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		deviceTableCoolingFan
				.setCellValueFactory(new Callback<CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Device, Boolean> param) {
						Device device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceCoolingFan());
						return booleanProp;
					}
				});
		deviceTableOpticalDrive = new TableColumn<>("Optikai");
		deviceTableOpticalDrive.setEditable(false);
		deviceTableOpticalDrive.setMinWidth(48);
		deviceTableOpticalDrive
				.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>() {
					@Override
					public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
						CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<Device, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		deviceTableOpticalDrive
				.setCellValueFactory(new Callback<CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Device, Boolean> param) {
						Device device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceOpticalDrive());
						return booleanProp;
					}
				});
		deviceTableExpansionCard = new TableColumn<>("Bővítőkártya");
		deviceTableExpansionCard.setEditable(false);
		deviceTableExpansionCard.setMinWidth(48);
		deviceTableExpansionCard
				.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>() {
					@Override
					public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
						CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<Device, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		deviceTableExpansionCard
				.setCellValueFactory(new Callback<CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Device, Boolean> param) {
						Device device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceExpansionCard());
						return booleanProp;
					}
				});
		deviceTableLaptop = new TableColumn<>("Laptop");
		deviceTableLaptop.setEditable(false);
		deviceTableLaptop.setMinWidth(48);
		deviceTableLaptop.setCellFactory(new Callback<TableColumn<Device, Boolean>, TableCell<Device, Boolean>>() {
			@Override
			public TableCell<Device, Boolean> call(TableColumn<Device, Boolean> p) {
				CheckBoxTableCell<Device, Boolean> cell = new CheckBoxTableCell<Device, Boolean>();
				cell.setAlignment(Pos.CENTER);
				return cell;
			}
		});
		deviceTableLaptop
				.setCellValueFactory(new Callback<CellDataFeatures<Device, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<Device, Boolean> param) {
						Device device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceLaptop());
						return booleanProp;
					}
				});

		removeCol = new TableColumn<>("Törlés");
		removeCol.setMinWidth(100);
		Callback<TableColumn<Device, String>, TableCell<Device, String>> cellFactory = new Callback<TableColumn<Device, String>, TableCell<Device, String>>() {
			@Override
			public TableCell<Device, String> call(final TableColumn<Device, String> param) {
				final TableCell<Device, String> cell = new TableCell<Device, String>() {
					final Button btn = new Button("Törlés");

					@Override
					public void updateItem(String item, boolean empty) {
						btn.setDisable(true);
						if (LoginDataBase.authority.equals("Admin")) {
							btn.setDisable(false);
						}
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							btn.setOnAction((ActionEvent event) -> {
								Device device = getTableView().getItems().get(getIndex());
								deviceDb.removeContact(device);
								setDataTable();
								tray = new TrayNotification("Törlés!", "Sikeres Törlés", NotificationType.SUCCESS);
								tray.showAndDismiss(Duration.seconds(1));
							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};
		removeCol.setCellFactory(cellFactory);
		
		deviceTable.setRowFactory(ts -> new TableRow<Device>() {
			@Override
			public void updateItem(Device item, boolean empty) {
				super.updateItem(item, empty);

				if (item == null) {
					setStyle("");
				} else {
					setStyle("");
					if (item.getDeviceStatus().equals("Bevételezve")) {
						setStyle("-fx-text-background-color: tomato;");
					} else {
						setStyle("-fx-text-background-color: white;");
					}
					if (item.getDeviceStatusz() != null) {
						if (item.getDeviceStatusz().equals("Bevizsgálás alatt")
								|| item.getDeviceStatusz().equals("Akkatrészre vár")
								|| item.getDeviceStatusz().equals("Garanciális")
								|| item.getDeviceStatusz().equals("Továbbküldve")) {

							setStyle("-fx-text-background-color: #40e0d0;");
						} else if (item.getDeviceStatus().equals("Bevételezve")
								&& item.getDeviceStatusz().equals("Bevizsgálva")) {
							setStyle("-fx-text-background-color: #7cfc00;");
						} else {
							setStyle("-fx-text-background-color: white;");
						}
					}

				}
			}
		});

		blackDeviceNumberT.setStyle(" -fx-font-size: 15pt ;");
		backClientNameT.setStyle("-fx-font-size: 15pt ;");
		deviceTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Device>() {
			@Override
			public void changed(ObservableValue<? extends Device> observable, Device oldValue, Device newValue) {
				if (oldValue == null || newValue != null) {
					backClientName.setText(newValue.getDeviceClientName());
					backClientName.setStyle(" -fx-font-size: 15pt ;");
					backDeviceNumber.setText(newValue.getDeviceNumber());
					backDeviceNumber.setStyle(" -fx-font-size: 15pt ;");
				}
			}
		});

		setDeviceTableNewDevice = new TableColumn<>("Új gép");
		setDeviceTableNewDevice.getColumns().addAll(deviceTableNewHouse, deviceTablePowerSupply, deviceTableProcessor,
				deviceTableBaseBoard, deviceTableMemory, deviceTableVideoCard, deviceTableSSDDrive,
				deviceTableHardDrive, deviceTableCoolingFan, deviceTableOpticalDrive, deviceTableExpansionCard,
				deviceTableLaptop);

		deviceTable.getColumns().addAll(colDeviceAction, deviceTableId, deviceTableClientID, deviceTableNumber,
				deviceTableClientName, deviceTableCompanyName, deviceTableName, deviceTabelManufacturer,
				deviceTabelSerialNumber, deviceTableRepairLocation, deviceTableStatus, deviceTableStatusz,
				deviceTableNewMachine, setDeviceTablePerson, deviceTablePriorit, deviceTablePassword,
				deviceTableReferences, deviceTableAccesssory, deviceTableInjury, deviceTableErrorDescription,
				deviceTableErrorCorrection, deviceTableComment, setDeviceAllDate, deviceTableDataRecovery,
				deviceTableSoftver, deviceTableOperatingSystem, deviceTableSoftverComment, setDeviceTableNewDevice,
				removeCol);

	}

	protected void setDataTable() {

	}

}
