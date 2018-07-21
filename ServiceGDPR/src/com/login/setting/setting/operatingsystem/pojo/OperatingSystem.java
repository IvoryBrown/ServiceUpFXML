package com.login.setting.setting.operatingsystem.pojo;

import javafx.beans.property.SimpleStringProperty;

public class OperatingSystem {

	private final SimpleStringProperty operatingSystemId;
	private final SimpleStringProperty operatingSystem;

	public OperatingSystem(Integer operatingSystemId, String operatingSystem) {
		this.operatingSystemId = new SimpleStringProperty(String.valueOf(operatingSystemId));
		this.operatingSystem = new SimpleStringProperty(operatingSystem);
	}

	public OperatingSystem(String operatingSystem) {
		this.operatingSystem = new SimpleStringProperty(operatingSystem);
		this.operatingSystemId = new SimpleStringProperty("");
	}

	public String getOperatingSystemId() {
		return this.operatingSystemId.get();
	}

	public void setOperatingSystemId(String operatingSystemId) {
		this.operatingSystemId.set(operatingSystemId);
	}

	public String getOperatingSystem() {
		return this.operatingSystem.get();
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem.set(operatingSystem);
	}
}
