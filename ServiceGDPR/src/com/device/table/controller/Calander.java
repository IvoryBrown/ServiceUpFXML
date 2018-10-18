package com.device.table.controller;

import com.calendar.setting.CalendarPane;

public class Calander extends DeviceTableController {
	
	@Override
	protected void setDataTable() {
		CalendarPane pane = new CalendarPane();
		deviceTable.setItems(pane.calendarNameList());
		pane.calendarNameList().clear();
		super.setDataTable();
	}
	

}
