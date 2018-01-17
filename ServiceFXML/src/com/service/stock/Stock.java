package com.service.stock;

import javafx.beans.property.SimpleStringProperty;

public class Stock {

	private final SimpleStringProperty stockId;
	private final SimpleStringProperty stockDeviceName;
	private final SimpleStringProperty stockDeviceQuantity;
	private final SimpleStringProperty stockDescription;

	public Stock() {
		this.stockId = new SimpleStringProperty("");
		this.stockDeviceName = new SimpleStringProperty("");
		this.stockDeviceQuantity = new SimpleStringProperty("");
		this.stockDescription = new SimpleStringProperty("");
	}

	public Stock(String sDeviceName, String sDeviceQuantity, String sDescription) {
		this.stockId = new SimpleStringProperty("");
		this.stockDeviceName = new SimpleStringProperty(sDeviceName);
		this.stockDeviceQuantity = new SimpleStringProperty(sDeviceQuantity);
		this.stockDescription = new SimpleStringProperty(sDescription);
	}

	public Stock(Integer sId, String sDeviceName, String sDeviceQuantity, String sDescription) {
		this.stockDeviceName = new SimpleStringProperty(sDeviceName);
		this.stockDeviceQuantity = new SimpleStringProperty(sDeviceQuantity);
		this.stockDescription = new SimpleStringProperty(sDescription);
		this.stockId = new SimpleStringProperty(String.valueOf(sId));
	}

	public String getStockId() {
		return stockId.get();
	}

	public void setStockId(String sId) {
		stockId.set(sId);
	}

	public String getStockDeviceQuantity() {
		return stockDeviceName.get();
	}

	public void setStockDeviceQuantity(String sDeviceName) {
		stockDeviceName.set(sDeviceName);
	}

	public String getStockDevice() {
		return stockDeviceQuantity.get();
	}

	public void setStockDevice(String sDeviceQuantity) {
		stockDeviceQuantity.set(sDeviceQuantity);
	}

	public String getStockDescription() {
		return stockDescription.get();
	}

	public void setStockDescription(String sDescription) {
		stockDescription.set(sDescription);
	}
}
