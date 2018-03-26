package com.service.device.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.LongAccumulator;

import com.service.device.Device;
import com.service.device.calendar.CalendarPane;
import com.service.device.fillteringdb.DeviceFillteringDB;
import com.service.device.table.DataEditingCellDevice;
import com.service.device.table.DeviceButtonCell;
import com.service.main.LoginController;
import com.service.setting.combobox.Combobox;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DeviceCalendarController implements Initializable {
	@FXML
	private TableView<Device> deviceTable;
	@FXML
	private TextField deviceClientNameFilteringTxt;
	private TableColumn<Device, Integer> deviceTableId, deviceTableNumber;
	private TableColumn<Device, Boolean> deviceTableNewHouse, deviceTablePowerSupply, deviceTableProcessor,
			deviceTableBaseBoard, deviceTableMemory, deviceTableVideoCard, deviceTableSSDDrive, deviceTableHardDrive,
			deviceTableCoolingFan, deviceTableOpticalDrive, deviceTableExpansionCard, deviceTableLaptop,
			colDeviceAction;
	private TableColumn<Device, String> deviceTableCompanyName, deviceTableClientName, deviceTableName,
			deviceTabelManufacturer, deviceTabelSerialNumber, deviceTableRepairLocation, deviceTableStatus,
			deviceTableNewMachine, deviceTableAdministrator, deviceTablePriorit, deviceTablePassword,
			deviceTableReferences, deviceTableAccesssory, deviceTableInjury, deviceTableErrorDescription,
			deviceTableComment, deviceTableDataRecovery, deviceTableSoftver, deviceTableOperatingSystem,
			deviceTableSoftverComment, deviceTableErrorCorrection, deviceTableTechnicalPerson, deviceTableStatusz,
			removeCol;
	private TableColumn<Device, Date> deviceTableSalesBuying, deviceTableAddDate, deviceTableEndDate,
			deviceTableDeliveryDate, deviceTbaleCompletedDate;
	private TableColumn<Device, String> setDeviceTablePerson;
	private TableColumn<Device, Boolean> setDeviceTableNewDevice;
	private TableColumn<Device, Date> setDeviceAllDate;
	private final ObservableList<Device> dataDevice = FXCollections.observableArrayList();
	private DeviceFillteringDB deviceDb = new DeviceFillteringDB();
	private LoginController login = new LoginController();
	private TrayNotification tray = new TrayNotification();
	

	private void setTable() {
		Callback<TableColumn<Device, Date>, TableCell<Device, Date>> dateCellFactory = (
				TableColumn<Device, Date> param) -> new DataEditingCellDevice();

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

		deviceTableNumber = new TableColumn<>("Azonosító");
		deviceTableNumber.setMinWidth(50);
		deviceTableNumber.setCellValueFactory(new PropertyValueFactory<Device, Integer>("deviceNumber"));

		deviceTableCompanyName = new TableColumn<>("Cég");
		deviceTableCompanyName.setMinWidth(200);
		deviceTableCompanyName.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceCompanyName"));

		deviceTableClientName = new TableColumn<>("Ügyfél");
		deviceTableClientName.setMinWidth(150);
		deviceTableClientName.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceClientName"));

		deviceTableName = new TableColumn<>("Eszköz*");
		deviceTableName.setMinWidth(150);
		deviceTableName.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceName"));

		deviceTableName.setCellValueFactory(i -> {
			final String value = i.getValue().getDeviceName();
			return Bindings.createObjectBinding(() -> value);
		});
		deviceTableName.setCellFactory(ComboBoxTableCell.forTableColumn(Combobox.setDeviceNameCombobox()));
		deviceTableName.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, String> d) {
				Device actualDevice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDevice.setDeviceName(d.getNewValue());
				deviceDb.updateDevice(actualDevice);
				tray = new TrayNotification("Eszköz!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));

			}
		});

		deviceTabelManufacturer = new TableColumn<>("Gyártó*");
		deviceTabelManufacturer.setMinWidth(150);
		deviceTabelManufacturer.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceManufacturer"));
		// if (login.admin.equals(login.adminLogin)) {
		deviceTabelManufacturer.setCellFactory(TextFieldTableCell.forTableColumn());
		deviceTabelManufacturer.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, String> d) {
				Device actualDvice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDvice.setDeviceManufacturer(d.getNewValue());
				deviceDb.updateDevice(actualDvice);
				tray = new TrayNotification("Gyártó!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));

			}
		});
		// }
		deviceTabelSerialNumber = new TableColumn<>("Serial no.");
		deviceTabelSerialNumber.setMinWidth(120);
		deviceTabelSerialNumber.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceSerialNumber"));

		deviceTableRepairLocation = new TableColumn<>("Javítás helye*");
		deviceTableRepairLocation.setMinWidth(130);
		deviceTableRepairLocation.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceRepairLocation"));
		// if (login.admin.equals(login.adminLogin)) {
		deviceTableRepairLocation.setCellValueFactory(i -> {
			final String value = i.getValue().getDeviceRepairLocation();
			return Bindings.createObjectBinding(() -> value);
		});
		deviceTableRepairLocation.setCellFactory(ComboBoxTableCell.forTableColumn(Combobox.setLocationCombobox()));
		deviceTableRepairLocation.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, String> d) {
				Device actualDevice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDevice.setDeviceRepairLocation(d.getNewValue());
				deviceDb.updateDevice(actualDevice);
				tray = new TrayNotification("Javítás helye!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));

			}
		});
		// }
		deviceTableStatus = new TableColumn<>("Állapot*");
		deviceTableStatus.setMinWidth(100);
		deviceTableStatus.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceStatus"));
		deviceTableStatus.setCellValueFactory(i -> {
			final String value = i.getValue().getDeviceStatus();
			return Bindings.createObjectBinding(() -> value);
		});
		deviceTableStatus.setCellFactory(ComboBoxTableCell.forTableColumn(Combobox.setDeviceStaCombobox()));
		deviceTableStatus.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, String> d) {
				Device actualDevice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDevice.setDeviceStatus(d.getNewValue());
				deviceDb.updateDevice(actualDevice);
				tray = new TrayNotification("Állapot!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));

			}
		});

		deviceTableStatusz = new TableColumn<>("Státusz*");
		deviceTableStatusz.setMinWidth(140);
		deviceTableStatusz.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceStatusz"));
		// if (login.admin.equals(login.adminLogin) ||
		// login.admin.equals(login.serviceLogin)) {
		deviceTableStatusz.setCellValueFactory(i -> {
			final String value = i.getValue().getDeviceStatusz();
			return Bindings.createObjectBinding(() -> value);
		});
		deviceTableStatusz.setCellFactory(ComboBoxTableCell.forTableColumn(Combobox.setDeviceStatusCombobox()));
		deviceTableStatusz.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, String> d) {
				Device actualDevice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDevice.setDeviceStatusz(d.getNewValue());
				deviceDb.updateDevice(actualDevice);
				tray = new TrayNotification("Állapot!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));

			}
		});
		// }
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
		// if (login.admin.equals(login.adminLogin) ||
		// login.admin.equals(login.serviceLogin)) {
		deviceTableTechnicalPerson.setCellFactory(ComboBoxTableCell.forTableColumn(deviceDb.technikalIstratorList));
		deviceTableTechnicalPerson.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, String> d) {
				Device actualDevice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDevice.setDeviceTechnicalPerson(d.getNewValue());
				deviceDb.updateDevice(actualDevice);
				tray = new TrayNotification("Technikus!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});
		// }
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
		// if (login.admin.equals(login.adminLogin)) {
		deviceTableErrorDescription.setCellFactory(TextFieldTableCell.forTableColumn());
		deviceTableErrorDescription.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, String> d) {
				Device actualDvice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDvice.setDeviceErrorDescription(d.getNewValue());
				deviceDb.updateDevice(actualDvice);
				tray = new TrayNotification("Hiba leírás!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});
		// }
		deviceTableErrorCorrection = new TableColumn<>("Valós hiba*");
		deviceTableErrorCorrection.setMinWidth(370);
		deviceTableErrorCorrection
				.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceErrorCorrection"));
		// if (login.admin.equals(login.adminLogin) ||
		// login.admin.equals(login.serviceLogin)) {
		deviceTableErrorCorrection.setCellFactory(TextFieldTableCell.forTableColumn());
		deviceTableErrorCorrection.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, String> d) {
				Device actualDvice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDvice.setDeviceErrorCorrection(d.getNewValue());
				deviceDb.updateDevice(actualDvice);
				tray = new TrayNotification("Valós hiba!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});
		// }
		deviceTableComment = new TableColumn<>("Eszközről megjegyzés*");
		deviceTableComment.setMinWidth(370);
		deviceTableComment.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceComment"));
		deviceTableComment.setCellFactory(TextFieldTableCell.forTableColumn());
		deviceTableComment.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, String> d) {
				Device actualDvice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDvice.setDeviceComment(d.getNewValue());
				deviceDb.updateDevice(actualDvice);
				tray = new TrayNotification("Eszközről megjegyzés!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});

		deviceTableSalesBuying = new TableColumn<>("Vásárlási");
		deviceTableSalesBuying.setMinWidth(70);
		deviceTableSalesBuying.setCellValueFactory(new PropertyValueFactory<Device, Date>("deviceSalesBuying"));

		deviceTableAddDate = new TableColumn<>("Bejelentés");
		deviceTableAddDate.setMinWidth(70);
		deviceTableAddDate.setCellValueFactory(new PropertyValueFactory<Device, Date>("deviceAddDate"));

		deviceTableEndDate = new TableColumn<>("Határidő*");
		deviceTableEndDate.setMinWidth(140);
		deviceTableEndDate.setCellValueFactory(cellData -> cellData.getValue().getDeviceEndDateObject());

		// if (login.admin.equals(login.adminLogin)) {
		deviceTableEndDate.setCellFactory(dateCellFactory);
		deviceTableEndDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, Date>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, Date> d) {
				Device actualDevice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDevice.setDeviceEndDate(d.getNewValue());
				deviceDb.updateDevice(actualDevice);
				tray = new TrayNotification("Határidő dátum!", "Sikeres Frissítés", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));

			}
		});
		// }
		deviceTableDeliveryDate = new TableColumn<>("Kiszállás*");
		deviceTableDeliveryDate.setMinWidth(140);
		deviceTableDeliveryDate.setCellValueFactory(cellData -> cellData.getValue().getDeviceDeliveryDateObject());
		deviceTableDeliveryDate.setCellFactory(dateCellFactory);
		deviceTableDeliveryDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, Date>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, Date> d) {
				Device actualDevice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDevice.setDeviceDeliveryDate(d.getNewValue());
				deviceDb.updateDevice(actualDevice);
				tray = new TrayNotification("Kiszállás dátum", "Sikeres Frissítés", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));

			}
		});
		deviceTbaleCompletedDate = new TableColumn<>("Elkészült*");
		deviceTbaleCompletedDate.setMinWidth(140);
		deviceTbaleCompletedDate.setCellValueFactory(cellData -> cellData.getValue().getDeviceCompletedDateObject());
		// if (login.admin.equals(login.adminLogin) ||
		// login.admin.equals(login.serviceLogin)) {
		deviceTbaleCompletedDate.setCellFactory(dateCellFactory);
		deviceTbaleCompletedDate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, Date>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, Date> d) {
				Device actualDevice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDevice.setDeviceCompletedDate(d.getNewValue());
				deviceDb.updateDevice(actualDevice);
				tray = new TrayNotification("Elkészült", "Sikeres Frissítés", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));

			}
		});
		// }
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
		// if (login.admin.equals(login.adminLogin) ||
		// login.admin.equals(login.serviceLogin)) {
		deviceTableSoftverComment.setCellFactory(TextFieldTableCell.forTableColumn());
		deviceTableSoftverComment.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, String> d) {
				Device actualDvice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDvice.setDeviceSoftverComment(d.getNewValue());
				deviceDb.updateDevice(actualDvice);
				tray = new TrayNotification("Szoftver Megjegyzés!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});
		// }

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
						// if (login.admin.equals(login.adminLogin)) {
						btn.setDisable(false);
						// }
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							btn.setOnAction((ActionEvent event) -> {
								Device device = getTableView().getItems().get(getIndex());
								dataDevice.remove(device);
								deviceDb.removeContact(device);
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
						setStyle("-fx-background-color: #669999;");
					}
					if (item.getDeviceStatusz() != null) {
						if (item.getDeviceStatusz().equals("Bevizsgálás alatt")
								|| item.getDeviceStatusz().equals("Akkatrészre vár")
								|| item.getDeviceStatusz().equals("Garanciális")
								|| item.getDeviceStatusz().equals("Továbbküldve")) {
							setStyle("-fx-background-color: #336666;");
						}
					}

				}

			}
		});

		setDeviceTableNewDevice = new TableColumn<>("Új gép");
		setDeviceTableNewDevice.getColumns().addAll(deviceTableNewHouse, deviceTablePowerSupply, deviceTableProcessor,
				deviceTableBaseBoard, deviceTableMemory, deviceTableVideoCard, deviceTableSSDDrive,
				deviceTableHardDrive, deviceTableCoolingFan, deviceTableOpticalDrive, deviceTableExpansionCard,
				deviceTableLaptop);

		deviceTable.setItems(dataDevice);
		deviceTable.getColumns().addAll(colDeviceAction, deviceTableId, deviceTableNumber, deviceTableClientName,
				deviceTableCompanyName, deviceTableName, deviceTabelManufacturer, deviceTabelSerialNumber,
				deviceTableRepairLocation, deviceTableStatus, deviceTableStatusz, deviceTableNewMachine,
				setDeviceTablePerson, deviceTablePriorit, deviceTablePassword, deviceTableReferences,
				deviceTableAccesssory, deviceTableInjury, deviceTableErrorDescription, deviceTableErrorCorrection,
				deviceTableComment, setDeviceAllDate, deviceTableDataRecovery, deviceTableSoftver,
				deviceTableOperatingSystem, deviceTableSoftverComment, setDeviceTableNewDevice, removeCol);
		setDataBase();
	}

	 
	public void setDataBase() {
		LocalDate date = null;
		CalendarPane date1 = new CalendarPane(date);
		String d = String.valueOf(date1);
		System.out.println(date1);
		dataDevice.addAll(DeviceFillteringDB.getDeviceNameCalendar(d));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setTable();

	}

}
