package com.service.client;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Client {
	private  final SimpleIntegerProperty clientId;
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
		this.clientId = new SimpleIntegerProperty(cClientId);
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

	

	public Integer getClientId() {
		return clientId.get();
	}

	public void setClientId(Integer cClientId) {
		clientId.set(cClientId);
	}

	public String getClientNumber() {
		return clientNumber.get();
	}

	public void setClientNumber(String cClientNumber) {
		clientNumber.set(cClientNumber);
	}

	public String getClientCompanyName() {
		return clientCompanyName.get();
	}

	public void setClientCompanyName(String cClientCompanyName) {
		clientCompanyName.set(cClientCompanyName);
	}

	public String getClientName() {
		return clientName.get();
	}

	public void setClientName(String cClientName) {
		clientName.set(cClientName);
	}

	public String getClientCounty() {
		return clientCounty.get();
	}

	public void setClientCounty(String cClientCounty) {
		clientCounty.set(cClientCounty);
	}

	public String getClientSettlement() {
		return clientSettlement.get();
	}

	public void setClientSettlement(String cClientSettlement) {
		clientSettlement.set(cClientSettlement);
	}

	public String getClientZipCode() {
		return clientZipCode.get();
	}

	public void setClientZipCode(String cClientZipCode) {
		clientZipCode.set(cClientZipCode);
	}

	public String getClientAddress() {
		return clientAddress.get();
	}

	public void setClientAddress(String cClientAddress) {
		clientAddress.set(cClientAddress);
	}

	public String getClientCompanyPhone() {
		return clientCompanyPhone.get();
	}

	public void setClientCompanyPhone(String cClientCompanyPhone) {
		clientCompanyPhone.set(cClientCompanyPhone);
	}

	public String getClientCompanyEmail() {
		return clientCompanyEmail.get();
	}

	public void setClientCompanyEmail(String cClientCompanyEmail) {
		clientCompanyEmail.set(cClientCompanyEmail);
	}

	public String getClientPhone() {
		return clientPhone.get();
	}

	public void setClientPhone(String cClientPhone) {
		clientPhone.set(cClientPhone);
	}

	public String getClientEmail() {
		return clientEmail.get();
	}

	public void setClientEmail(String cClientEmail) {
		clientEmail.set(cClientEmail);
	}

	public String getClientPackage() {
		return clientPackage.get();
	}

	public void setClientPackage(String cClientPackage) {
		clientPackage.set(cClientPackage);
	}

	public String getClientComment() {
		return clientComment.get();
	}

	public void setClientComment(String cClientComment) {
		clientComment.set(cClientComment);
	}
}
