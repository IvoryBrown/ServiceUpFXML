package com.service.device;

import java.util.Date;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Device {
	private final SimpleStringProperty deviceID;
	private final SimpleStringProperty deviceNumber;
	private final SimpleStringProperty deviceCompanyName;
	private final SimpleStringProperty deviceClientName;
	private final SimpleStringProperty deviceName;
	private final SimpleStringProperty deviceManufacturer;
	private final SimpleStringProperty deviceSerialNumber;
	private final SimpleStringProperty deviceRepairLocation;
	private final SimpleStringProperty deviceStatus;
	private final SimpleStringProperty deviceNewMachine;
	private final SimpleStringProperty deviceAdministrator;
	private final SimpleStringProperty devicePriorit;
	private final SimpleStringProperty devicePassword;
	private final SimpleStringProperty deviceReferences;
	private final SimpleStringProperty deviceAccesssory;
	private final SimpleStringProperty deviceInjury;
	private final SimpleStringProperty deviceErrorDescription;
	private final SimpleStringProperty deviceComment;
	private final SimpleObjectProperty<Date> deviceSalesBuying;
	private final SimpleObjectProperty<Date> deviceAddDate;
	private final SimpleObjectProperty<Date> deviceEndDate;
	private final SimpleObjectProperty<Date> deviceDeliveryDate;
	private final SimpleStringProperty deviceDataRecovery;
	private final SimpleStringProperty deviceSoftver;
	private final SimpleStringProperty deviceOperatingSystem;
	private final SimpleStringProperty deviceSoftverComment;
	private final SimpleBooleanProperty deviceNewHouse;
	private final SimpleBooleanProperty devicePowerSupply;
	private final SimpleBooleanProperty deviceProcessor;
	private final SimpleBooleanProperty deviceBaseBoard;
	private final SimpleBooleanProperty deviceMemory;
	private final SimpleBooleanProperty deviceVideoCard;
	private final SimpleBooleanProperty deviceSSDDrive;
	private final SimpleBooleanProperty deviceHardDrive;
	private final SimpleBooleanProperty deviceCoolingFan;
	private final SimpleBooleanProperty deviceOpticalDrive;
	private final SimpleBooleanProperty deviceExpansionCard;
	private final SimpleBooleanProperty deviceLaptop;

	public Device(String deviceID, String deviceNumber, String deviceCompanyName, String deviceClientName,
			String deviceName, String deviceManufacturer, String deviceSerialNumber, String deviceRepairLocation,
			String deviceStatus, String deviceNewMachine, String deviceAdministrator, String devicePriorit,
			String devicePassword, String deviceReferences, String deviceAccesssory, String deviceInjury,
			String deviceErrorDescription, String deviceComment, Date deviceSalesBuying, Date deviceAddDate,
			Date deviceEndDate, Date deviceDeliveryDate, String deviceDataRecovery, String deviceSoftver,
			String deviceOperatingSystem, String deviceSoftverComment, boolean deviceNewHouse,
			boolean devicePowerSupply, boolean deviceProcessor, boolean deviceBaseBoard, boolean deviceMemory,
			boolean deviceVideoCard, boolean deviceSSDDrive, boolean deviceHardDrive, boolean deviceCoolingFan,
			boolean deviceOpticalDrive, boolean deviceExpansionCard, boolean deviceLaptop) {
		this.deviceID = new SimpleStringProperty(deviceID);
		this.deviceNumber = new SimpleStringProperty(deviceNumber);
		this.deviceCompanyName = new SimpleStringProperty(deviceCompanyName);
		this.deviceClientName = new SimpleStringProperty(deviceClientName);
		this.deviceName = new SimpleStringProperty(deviceName);
		this.deviceManufacturer = new SimpleStringProperty(deviceManufacturer);
		this.deviceSerialNumber = new SimpleStringProperty(deviceSerialNumber);
		this.deviceRepairLocation = new SimpleStringProperty(deviceRepairLocation);
		this.deviceStatus = new SimpleStringProperty(deviceStatus);
		this.deviceNewMachine = new SimpleStringProperty(deviceNewMachine);
		this.deviceAdministrator = new SimpleStringProperty(deviceAdministrator);
		this.devicePriorit = new SimpleStringProperty(devicePriorit);
		this.devicePassword = new SimpleStringProperty(devicePassword);
		this.deviceReferences = new SimpleStringProperty(deviceReferences);
		this.deviceAccesssory = new SimpleStringProperty(deviceAccesssory);
		this.deviceInjury = new SimpleStringProperty(deviceInjury);
		this.deviceErrorDescription = new SimpleStringProperty(deviceErrorDescription);
		this.deviceComment = new SimpleStringProperty(deviceComment);
		this.deviceSalesBuying = new SimpleObjectProperty<>(deviceSalesBuying);
		this.deviceAddDate = new SimpleObjectProperty<>(deviceAddDate);
		this.deviceEndDate = new SimpleObjectProperty<>(deviceEndDate);
		this.deviceDeliveryDate = new SimpleObjectProperty<>(deviceDeliveryDate);
		this.deviceDataRecovery = new SimpleStringProperty(deviceDataRecovery);
		this.deviceSoftver = new SimpleStringProperty(deviceSoftver);
		this.deviceOperatingSystem = new SimpleStringProperty(deviceOperatingSystem);
		this.deviceSoftverComment = new SimpleStringProperty(deviceSoftverComment);
		this.deviceNewHouse = new SimpleBooleanProperty(deviceNewHouse);
		this.devicePowerSupply = new SimpleBooleanProperty(devicePowerSupply);
		this.deviceProcessor = new SimpleBooleanProperty(deviceProcessor);
		this.deviceBaseBoard = new SimpleBooleanProperty(deviceBaseBoard);
		this.deviceMemory = new SimpleBooleanProperty(deviceMemory);
		this.deviceVideoCard = new SimpleBooleanProperty(deviceVideoCard);
		this.deviceSSDDrive = new SimpleBooleanProperty(deviceSSDDrive);
		this.deviceHardDrive = new SimpleBooleanProperty(deviceHardDrive);
		this.deviceCoolingFan = new SimpleBooleanProperty(deviceCoolingFan);
		this.deviceOpticalDrive = new SimpleBooleanProperty(deviceOpticalDrive);
		this.deviceExpansionCard = new SimpleBooleanProperty(deviceExpansionCard);
		this.deviceLaptop = new SimpleBooleanProperty(deviceLaptop);
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
