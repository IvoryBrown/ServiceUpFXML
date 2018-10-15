package com.device.table.controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.calendar.setting.CalendarPane;
import com.device.pojo.Device;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DeviceTableController implements Initializable{
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
private void setDeviceTableData(){
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
	
	CalendarPane pane = new CalendarPane();
	deviceTable.setItems(pane.calendarNameList());
	
	deviceTable.getColumns().addAll( deviceTableId, deviceTableClientID, deviceTableNumber,deviceTableCompanyName,
			deviceTableClientName);
}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setDeviceTableData();
		
	}

}
