package com.service.statistics;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Statistics {
	private final SimpleStringProperty deviceName;
	private final SimpleIntegerProperty deviceNumber;

	public Statistics(String deviceName, int deviceNumber) {
		this.deviceName = new SimpleStringProperty(deviceName);
		this.deviceNumber = new SimpleIntegerProperty(deviceNumber);
	}
	public Integer getDeviceNumber() {
		return this.deviceNumber.get();
	}

	public void setDeviceNumber(Integer deviceNumber) {
		this.deviceNumber.set(deviceNumber);
	}
	public String getDeviceName() {
		return this.deviceName.get();
	}

	public void setDeviceName(String deviceName) {
		this.deviceName.set(deviceName);
	}
}
