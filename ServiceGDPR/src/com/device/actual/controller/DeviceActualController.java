package com.device.actual.controller;

import com.device.actual.database.DeviceActualDB;
import com.device.pojo.Device;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeviceActualController {
	public static boolean actual = false;

	public ObservableList<Device> calendarNameList(){
		ObservableList<Device> calendarNameList = FXCollections.observableArrayList();
		DeviceActualDB data = new DeviceActualDB();
		calendarNameList.addAll(data.getActualDevice());
		actual = false;
		return calendarNameList;
		
	}
}
