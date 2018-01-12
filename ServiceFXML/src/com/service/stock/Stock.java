package com.service.stock;

import javafx.beans.property.SimpleStringProperty;

public class Stock {

	private final SimpleStringProperty stockId;
	private final SimpleStringProperty stockDevice;
	private final SimpleStringProperty stockDescription;

	public Stock() {
		this.stockId = new SimpleStringProperty("");
		this.stockDevice = new SimpleStringProperty("");
		this.stockDescription = new SimpleStringProperty("");
	}

	public Stock(String sDevice, String sDescription) {
		this.stockId = new SimpleStringProperty("");
		this.stockDevice = new SimpleStringProperty(sDevice);
		this.stockDescription = new SimpleStringProperty(sDescription);
	}

	public Stock(Integer sId, String sDevice, String sDescription) {
		this.stockDevice = new SimpleStringProperty(sDevice);
		this.stockDescription = new SimpleStringProperty(sDescription);
		this.stockId = new SimpleStringProperty(String.valueOf(sId));
	}

	public String getStockId() {
		return stockId.get();
	}

	public void setStockId(String sId) {
		stockId.set(sId);
	}

	public String getStockDevice() {
		return stockDevice.get();
	}

	public void setStockDevice(String sDevice) {
		stockDevice.set(sDevice);
	}

	public String getStockDescription() {
		return stockDescription.get();
	}

	public void setStockDescription(String sDescription) {
		stockDescription.set(sDescription);
	}
}
