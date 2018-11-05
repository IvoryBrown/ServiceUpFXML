package com.stock.pojo;

import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Stock {
	private final SimpleIntegerProperty stockDeviceId;
	private final SimpleStringProperty stockDeviceName;
	private final SimpleStringProperty stockDeviceDate;
	private final SimpleObjectProperty<Date> stockDeviceSalesDate;
	private final SimpleIntegerProperty stockDeviceQuantity;
	private final SimpleStringProperty stockDeviceInStock;
	private final SimpleStringProperty stockDeviceDescription;
	private final SimpleStringProperty stockDeviceAccountIdentity;

	public Stock(Integer sDeviceId, String sDeviceName, String sDeviceDate, Date sDeviceSalesDate,
			Integer sDeviceQuantity, String sDeviceInStock, String sDeviceDescription, String sDeviceAccountIdentity) {
		this.stockDeviceId = new SimpleIntegerProperty(sDeviceId);
		this.stockDeviceName = new SimpleStringProperty(sDeviceName);
		this.stockDeviceDate = new SimpleStringProperty(sDeviceDate);
		this.stockDeviceSalesDate = new SimpleObjectProperty<>(sDeviceSalesDate);
		this.stockDeviceQuantity = new SimpleIntegerProperty(sDeviceQuantity);
		this.stockDeviceInStock = new SimpleStringProperty(String.valueOf(sDeviceInStock));
		this.stockDeviceDescription = new SimpleStringProperty(sDeviceDescription);
		this.stockDeviceAccountIdentity = new SimpleStringProperty(sDeviceAccountIdentity);
	}

	public Integer getStockDeviceId() {
		return this.stockDeviceId.get();
	}

	public void setStockDeviceId(Integer sDeviceId) {
		this.stockDeviceId.set(sDeviceId);
	}

	public String getStockDeviceInStock() {
		return this.stockDeviceInStock.get();
	}

	public void setStockDeviceSalesDate(Date date) {
		this.stockDeviceSalesDate.set(date);
	}

	public Date getStockDeviceSalesDate() {
		return (Date) this.stockDeviceSalesDate.get();
	}

	public ObjectProperty<Date> birthdayProperty() {
		return this.stockDeviceSalesDate;
	}

	public void setStockDeviceInStock(String sDeviceInStock) {
		this.stockDeviceInStock.set(sDeviceInStock);
	}

	public String getStockDeviceName() {
		return this.stockDeviceName.get();
	}

	public void setStockDeviceName(String sDeviceName) {
		this.stockDeviceName.set(sDeviceName);
	}

	public String getStockDeviceDate() {
		return this.stockDeviceDate.get();
	}

	public void getStockDeviceDate(String sDeviceDate) {
		this.stockDeviceDate.set(sDeviceDate);
	}

	public Integer getStockDeviceQuantity() {
		return this.stockDeviceQuantity.get();
	}

	public void setStockDeviceQuantity(Integer sDeviceQuantity) {
		this.stockDeviceQuantity.set(sDeviceQuantity);
	}

	public String getStockDeviceDescription() {
		return this.stockDeviceDescription.get();
	}

	public void setStockDeviceDescription(String sDeviceDescription) {
		this.stockDeviceDescription.set(sDeviceDescription);
	}

	public String getStockDeviceAccountIdentity() {
		return this.stockDeviceAccountIdentity.get();
	}

	public void setStockDeviceAccountIdentity(String sDeviceAccountIdentity) {
		this.stockDeviceAccountIdentity.set(sDeviceAccountIdentity);
	}

}
