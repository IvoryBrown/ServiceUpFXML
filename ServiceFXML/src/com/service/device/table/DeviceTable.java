package com.service.device.table;

import java.net.URL;
import java.util.ResourceBundle;

import com.service.device.Device;
import com.service.device.controller.DeviceNewController;
import com.service.device.fillteringdb.DeviceFillteringDB;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Duration;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DeviceTable extends DeviceNewController implements Initializable {
	@FXML
	private TableView<Device> deviceAllTable;
	@FXML
	private TextField deviceClientNameFilteringTxt;
	private TableColumn<Device, Integer> deviceTableId, deviceTableNumber;
	private TableColumn<Device, String> deviceTableCompanyName, deviceTableClientName, deviceTableName,
			deviceTabelManufacturer, deviceTabelSerialNumber, deviceTableRepairLocation, deviceTableStatus,
			deviceTableNewMachine, deviceTableAdministrator, deviceTablePriorit, deviceTablePassword,
			deviceTableReferences, deviceTableAccesssory, deviceTableInjury, deviceTableErrorDescription,
			deviceTableComment;
	private final ObservableList<Device> dataDevice = FXCollections.observableArrayList();
	DeviceFillteringDB deviceDb = new DeviceFillteringDB();

	private final String CMBDEVICESTATUSS[] = { "Bevételezve", "Kiadva" };

	@SuppressWarnings("unchecked")
	protected void setDeviceTableData() {
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
		deviceTableName.setCellFactory(ComboBoxTableCell.forTableColumn(CMBDEVICENAME));
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

		deviceTabelSerialNumber = new TableColumn<>("Serial no.");
		deviceTabelSerialNumber.setMinWidth(120);
		deviceTabelSerialNumber.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceSerialNumber"));

		deviceTableRepairLocation = new TableColumn<>("Javítás helye*");
		deviceTableRepairLocation.setMinWidth(100);
		deviceTableRepairLocation.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceRepairLocation"));
		deviceTableRepairLocation.setCellValueFactory(i -> {
			final String value = i.getValue().getDeviceRepairLocation();
			return Bindings.createObjectBinding(() -> value);
		});
		deviceTableRepairLocation.setCellFactory(ComboBoxTableCell.forTableColumn(CMBDEVICEREPAIRLOCATION));
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

		deviceTableStatus = new TableColumn<>("Állapot*");
		deviceTableStatus.setMinWidth(100);
		deviceTableStatus.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceStatus"));
		deviceTableStatus.setCellValueFactory(i -> {
			final String value = i.getValue().getDeviceStatus();
			return Bindings.createObjectBinding(() -> value);
		});
		deviceTableStatus.setCellFactory(ComboBoxTableCell.forTableColumn(CMBDEVICESTATUSS));
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

		deviceTableNewMachine = new TableColumn<>("Új gép");
		deviceTableNewMachine.setMinWidth(30);
		deviceTableNewMachine.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceNewMachine"));

		deviceTableAdministrator = new TableColumn<>("Ügyintéző");
		deviceTableAdministrator.setMinWidth(70);
		deviceTableAdministrator.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceAdministrator"));

		deviceTablePriorit = new TableColumn<>("Prioritás");
		deviceTablePriorit.setMinWidth(70);
		deviceTablePriorit.setCellValueFactory(new PropertyValueFactory<Device, String>("devicePriorit"));

		deviceTablePassword = new TableColumn<>("Jelszó");
		deviceTablePassword.setMinWidth(70);
		deviceTablePassword.setCellValueFactory(new PropertyValueFactory<Device, String>("devicePassword"));

		deviceTableReferences = new TableColumn<>("Hivatkozási szám");
		deviceTableReferences.setMinWidth(70);
		deviceTableReferences.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceReferences"));

		deviceTableAccesssory = new TableColumn<>("Tartozékok*");
		deviceTableAccesssory.setMinWidth(170);
		deviceTableAccesssory.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceAccesssory"));
		deviceTableAccesssory.setCellFactory(TextFieldTableCell.forTableColumn());
		deviceTableAccesssory.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Device, String>>() {
			@Override
			public void handle(TableColumn.CellEditEvent<Device, String> d) {
				Device actualDvice = (Device) d.getTableView().getItems().get(d.getTablePosition().getRow());
				actualDvice.setDeviceAccesssory(d.getNewValue());
				deviceDb.updateDevice(actualDvice);
				tray = new TrayNotification("Tartozékok!", "Sikeres Frissítése", NotificationType.SUCCESS);
				tray.showAndDismiss(Duration.seconds(1));
			}
		});
		
		deviceTableInjury = new TableColumn<>("Sérülés");
		deviceTableInjury.setMinWidth(170);
		deviceTableInjury.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceInjury"));
		
		deviceTableErrorDescription = new TableColumn<>("Hiba leírás*");
		deviceTableErrorDescription.setMinWidth(370);
		deviceTableErrorDescription.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceErrorDescription"));
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
		
		deviceTableComment= new TableColumn<>("Eszközről megjegyzés*");
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
		
		

		deviceAllTable.setItems(dataDevice);
		deviceAllTable.getColumns().addAll(deviceTableId, deviceTableNumber, deviceTableCompanyName,
				deviceTableClientName, deviceTableName, deviceTabelManufacturer, deviceTabelSerialNumber,
				deviceTableRepairLocation, deviceTableStatus, deviceTableNewMachine, deviceTableAdministrator,
				deviceTablePriorit, deviceTablePassword, deviceTableReferences, deviceTableAccesssory,
				deviceTableInjury,deviceTableErrorDescription,deviceTableComment);
		dataDevice.addAll(DeviceFillteringDB.getAllDevice());
	}

	@FXML
	private void updateDeviceBtn(ActionEvent event) {
		dataDevice.clear();
		dataDevice.addAll(DeviceFillteringDB.getAllDevice());
		tray = new TrayNotification("Remek!", "Sikeres Frissítés", NotificationType.SUCCESS);
		tray.showAndDismiss(Duration.seconds(1));
		deviceClientNameFilteringTxt.setStyle("-fx-prompt-text-fill: #61a2b1");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDeviceTableData();
		setComboxAll();
		setClientTableData();
		setStockTableData();
		setComponentAll();
		setMenuData();

	}

}
