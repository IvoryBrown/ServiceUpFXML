package com.service.stock;

import javafx.beans.property.SimpleStringProperty;

public class Stock {

	private final SimpleStringProperty stockDeviceId;
	private final SimpleStringProperty stockDeviceName;
	private final SimpleStringProperty stockDeviceQuantity;
	private final SimpleStringProperty stockDeviceDescription;

	public Stock() {
		this.stockDeviceId = new SimpleStringProperty("");
		this.stockDeviceName = new SimpleStringProperty("");
		this.stockDeviceQuantity = new SimpleStringProperty("");
		this.stockDeviceDescription = new SimpleStringProperty("");
	}

	public Stock(String sDeviceName, String sDeviceQuantity, String sDeviceDescription) {
		this.stockDeviceId = new SimpleStringProperty("");
		this.stockDeviceName = new SimpleStringProperty(sDeviceName);
		this.stockDeviceQuantity = new SimpleStringProperty(sDeviceQuantity);
		this.stockDeviceDescription = new SimpleStringProperty(sDeviceDescription);
	}

	public Stock(Integer sDeviceId, String sDeviceName, String sDeviceQuantity, String sDeviceDescription) {
		this.stockDeviceName = new SimpleStringProperty(sDeviceName);
		this.stockDeviceQuantity = new SimpleStringProperty(sDeviceQuantity);
		this.stockDeviceDescription = new SimpleStringProperty(sDeviceDescription);
		this.stockDeviceId = new SimpleStringProperty(String.valueOf(sDeviceId));
	}

	public String getStockId() {
		return stockDeviceId.get();
	}

	public void setStockId(String sDeviceId) {
		stockDeviceId.set(sDeviceId);
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
		return stockDeviceDescription.get();
	}

	public void setStockDescription(String sDeviceDescription) {
		stockDeviceDescription.set(sDeviceDescription);
	}
}
