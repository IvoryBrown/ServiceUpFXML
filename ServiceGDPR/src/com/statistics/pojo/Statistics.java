package com.statistics.pojo;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Statistics {
	private final SimpleStringProperty deviceName;
	private final SimpleIntegerProperty deviceNumber;
	private final SimpleStringProperty devicePercent;

	public Statistics(String deviceName, int deviceNumber,String devicePercent ) {
		this.deviceName = new SimpleStringProperty(deviceName);
		this.deviceNumber = new SimpleIntegerProperty(deviceNumber);
		this.devicePercent = new SimpleStringProperty(devicePercent);
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
	public String getDevicePercent() {
		return this.devicePercent.get();
	}
	public void setDevicePercent(String devicePercent) {
		this.deviceName.set(devicePercent);
	}
}
