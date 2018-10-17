package com.device.pojo;

import javafx.beans.property.SimpleStringProperty;

public class DeviceSubRecord {
	private SimpleStringProperty fieldSubRecordName;
	private SimpleStringProperty fieldSubRecordValue;

	public DeviceSubRecord(String sn, String sv) {
		this.fieldSubRecordName = new SimpleStringProperty(sn);
		this.fieldSubRecordValue = new SimpleStringProperty(sv);
	}

	public String getFieldSubRecordName() {
		return fieldSubRecordName.get();
	}

	public String getFieldSubRecordValue() {
		return fieldSubRecordValue.get();
	}
}
