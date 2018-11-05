package com.device.information.pojo;

import java.sql.Blob;

import javafx.beans.property.SimpleStringProperty;

public class DeviceInfo {
	private final SimpleStringProperty deviceInfoID;
	private final SimpleStringProperty deviceNumber;
	private Blob html;

	public DeviceInfo(String deviceInfoID, String deviceNumber, Blob html) {
		this.deviceInfoID = new SimpleStringProperty(deviceInfoID);
		this.deviceNumber = new SimpleStringProperty(deviceNumber);
		this.html = html;
	}

	public String getDeviceInfoID() {
		return this.deviceInfoID.get();
	}

	public void setDeviceInfoID(String deviceInfoID) {
		this.deviceInfoID.set(deviceInfoID);
	}

	public String getDeviceNumber() {
		return this.deviceNumber.get();
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber.set(deviceNumber);
	}

	public Blob getHtml() {
		return this.html;
	}
}
