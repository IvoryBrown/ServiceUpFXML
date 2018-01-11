package com.service.stock;

import javafx.beans.property.SimpleSetProperty;

public class Stock {

	private final SimpleSetProperty device;
	private final  SimpleSetProperty device1;
	 
	public Stock(SimpleSetProperty device, SimpleSetProperty device1) {
		this.device = new SimpleSetProperty(device);
		this.device1 = new SimpleSetProperty(device1);
	}

	public SimpleSetProperty getDevice() {
		return  device.get();
	}

	

	

	
}
