package com.login.setting.setting.location.pojo;

import javafx.beans.property.SimpleStringProperty;

public class Location {
	private final SimpleStringProperty locationId;
	private final SimpleStringProperty location;

	public Location(Integer locationId, String location) {
		this.locationId = new SimpleStringProperty(String.valueOf(locationId));
		this.location = new SimpleStringProperty(location);
	}

	public Location(String location) {
		this.location = new SimpleStringProperty(location);
		this.locationId = new SimpleStringProperty("");
	}

	public String getLocationId() {
		return this.locationId.get();
	}

	public void setLocationId(String locationId) {
		this.locationId.set(locationId);
	}

	public String getLocation() {
		return this.location.get();
	}

	public void setLocation(String location) {
		this.location.set(location);
	}
}
