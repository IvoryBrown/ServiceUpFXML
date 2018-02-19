package com.service.stock;

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
		return stockDeviceId.get();
	}

	public void setStockDeviceId(Integer sDeviceId) {
		stockDeviceId.set(sDeviceId);
	}

	public String getStockDeviceInStock() {
		return stockDeviceInStock.get();
	}

	public void setStockDeviceSalesDate(Date date) {
		this.stockDeviceSalesDate.set(date);
	}

	public Date getStockDeviceSalesDate() {
		return (Date) stockDeviceSalesDate.get();
	}
	public ObjectProperty<Date> birthdayProperty() {
		return this.stockDeviceSalesDate;
	}

//	  public String getDateAsString() {
//	        SimpleDateFormat smp = new SimpleDateFormat("dd MMMMM yyyy");
//	        String strDate = (null == stockDeviceSalesDate || null == stockDeviceSalesDate.get())
//	                ? "" : smp.format(stockDeviceSalesDate.get());
//	        
//	        return strDate;
//	    }

	public void setStockDeviceInStock(String sDeviceInStock) {
		stockDeviceInStock.set(sDeviceInStock);
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
