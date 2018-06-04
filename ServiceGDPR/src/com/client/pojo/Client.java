package com.client.pojo;

import javafx.beans.property.SimpleStringProperty;

public class Client {
	private final SimpleStringProperty clientId;
	private final SimpleStringProperty clientNumber;
	private final SimpleStringProperty clientCompanyName;
	private final SimpleStringProperty clientName;
	private final SimpleStringProperty clientCounty;
	private final SimpleStringProperty clientSettlement;
	private final SimpleStringProperty clientZipCode;
	private final SimpleStringProperty clientAddress;
	private final SimpleStringProperty clientCompanyPhone;
	private final SimpleStringProperty clientCompanyEmail;
	private final SimpleStringProperty clientPhone;
	private final SimpleStringProperty clientEmail;
	private final SimpleStringProperty clientPackage;
	private final SimpleStringProperty clientComment;

	public Client(Integer cClientId, String cClientNumber, String cClientCompanyName, String cClientName,
			String cClientCounty, String cClientSettlement, String cClientZipCode, String cClientAddress,
			String cClientCompanyPhone, String cClientCompanyEmail, String cClientPhone, String cClientEmail,
			String cClientPackage, String cClientComment) {
		this.clientId = new SimpleStringProperty(String.valueOf(cClientId));
		this.clientNumber = new SimpleStringProperty(cClientNumber);
		this.clientCompanyName = new SimpleStringProperty(cClientCompanyName);
		this.clientName = new SimpleStringProperty(cClientName);
		this.clientCounty = new SimpleStringProperty(cClientCounty);
		this.clientSettlement = new SimpleStringProperty(cClientSettlement);
		this.clientZipCode = new SimpleStringProperty(cClientZipCode);
		this.clientAddress = new SimpleStringProperty(cClientAddress);
		this.clientCompanyPhone = new SimpleStringProperty(cClientCompanyPhone);
		this.clientCompanyEmail = new SimpleStringProperty(cClientCompanyEmail);
		this.clientPhone = new SimpleStringProperty(cClientPhone);
		this.clientEmail = new SimpleStringProperty(cClientEmail);
		this.clientPackage = new SimpleStringProperty(cClientPackage);
		this.clientComment = new SimpleStringProperty(cClientComment);
	}

	public String getClientId() {
		return this.clientId.get();
	}

	public void setClientId(String cClientId) {
		this.clientId.set(cClientId);
	}

	public String getClientNumber() {
		return this.clientNumber.get();
	}

	public void setClientNumber(String cClientNumber) {
		this.clientNumber.set(cClientNumber);
	}

	public String getClientCompanyName() {
		return this.clientCompanyName.get();
	}

	public void setClientCompanyName(String cClientCompanyName) {
		this.clientCompanyName.set(cClientCompanyName);
	}

	public String getClientName() {
		return this.clientName.get();
	}

	public void setClientName(String cClientName) {
		this.clientName.set(cClientName);
	}

	public String getClientCounty() {
		return this.clientCounty.get();
	}

	public void setClientCounty(String cClientCounty) {
		this.clientCounty.set(cClientCounty);
	}

	public String getClientSettlement() {
		return this.clientSettlement.get();
	}

	public void setClientSettlement(String cClientSettlement) {
		this.clientSettlement.set(cClientSettlement);
	}

	public String getClientZipCode() {
		return this.clientZipCode.get();
	}

	public void setClientZipCode(String cClientZipCode) {
		this.clientZipCode.set(cClientZipCode);
	}

	public String getClientAddress() {
		return this.clientAddress.get();
	}

	public void setClientAddress(String cClientAddress) {
		this.clientAddress.set(cClientAddress);
	}

	public String getClientCompanyPhone() {
		return this.clientCompanyPhone.get();
	}

	public void setClientCompanyPhone(String cClientCompanyPhone) {
		this.clientCompanyPhone.set(cClientCompanyPhone);
	}

	public String getClientCompanyEmail() {
		return this.clientCompanyEmail.get();
	}

	public void setClientCompanyEmail(String cClientCompanyEmail) {
		this.clientCompanyEmail.set(cClientCompanyEmail);
	}

	public String getClientPhone() {
		return this.clientPhone.get();
	}

	public void setClientPhone(String cClientPhone) {
		this.clientPhone.set(cClientPhone);
	}

	public String getClientEmail() {
		return this.clientEmail.get();
	}

	public void setClientEmail(String cClientEmail) {
		this.clientEmail.set(cClientEmail);
	}

	public String getClientPackage() {
		return this.clientPackage.get();
	}

	public void setClientPackage(String cClientPackage) {
		this.clientPackage.set(cClientPackage);
	}

	public String getClientComment() {
		return this.clientComment.get();
	}

	public void setClientComment(String cClientComment) {
		this.clientComment.set(cClientComment);
	}
}
