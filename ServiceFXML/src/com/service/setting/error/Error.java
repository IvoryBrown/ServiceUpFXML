package com.service.setting.error;

import javafx.beans.property.SimpleStringProperty;

public class Error {
	private final SimpleStringProperty errorId;
	private final SimpleStringProperty errorName;
	private final SimpleStringProperty errorComment;
	private final SimpleStringProperty errorStatus;

	public Error(String errorId, String errorName, String errorComment, String errorStatus) {
		this.errorId = new SimpleStringProperty(String.valueOf(errorId));
		this.errorName = new SimpleStringProperty(errorName);
		this.errorComment = new SimpleStringProperty(errorComment);
		this.errorStatus = new SimpleStringProperty(errorStatus);
	}

	public String getErrorId() {
		return this.errorId.get();
	}

	public void setErrorId(String errorId) {
		this.errorId.set(errorId);
	}

	public String getErrorName() {
		return this.errorName.get();
	}

	public void setErrorName(String errorName) {
		this.errorName.set(errorName);
	}

	public String getErrorComment() {
		return this.errorComment.get();
	}

	public void setErrorComment(String errorComment) {
		this.errorComment.set(errorComment);
	}

	public String getErrorStatus() {
		return this.errorStatus.get();
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus.set(errorStatus);
	}
}
