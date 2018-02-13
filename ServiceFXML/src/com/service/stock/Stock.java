package com.service.stock;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Stock {

	private final SimpleIntegerProperty stockDeviceId;
	private final SimpleStringProperty stockDeviceName;
	private final SimpleStringProperty stockDeviceDate;
	private final SimpleStringProperty stockDeviceSalesDate;
	private final SimpleIntegerProperty stockDeviceQuantity;
	private final SimpleStringProperty stockDeviceDescription;
	private final SimpleStringProperty stockDeviceAccountIdentity;

	public Stock(Integer sDeviceId, String sDeviceName, String sDeviceDate, String sDeviceSalesDate,
			Integer sDeviceQuantity, String sDeviceDescription, String sDeviceAccountIdentity) {
		this.stockDeviceId = new SimpleIntegerProperty(sDeviceId);
		this.stockDeviceName = new SimpleStringProperty(sDeviceName);
		this.stockDeviceDate = new SimpleStringProperty(sDeviceDate);
		this.stockDeviceSalesDate = new SimpleStringProperty(sDeviceSalesDate);
		this.stockDeviceQuantity = new SimpleIntegerProperty(sDeviceQuantity);
		this.stockDeviceDescription = new SimpleStringProperty(sDeviceDescription);
		this.stockDeviceAccountIdentity = new SimpleStringProperty(sDeviceAccountIdentity);
	}

	public Integer getStockDeviceId() {
		return stockDeviceId.get();
	}

	public void setStockDeviceId(Integer sDeviceId) {
		stockDeviceId.set(sDeviceId);
	}

	public String getStockDeviceName() {
		return stockDeviceName.get();
	}

	public void setStockDeviceName(String sDeviceName) {
		stockDeviceName.set(sDeviceName);
	}

	public String getStockDeviceDate() {
		return stockDeviceDate.get();
	}

	public void getStockDeviceDate(String sDeviceDate) {
		stockDeviceDate.set(sDeviceDate);
	}

	public String getStockDeviceSalesDate() {
		return stockDeviceSalesDate.get();
	}

	public void setStockDeviceSalesDate(String sDeviceSalesDate) {
		stockDeviceSalesDate.set(sDeviceSalesDate);
	}

	public Integer getStockDeviceQuantity() {
		return stockDeviceQuantity.get();
	}

	public void setStockDeviceQuantity(Integer sDeviceQuantity) {
		stockDeviceQuantity.set(sDeviceQuantity);
	}

	public String getStockDeviceDescription() {
		return stockDeviceDescription.get();
	}

	public void setStockDeviceDescription(String sDeviceDescription) {
		stockDeviceDescription.set(sDeviceDescription);
	}

	public String getStockDeviceAccountIdentity() {
		return stockDeviceAccountIdentity.get();
	}

	public void setStockDeviceAccountIdentity(String sDeviceAccountIdentity) {
		stockDeviceAccountIdentity.set(sDeviceAccountIdentity);
	}
}
