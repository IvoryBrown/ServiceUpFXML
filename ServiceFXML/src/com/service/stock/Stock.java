package com.service.stock;

import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Stock {

	private final SimpleIntegerProperty stockDeviceId;
	private final SimpleStringProperty stockDeviceName;
	private final SimpleObjectProperty<Date> stockDeviceDate;
	private final SimpleObjectProperty<Date> stockDeviceSalesDate;
	private final SimpleStringProperty stockDeviceQuantity;
	private final SimpleStringProperty stockDeviceDescription;

	public Stock(Integer sDeviceId, String sDeviceName, Date sDeviceDate, Date sDeviceSalesDate,
			Integer sDeviceQuantity, String sDeviceDescription) {
		this.stockDeviceId = new SimpleIntegerProperty(sDeviceId);
		this.stockDeviceName = new SimpleStringProperty(sDeviceName);
		this.stockDeviceDate = new SimpleObjectProperty<Date>(sDeviceDate);
		this.stockDeviceSalesDate = new SimpleObjectProperty<Date>(sDeviceSalesDate);
		this.stockDeviceQuantity = new SimpleStringProperty(String.valueOf(sDeviceQuantity));
		this.stockDeviceDescription = new SimpleStringProperty(sDeviceDescription);
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
		SimpleDateFormat smp = new SimpleDateFormat("yyyy MMMMM dd");
		String strDate = (null == stockDeviceDate || null == stockDeviceDate.get()) ? ""
				: smp.format(stockDeviceDate.get());
		return strDate;
	}

	public void getStockDeviceDate(Date sDeviceDate) {
		stockDeviceDate.set(sDeviceDate);
	}

	public String getStockDeviceSalesDate() {
		SimpleDateFormat smp = new SimpleDateFormat("yyyy MMMMM dd");
		String strDate = (null == stockDeviceSalesDate || null == stockDeviceSalesDate.get()) ? ""
				: smp.format(stockDeviceSalesDate.get());
		return strDate;
	}

	public void setStockDeviceSalesDate(Date sDeviceSalesDate) {
		stockDeviceSalesDate.set(sDeviceSalesDate);
	}

	public String getStockDeviceQuantity() {
		return stockDeviceQuantity.get();
	}

	public void setStockDeviceQuantity(String sDeviceQuantity) {
		stockDeviceQuantity.set(sDeviceQuantity);
	}

	public String getStockDeviceDescription() {
		return stockDeviceDescription.get();
	}

	public void setStockDeviceDescription(String sDeviceDescription) {
		stockDeviceDescription.set(sDeviceDescription);
	}
}
