package com.service.stock;

import javafx.beans.property.SimpleStringProperty;

public class Stock {

	private final SimpleStringProperty device;
	private final  SimpleStringProperty device1;
	
	public Stock(SimpleStringProperty device, SimpleStringProperty device1) {
		this.device = device;
		this.device1 = device1;
	}
	public String  getDevice() {
		return device.get();
	}
	public SimpleStringProperty getDevice1() {
		return device1;
	}
	 
	

	

	

	
}
