package com.service.client;

import javafx.beans.property.SimpleStringProperty;

public class SubRecord {
	private SimpleStringProperty fieldSubRecordName;
	private SimpleStringProperty fieldSubRecordValue;

	public SubRecord(String sn, String sv) {
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