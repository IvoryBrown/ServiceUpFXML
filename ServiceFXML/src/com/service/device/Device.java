package com.service.device;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Device {
	private final SimpleStringProperty deviceID;
	private final SimpleBooleanProperty deviceLaptop;

	public Device(String dDeviceID, boolean dDeviceLaptop) {
		this.deviceID = new SimpleStringProperty(dDeviceID);
		this.deviceLaptop = new SimpleBooleanProperty(dDeviceLaptop);
	}

	public boolean setDeviceLaptop() {
		return deviceLaptop.get();
	}

	public void setDeviceLaptop(boolean setDeviceLaptop) {
		this.deviceLaptop.set(setDeviceLaptop);
	}

	public String getDeviceID() {
		return deviceID.get();
	}

	public void setDeviceID(String deviceID) {
		this.deviceID.set(deviceID);
	}

}
