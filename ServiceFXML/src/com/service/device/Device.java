package com.service.device;

import java.util.Date;

import javafx.beans.property.ObjectProperty;
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

	public String getDeviceID() {
		return this.deviceID.get();
	}

	public void setDeviceID(String deviceID) {
		this.deviceID.set(deviceID);
	}

	public String getDeviceNumber() {
		return this.deviceNumber.get();
	}

	public void setDeviceNumber(String deviceNumber) {
		this.deviceNumber.set(deviceNumber);
	}

	public String getDeviceCompanyName() {
		return this.deviceCompanyName.get();
	}

	public void setDeviceCompanyName(String deviceCompanyName) {
		this.deviceCompanyName.set(deviceCompanyName);
	}

	public String getDeviceClientName() {
		return this.deviceClientName.get();
	}

	public void setDeviceClientName(String deviceClientName) {
		this.deviceClientName.set(deviceClientName);
	}

	public String getDeviceName() {
		return this.deviceName.get();
	}

	public void setDeviceName(String deviceName) {
		this.deviceName.set(deviceName);
	}

	public String getDeviceManufacturer() {
		return this.deviceManufacturer.get();
	}

	public void setDeviceManufacturer(String deviceManufacturer) {
		this.deviceManufacturer.set(deviceManufacturer);
	}

	public String getDeviceSerialNumber() {
		return this.deviceSerialNumber.get();
	}

	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber.set(deviceSerialNumber);
	}

	public String getDeviceRepairLocation() {
		return this.deviceRepairLocation.get();
	}

	public void setDeviceRepairLocation(String deviceRepairLocation) {
		this.deviceRepairLocation.set(deviceRepairLocation);
	}

	public String getDeviceStatus() {
		return this.deviceStatus.get();
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus.set(deviceStatus);
	}

	public String getDeviceNewMachine() {
		return this.deviceNewMachine.get();
	}

	public void setDeviceNewMachine(String deviceNewMachine) {
		this.deviceNewMachine.set(deviceNewMachine);
	}

	public String getDeviceAdministrator() {
		return this.deviceAdministrator.get();
	}

	public void setDeviceAdministrator(String deviceAdministrator) {
		this.deviceAdministrator.set(deviceAdministrator);
	}

	public String getDevicePriorit() {
		return this.devicePriorit.get();
	}

	public void setDevicePriorit(String devicePriorit) {
		this.devicePriorit.set(devicePriorit);
	}

	public String getDevicePassword() {
		return this.devicePassword.get();
	}

	public void setDevicePassword(String devicePassword) {
		this.devicePassword.set(devicePassword);
	}

	public String getDeviceReferences() {
		return this.deviceReferences.get();
	}

	public void setDeviceReferences(String deviceReferences) {
		this.deviceReferences.set(deviceReferences);
	}

	public String getDeviceAccesssory() {
		return this.deviceAccesssory.get();
	}

	public void setDeviceAccesssory(String deviceAccesssory) {
		this.deviceAccesssory.set(deviceAccesssory);
	}

	public String getDeviceInjury() {
		return this.deviceInjury.get();
	}

	public void setDeviceInjury(String deviceInjury) {
		this.deviceInjury.set(deviceInjury);
	}

	public String getDeviceErrorDescription() {
		return this.deviceErrorDescription.get();
	}

	public void setDeviceErrorDescription(String deviceErrorDescription) {
		this.deviceErrorDescription.set(deviceErrorDescription);
	}

	public String getDeviceComment() {
		return this.deviceComment.get();
	}

	public void setDeviceComment(String deviceComment) {
		this.deviceComment.set(deviceComment);
	}

	public ObjectProperty<Date> getDeviceSalesBuyingObject() {
		return this.deviceSalesBuying;
	}

	public Date getDeviceSalesBuying() {
		return (Date) this.deviceSalesBuying.get();
	}

	public void setDeviceSalesBuying(Date deviceSalesBuying) {
		this.deviceSalesBuying.set(deviceSalesBuying);
	}

	public ObjectProperty<Date> getDeviceAddDateObject() {
		return this.deviceAddDate;
	}

	public Date getDeviceAddDate() {
		return (Date) this.deviceAddDate.get();
	}

	public void setDeviceAddDate(Date deviceAddDate) {
		this.deviceAddDate.set(deviceAddDate);
	}

	public ObjectProperty<Date> getDeviceEndDateObject() {
		return deviceEndDate;
	}

	public Date getDeviceEndDate() {
		return (Date) this.deviceEndDate.get();
	}

	public void setDeviceEndDate(Date deviceEndDate) {
		this.deviceEndDate.set(deviceEndDate);
	}

	public ObjectProperty<Date> getDeviceDeliveryDateObject() {
		return deviceDeliveryDate;
	}

	public Date getDeviceDeliveryDate() {
		return (Date) this.deviceDeliveryDate.get();
	}

	public void setDeviceDeliveryDate(Date deviceDeliveryDate) {
		this.deviceDeliveryDate.set(deviceDeliveryDate);
	}

	public String getDeviceDataRecovery() {
		return this.deviceDataRecovery.get();
	}

	public void setDeviceDataRecovery(String deviceDataRecovery) {
		this.deviceDataRecovery.set(deviceDataRecovery);
	}

	public String getDeviceSoftver() {
		return this.deviceSoftver.get();
	}

	public void setDeviceSoftver(String deviceSoftver) {
		this.deviceSoftver.set(deviceSoftver);
	}

	public String getDeviceOperatingSystem() {
		return this.deviceOperatingSystem.get();
	}

	public void setDeviceOperatingSystem(String deviceOperatingSystem) {
		this.deviceOperatingSystem.set(deviceOperatingSystem);
	}

	public String getDeviceSoftverComment() {
		return this.deviceSoftverComment.get();
	}

	public void setDeviceSoftverComment(String deviceSoftverComment) {
		this.deviceSoftverComment.set(deviceSoftverComment);
	}

	public Boolean getDeviceNewHouse() {
		return this.deviceNewHouse.get();
	}

	public void setDeviceNewHouse(boolean deviceSoftverComment) {
		this.deviceNewHouse.set(deviceSoftverComment);
	}

	public Boolean getDevicePowerSupply() {
		return this.devicePowerSupply.get();
	}

	public void setDevicePowerSupply(boolean devicePowerSupply) {
		this.devicePowerSupply.set(devicePowerSupply);
	}

	public Boolean getDeviceProcessor() {
		return this.deviceProcessor.get();
	}

	public void setDeviceProcessor(boolean deviceProcessor) {
		this.deviceProcessor.set(deviceProcessor);
	}

	public Boolean getDeviceBaseBoard() {
		return this.deviceBaseBoard.get();
	}

	public void setDeviceBaseBoard(boolean deviceBaseBoard) {
		this.deviceBaseBoard.set(deviceBaseBoard);
	}

	public Boolean getDeviceMemory() {
		return this.deviceMemory.get();
	}

	public void setDeviceMemory(boolean deviceMemory) {
		this.deviceMemory.set(deviceMemory);
	}

	public Boolean getDeviceVideoCard() {
		return this.deviceVideoCard.get();
	}

	public void setDeviceVideoCard(boolean deviceVideoCard) {
		this.deviceVideoCard.set(deviceVideoCard);
	}

	public Boolean getDeviceSSDDrive() {
		return this.deviceSSDDrive.get();
	}

	public void setDeviceSSDDrive(boolean deviceSSDDrive) {
		this.deviceSSDDrive.set(deviceSSDDrive);
	}

	public Boolean getDeviceHardDrive() {
		return this.deviceHardDrive.get();
	}

	public void setDeviceHardDrive(boolean deviceHardDrive) {
		this.deviceHardDrive.set(deviceHardDrive);
	}

	public Boolean getDeviceCoolingFan() {
		return this.deviceCoolingFan.get();
	}

	public void setDeviceCoolingFan(boolean deviceCoolingFan) {
		this.deviceCoolingFan.set(deviceCoolingFan);
	}

	public Boolean getDeviceOpticalDrive() {
		return this.deviceOpticalDrive.get();
	}

	public void setDeviceOpticalDrive(boolean deviceOpticalDrive) {
		this.deviceOpticalDrive.set(deviceOpticalDrive);
	}

	public Boolean getDeviceExpansionCard() {
		return this.deviceExpansionCard.get();
	}

	public void setDeviceExpansionCard(boolean deviceExpansionCard) {
		this.deviceExpansionCard.set(deviceExpansionCard);
	}

	public Boolean getDeviceLaptop() {
		return this.deviceLaptop.get();
	}

	public void setDeviceLaptop(boolean deviceLaptop) {
		this.deviceLaptop.set(deviceLaptop);
	}
}