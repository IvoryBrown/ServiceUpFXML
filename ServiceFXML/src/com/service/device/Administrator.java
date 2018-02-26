package com.service.device;

import javafx.beans.property.SimpleStringProperty;

public class Administrator {
	private final SimpleStringProperty administrator;
	public Administrator(String aAdministrator) {
		this.administrator= new SimpleStringProperty(aAdministrator);
	}
	public SimpleStringProperty getAdministrator() {
		return administrator;
	}
}
