package com.device.table.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.calendar.setting.CalendarPane;
import com.device.actual.controller.DeviceActualController;
import com.device.actual.database.DeviceActualDB;
import com.device.pojo.Device;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeviceTableController implements Initializable {
	@FXML
	private TableView<Device> deviceTable;
	private TableColumn<Device, Integer> deviceTableId, deviceTableClientID, deviceTableNumber;
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

	@SuppressWarnings("unchecked")
	private void setDeviceTableData() {
		deviceTableId = new TableColumn<>("ID");
		deviceTableId.setMinWidth(50);
		deviceTableId.setCellValueFactory(new PropertyValueFactory<Device, Integer>("deviceID"));

		deviceTableClientID = new TableColumn<>("ID CLient");
		deviceTableClientID.setMinWidth(50);
		deviceTableClientID.setCellValueFactory(new PropertyValueFactory<Device, Integer>("deviceClientID"));
		deviceTableClientID.setVisible(false);

		deviceTableNumber = new TableColumn<>("Azonosító");
		deviceTableNumber.setMinWidth(50);
		deviceTableNumber.setCellValueFactory(new PropertyValueFactory<Device, Integer>("deviceNumber"));

		deviceTableCompanyName = new TableColumn<>("Cég");
		deviceTableCompanyName.setMinWidth(200);
		deviceTableCompanyName.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceCompanyName"));

		deviceTableClientName = new TableColumn<>("Ügyfél");
		deviceTableClientName.setMinWidth(150);
		deviceTableClientName.setCellValueFactory(new PropertyValueFactory<Device, String>("deviceClientName"));
		// Data
		setDataTable();
		deviceTable.getColumns().addAll(deviceTableId, deviceTableClientID, deviceTableNumber, deviceTableCompanyName,
				deviceTableClientName);
	}

	private void setDataTable() {
		// CalendarPane pane = new CalendarPane();
		// System.out.println("Naptár 34 = "+pane.calendarNameList().size());
		// if (pane.calendarNameList().size() != 0) {
		// deviceTable.setItems(pane.calendarNameList());
		// System.out.println("Naptár"+pane.calendarNameList().size());
		// pane.calendarNameList().clear();
		// return;
		// }
		// DeviceActualController actual = new DeviceActualController();
		// if (actual.calendarNameList().size() != 0) {
		// deviceTable.setItems(actual.calendarNameList());
		// System.out.println("Act"+actual.calendarNameList().size());
		// actual.calendarNameList().clear();
		// return;
		// }

		ObservableList<Device> calendarNameList = FXCollections.observableArrayList();
		CalendarPane pane = new CalendarPane();
		calendarNameList.addAll(pane.calendarNameList());
		System.out.println("Naptár01 "+calendarNameList.size());
		if (calendarNameList.size() != 0) {
			ObservableList<Device> calendarNameList1 = FXCollections.observableArrayList();
			calendarNameList1.addAll(calendarNameList);
			deviceTable.setItems(calendarNameList1);
			System.out.println("Naptár1 "+calendarNameList1.size());
			System.out.println("Naptár0 "+calendarNameList.size());
			calendarNameList.clear();
			System.out.println("Naptár11 "+calendarNameList1.size());
			System.out.println("Naptár10 "+calendarNameList.size());
			return;
		}
		ObservableList<Device> actual = FXCollections.observableArrayList();
		DeviceActualDB data = new DeviceActualDB();
		actual.addAll(data.getActualDevice());
		if (actual.size() != 0) {
			ObservableList<Device> actual1 = FXCollections.observableArrayList();
			actual1.addAll(actual);
			deviceTable.setItems(actual1);
			actual.clear();
			return;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDeviceTableData();

	}

}
