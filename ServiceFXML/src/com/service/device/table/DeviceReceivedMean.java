package com.service.device.table;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.service.device.DeviceClient;
import com.service.device.fillteringdb.DeviceFillteringDB;
import com.service.setting.combobox.Combobox;
import com.service.setting.export.CreatingPdf;

import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DeviceReceivedMean implements Initializable {
	@FXML
	private TableView<DeviceClient> deviceTableMean;
	@FXML
	private TextField deviceClientNameFilteringTxt;


	private TableColumn<DeviceClient, Integer> deviceTableNumber;
	private TableColumn<DeviceClient, Boolean> deviceTableNewHouse, deviceTablePowerSupply, deviceTableProcessor,
			deviceTableBaseBoard, deviceTableMemory, deviceTableVideoCard, deviceTableSSDDrive, deviceTableHardDrive,
			deviceTableCoolingFan, deviceTableOpticalDrive, deviceTableExpansionCard, deviceTableLaptop;
	private TableColumn<DeviceClient, String> clientTableNumber, clientTableSettlement, clientTableName,
			clientTableZipCode, clientTableAddress, clientTablePhone, deviceTableCompanyName, deviceTableName,
			deviceTabelManufacturer, deviceTabelSerialNumber, deviceTableRepairLocation, deviceTableStatus,
			deviceTableNewMachine, deviceTableAdministrator, deviceTablePriorit, deviceTablePassword,
			deviceTableReferences, deviceTableAccesssory, deviceTableInjury, deviceTableErrorDescription,
			deviceTableComment, deviceTableDataRecovery, deviceTableSoftver, deviceTableOperatingSystem,
			deviceTableSoftverComment, deviceTableErrorCorrection, deviceTableTechnicalPerson, deviceTableStatusz;
	private TableColumn<DeviceClient, Date> deviceTableSalesBuying, deviceTableAddDate, deviceTableEndDate,
			deviceTableDeliveryDate, deviceTbaleCompletedDate;
	private final ObservableList<DeviceClient> dataDeviceClient = FXCollections.observableArrayList();
	private DeviceFillteringDB deviceDb = new DeviceFillteringDB();
	private TrayNotification tray = new TrayNotification();

	private String clientName, clientZipCode, clientSettlement, clientAddress, clientPhone, clientNumber, deviceName,
			deviceNumber, deviceManufacturer, deviceSalesBuying, deviceAddDate, deviceEndDate, devicePassword,
			deviceAccesssory, deviceInjury, deviceErrorDescription, deviceDataRecovery;

	@FXML
	private void exportList(ActionEvent event) {

		if (clientName != null && !clientName.equals("")) {
			CreatingPdf pdfCreator = new CreatingPdf();
			pdfCreator.creating(clientName, clientZipCode, clientSettlement, clientAddress, clientPhone, clientNumber,
					deviceNumber, deviceSalesBuying, deviceAddDate, deviceEndDate, deviceName, deviceManufacturer,
					devicePassword, deviceAccesssory, deviceInjury, deviceErrorDescription, deviceDataRecovery);
		} else {
			tray = new TrayNotification("HIBA", "Nincs kiválasztva semmi", NotificationType.ERROR);
			tray.showAndDismiss(Duration.seconds(2));
		}
	}

	@SuppressWarnings("unchecked")
	protected void setDeviceTableData() {
		

		clientTableNumber = new TableColumn<>("Azonosító");
		clientTableNumber.setMinWidth(90);
		clientTableNumber.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("clientNumber"));

		clientTableName = new TableColumn<>("Ügyfél");
		clientTableName.setMinWidth(150);
		clientTableName.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceClientName"));

		deviceTableCompanyName = new TableColumn<>("Cég");
		deviceTableCompanyName.setMinWidth(200);
		deviceTableCompanyName.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceCompanyName"));

		clientTableZipCode = new TableColumn<>("Irányítószám");
		clientTableZipCode.setMinWidth(40);
		clientTableZipCode.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("clientZipCode"));

		clientTableSettlement = new TableColumn<>("Település");
		clientTableSettlement.setMinWidth(100);
		clientTableSettlement.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("clientSettlement"));

		clientTableAddress = new TableColumn<>("Cím");
		clientTableAddress.setMinWidth(200);
		clientTableAddress.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("clientAddress"));

		clientTablePhone = new TableColumn<>("Telefon");
		clientTablePhone.setMinWidth(140);
		clientTablePhone.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("clientPhone"));

		deviceTableNumber = new TableColumn<>("Azonosító");
		deviceTableNumber.setMinWidth(50);
		deviceTableNumber.setCellValueFactory(new PropertyValueFactory<DeviceClient, Integer>("deviceNumber"));

		deviceTableName = new TableColumn<>("Eszköz");
		deviceTableName.setMinWidth(150);
		deviceTableName.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceName"));

		deviceTabelManufacturer = new TableColumn<>("Gyártó");
		deviceTabelManufacturer.setMinWidth(150);
		deviceTabelManufacturer
				.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceManufacturer"));

		deviceTabelSerialNumber = new TableColumn<>("Serial no.");
		deviceTabelSerialNumber.setMinWidth(120);
		deviceTabelSerialNumber
				.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceSerialNumber"));

		deviceTableRepairLocation = new TableColumn<>("Javítás helye");
		deviceTableRepairLocation.setMinWidth(100);
		deviceTableRepairLocation
				.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceRepairLocation"));

		deviceTableStatus = new TableColumn<>("Állapot*");
		deviceTableStatus.setMinWidth(100);
		deviceTableStatus.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceStatus"));
		deviceTableStatus.setCellValueFactory(i -> {
			final String value = i.getValue().getDeviceStatus();
			return Bindings.createObjectBinding(() -> value);
		});
		deviceTableStatus.setCellFactory(ComboBoxTableCell.forTableColumn(Combobox.setDeviceStaCombobox()));
		deviceTableStatus.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<DeviceClient, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<DeviceClient, String> d) {
				DeviceClient actualDevice = (DeviceClient) d.getTableView().getItems()
						.get(d.getTablePosition().getRow());
				actualDevice.setDeviceStatus(d.getNewValue());
				deviceDb.updateDeviceClient(actualDevice);
				updateDeviceTableDate();
				tray = new TrayNotification("Állapot!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});

		deviceTableStatusz = new TableColumn<>("Státusz");
		deviceTableStatusz.setMinWidth(140);
		deviceTableStatusz.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceStatusz"));

		deviceTableNewMachine = new TableColumn<>("Új gép");
		deviceTableNewMachine.setMinWidth(30);
		deviceTableNewMachine.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceNewMachine"));

		deviceTableAdministrator = new TableColumn<>("Ügyintéző");
		deviceTableAdministrator.setMinWidth(70);
		deviceTableAdministrator
				.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceAdministrator"));

		deviceTableTechnicalPerson = new TableColumn<>("Technikus");
		deviceTableTechnicalPerson.setMinWidth(100);
		deviceTableTechnicalPerson.setCellValueFactory(i -> {
			final String value = i.getValue().getDeviceTechnicalPerson();
			return Bindings.createObjectBinding(() -> value);
		});

		deviceTablePriorit = new TableColumn<>("Prioritás");
		deviceTablePriorit.setMinWidth(70);
		deviceTablePriorit.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("devicePriorit"));

		deviceTablePassword = new TableColumn<>("Jelszó");
		deviceTablePassword.setMinWidth(70);
		deviceTablePassword.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("devicePassword"));

		deviceTableReferences = new TableColumn<>("Hivatkozási szám");
		deviceTableReferences.setMinWidth(70);
		deviceTableReferences.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceReferences"));

		deviceTableAccesssory = new TableColumn<>("Tartozékok");
		deviceTableAccesssory.setMinWidth(170);
		deviceTableAccesssory.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceAccesssory"));

		deviceTableInjury = new TableColumn<>("Sérülés");
		deviceTableInjury.setMinWidth(170);
		deviceTableInjury.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceInjury"));

		deviceTableErrorDescription = new TableColumn<>("Hiba leírás");
		deviceTableErrorDescription.setMinWidth(370);
		deviceTableErrorDescription
				.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceErrorDescription"));

		deviceTableErrorCorrection = new TableColumn<>("Valós hiba");
		deviceTableErrorCorrection.setMinWidth(370);
		deviceTableErrorCorrection
				.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceErrorCorrection"));

		deviceTableComment = new TableColumn<>("Eszközről megjegyzés");
		deviceTableComment.setMinWidth(370);
		deviceTableComment.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceComment"));

		deviceTableSalesBuying = new TableColumn<>("Vásárlási");
		deviceTableSalesBuying.setMinWidth(70);
		deviceTableSalesBuying.setCellValueFactory(new PropertyValueFactory<DeviceClient, Date>("deviceSalesBuying"));

		deviceTableAddDate = new TableColumn<>("Bejelentés");
		deviceTableAddDate.setMinWidth(70);
		deviceTableAddDate.setCellValueFactory(new PropertyValueFactory<DeviceClient, Date>("deviceAddDate"));

		deviceTableEndDate = new TableColumn<>("Határidő");
		deviceTableEndDate.setMinWidth(70);
		deviceTableEndDate.setCellValueFactory(cellData -> cellData.getValue().getDeviceEndDateObject());

		deviceTableDeliveryDate = new TableColumn<>("Kiszállás");
		deviceTableDeliveryDate.setMinWidth(140);
		deviceTableDeliveryDate.setCellValueFactory(cellData -> cellData.getValue().getDeviceDeliveryDateObject());

		deviceTbaleCompletedDate = new TableColumn<>("Elkészült");
		deviceTbaleCompletedDate.setMinWidth(140);
		deviceTbaleCompletedDate.setCellValueFactory(cellData -> cellData.getValue().getDeviceCompletedDateObject());

		deviceTableDataRecovery = new TableColumn<>("Adatmentés");
		deviceTableDataRecovery.setMinWidth(50);
		deviceTableDataRecovery
				.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceDataRecovery"));

		deviceTableSoftver = new TableColumn<>("Szoftver");
		deviceTableSoftver.setMinWidth(50);
		deviceTableSoftver.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceSoftver"));

		deviceTableOperatingSystem = new TableColumn<>("Operációs rendszer");
		deviceTableOperatingSystem.setMinWidth(170);
		deviceTableOperatingSystem
				.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceOperatingSystem"));

		deviceTableSoftverComment = new TableColumn<>("Szoftver Megjegyzés");
		deviceTableSoftverComment.setMinWidth(170);
		deviceTableSoftverComment
				.setCellValueFactory(new PropertyValueFactory<DeviceClient, String>("deviceSoftverComment"));

		deviceTableNewHouse = new TableColumn<>("Ház");
		deviceTableNewHouse.setEditable(false);
		deviceTableNewHouse.setMinWidth(48);
		deviceTableNewHouse
				.setCellFactory(new Callback<TableColumn<DeviceClient, Boolean>, TableCell<DeviceClient, Boolean>>() {
					@Override
					public TableCell<DeviceClient, Boolean> call(TableColumn<DeviceClient, Boolean> p) {
						CheckBoxTableCell<DeviceClient, Boolean> cell = new CheckBoxTableCell<DeviceClient, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		deviceTableNewHouse
				.setCellValueFactory(new Callback<CellDataFeatures<DeviceClient, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<DeviceClient, Boolean> param) {
						DeviceClient device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceNewHouse());
						return booleanProp;
					}
				});

		deviceTablePowerSupply = new TableColumn<>("Tápegység");
		deviceTablePowerSupply.setEditable(false);
		deviceTablePowerSupply.setMinWidth(48);
		deviceTablePowerSupply
				.setCellFactory(new Callback<TableColumn<DeviceClient, Boolean>, TableCell<DeviceClient, Boolean>>() {
					@Override
					public TableCell<DeviceClient, Boolean> call(TableColumn<DeviceClient, Boolean> p) {
						CheckBoxTableCell<DeviceClient, Boolean> cell = new CheckBoxTableCell<DeviceClient, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		deviceTablePowerSupply
				.setCellValueFactory(new Callback<CellDataFeatures<DeviceClient, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<DeviceClient, Boolean> param) {
						DeviceClient device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDevicePowerSupply());
						return booleanProp;
					}
				});

		deviceTableProcessor = new TableColumn<>("Processzor");
		deviceTableProcessor.setEditable(false);
		deviceTableProcessor.setMinWidth(48);
		deviceTableProcessor
				.setCellFactory(new Callback<TableColumn<DeviceClient, Boolean>, TableCell<DeviceClient, Boolean>>() {
					@Override
					public TableCell<DeviceClient, Boolean> call(TableColumn<DeviceClient, Boolean> p) {
						CheckBoxTableCell<DeviceClient, Boolean> cell = new CheckBoxTableCell<DeviceClient, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		deviceTableProcessor
				.setCellValueFactory(new Callback<CellDataFeatures<DeviceClient, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<DeviceClient, Boolean> param) {
						DeviceClient device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceProcessor());
						return booleanProp;
					}
				});

		deviceTableBaseBoard = new TableColumn<>("Alaplap");
		deviceTableBaseBoard.setEditable(false);
		deviceTableBaseBoard.setMinWidth(48);
		deviceTableBaseBoard
				.setCellFactory(new Callback<TableColumn<DeviceClient, Boolean>, TableCell<DeviceClient, Boolean>>() {
					@Override
					public TableCell<DeviceClient, Boolean> call(TableColumn<DeviceClient, Boolean> p) {
						CheckBoxTableCell<DeviceClient, Boolean> cell = new CheckBoxTableCell<DeviceClient, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		deviceTableBaseBoard
				.setCellValueFactory(new Callback<CellDataFeatures<DeviceClient, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<DeviceClient, Boolean> param) {
						DeviceClient device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceBaseBoard());
						return booleanProp;
					}
				});

		deviceTableMemory = new TableColumn<>("Memória");
		deviceTableMemory.setEditable(false);
		deviceTableMemory.setMinWidth(48);
		deviceTableMemory
				.setCellFactory(new Callback<TableColumn<DeviceClient, Boolean>, TableCell<DeviceClient, Boolean>>() {
					@Override
					public TableCell<DeviceClient, Boolean> call(TableColumn<DeviceClient, Boolean> p) {
						CheckBoxTableCell<DeviceClient, Boolean> cell = new CheckBoxTableCell<DeviceClient, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		deviceTableMemory
				.setCellValueFactory(new Callback<CellDataFeatures<DeviceClient, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<DeviceClient, Boolean> param) {
						DeviceClient device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceMemory());
						return booleanProp;
					}
				});
		deviceTableVideoCard = new TableColumn<>("Videokártya");
		deviceTableVideoCard.setEditable(false);
		deviceTableVideoCard.setMinWidth(48);
		deviceTableVideoCard
				.setCellFactory(new Callback<TableColumn<DeviceClient, Boolean>, TableCell<DeviceClient, Boolean>>() {
					@Override
					public TableCell<DeviceClient, Boolean> call(TableColumn<DeviceClient, Boolean> p) {
						CheckBoxTableCell<DeviceClient, Boolean> cell = new CheckBoxTableCell<DeviceClient, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		deviceTableVideoCard
				.setCellValueFactory(new Callback<CellDataFeatures<DeviceClient, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<DeviceClient, Boolean> param) {
						DeviceClient device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceVideoCard());
						return booleanProp;
					}
				});
		deviceTableSSDDrive = new TableColumn<>("SSD");
		deviceTableSSDDrive.setEditable(false);
		deviceTableSSDDrive.setMinWidth(48);
		deviceTableSSDDrive
				.setCellFactory(new Callback<TableColumn<DeviceClient, Boolean>, TableCell<DeviceClient, Boolean>>() {
					@Override
					public TableCell<DeviceClient, Boolean> call(TableColumn<DeviceClient, Boolean> p) {
						CheckBoxTableCell<DeviceClient, Boolean> cell = new CheckBoxTableCell<DeviceClient, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		deviceTableSSDDrive
				.setCellValueFactory(new Callback<CellDataFeatures<DeviceClient, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<DeviceClient, Boolean> param) {
						DeviceClient device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceSSDDrive());
						return booleanProp;
					}
				});
		deviceTableHardDrive = new TableColumn<>("Merevlemez");
		deviceTableHardDrive.setEditable(false);
		deviceTableHardDrive.setMinWidth(48);
		deviceTableHardDrive
				.setCellFactory(new Callback<TableColumn<DeviceClient, Boolean>, TableCell<DeviceClient, Boolean>>() {
					@Override
					public TableCell<DeviceClient, Boolean> call(TableColumn<DeviceClient, Boolean> p) {
						CheckBoxTableCell<DeviceClient, Boolean> cell = new CheckBoxTableCell<DeviceClient, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		deviceTableHardDrive
				.setCellValueFactory(new Callback<CellDataFeatures<DeviceClient, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<DeviceClient, Boolean> param) {
						DeviceClient device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceHardDrive());
						return booleanProp;
					}
				});
		deviceTableCoolingFan = new TableColumn<>("Hűtőventillátor");
		deviceTableCoolingFan.setEditable(false);
		deviceTableCoolingFan.setMinWidth(48);
		deviceTableCoolingFan
				.setCellFactory(new Callback<TableColumn<DeviceClient, Boolean>, TableCell<DeviceClient, Boolean>>() {
					@Override
					public TableCell<DeviceClient, Boolean> call(TableColumn<DeviceClient, Boolean> p) {
						CheckBoxTableCell<DeviceClient, Boolean> cell = new CheckBoxTableCell<DeviceClient, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		deviceTableCoolingFan
				.setCellValueFactory(new Callback<CellDataFeatures<DeviceClient, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<DeviceClient, Boolean> param) {
						DeviceClient device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceCoolingFan());
						return booleanProp;
					}
				});
		deviceTableOpticalDrive = new TableColumn<>("Optikai");
		deviceTableOpticalDrive.setEditable(false);
		deviceTableOpticalDrive.setMinWidth(48);
		deviceTableOpticalDrive
				.setCellFactory(new Callback<TableColumn<DeviceClient, Boolean>, TableCell<DeviceClient, Boolean>>() {
					@Override
					public TableCell<DeviceClient, Boolean> call(TableColumn<DeviceClient, Boolean> p) {
						CheckBoxTableCell<DeviceClient, Boolean> cell = new CheckBoxTableCell<DeviceClient, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		deviceTableOpticalDrive
				.setCellValueFactory(new Callback<CellDataFeatures<DeviceClient, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<DeviceClient, Boolean> param) {
						DeviceClient device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceOpticalDrive());
						return booleanProp;
					}
				});
		deviceTableExpansionCard = new TableColumn<>("Bővítőkártya");
		deviceTableExpansionCard.setEditable(false);
		deviceTableExpansionCard.setMinWidth(48);
		deviceTableExpansionCard
				.setCellFactory(new Callback<TableColumn<DeviceClient, Boolean>, TableCell<DeviceClient, Boolean>>() {
					@Override
					public TableCell<DeviceClient, Boolean> call(TableColumn<DeviceClient, Boolean> p) {
						CheckBoxTableCell<DeviceClient, Boolean> cell = new CheckBoxTableCell<DeviceClient, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		deviceTableExpansionCard
				.setCellValueFactory(new Callback<CellDataFeatures<DeviceClient, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<DeviceClient, Boolean> param) {
						DeviceClient device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceExpansionCard());
						return booleanProp;
					}
				});
		deviceTableLaptop = new TableColumn<>("Laptop");
		deviceTableLaptop.setEditable(false);
		deviceTableLaptop.setMinWidth(48);
		deviceTableLaptop
				.setCellFactory(new Callback<TableColumn<DeviceClient, Boolean>, TableCell<DeviceClient, Boolean>>() {
					@Override
					public TableCell<DeviceClient, Boolean> call(TableColumn<DeviceClient, Boolean> p) {
						CheckBoxTableCell<DeviceClient, Boolean> cell = new CheckBoxTableCell<DeviceClient, Boolean>();
						cell.setAlignment(Pos.CENTER);
						return cell;
					}
				});
		deviceTableLaptop
				.setCellValueFactory(new Callback<CellDataFeatures<DeviceClient, Boolean>, ObservableValue<Boolean>>() {
					@Override
					public ObservableValue<Boolean> call(CellDataFeatures<DeviceClient, Boolean> param) {
						DeviceClient device = param.getValue();
						SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(device.getDeviceLaptop());
						return booleanProp;
					}
				});
		deviceTableMean.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DeviceClient>() {
			@Override
			public void changed(ObservableValue<? extends DeviceClient> observable, DeviceClient oldValue,
					DeviceClient newValue) {
				if (oldValue == null || newValue != null) {
					clientName = newValue.getDeviceClientName();
					clientZipCode = newValue.getClientZipCode();
					clientAddress = newValue.getClientAddress();
					clientSettlement = newValue.getClientSettlement();
					clientPhone = newValue.getClientPhone();
					clientNumber = newValue.getClientNumber();
					deviceName = newValue.getDeviceName();
					deviceNumber = newValue.getDeviceNumber();
					deviceManufacturer = newValue.getDeviceManufacturer();
					deviceSalesBuying = newValue.getDeviceSalesBuyingConverter();
					deviceAddDate = newValue.getDeviceAddDateConverter();
					deviceEndDate = newValue.getDeviceEndDateConverter();
					devicePassword = newValue.getDevicePassword();
					deviceAccesssory = newValue.getDeviceAccesssory();
					deviceInjury = newValue.getDeviceInjury();
					deviceErrorDescription = newValue.getDeviceErrorDescription();
					deviceDataRecovery = newValue.getDeviceDataRecovery();
				}
			}
		});

		deviceTableMean.setItems(dataDeviceClient);
		deviceTableMean.getColumns().addAll(clientTableNumber, clientTableName, deviceTableCompanyName,
				clientTableZipCode, clientTableSettlement, clientTableAddress, clientTablePhone, deviceTableNumber,
				deviceTableName, deviceTabelManufacturer, deviceTabelSerialNumber, deviceTableRepairLocation,
				deviceTableStatus, deviceTableStatusz, deviceTableNewMachine, deviceTableAdministrator,
				deviceTableTechnicalPerson, deviceTablePriorit, deviceTablePassword, deviceTableReferences,
				deviceTableAccesssory, deviceTableInjury, deviceTableErrorDescription, deviceTableErrorCorrection,
				deviceTableComment, deviceTableSalesBuying, deviceTableAddDate, deviceTableEndDate,
				deviceTableDeliveryDate, deviceTbaleCompletedDate, deviceTableDataRecovery, deviceTableSoftver,
				deviceTableOperatingSystem, deviceTableSoftverComment, deviceTableNewHouse, deviceTablePowerSupply,
				deviceTableProcessor, deviceTableBaseBoard, deviceTableMemory, deviceTableVideoCard,
				deviceTableSSDDrive, deviceTableHardDrive, deviceTableCoolingFan, deviceTableOpticalDrive,
				deviceTableExpansionCard, deviceTableLaptop);
		dataDeviceClient.addAll(DeviceFillteringDB.getAllDeviceClient());

	}

	private void updateDeviceTableDate() {
		dataDeviceClient.clear();
		dataDeviceClient.addAll(DeviceFillteringDB.getAllDeviceClient());
	}

	@FXML
	private void updateDeviceBtn(ActionEvent event) {
		deviceTableMean.getItems().clear();
		dataDeviceClient.clear();
		dataDeviceClient.addAll(DeviceFillteringDB.getAllDeviceClient());
		tray = new TrayNotification("Remek!", "Sikeres Frissítés", NotificationType.SUCCESS);
		tray.showAndDismiss(Duration.seconds(1));
		deviceClientNameFilteringTxt.setStyle("-fx-prompt-text-fill: #61a2b1");
	}

	@FXML
	private void filteringClientBtn(ActionEvent event) {
		if (setDevicetCheckTxt()) {
			dataDeviceClient.clear();
			dataDeviceClient
					.addAll(DeviceFillteringDB.getAllDeviceClientFiltering(deviceClientNameFilteringTxt.getText()));
			deviceClientNameFilteringTxt.clear();
			deviceClientNameFilteringTxt.setStyle("-fx-prompt-text-fill: #61a2b1");
			tray = new TrayNotification("Remek!", "Sikeres Frissítés", NotificationType.SUCCESS);
			tray.showAndDismiss(Duration.seconds(1));
		} else {
			tray = new TrayNotification("HIBA", "Üres a kereső mező", NotificationType.ERROR);
			tray.showAndDismiss(Duration.seconds(2));
		}
	}

	@FXML
	private void filteringCompanyBtn(ActionEvent event) {
		if (setDevicetCheckTxt()) {
			dataDeviceClient.clear();
			dataDeviceClient
					.addAll(DeviceFillteringDB.getAllDeviceCompanyFiltering(deviceClientNameFilteringTxt.getText()));
			deviceClientNameFilteringTxt.clear();
			deviceClientNameFilteringTxt.setStyle("-fx-prompt-text-fill: #61a2b1");
			tray = new TrayNotification("Remek!", "Sikeres Frissítés", NotificationType.SUCCESS);
			tray.showAndDismiss(Duration.seconds(1));
		} else {
			tray = new TrayNotification("HIBA", "Üres a kereső mező", NotificationType.ERROR);
			tray.showAndDismiss(Duration.seconds(2));
		}
	}

	private boolean setDevicetCheckTxt() {
		if (deviceClientNameFilteringTxt.getText().trim().isEmpty()) {
			deviceClientNameFilteringTxt.setStyle("-fx-prompt-text-fill: #CC0033");
		}
		if (deviceClientNameFilteringTxt.getText().trim().isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDeviceTableData();

	}

}
