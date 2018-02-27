package com.service.device;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Device {
	private final SimpleStringProperty deviceID;
	private final SimpleStringProperty deviceAdministrator;
	private final SimpleBooleanProperty deviceLaptop;

	public Device(String dDeviceID,String dDeviceAdministrator, boolean dDeviceLaptop) {
		this.deviceID = new SimpleStringProperty(dDeviceID);
		this.deviceAdministrator = new SimpleStringProperty(dDeviceAdministrator);
		this.deviceLaptop = new SimpleBooleanProperty(dDeviceLaptop);
	}

	public boolean getDeviceLaptop() {
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
	public String getDeviceAdministrator() {
		return deviceAdministrator.get();
	}
	
	public void setDeviceAdministrator(String dDeviceAdministrator) {
		this.deviceAdministrator.set(dDeviceAdministrator);
	}

}
