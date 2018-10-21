package com.calendar.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.calendar.setting.CalendarPane;
import com.device.table.controller.DeviceTableController;

import javafx.fxml.Initializable;

public class CalanderTableController extends DeviceTableController implements Initializable{
	
	@Override
	protected void setDeviceTableData() {
		this.setDataTable();
		super.setDeviceTableData();
	}
	
	@Override
	protected void setDataTable() {
		CalendarPane pane = new CalendarPane();
		deviceTable.setItems(pane.calendarNameList());
		pane.calendarNameList().clear();
		super.setDataTable();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.setDeviceTableData();
		
	}
	

}
