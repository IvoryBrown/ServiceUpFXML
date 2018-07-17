package com.login.setting.setting.devicename.pojo;

import javafx.beans.property.SimpleStringProperty;

public class DeviceName {
	private final SimpleStringProperty deviceNameId;
	private final SimpleStringProperty deviceName;

	public DeviceName(Integer deviceNameId, String deviceName) {
		this.deviceNameId = new SimpleStringProperty(String.valueOf(deviceNameId));
		this.deviceName = new SimpleStringProperty(deviceName);
	}

	public DeviceName(String deviceName) {
		this.deviceName = new SimpleStringProperty(deviceName);
		this.deviceNameId = new SimpleStringProperty("");
	}

	public String getDeviceNameId() {
		return this.deviceNameId.get();
	}

	public void setDeviceNameId(String deviceNameId) {
		this.deviceNameId.set(deviceNameId);
	}

	public String getDeviceName() {
		return this.deviceName.get();
	}

	public void setDeviceName(String deviceName) {
		this.deviceName.set(deviceName);
	}
}
