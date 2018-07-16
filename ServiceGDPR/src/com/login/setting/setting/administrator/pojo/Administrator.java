package com.login.setting.setting.administrator.pojo;

import javafx.beans.property.SimpleStringProperty;

public class Administrator {

	private final SimpleStringProperty administratorId;
	private final SimpleStringProperty administratorName;
	private final SimpleStringProperty administratorEmail;
	private final SimpleStringProperty administratorPost;

	public Administrator(Integer administratorId, String administratorName, String administratorEmail,
			String administratorPost) {
		this.administratorId = new SimpleStringProperty(String.valueOf(administratorId));
		this.administratorName = new SimpleStringProperty(administratorName);
		this.administratorEmail = new SimpleStringProperty(administratorEmail);
		this.administratorPost = new SimpleStringProperty(administratorPost);
	}

	public Administrator(String administratorName, String administratorEmail, String administratorPost) {
		this.administratorName = new SimpleStringProperty(administratorName);
		this.administratorEmail = new SimpleStringProperty(administratorEmail);
		this.administratorPost = new SimpleStringProperty(administratorPost);
		this.administratorId = new SimpleStringProperty("");
	}

	public String getAdministratorId() {
		return this.administratorId.get();
	}

	public void setAdministratorId(String administratorId) {
		this.administratorId.set(administratorId);
	}

	public String getAdministratorName() {
		return this.administratorName.get();
	}

	public void setAdministratorName(String administratorName) {
		this.administratorName.set(administratorName);
	}

	public String getAdministratorEmail() {
		return this.administratorEmail.get();
	}

	public void setAdministratorEmail(String administratorEmail) {
		this.administratorEmail.set(administratorEmail);
	}

	public String getAdministratorPost() {
		return this.administratorPost.get();
	}

	public void setAdministratorPost(String administratorPost) {
		this.administratorPost.set(administratorPost);
	}
}
