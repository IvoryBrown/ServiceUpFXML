package com.log.pojo;

import java.util.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Log {
	private final SimpleStringProperty logId;
	private final SimpleStringProperty logAdmistratorName;
	private final SimpleObjectProperty<Date> logDate;
	private final SimpleStringProperty logDescription;

	public Log(String logAdmistratorName, Date logDate, String logDescription) {
		this.logId = new SimpleStringProperty("");
		this.logAdmistratorName = new SimpleStringProperty(logAdmistratorName);
		this.logDate = new SimpleObjectProperty<>(logDate);
		this.logDescription = new SimpleStringProperty(logDescription);

	}

	public Log(Integer logId, String logAdmistratorName, Date logDate, String logDescription) {
		this.logId = new SimpleStringProperty(String.valueOf(logId));
		this.logAdmistratorName = new SimpleStringProperty(logAdmistratorName);
		this.logDate = new SimpleObjectProperty<>(logDate);
		this.logDescription = new SimpleStringProperty(logDescription);

	}

	public String getLogId() {
		return this.logId.get();
	}

	public void setLogId(String logId) {
		this.logId.set(logId);
	}

	public String getLogAdmistratorName() {
		return this.logAdmistratorName.get();
	}

	public void setLogAdmistratorName(String logAdmistratorName) {
		this.logAdmistratorName.set(logAdmistratorName);
	}

	public ObjectProperty<Date> getLogDateObject() {
		return this.logDate;
	}

	public Date getLogDate() {
		return (Date) this.logDate.get();
	}

	public void setLogDate(Date logDate) {
		this.logDate.set(logDate);
	}

	public String getLogDescription() {
		return this.logDescription.get();
	}

	public void setLogDescription(String logDescription) {
		this.logDescription.set(logDescription);
	}
}
